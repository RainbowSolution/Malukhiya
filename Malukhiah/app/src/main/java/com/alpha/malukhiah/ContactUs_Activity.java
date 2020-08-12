package com.alpha.malukhiah;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.ContactUsPkg.ContactPozo;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.alpha.malukhiah.utility.CustomToast;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUs_Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title;
    AppCompatImageView iv_notification;
    AppCompatButton btn_post;
    TextInputEditText tv_nameContact_id, tvEmailContactId, tvMessageContactId;
    private static Animation shakeAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        btn_post = findViewById(R.id.btn_post);

        tv_nameContact_id = findViewById(R.id.tv_nameContact_id);
        tvEmailContactId = findViewById(R.id.tvEmailContactId);
        tvMessageContactId = findViewById(R.id.tvMessageContactId);

        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_post.setOnClickListener(this);

        tv_title.setText(getResources().getString(R.string.contactus));


        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                validation(v);
                break;
            case R.id.id_notification:
                startActivity(new Intent(ContactUs_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }


    private void validation(View v) {
        if (tv_nameContact_id.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.name_cannot_empty));
            tv_nameContact_id.startAnimation(shakeAnimation);
            tv_nameContact_id.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_nameContact_id.requestFocus();
        } else if (!tv_nameContact_id.getText().toString().matches("^[a-zA-Z\\s]+")) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_name));
            tv_nameContact_id.startAnimation(shakeAnimation);
            tv_nameContact_id.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tv_nameContact_id.requestFocus();
        } else if (tvEmailContactId.getText().toString().isEmpty()) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.email_cant_empty));
            tvEmailContactId.startAnimation(shakeAnimation);
            tvEmailContactId.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tvEmailContactId.requestFocus();
        } else if (!Constants.isValidEmail(tvEmailContactId.getText().toString())) {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.invalid_email));
            tvEmailContactId.startAnimation(shakeAnimation);
            tvEmailContactId.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            tvEmailContactId.requestFocus();
        } else if (CheckNetwork.isNetAvailable(this)) {
            contactUs(tv_nameContact_id.getText().toString().trim(),
                    tvEmailContactId.getText().toString().trim(),
                    tvMessageContactId.getText().toString().trim()
            );
        } else {
            new CustomToast().Show_Toast(this, v, getResources().getString(R.string.chek_network_connection));
        }
    }


    private void contactUs(String name, String email, String msg) {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().contactForm(name, email, msg)).enqueue(new Callback<ContactPozo>() {
            @Override
            public void onResponse(Call<ContactPozo> call, Response<ContactPozo> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    ContactPozo contactPozo = response.body();
                    if (contactPozo.getStatus()) {
                        Toast.makeText(getApplicationContext(), contactPozo.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), contactPozo.getMessage(), Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<ContactPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }
}
