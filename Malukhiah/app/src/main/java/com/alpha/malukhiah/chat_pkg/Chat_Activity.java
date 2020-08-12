package com.alpha.malukhiah.chat_pkg;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.model.HistoryData;

import java.util.ArrayList;

public class Chat_Activity extends AppCompatActivity implements View.OnClickListener{
    public ArrayList<HistoryData> catList;
    RecyclerView cat_rv;
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title;
    AppCompatImageView iv_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_rv);

        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);

        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        tv_title.setText(getString(R.string.chatlist));

        cat_rv = findViewById(R.id.id_rv_category);
        catList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        cat_rv.setLayoutManager(linearLayoutManager);

        getWeekendList();
    }

    public void getWeekendList(){
        for(int i=0; i<4; i++){
            catList.add(new HistoryData("Name Here"));
        }
        Chat_Adapter adapter = new Chat_Adapter(this, catList);
        cat_rv.setAdapter(adapter);
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
