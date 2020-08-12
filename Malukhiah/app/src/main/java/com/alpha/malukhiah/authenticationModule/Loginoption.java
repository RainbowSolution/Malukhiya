package com.alpha.malukhiah.authenticationModule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.home_pkg.HomeActivity;
import com.alpha.malukhiah.model.loginPkg.LoginPozo;
import com.alpha.malukhiah.model.socialLoginPkgModel.SocialLoginModel;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Loginoption extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton abtnSignInCustomer,abtnSignInVendor;
    LinearLayout li_continue_email;
    AppCompatTextView tv_new_user,tvRegisteredNowId,tv_continue_email;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 234;
    GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "malukhiah";
    private AppCompatImageView google_login;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        tv_continue_email = findViewById(R.id.tv_continue_email);
        tv_new_user  = findViewById(R.id.tv_new_user);
        tvRegisteredNowId = findViewById(R.id.tvRegisteredNowId);
        google_login = findViewById(R.id.google_login_Id);
        google_login.setOnClickListener(this);
   //     abtnSignInCustomer.setOnClickListener(this);
        tvRegisteredNowId.setOnClickListener(this);
        tv_new_user.setOnClickListener(this);
        tv_continue_email.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        // apiServices = RetrofitClient.getClient().create(ApiServices.class);
        // init();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        FirebaseApp.initializeApp(Loginoption.this);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            // Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        token = task.getResult().getToken();
                        Log.d("token", token);
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
       login(currentUser.getDisplayName(),currentUser.getEmail(),"Gmail login","","",currentUser.getUid(),token);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_continue_email:
                CheckNetwork.nextScreenWithoutFinish(Loginoption.this, Login_Activity.class);
                break;
            case R.id.tv_new_user:
                CheckNetwork.nextScreenWithoutFinish(Loginoption.this, SignUp_Activity.class);
                break;
            case R.id.tvRegisteredNowId:
                CheckNetwork.nextScreenWithoutFinish(Loginoption.this, SignUp_Activity.class);
                break;
            case R.id.google_login_Id:
                signIn();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                           // Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void login(String name, String email,String social,String facebookID,String instaID,String gmailId,String device_token) {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().socialLogin(name,email,social,facebookID,instaID, gmailId,device_token)).enqueue(new Callback<SocialLoginModel>() {
            @Override
            public void onResponse(Call<SocialLoginModel> call, Response<SocialLoginModel> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    SocialLoginModel loginResponseModle = response.body();
                    if (loginResponseModle.getStatus()) {
                        AppSession.setStringPreferences(Loginoption.this, Constants.USER_ID, loginResponseModle.getData().getUserId());
                        AppSession.setStringPreferences(Loginoption.this, Constants.USERNAME, loginResponseModle.getData().getFirstname()+loginResponseModle.getData().getLastname());
                        AppSession.setStringPreferences(Loginoption.this, Constants.MOBILE_NUMBER, loginResponseModle.getData().getUserPhone());
                        AppSession.setStringPreferences(Loginoption.this, Constants.EMAIL, loginResponseModle.getData().getUserEmail());
                        AppSession.setStringPreferences(Loginoption.this, Constants.GENDER, loginResponseModle.getData().getGender());
                        AppSession.setStringPreferences(Loginoption.this, Constants.PROFILE_PIC, loginResponseModle.getData().getUserImage());
                        AppSession.setStringPreferences(Loginoption.this, Constants.SOCIAL, loginResponseModle.getData().getSocial());
                        AppSession.setStringPreferences(Loginoption.this, Constants.STATUS, "auth");
                        CheckNetwork.nextScreenFinishAllTop(Loginoption.this, HomeActivity.class);
                    } else {
                        Toast.makeText(Loginoption.this, loginResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 400) {
                        Toast.makeText(Loginoption.this, "Wrong email or password", Toast.LENGTH_LONG).show();
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SocialLoginModel> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }
}
