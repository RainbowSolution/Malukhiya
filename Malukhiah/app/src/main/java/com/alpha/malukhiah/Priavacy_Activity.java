package com.alpha.malukhiah;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.aboutUs.AboutUsPozo;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.CustomProgressbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Priavacy_Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title, tvTermsAndCondition;
    AppCompatImageView iv_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termofuse);

        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        tvTermsAndCondition = findViewById(R.id.tvTermsAndConditionId);

        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        tv_title.setText(getResources().getString(R.string.privacypolicy));

        if (CheckNetwork.isNetAvailable(getApplicationContext())) {
            getPrivacyPolicy();
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.chek_network_connection), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
//                startActivity(new Intent(About_App_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }


    public void getPrivacyPolicy() {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().privacyPolicy()).enqueue(new Callback<AboutUsPozo>() {
            @Override
            public void onResponse(Call<AboutUsPozo> call, Response<AboutUsPozo> response) {
                if (response.isSuccessful()) {
                    try {
                        CustomProgressbar.hideProgressBar();
                        AboutUsPozo aboutUsPozo = response.body();
                        if (aboutUsPozo.getStatus()) {
                            tvTermsAndCondition.setText(Html.fromHtml(aboutUsPozo.getData().getPgDescri()));
                        } else {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<AboutUsPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
                Toast.makeText(getApplicationContext(), getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
