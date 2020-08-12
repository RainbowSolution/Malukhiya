package com.alpha.malukhiah.category_pkg;

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
import com.alpha.malukhiah.home_pkg.Home_Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.categoryPozoPkg.CategoryPozo;
import com.alpha.malukhiah.model.categoryPozoPkg.Datum;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.post_pkg.AdapterPkgModel.CategoryAdapter;
import com.alpha.malukhiah.post_pkg.PostActivity;
import com.alpha.malukhiah.subcat_product_pkg.SubCat_Product_Activity;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Activity extends AppCompatActivity implements View.OnClickListener {
    List<Datum> catList;
    RecyclerView cat_rv;
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title,tv_notification;
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
        tv_title.setText(getString(R.string.category));
        cat_rv = findViewById(R.id.id_rv_category);
        tv_notification = findViewById(R.id.tv_notification_id);
        catList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        cat_rv.setLayoutManager(linearLayoutManager);
        getCategoryList();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(Category_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;
        }
    }

    public void getCategoryList() {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            CustomProgressbar.showProgressBar(Category_Activity.this, false);
            (RetrofitClient.getClient().getCategory()).enqueue(new Callback<CategoryPozo>() {
                @Override
                public void onResponse(Call<CategoryPozo> call, Response<CategoryPozo> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            CategoryPozo sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                catList = sectorPojo.getData();
                                Category_Adapter customAdapter = new Category_Adapter(getApplicationContext(), catList);
                                cat_rv.setAdapter(customAdapter);
                            } else {
                                tv_notification.setVisibility(View.VISIBLE);
                                Toast.makeText(Category_Activity.this, resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<CategoryPozo> call, Throwable t) {
                    Toast.makeText(Category_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }
}
