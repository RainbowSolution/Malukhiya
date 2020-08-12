package com.alpha.malukhiah.subcat_product_pkg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alpha.malukhiah.Constant;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.filter_pkg.FilterActivity;
import com.alpha.malukhiah.model.CategoryProductPkgModel.CategoryProductPozo;
import com.alpha.malukhiah.model.CategoryProductPkgModel.Datum;
import com.alpha.malukhiah.model.subChildCategoryPozoPkg.SubChildCategoryPozo;
import com.alpha.malukhiah.model.subChildCategoryPozoPkg.SubChildDatum;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.post_pkg.AdapterPkgModel.SubChildCategoryAdapter;
import com.alpha.malukhiah.post_pkg.PostActivity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.alpha.malukhiah.utility.RecyclerItemClickListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubCat_Product_Activity extends AppCompatActivity implements View.OnClickListener,SubCategory_child_Adapter.Onclick{
    AppCompatTextView tv_title,tv_notification;
    AppCompatImageView iv_notification;
    private ViewPager vpBookingHistory;
    private RecyclerView rv_child;
    ImageView iv_back; AppCompatImageView iv_filter;
    View toolbar;
    private RecyclerView rvCurrentBooking;
    List<SubChildDatum> subChildDatumList;
    List<Datum> categoryProduct;
    String selectedSubCategoryId, userid,selectedSubCategoryProductId;
    SubCategory_child_Adapter customAdapter;
    LinearLayout li_serarch;
    Kitchen_Adapter currentBooking_adapter;
    TextInputEditText edit_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_rv);
        userid = AppSession.getStringPreferences(SubCat_Product_Activity.this, Constants.USER_ID);
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        iv_filter = findViewById(R.id.iv_filter);
        subChildDatumList = new ArrayList<>();
        iv_notification.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        iv_filter.setOnClickListener(this);
        tv_title.setText(getResources().getString(R.string.whatdoyouwanttoeat));
        tv_notification = findViewById(R.id.tv_notification_id);
        li_serarch = findViewById(R.id.li_serarch_Id);
        edit_search = findViewById(R.id.edit_search_id);
      //  vpBookingHistory = findViewById(R.id.vpBookingHistoryId);
        rv_child = findViewById(R.id.rv_child);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubCat_Product_Activity.this, RecyclerView.HORIZONTAL, false);
        rv_child.setLayoutManager(linearLayoutManager);
        selectedSubCategoryId = getIntent().getStringExtra("sub_category");
        getSubChildCategory(selectedSubCategoryId);
        rvCurrentBooking = findViewById(R.id.id_rv_product);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SubCat_Product_Activity.this, 2);
        rvCurrentBooking.setLayoutManager(gridLayoutManager);
        edit_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                currentBooking_adapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    currentBooking_adapter.getFilter().filter(s);
                   // ivLogoHome.setVisibility(View.VISIBLE);
                } else {
                   // ivLogoHome.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(SubCat_Product_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;

            case R.id.iv_filter:
                startActivity(new Intent(SubCat_Product_Activity.this, FilterActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
    }

    public void getSubChildCategory(String selectedSubCategoryId) {
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            CustomProgressbar.showProgressBar(SubCat_Product_Activity.this, false);
            (RetrofitClient.getClient().getSubChildCategory(selectedSubCategoryId)).enqueue(new Callback<SubChildCategoryPozo>() {
                @Override
                public void onResponse(Call<SubChildCategoryPozo> call, Response<SubChildCategoryPozo> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            SubChildCategoryPozo sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                 subChildDatumList = sectorPojo.getData();
                                 customAdapter = new SubCategory_child_Adapter(SubCat_Product_Activity.this, subChildDatumList,SubCat_Product_Activity.this);
                                 rv_child.setAdapter(customAdapter);
                                 selectedSubCategoryProductId = subChildDatumList.get(0).getSubCatId();
                                 getChildProduct(selectedSubCategoryProductId);
                            } else {
                                tv_notification.setVisibility(View.VISIBLE);
                                li_serarch.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<SubChildCategoryPozo> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                    Toast.makeText(SubCat_Product_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }


    public void getChildProduct(String selectedSubCategoryId) {
        CustomProgressbar.showProgressBar(SubCat_Product_Activity.this, false);
        if (Constant.isNetworkAvailable(getApplicationContext())) {
            (RetrofitClient.getClient().getProductByCategory(selectedSubCategoryId,userid)).enqueue(new Callback<CategoryProductPozo>() {
                @Override
                public void onResponse(Call<CategoryProductPozo> call, Response<CategoryProductPozo> response) {
                    if (response.isSuccessful()) {
                        try {
                            CustomProgressbar.hideProgressBar();
                            Log.e("TAG", "State list response : " + new Gson().toJson(response.body()));
                            CategoryProductPozo sectorPojo = response.body();
                            String resMessage = sectorPojo.getMessage();
                            if (sectorPojo.getStatus()) {
                                tv_notification.setVisibility(View.GONE);
                                categoryProduct = sectorPojo.getData();
                                currentBooking_adapter = new Kitchen_Adapter(SubCat_Product_Activity.this, categoryProduct);
                                rvCurrentBooking.setAdapter(currentBooking_adapter);
                            } else {
                                tv_notification.setVisibility(View.VISIBLE);
                             //   Toast.makeText(SubCat_Product_Activity.this, ""+resMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                    }
                }

                @Override
                public void onFailure(Call<CategoryProductPozo> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                    Toast.makeText(SubCat_Product_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onEvent(View view, int pos) {
        if (categoryProduct==null){
            SubChildDatum pu = subChildDatumList.get(pos);
            getChildProduct(pu.getSubCatId());
        }else {
            categoryProduct.clear();
            rvCurrentBooking.setAdapter(null);
            SubChildDatum pu = subChildDatumList.get(pos);
            getChildProduct(pu.getSubCatId());
        }

    }
}
