package com.alpha.malukhiah.followers_pkg;

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
import com.alpha.malukhiah.following_pkg.Following_Activity;
import com.alpha.malukhiah.following_pkg.Following_Adapter;
import com.alpha.malukhiah.following_pkg.Suggestion_people_Adapter;
import com.alpha.malukhiah.model.FollowerPkg.Datum;
import com.alpha.malukhiah.model.FollowerPkg.FollowerList;
import com.alpha.malukhiah.model.FollowingPkg.FollowingList;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Activity;
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

public class Followers_Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title,tv_data_not_id,total_follower;
    AppCompatImageView iv_notification;
    public List<Datum> followingList;
    RecyclerView following_rv;
    String  userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followers_rv);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        tv_data_not_id = findViewById(R.id.tv_data_not_id);
        total_follower = findViewById(R.id.total_follower);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        userid = AppSession.getStringPreferences(Followers_Activity.this, Constants.USER_ID);

        tv_title.setText(getResources().getString(R.string.followers));

        following_rv = findViewById(R.id.id_rv_followers);
      //  suggestion_ppl_rv = findViewById(R.id.id_rv_suggestion_ppl);
        followingList = new ArrayList<>();
       // suggestion_ppl_List = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        following_rv.setLayoutManager(linearLayoutManager);

       /* LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        suggestion_ppl_rv.setLayoutManager(linearLayoutManager1);
*/
        getMyAdv();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(Followers_Activity.this, Notification_Activity.class));
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
            CustomProgressbar.showProgressBar(Followers_Activity.this, false);

            (RetrofitClient.getClient().getFollowerList(userid)).enqueue(new Callback<FollowerList>() {
                @Override
                public void onResponse(Call<FollowerList> call, Response<FollowerList> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            FollowerList sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                followingList = sectorPojo.getData();
                                Followers_Adapter customAdapter = new Followers_Adapter(getApplicationContext(), followingList);
                                following_rv.setAdapter(customAdapter);
                                total_follower.setText("Followers ("+String.valueOf(followingList.size()+")"));

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
                public void onFailure(Call<FollowerList> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                    Toast.makeText(Followers_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }
}
