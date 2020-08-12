package com.alpha.malukhiah.profile_pkg;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.About_App_Activity;
import com.alpha.malukhiah.ContactUs_Activity;
import com.alpha.malukhiah.Help_Activity;
import com.alpha.malukhiah.MyPurchase_pkg.MyPurchase_Adv_Activity;
import com.alpha.malukhiah.Priavacy_Activity;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.RecentlyViewAdv_pkg.RecentlyView_Adv_Activity;
import com.alpha.malukhiah.TermOfUse_Activity;
import com.alpha.malukhiah.authenticationModule.Splash_Activity;
import com.alpha.malukhiah.blocklist_pkg.BlockList_Activity;
import com.alpha.malukhiah.followers_pkg.Followers_Activity;
import com.alpha.malukhiah.following_pkg.Following_Activity;
import com.alpha.malukhiah.home_pkg.HomeActivity;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Activity;
import com.alpha.malukhiah.myFavoriteAdv_pkg.MyFav_Adv_Activity;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.notification_pkg.Notification_Setting_Activity;
import com.alpha.malukhiah.post_pkg.PostActivity;
import com.alpha.malukhiah.recently_search_pkg.RecentlySearch_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.Constants;

public class Setting_Activity extends AppCompatActivity implements View.OnClickListener  {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title;
    AppCompatImageView iv_notification;
    LinearLayout ll_change_language,
            ll_term_use,ll_about_app,ll_contact_us,
            ll_help,ll_privacy_policy,ll_change_country,
            ll_notification_setting,ll_blocked_user,ll_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);

        ll_change_language = findViewById(R.id.ll_change_language);
        ll_change_country = findViewById(R.id.ll_change_country);
        ll_notification_setting = findViewById(R.id.ll_notification_setting);
        ll_blocked_user = findViewById(R.id.ll_blocked_user);
        ll_term_use = findViewById(R.id.ll_term_use);
        ll_privacy_policy = findViewById(R.id.ll_privacy_policy);
        ll_help = findViewById(R.id.ll_help);
        ll_contact_us = findViewById(R.id.ll_contact_us);
        ll_about_app = findViewById(R.id.ll_about_app);
        ll_logout = findViewById(R.id.ll_logout);

        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        ll_change_language.setOnClickListener(this);
        ll_change_country.setOnClickListener(this);
        ll_notification_setting.setOnClickListener(this);
        ll_blocked_user.setOnClickListener(this);
        ll_term_use.setOnClickListener(this);
        ll_privacy_policy.setOnClickListener(this);
        ll_help.setOnClickListener(this);
        ll_contact_us.setOnClickListener(this);
        ll_about_app.setOnClickListener(this);
        ll_logout.setOnClickListener(this);

        tv_title.setText(getResources().getString(R.string.setting));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(Setting_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_change_language:
                dialog_change_language();
                break;

            case R.id.ll_change_country:
                dialog_change_country();
                break;

            case R.id.ll_notification_setting:
                startActivity(new Intent(Setting_Activity.this, Notification_Setting_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_blocked_user:
                startActivity(new Intent(Setting_Activity.this, BlockList_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_term_use:
                startActivity(new Intent(Setting_Activity.this, TermOfUse_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_privacy_policy:
                startActivity(new Intent(Setting_Activity.this, Priavacy_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_help:
                startActivity(new Intent(Setting_Activity.this, Help_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_contact_us:
                startActivity(new Intent(Setting_Activity.this, ContactUs_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.ll_about_app:
                startActivity(new Intent(Setting_Activity.this, About_App_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.ll_logout:
                AppSession.setStringPreferences(Setting_Activity.this, Constants.STATUS, "");
                startActivity(new Intent(Setting_Activity.this, Splash_Activity.class));
                finishAffinity();// contact us
                break;

        }
    }

        public void dialog_change_language() {
        final Dialog dialog = new Dialog(Setting_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_language);
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AppCompatImageView icon_cross = dialog.findViewById(R.id.ivlogoutCloseId);
            icon_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void dialog_change_country() {
        final Dialog dialog = new Dialog(Setting_Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_country);
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AppCompatImageView icon_cross = dialog.findViewById(R.id.ivlogoutCloseId);

        icon_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
