package com.alpha.malukhiah.myAdv_pkg;

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
import com.alpha.malukhiah.blocklist_pkg.BlockList_Activity;
import com.alpha.malukhiah.category_pkg.Category_Activity;
import com.alpha.malukhiah.category_pkg.Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.MyAdvListPkg.Datum;
import com.alpha.malukhiah.model.MyAdvListPkg.MyAdsList;
import com.alpha.malukhiah.model.categoryPozoPkg.CategoryPozo;
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

public class MyAdv_Activity extends AppCompatActivity  implements View.OnClickListener{
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title,tv_data_not_id;
    AppCompatImageView iv_notification;
    public List<Datum> myadvList;
    RecyclerView myadv_rv;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_rv);
        userid = AppSession.getStringPreferences(MyAdv_Activity.this, Constants.USER_ID);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        iv_notification.setOnClickListener(this);
        tv_data_not_id = findViewById(R.id.tv_notification);
        iv_back.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.myad));
        myadv_rv = findViewById(R.id.id_rv_category);
        myadvList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        myadv_rv.setLayoutManager(linearLayoutManager);
        getMyAdv();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(MyAdv_Activity.this, Notification_Activity.class));
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
            CustomProgressbar.showProgressBar(MyAdv_Activity.this, false);
            (RetrofitClient.getClient().getMyAdvList(userid)).enqueue(new Callback<MyAdsList>() {
                @Override
                public void onResponse(Call<MyAdsList> call, Response<MyAdsList> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            MyAdsList sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                myadvList = sectorPojo.getData();
                                MyAdv_Adapter customAdapter = new MyAdv_Adapter(getApplicationContext(), myadvList);
                                myadv_rv.setAdapter(customAdapter);
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
                public void onFailure(Call<MyAdsList> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                    Toast.makeText(MyAdv_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }

}
