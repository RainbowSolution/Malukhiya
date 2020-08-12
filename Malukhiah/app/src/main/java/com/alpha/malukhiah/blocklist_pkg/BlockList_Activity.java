package com.alpha.malukhiah.blocklist_pkg;

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
import com.alpha.malukhiah.MyPurchase_pkg.MyPurchase_Adv_Activity;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.category_pkg.Category_Activity;
import com.alpha.malukhiah.category_pkg.Category_Adapter;
import com.alpha.malukhiah.model.BlockUserPkg.BlockData;
import com.alpha.malukhiah.model.BlockUserPkg.Datum;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.categoryPozoPkg.CategoryPozo;
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
public class BlockList_Activity extends AppCompatActivity  implements View.OnClickListener {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title, tv_data_not_id,total_count;
    AppCompatImageView iv_notification;
    public List<Datum> catList;
    RecyclerView myadv_rv;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blocklist_rv);
        userid = AppSession.getStringPreferences(BlockList_Activity.this, Constants.USER_ID);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        iv_notification.setOnClickListener(this);
        tv_data_not_id = findViewById(R.id.tv_data_not_id);
        total_count = findViewById(R.id.total_count);
        iv_back.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.blocklist));
        myadv_rv = findViewById(R.id.id_rv_category);
        catList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        myadv_rv.setLayoutManager(linearLayoutManager);
        getCategoryList();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(BlockList_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }


   public void getCategoryList() {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            CustomProgressbar.showProgressBar( BlockList_Activity.this, false);
            (RetrofitClient.getClient().getBlockList(userid)).enqueue(new Callback<BlockData>() {
                @Override
                public void onResponse(Call<BlockData> call, Response<BlockData> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            BlockData sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                catList = sectorPojo.getData();
                                BlockList_Adapter adapter = new BlockList_Adapter(BlockList_Activity.this, catList);
                                myadv_rv.setAdapter(adapter);
                                total_count.setText("Blocklist ("+String.valueOf(catList.size()+")"));
                            //    Toast.makeText(BlockList_Activity.this, ""+catList.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                tv_data_not_id.setVisibility(View.VISIBLE);
                                Toast.makeText(BlockList_Activity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<BlockData> call, Throwable t) {
                    Toast.makeText(BlockList_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }


}
