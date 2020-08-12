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
import com.alpha.malukhiah.model.termsAndConditionPkg.TermsAndConditionPozo;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.CustomProgressbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermOfUse_Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title, tvTermsAndCondition;
    AppCompatImageView iv_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termofuse);
        init();
        if (CheckNetwork.isNetAvailable(getApplicationContext())) {
            getTerms();
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.chek_network_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        tvTermsAndCondition = findViewById(R.id.tvTermsAndConditionId);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.termofuse));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }

    public void getTerms() {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().termsCondition()).enqueue(new Callback<TermsAndConditionPozo>() {
            @Override
            public void onResponse(Call<TermsAndConditionPozo> call, Response<TermsAndConditionPozo> response) {
                if (response.isSuccessful()) {
                    try {
                        CustomProgressbar.hideProgressBar();
                        TermsAndConditionPozo termsAndConditionPozo = response.body();
                        if (termsAndConditionPozo.getStatus()) {
                            tvTermsAndCondition.setText(Html.fromHtml(termsAndConditionPozo.getData().getPgDescri()));
                        } else {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<TermsAndConditionPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
                Toast.makeText(getApplicationContext(), getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
