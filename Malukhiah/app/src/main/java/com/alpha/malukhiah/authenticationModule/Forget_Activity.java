package com.alpha.malukhiah.authenticationModule;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.google.android.material.textfield.TextInputEditText;

public class Forget_Activity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton mbtnSendEmail;
    private AppCompatImageView ivBackForgetId;
    private static Animation shakeAnimation;
    private TextInputEditText tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_forget);

        tv_email = findViewById(R.id.tv_email);
        mbtnSendEmail = findViewById(R.id.btn_send);
        ivBackForgetId = findViewById(R.id.ivBackForgetId);

        mbtnSendEmail.setOnClickListener(this);
        ivBackForgetId.setOnClickListener(this);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBackForgetId:
                CheckNetwork.backScreenWithFinis(Forget_Activity.this);
                break;
            case R.id.btn_send:
                Toast.makeText(this, "Working....", Toast.LENGTH_SHORT).show();
              //  validation(v);
                break;
        }
    }


 /*   private void validation(View v) {
        if (tieEmailForget.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, "Email Can't Empty");
            tieEmailForget.startAnimation(shakeAnimation);
            tieEmailForget.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (!Constants.isValidEmail(tieEmailForget.getText().toString())) {
            new CustomToast().Show_Toast(this, v, "Invalid Email");
            tieEmailForget.startAnimation(shakeAnimation);
            tieEmailForget.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (CheckNetwork.isNetAvailable(this)) {
            forget(tieEmailForget.getText().toString().trim());
        } else {
            new CustomToast().Show_Toast(this, v, "Check Network Connection");
        }
    }


    private void forget(String email) {
        CustomProgressbar.showProgressBar(this, false);
        apiServices.forgotPassword(email).enqueue(new Callback<ForgetResponseModle>() {
            @Override
            public void onResponse(Call<ForgetResponseModle> call, Response<ForgetResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    ForgetResponseModle forgetResponseModle = response.body();
                    if (forgetResponseModle.getStatus()) {
                        Toast.makeText(getApplicationContext(), forgetResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), forgetResponseModle.getMessage(), Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<ForgetResponseModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }*/
}
