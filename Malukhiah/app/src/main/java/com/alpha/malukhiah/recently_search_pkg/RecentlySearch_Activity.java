package com.alpha.malukhiah.recently_search_pkg;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.Constant;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.RecentlyViewAdv_pkg.RecentlyView_Adv_Activity;
import com.alpha.malukhiah.RecentlyViewAdv_pkg.RecentlyView_Adv_Adapter;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.MyRecentViewPkg.Datum;
import com.alpha.malukhiah.model.MyRecentViewPkg.MyRecentViewAdsList;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentlySearch_Activity extends AppCompatActivity implements View.OnClickListener {
    public List<Datum> myadvList;
    RecyclerView myfavorideAdv_rv;
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title,tv_data_not_id;
    TextInputEditText tv_search_id;
    AppCompatImageView iv_notification;
    RecentlySearch_Adapter customAdapter;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recently_search_rv);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        tv_data_not_id = findViewById(R.id.tv_data_not_id);
        tv_search_id = findViewById(R.id.tv_search_id);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.recentlyserach));
        myfavorideAdv_rv = findViewById(R.id.id_rv_product);
        userid = AppSession.getStringPreferences(RecentlySearch_Activity.this, Constants.USER_ID);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        myfavorideAdv_rv.setLayoutManager(linearLayoutManager);
        getMyAdv();
        tv_search_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                customAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    customAdapter.getFilter().filter(s);
                    // ivLogoHome.setVisibility(View.VISIBLE);
                } else {
                    // ivLogoHome.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(RecentlySearch_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }

    public void getMyAdv() {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            CustomProgressbar.showProgressBar(RecentlySearch_Activity.this, false);
            (RetrofitClient.getClient().getRecentViewAdv(userid)).enqueue(new Callback<MyRecentViewAdsList>() {
                @Override
                public void onResponse(Call<MyRecentViewAdsList> call, Response<MyRecentViewAdsList> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            MyRecentViewAdsList sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                myadvList = sectorPojo.getData();
                                 customAdapter = new RecentlySearch_Adapter(RecentlySearch_Activity.this, myadvList);
                                myfavorideAdv_rv.setAdapter(customAdapter);
                            } else {
                                tv_data_not_id.setVisibility(View.VISIBLE);
                               // Toast.makeText(RecentlySearch_Activity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<MyRecentViewAdsList> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                    Toast.makeText(RecentlySearch_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            CustomProgressbar.hideProgressBar();
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }
}
