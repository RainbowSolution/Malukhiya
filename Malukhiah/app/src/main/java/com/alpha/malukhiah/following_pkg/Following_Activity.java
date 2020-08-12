package com.alpha.malukhiah.following_pkg;

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
import com.alpha.malukhiah.followers_pkg.Followers_Activity;
import com.alpha.malukhiah.model.FollowingPkg.Datum;
import com.alpha.malukhiah.model.FollowingPkg.FollowingList;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.MyAdvListPkg.MyAdsList;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Activity;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Adapter;
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

public class Following_Activity extends AppCompatActivity  implements View.OnClickListener {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title,tv_data_not_id, tv_folowingdata;
    AppCompatImageView iv_notification;
    public List<Datum> followingList;
    RecyclerView following_rv,suggestion_ppl_rv;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following_rv);
        userid = AppSession.getStringPreferences(Following_Activity.this, Constants.USER_ID);
        tv_folowingdata = findViewById(R.id.tv_folowingdata);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.following));
        following_rv = findViewById(R.id.id_rv_followers);
        followingList = new ArrayList<>();
        tv_data_not_id = findViewById(R.id.tv_data_not_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        following_rv.setLayoutManager(linearLayoutManager);
        getMyAdv();

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(Following_Activity.this, Notification_Activity.class));
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
            CustomProgressbar.showProgressBar(Following_Activity.this, false);
            (RetrofitClient.getClient().getFollowingList(userid)).enqueue(new Callback<FollowingList>() {
                @Override
                public void onResponse(Call<FollowingList> call, Response<FollowingList> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            FollowingList sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                followingList = sectorPojo.getData();
                                Following_Adapter customAdapter = new Following_Adapter(getApplicationContext(), followingList);
                                following_rv.setAdapter(customAdapter);
                                tv_folowingdata.setText("Followings ("+String.valueOf(followingList.size()+")"));
                            } else {
                                tv_data_not_id.setVisibility(View.VISIBLE);
                                // Toast.makeText(MyAdv_Activity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<FollowingList> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();

                    Toast.makeText(Following_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }
}
