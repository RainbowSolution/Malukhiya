package com.alpha.malukhiah.profile_pkg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.Constant;
import com.alpha.malukhiah.MyPurchase_pkg.MyPurchase_Adv_Activity;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.RecentlyViewAdv_pkg.RecentlyView_Adv_Activity;
import com.alpha.malukhiah.RecentlyViewAdv_pkg.RecentlyView_Adv_Adapter;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.followers_pkg.Followers_Activity;
import com.alpha.malukhiah.following_pkg.Following_Activity;
import com.alpha.malukhiah.model.GetProfilePkg.GetProfileData;
import com.alpha.malukhiah.model.MyRecentViewPkg.MyRecentViewAdsList;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Activity;
import com.alpha.malukhiah.myFavoriteAdv_pkg.MyFav_Adv_Activity;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.post_pkg.PostActivity;
import com.alpha.malukhiah.recently_search_pkg.RecentlySearch_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile_Activity extends AppCompatActivity implements View.OnClickListener  {
    ImageView iv_back;
    View toolbar;
    RoundedImageView  iv_imageView;
    AppCompatTextView tv_title,tv_ads_count,tv_viewd,tv_folower,tv_username,tv_memebership;
    AppCompatImageView iv_notification;
    LinearLayout ll_post_freead,ll_setting,ll_followers,ll_edit_profile,ll_recently_view_ad,ll_my_ad,ll_recently_search,ll_my_fav_ad,ll_my_purchase,ll_following;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        userid = AppSession.getStringPreferences(MyProfile_Activity.this, Constants.USER_ID);
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        ll_edit_profile = findViewById(R.id.ll_edit_profile);
        ll_post_freead = findViewById(R.id.ll_post_freead);
        ll_my_ad = findViewById(R.id.ll_my_ad);
        ll_my_fav_ad = findViewById(R.id.ll_my_fav_ad);
        ll_my_purchase = findViewById(R.id.ll_my_purchase);
        ll_following = findViewById(R.id.ll_following);
        iv_imageView = findViewById(R.id.iv_imageView);
        ll_recently_view_ad = findViewById(R.id.ll_recently_view_ad);
        ll_recently_search = findViewById(R.id.ll_recently_search);
        tv_username = findViewById(R.id.tv_username);
        ll_followers = findViewById(R.id.ll_followers);
        ll_setting = findViewById(R.id.ll_setting);
        tv_ads_count = findViewById(R.id.tv_ads_count);
        tv_viewd  = findViewById(R.id.tv_viewd);
        tv_folower = findViewById(R.id.tv_folower);
        tv_memebership = findViewById(R.id.tv_memebership);
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        ll_edit_profile.setOnClickListener(this);
        ll_post_freead.setOnClickListener(this);
        ll_my_ad.setOnClickListener(this);
        ll_my_fav_ad.setOnClickListener(this);
        ll_my_purchase.setOnClickListener(this);
        ll_following.setOnClickListener(this);
        ll_recently_view_ad.setOnClickListener(this);
        ll_recently_search.setOnClickListener(this);
        ll_followers.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.myprofile));
        getMyAdv();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(MyProfile_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_edit_profile:
                startActivity(new Intent(MyProfile_Activity.this, EditProfile_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_post_freead:
                startActivity(new Intent(MyProfile_Activity.this, PostActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_my_ad:
                startActivity(new Intent(MyProfile_Activity.this, MyAdv_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_my_fav_ad:
                startActivity(new Intent(MyProfile_Activity.this, MyFav_Adv_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_my_purchase:
                startActivity(new Intent(MyProfile_Activity.this, MyPurchase_Adv_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_following:
                startActivity(new Intent(MyProfile_Activity.this, Following_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_recently_view_ad:
                startActivity(new Intent(MyProfile_Activity.this, RecentlyView_Adv_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_recently_search:
                startActivity(new Intent(MyProfile_Activity.this, RecentlySearch_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_followers:
                startActivity(new Intent(MyProfile_Activity.this, Followers_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_setting:
                startActivity(new Intent(MyProfile_Activity.this, Setting_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
    }
    }

    public void getMyAdv() {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            CustomProgressbar.showProgressBar(MyProfile_Activity.this, false);
            (RetrofitClient.getClient().getProfileUser(userid)).enqueue(new Callback<GetProfileData>() {
                @Override
                public void onResponse(Call<GetProfileData> call, Response<GetProfileData> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            GetProfileData sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                tv_ads_count.setText(String.valueOf(sectorPojo.getData().getMyAdds()));
                                tv_viewd.setText(String.valueOf(sectorPojo.getData().getViewed()));
                                tv_folower.setText(String.valueOf(sectorPojo.getData().getFollowerCount()));
                                tv_username.setText(String.valueOf(sectorPojo.getData().getUserFullname()));
                                if (sectorPojo.getData().getIsPaid().equalsIgnoreCase("1")) {
                                    tv_memebership.setText("Paid Member");
                                }else {
                                    tv_memebership.setText("Free Member");
                                }
                                if (sectorPojo.getData().getUserImage() == null || sectorPojo.getData().getUserImage().isEmpty()) {
                                } else {
                                    Picasso.with(getApplicationContext())
                                            .load(RetrofitClient.PROFILE + sectorPojo.getData().getUserImage())
                                            .resize(200, 200)
                                            .into(iv_imageView);
                                }

                            } else {
                                Toast.makeText(MyProfile_Activity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<GetProfileData> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                    Toast.makeText(MyProfile_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }
}
