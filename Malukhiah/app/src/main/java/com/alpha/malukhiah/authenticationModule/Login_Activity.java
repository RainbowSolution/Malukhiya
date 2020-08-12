package com.alpha.malukhiah.authenticationModule;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.home_pkg.HomeActivity;
import com.alpha.malukhiah.model.loginPkg.LoginPozo;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.alpha.malukhiah.utility.CustomToast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatTextView tvForgetPassword, tvRegisteredNow, tv_new_user;
    private TextInputEditText tv_password, tv_email;
    private static Animation shakeAnimation;
    AppCompatButton btn_login;
    AppCompatImageView ivBackForgetId;
    private String token;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init() {
        tv_email = findViewById(R.id.tv_email);
        tv_password = findViewById(R.id.tv_password);
        tvForgetPassword = findViewById(R.id.tvForgetPasswordId);
        tvRegisteredNow = findViewById(R.id.tvRegisteredNowId);
        tv_new_user = findViewById(R.id.tv_new_user);
        btn_login = findViewById(R.id.btn_login);
        ivBackForgetId = findViewById(R.id.ivBackForgetId);
        ivBackForgetId.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvRegisteredNow.setOnClickListener(this);
        tv_new_user.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);

 /*       FirebaseApp.initializeApp(Login_Activity.this);
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
                });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackForgetId:
                finish();
                break;
            case R.id.tvForgetPasswordId:
                CheckNetwork.nextScreenWithoutFinish(Login_Activity.this, Forget_Activity.class);
                break;
            case R.id.btn_login:
                validation(v);
                break;
            case R.id.tvRegisteredNowId:
                CheckNetwork.nextScreenWithoutFinish(Login_Activity.this, SignUp_Activity.class);
                break;
            case R.id.tv_new_user:
                CheckNetwork.nextScreenWithoutFinish(Login_Activity.this, SignUp_Activity.class);
                break;
        }
    }


    private void validation(View v) {
        if (tv_email.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.email_cant_empty));
            tv_email.startAnimation(shakeAnimation);
            tv_email.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_email.requestFocus();
        } else if (!Constants.isValidEmail(tv_email.getText().toString())) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_email));
            tv_email.startAnimation(shakeAnimation);
            tv_email.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_email.requestFocus();
        } else if (tv_password.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.password_cant_empty));
            tv_password.startAnimation(shakeAnimation);
            tv_password.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_password.requestFocus();
        } else if (tv_password.getText().toString().length() <=5) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.pass_digit_must_be_greater_than_six));
            tv_password.startAnimation(shakeAnimation);
            tv_password.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_password.requestFocus();
        } else if (CheckNetwork.isNetAvailable(this)) {
            login(tv_email.getText().toString().trim(),
                    tv_password.getText().toString().trim());
        } else {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.chek_network_connection));
        }
    }


    private void login(String email, String pass) {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().login(email, pass, token)).enqueue(new Callback<LoginPozo>() {
            @Override
            public void onResponse(Call<LoginPozo> call, Response<LoginPozo> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    LoginPozo loginResponseModle = response.body();
                    if (loginResponseModle.getStatus()) {
                        AppSession.setStringPreferences(Login_Activity.this, Constants.STATUS, "auth");
                        AppSession.setStringPreferences(Login_Activity.this, Constants.USER_ID, loginResponseModle.getData().getUserId());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.USERNAME, loginResponseModle.getData().getUserFullname());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.MOBILE_NUMBER, loginResponseModle.getData().getUserPhone());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.EMAIL, loginResponseModle.getData().getUserEmail());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.GENDER, loginResponseModle.getData().getGender());
                        AppSession.setStringPreferences(Login_Activity.this, Constants.PROFILE_PIC, loginResponseModle.getData().getUserImage());
                        CheckNetwork.nextScreenFinishAllTop(Login_Activity.this, HomeActivity.class);
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
            public void onFailure(Call<LoginPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }
}
