package com.alpha.malukhiah.RecentlyViewAdv_pkg;

import android.content.Intent;
import android.os.Bundle;
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
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.MyAdvListPkg.MyAdsList;
import com.alpha.malukhiah.model.MyRecentViewPkg.Datum;
import com.alpha.malukhiah.model.MyRecentViewPkg.MyRecentViewAdsList;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Activity;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Adapter;
import com.alpha.malukhiah.myFavoriteAdv_pkg.MyFav_Adv_Activity;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentlyView_Adv_Activity extends AppCompatActivity implements View.OnClickListener {
    public List<Datum> myadvList;
    RecyclerView myfavorideAdv_rv;
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title,tv_data_not_id;
    AppCompatImageView iv_notification;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_rv);
        userid = AppSession.getStringPreferences(RecentlyView_Adv_Activity.this, Constants.USER_ID);

        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        tv_data_not_id = findViewById(R.id.tv_notification);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.recentlyview));
        myfavorideAdv_rv = findViewById(R.id.id_rv_category);
       // catList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        myfavorideAdv_rv.setLayoutManager(linearLayoutManager);
       getMyAdv();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(RecentlyView_Adv_Activity.this, Notification_Activity.class));
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
            CustomProgressbar.showProgressBar(RecentlyView_Adv_Activity.this, false);
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
                                RecentlyView_Adv_Adapter customAdapter = new RecentlyView_Adv_Adapter(RecentlyView_Adv_Activity.this, myadvList);
                                myfavorideAdv_rv.setAdapter(customAdapter);
                            } else {
                                tv_data_not_id.setVisibility(View.VISIBLE);
                               // Toast.makeText(RecentlyView_Adv_Activity.this, resMessage, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RecentlyView_Adv_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }
}
