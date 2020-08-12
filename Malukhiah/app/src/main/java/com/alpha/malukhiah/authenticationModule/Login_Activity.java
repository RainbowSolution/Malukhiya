package com.alpha.malukhiah.authenticationModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.MainActivity;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.home_pkg.HomeActivity;
import com.alpha.malukhiah.utility.CheckNetwork;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

  //  private MaterialButton mbtnSignInNow, mbtnRegisteredNow;
    private AppCompatTextView tvForgetPassword ,tvRegisteredNow,tv_new_user;
  //  private ApiServices apiServices;
   // private TextInputEditText tiePasswordLogin, tieEmailAddressLogin;
    private static Animation shakeAnimation;
    AppCompatButton btn_login;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_login);
        tvForgetPassword = findViewById(R.id.tvForgetPasswordId);
        tvRegisteredNow = findViewById(R.id.tvRegisteredNowId);
        tv_new_user = findViewById(R.id.tv_new_user);
        btn_login = findViewById(R.id.btn_login);
        tvForgetPassword.setOnClickListener(this);
        tvRegisteredNow.setOnClickListener(this);
        tv_new_user.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvForgetPasswordId:
                CheckNetwork.nextScreenWithoutFinish(Login_Activity.this, Forget_Activity.class);
                break;
           case R.id.btn_login:
               startActivity(new Intent(Login_Activity.this, HomeActivity.class));
               finish();
               // validation(v);
                break;
            case R.id.tvRegisteredNowId:
                CheckNetwork.nextScreenWithoutFinish(Login_Activity.this, SignUp_Activity.class);
                break;
            case R.id.tv_new_user:
                CheckNetwork.nextScreenWithoutFinish(Login_Activity.this, SignUp_Activity.class);
                break;
        }
    }

/*
    private void init()
    {
        try {
            tiePasswordLogin = findViewById(R.id.tiePasswordLoginId);
            tieEmailAddressLogin = findViewById(R.id.tieEmailAddressLoginId);
            tvForgetPassword = findViewById(R.id.tvForgetPasswordId);
            mbtnSignInNow = findViewById(R.id.mbtnSignInNowId);
            mbtnRegisteredNow = findViewById(R.id.mbtnRegisteredNowId);
            mbtnSignInNow.setOnClickListener(this);
            mbtnRegisteredNow.setOnClickListener(this);
            tvForgetPassword.setOnClickListener(this);
            shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);

            FirebaseApp.initializeApp(Login_Activity.this);
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

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    private void validation(View v) {
        if (tieEmailAddressLogin.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, "Email Can't Empty");
            tieEmailAddressLogin.startAnimation(shakeAnimation);
            tieEmailAddressLogin.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tieEmailAddressLogin.requestFocus();
        } else if (!Constants.isValidEmail(tieEmailAddressLogin.getText().toString())) {
            new CustomToast().Show_Toast(this, v, "Invalid Email");
            tieEmailAddressLogin.startAnimation(shakeAnimation);
            tieEmailAddressLogin.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tieEmailAddressLogin.requestFocus();
        } else if (tiePasswordLogin.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, "Password Can't Empty");
            tiePasswordLogin.startAnimation(shakeAnimation);
            tiePasswordLogin.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tiePasswordLogin.requestFocus();
        } else if (tiePasswordLogin.getText().toString().length() < 6) {
            new CustomToast().Show_Toast(this, v, "Password digits must be greater than six");
            tiePasswordLogin.startAnimation(shakeAnimation);
            tiePasswordLogin.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tiePasswordLogin.requestFocus();
        } else if (CheckNetwork.isNetAvailable(this)) {
            login(tieEmailAddressLogin.getText().toString().trim(),
                    tiePasswordLogin.getText().toString().trim());
        } else {
            new CustomToast().Show_Toast(this, v, "Check Network Connection");
        }
    }


    private void login(String email, String pass) {
        CustomProgressbar.showProgressBar(this, false);
        apiServices.login(email, pass, token).enqueue(new Callback<LoginResponseModle>() {
            @Override
            public void onResponse(Call<LoginResponseModle> call, Response<LoginResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    LoginResponseModle loginResponseModle = response.body();
                    if (loginResponseModle.getStatus()) {
                        AppSession.setStringPreferences(Login_Activity.this, Constants.STATUS, "auth");
                        AppSession.setStringPreferences(Login_Activity.this, Constants.USER_ID, loginResponseModle.getData().getUserId());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.USERNAME, loginResponseModle.getData().getUserFullname());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.MOBILE_NUMBER, loginResponseModle.getData().getUserPhone());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.EMAIL, loginResponseModle.getData().getUserEmail());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.GENDER, loginResponseModle.getData().getGender());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.PROFILE_PIC, loginResponseModle.getData().getUserImage());
                        CheckNetwork.nextScreenWithFinish(Login_Activity.this, HomeActivity.class);
                    } else {
                        Toast.makeText(Login_Activity.this, loginResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 400) {
                        Toast.makeText(Login_Activity.this, "Wrong email or password", Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<LoginResponseModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }*/
}
