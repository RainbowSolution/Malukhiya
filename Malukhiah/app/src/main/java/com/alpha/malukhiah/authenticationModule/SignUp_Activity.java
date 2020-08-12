package com.alpha.malukhiah.authenticationModule;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.utility.CheckNetwork;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

  /*  private MaterialButton mbtnRegisteredNow, mbtnSignInNow;
    private TextInputEditText tieFullNameReg, tieEmailReg, tiePassReg;*/
    private static Animation shakeAnimation;
   // private ApiServices apiServices;
    private String token;
    private AppCompatImageView ivBackForgetId;
    private AppCompatTextView tv_back_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_signup);
     //   apiServices = RetrofitClient.getClient().create(ApiServices.class);
       // init();
        tv_back_signup = findViewById(R.id.tv_back_signup);
        ivBackForgetId = findViewById(R.id.ivBackForgetId);
        tv_back_signup.setOnClickListener(this);
        ivBackForgetId.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackForgetId:
                CheckNetwork.backScreenWithFinis(SignUp_Activity.this);
                break;
          /*  case R.id.mbtnSignInNowId:
              //  validation(v);
                break;*/
            case R.id.tv_back_signup:
                CheckNetwork.backScreenWithFinis(SignUp_Activity.this);
                break;
        }
    }

   /* private void init() {
        tieFullNameReg = findViewById(R.id.tieFullNameRegId);
        tieEmailReg = findViewById(R.id.tieEmailRegId);
        tiePassReg = findViewById(R.id.tiePassRegId);
        mbtnRegisteredNow = findViewById(R.id.mbtnRegisteredNowId);
        mbtnSignInNow = findViewById(R.id.mbtnSignInNowId);
        mbtnRegisteredNow.setOnClickListener(this);
        mbtnSignInNow.setOnClickListener(this);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        FirebaseApp.initializeApp(SignUp_Activity.this);
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




    private void validation(View v) {
        if (tieFullNameReg.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, "Name Can't Empty");
            tieFullNameReg.startAnimation(shakeAnimation);
            tieFullNameReg.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tieFullNameReg.requestFocus();
        } else if (tieEmailReg.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, "Email Can't Empty");
            tieEmailReg.startAnimation(shakeAnimation);
            tieEmailReg.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tieEmailReg.requestFocus();
        } else if (!Constants.isValidEmail(tieEmailReg.getText().toString())) {
            new CustomToast().Show_Toast(this, v, "Invalid Email");
            tieEmailReg.startAnimation(shakeAnimation);
            tieEmailReg.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tieEmailReg.requestFocus();
        } else if (tiePassReg.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, "Password Can't Empty");
            tiePassReg.startAnimation(shakeAnimation);
            tiePassReg.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tiePassReg.requestFocus();
        } else if (tiePassReg.getText().toString().length() <= 6) {
            new CustomToast().Show_Toast(this, v, "Password digits must be greater than six");
            tiePassReg.startAnimation(shakeAnimation);
            tiePassReg.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tiePassReg.requestFocus();
        } else if (CheckNetwork.isNetAvailable(this)) {
            signup(tieFullNameReg.getText().toString().trim(),
                    tieEmailReg.getText().toString().trim(),
                    tiePassReg.getText().toString().trim()
            );
        } else {
            new CustomToast().Show_Toast(this, v, "Check Network Connection");
        }
    }

    private void signup(String fullName, String email, String pass) {
        CustomProgressbar.showProgressBar(this, false);
        apiServices.registration(fullName, email, pass, token).enqueue(new Callback<RegistrationResponseModle>() {
            @Override
            public void onResponse(Call<RegistrationResponseModle> call, Response<RegistrationResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    RegistrationResponseModle registrationResponseModle = response.body();
                    if (registrationResponseModle.getStatus()) {
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.STATUS, "auth");
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.USER_ID, registrationResponseModle.getData().getUserId());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.USERNAME, registrationResponseModle.getData().getUserFullname());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.MOBILE_NUMBER, registrationResponseModle.getData().getUserPhone());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.EMAIL, registrationResponseModle.getData().getUserEmail());
                        AppSession.setStringPreferences(SignUp_Activity.this, Constants.GENDER, registrationResponseModle.getData().getGender());
                        CheckNetwork.nextScreenWithoutFinish(SignUp_Activity.this, HomeActivity.class);
                    } else {
                        Toast.makeText(getApplicationContext(), registrationResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 400) {
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
            public void onFailure(Call<RegistrationResponseModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
