package com.alpha.malukhiah.filter_pkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.ViewPager;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.subcat_product_pkg.SubCat_Product_Activity;
import com.google.android.material.tabs.TabLayout;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener{
    AppCompatTextView tv_title;
    AppCompatImageView iv_notification;
    ImageView iv_back;
    View toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);

        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        tv_title.setText(getResources().getString(R.string.filter));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(FilterActivity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

        }
    }
}
