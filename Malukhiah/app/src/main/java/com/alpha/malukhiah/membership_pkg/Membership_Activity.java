package com.alpha.malukhiah.membership_pkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.categoryPozoPkg.CategoryPozo;
import com.alpha.malukhiah.model.memberShipPkg.MembershipPozo;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.CustomProgressbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Membership_Activity extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<HistoryData> catList;
    RecyclerView cat_rv;
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title;
    AppCompatImageView iv_notification;
    Membership_Adapter adapter;

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

        tv_title.setText(getString(R.string.membership));


        cat_rv = findViewById(R.id.id_rv_category);
        catList = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        cat_rv.setLayoutManager(gridLayoutManager);
        adapter = new Membership_Adapter(this);
        cat_rv.setAdapter(adapter);

        if (CheckNetwork.isNetAvailable(getApplicationContext())) {
            getMembership();
        } else {

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(Membership_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }


    public void getMembership() {
        CustomProgressbar.showProgressBar(this, false);
        (RetrofitClient.getClient().getMembership()).enqueue(new Callback<MembershipPozo>() {
            @Override
            public void onResponse(Call<MembershipPozo> call, Response<MembershipPozo> response) {
                if (response.isSuccessful()) {
                    try {
                        CustomProgressbar.hideProgressBar();
                        MembershipPozo membershipPozo = response.body();
                        if (membershipPozo.getStatus()) {
                            adapter.addMemberShipList(membershipPozo.getData());
                        } else {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<MembershipPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
                Toast.makeText(getApplicationContext(), getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
