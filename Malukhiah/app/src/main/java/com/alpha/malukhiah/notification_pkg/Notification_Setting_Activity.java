package com.alpha.malukhiah.notification_pkg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.alpha.malukhiah.R;

public class Notification_Setting_Activity extends AppCompatActivity implements View.OnClickListener  {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title;
    AppCompatImageView iv_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting);

        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);

        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        tv_title.setText(getResources().getString(R.string.notification_setting));
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
    }
    }
}
