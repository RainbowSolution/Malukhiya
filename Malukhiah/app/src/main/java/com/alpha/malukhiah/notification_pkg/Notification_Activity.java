package com.alpha.malukhiah.notification_pkg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.category_pkg.Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.NotificaitonCountPkg.NotificationClear;
import com.alpha.malukhiah.model.NotificationListPkg.Datum;
import com.alpha.malukhiah.model.NotificationListPkg.GetNotificationListModel;
import com.alpha.malukhiah.subcat_product_pkg.SubCat_Product_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification_Activity extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<HistoryData> catList;
    RecyclerView cat_rv;
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title, tv_notification,tv_clearNotification;
    AppCompatImageView iv_notification;
    private List<Datum> getnotificationList;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaition);
        userid = AppSession.getStringPreferences(Notification_Activity.this, Constants.USER_ID);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_clearNotification = findViewById(R.id.tv_clearNotificationId);
        tv_title.setText(getString(R.string.notification));
        tv_notification = findViewById(R.id.tv_notification);
        cat_rv = findViewById(R.id.id_rv_category);
        catList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        cat_rv.setLayoutManager(linearLayoutManager);
        getnotificationList();
        tv_clearNotification.setOnClickListener(this);
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
            case R.id.tv_clearNotificationId:
                if (CheckNetwork.isNetAvailable(Notification_Activity.this)) {
                    clearNotification();
                } else {
                    Toast.makeText(Notification_Activity.this, "Check Network Connection", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void getnotificationList() {
        CustomProgressbar.showProgressBar(Notification_Activity.this, false);
        RetrofitClient.getClient().notificaitonList(userid).enqueue(new Callback<GetNotificationListModel>() {
            @Override
            public void onResponse(Call<GetNotificationListModel> call, Response<GetNotificationListModel> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    GetNotificationListModel getBookingModle = response.body();
                    if (getBookingModle.getStatus().equals(true)) {
                        getnotificationList = getBookingModle.getData();
                        Notification_Adapter adapter = new Notification_Adapter(Notification_Activity.this, getnotificationList);
                        cat_rv.setAdapter(adapter);
                        tv_clearNotification.setVisibility(View.VISIBLE);
                        tv_notification.setVisibility(View.GONE);
                    } else {
                        tv_notification.setVisibility(View.VISIBLE);
                        tv_clearNotification.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNotificationListModel> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });
    }
    private void clearNotification() {
        // CustomProgressbar.showProgressBar(getActivity(), false);
        RetrofitClient.getClient().clearNotification(userid).enqueue(new Callback<NotificationClear>() {
            @Override
            public void onResponse(Call<NotificationClear> call, Response<NotificationClear> response) {
                if (response.isSuccessful()) {
                    // CustomProgressbar.hideProgressBar();
                    NotificationClear notificationResponseModle = response.body();
                    if (notificationResponseModle.getStatus()) {
                        // tvNotificationTitle.setVisibility(View.VISIBLE);
                        getnotificationList();
                    }
                } else {
                    if (response.code() == 400) {
                        tv_notification.setVisibility(View.VISIBLE);
                        if (!false) {

                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                //  CustomProgressbar.hideProgressBar();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationClear> call, Throwable t) {
                //  CustomProgressbar.hideProgressBar();
                tv_notification.setVisibility(View.VISIBLE);

            }
        });


    }
}
