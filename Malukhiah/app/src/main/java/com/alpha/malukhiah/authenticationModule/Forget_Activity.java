package com.alpha.malukhiah.authenticationModule;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.forgetPasspkg.ForgetPassPozo;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.alpha.malukhiah.utility.CustomToast;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        forgetPass();

    }

    private void forgetPass() {
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
                validation(v);
                break;
        }
    }


    private void validation(View v) {
        if (tv_email.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.email_cant_empty));
            tv_email.startAnimation(shakeAnimation);
            tv_email.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (!Constants.isValidEmail(tv_email.getText().toString())) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_email));
            tv_email.startAnimation(shakeAnimation);
            tv_email.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        } else if (CheckNetwork.isNetAvailable(this)) {
            forget(tv_email.getText().toString().trim());
        } else {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.chek_network_connection));
        }
    }


    private void forget(String email) {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().forget(email)).enqueue(new Callback<ForgetPassPozo>() {
            @Override
            public void onResponse(Call<ForgetPassPozo> call, Response<ForgetPassPozo> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    ForgetPassPozo forgetPassPozo = response.body();
                    if (forgetPassPozo.getStatus()) {
                        Toast.makeText(Forget_Activity.this, forgetPassPozo.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Forget_Activity.this, forgetPassPozo.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 400) {

                        if (!false) {
                            try {
                                CustomProgressbar.hideProgressBar();
                                ForgetPassPozo forgetPassPozo = response.body();
                                Toast.makeText(Forget_Activity.this, forgetPassPozo.getMessage(), Toast.LENGTH_LONG).show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgetPassPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }
}
