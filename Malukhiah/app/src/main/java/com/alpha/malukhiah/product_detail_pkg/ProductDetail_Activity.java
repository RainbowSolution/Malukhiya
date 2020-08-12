package com.alpha.malukhiah.product_detail_pkg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.alpha.malukhiah.model.CategoryProductPkgModel.CategoryProductPozo;
import com.alpha.malukhiah.model.CategoryProductPkgModel.Datum;
import com.alpha.malukhiah.model.FollowerPkg.Followuser;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.RecentViewAddPkg.RecentViewAdd;
import com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg.AddWishListResponseModle;
import com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg.AddWishlistData;
import com.alpha.malukhiah.model.wishilistModlePkg.removeWishListPkg.DeleteWishListResponseModle;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Adapter;
import com.alpha.malukhiah.notification_pkg.Notification_Activity;
import com.alpha.malukhiah.subcat_product_pkg.Kitchen_Adapter;
import com.alpha.malukhiah.subcat_product_pkg.SubCat_Product_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail_Activity extends AppCompatActivity implements View.OnClickListener  {
    ImageView iv_back;
    View toolbar;
    AppCompatTextView tv_title,tv_productname,tv_productloc,
            tv_productdes,tv_follow_fill,tv_call,tv_follow_not_fill;
    AppCompatImageView iv_notification,id_userpic,iv_fav_not_fill,iv_fav_fill;
    public ArrayList<HistoryData> catList;
    RecyclerView myadv_rv;
    String imagepath,title,price,description,productid,
            location,child_sub_category,category_name,user_number,
            userid,Fav_status,user_id_by_post,follower_status;
    List<Datum> categoryProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_deatils);
        imagepath = getIntent().getStringExtra("images");
        title = getIntent().getStringExtra("title");
        price = getIntent().getStringExtra("price");
        description = getIntent().getStringExtra("description");
        productid =getIntent().getStringExtra("product_id");
        location = getIntent().getStringExtra("location");
        child_sub_category = getIntent().getStringExtra("child_category_id");
        Fav_status = getIntent().getStringExtra("Fav_status");
        user_id_by_post = getIntent().getStringExtra("user_id_by_post");
        category_name =getIntent().getStringExtra("category_name");
        follower_status = getIntent().getStringExtra("follower_status");
        user_number  = getIntent().getStringExtra("user_number");
        userid = AppSession.getStringPreferences(ProductDetail_Activity.this, Constants.USER_ID);
        init();
    }


    public void init(){
        toolbar = findViewById(R.id.toolbar);
        tv_title = toolbar.findViewById(R.id.tv_tittle);
        iv_notification = toolbar.findViewById(R.id.id_notification);
        iv_back = toolbar.findViewById(R.id.id_back);
        iv_notification.setOnClickListener(this);
        id_userpic = findViewById(R.id.id_userpic);
        iv_back.setOnClickListener(this);
        tv_title.setText("Food Detail");
        myadv_rv = findViewById(R.id.id_rv_product);
        tv_productname = findViewById(R.id.tv_productname);
        tv_productloc = findViewById(R.id.tv_productloc);
        tv_productdes = findViewById(R.id.tv_productdes);
        iv_fav_not_fill = findViewById(R.id.iv_fav_not_fill);
        iv_fav_fill = findViewById(R.id.iv_fav_fill);
        tv_follow_fill = findViewById(R.id.follow_fill_id);
        tv_follow_not_fill = findViewById(R.id.follow_fill_not_id);
        tv_call = findViewById(R.id.tv_call);
        tv_call.setOnClickListener(this);
        catList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        myadv_rv.setLayoutManager(linearLayoutManager);
        tv_productname.setText(title);
        tv_productloc.setText(location);
        tv_productdes.setText(description);
        if (imagepath.isEmpty()) {
        } else {
            Picasso.with(ProductDetail_Activity.this)
                    .load(RetrofitClient.PRODUCT_IMAGE_URL + imagepath)
                    .resize(200, 200)
                    .into(id_userpic);
        }
        getChildProduct(child_sub_category);
        if (Fav_status.equalsIgnoreCase("1")) {
            iv_fav_not_fill.setVisibility(View.GONE);
            iv_fav_fill.setVisibility(View.VISIBLE);
        } else {
            iv_fav_not_fill.setVisibility(View.VISIBLE);
            iv_fav_fill.setVisibility(View.GONE);
        }
        if (follower_status.equalsIgnoreCase("1")) {
            tv_follow_not_fill.setVisibility(View.GONE);
            tv_follow_fill.setVisibility(View.VISIBLE);
        } else {
            tv_follow_not_fill.setVisibility(View.VISIBLE);
            tv_follow_fill.setVisibility(View.GONE);
        }
        iv_fav_not_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isNetAvailable(ProductDetail_Activity.this)) {
                    addTowishList(productid);
                } else {
                    Toast.makeText(ProductDetail_Activity.this, "Check Network Connection", Toast.LENGTH_LONG).show();
                }
            }
        });
        iv_fav_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isNetAvailable(ProductDetail_Activity.this)) {
                    removeTowishList(productid);
                } else {
                    Toast.makeText(ProductDetail_Activity.this, "Check Network Connection", Toast.LENGTH_LONG).show();
                }
            }
        });
        tv_follow_not_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFollowList();
            }
        });
        tv_follow_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFollowUnfOllowList();
            }
        });
        RecentViewAdd();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_notification:
                startActivity(new Intent(ProductDetail_Activity.this, Notification_Activity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.id_back:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
            case R.id.tv_call:
                String number = ("tel:" + user_number);
                Intent mIntent = new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse(number));
                startActivity(mIntent);
                break;
    }
    }

    public void getChildProduct(String selectedSubCategoryId) {
        CustomProgressbar.showProgressBar(ProductDetail_Activity.this, false);
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
                                categoryProduct = sectorPojo.getData();
                                Related_product_Adapter currentBooking_adapter = new Related_product_Adapter(ProductDetail_Activity.this, categoryProduct);
                                myadv_rv.setAdapter(currentBooking_adapter);
                            } else {
                             //   tv_notification.setVisibility(View.VISIBLE);
                                Toast.makeText(ProductDetail_Activity.this, ""+resMessage, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ProductDetail_Activity.this, getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.plz_check_your_intrenet_text), Toast.LENGTH_SHORT).show();
        }
    }

    private void addTowishList(String productId) {
        CustomProgressbar.showProgressBar(ProductDetail_Activity.this, false);
        RetrofitClient.getClient().addWishlist(userid, productId).enqueue(new Callback<AddWishListResponseModle>() {
            @Override
            public void onResponse(Call<AddWishListResponseModle> call, Response<AddWishListResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    AddWishListResponseModle addWishListResponseModle = response.body();
                    AddWishlistData addWishlistData;
                    if (addWishListResponseModle.getStatus()) {
                        addWishlistData = addWishListResponseModle.getAddWishlistData();
                        iv_fav_not_fill.setVisibility(View.GONE);
                        iv_fav_fill.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(ProductDetail_Activity.this, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 500) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                CustomProgressbar.hideProgressBar();
                                Toast.makeText(ProductDetail_Activity.this, "Please login here", Toast.LENGTH_LONG).show();
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AddWishListResponseModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });


    }

    private void removeTowishList(String productId) {
        CustomProgressbar.showProgressBar(ProductDetail_Activity.this, false);
        RetrofitClient.getClient().deleteWishlists(userid, productId).enqueue(new Callback<DeleteWishListResponseModle>() {
            @Override
            public void onResponse(Call<DeleteWishListResponseModle> call, Response<DeleteWishListResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    DeleteWishListResponseModle addWishListResponseModle = response.body();
                    if (addWishListResponseModle.getStatus()) {
                        iv_fav_not_fill.setVisibility(View.VISIBLE);
                        iv_fav_fill.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(ProductDetail_Activity.this, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 400) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<DeleteWishListResponseModle> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });
    }



    private void addFollowList() {
        CustomProgressbar.showProgressBar(ProductDetail_Activity.this, false);
        RetrofitClient.getClient().addFollowlist(userid, user_id_by_post).enqueue(new Callback<Followuser>() {
            @Override
            public void onResponse(Call<Followuser> call, Response<Followuser> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    Followuser addWishListResponseModle = response.body();
                    if (addWishListResponseModle.getStatus()) {
                        tv_follow_not_fill.setVisibility(View.GONE);
                        tv_follow_fill.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(ProductDetail_Activity.this, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 500) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                CustomProgressbar.hideProgressBar();
                                Toast.makeText(ProductDetail_Activity.this, "Please login here", Toast.LENGTH_LONG).show();
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Followuser> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });
    }
        private void addFollowUnfOllowList() {
            CustomProgressbar.showProgressBar(ProductDetail_Activity.this, false);
            RetrofitClient.getClient().addFollowUnfOllowList(userid, user_id_by_post).enqueue(new Callback<Followuser>() {
                @Override
                public void onResponse(Call<Followuser> call, Response<Followuser> response) {
                    if (response.isSuccessful()) {
                        CustomProgressbar.hideProgressBar();
                        Followuser addWishListResponseModle = response.body();
                        if (addWishListResponseModle.getStatus()) {
                            tv_follow_not_fill.setVisibility(View.GONE);
                            tv_follow_fill.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(ProductDetail_Activity.this, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if (response.code() == 500) {
                            if (!false) {
                                JSONObject jsonObject = null;
                                try {
                                    CustomProgressbar.hideProgressBar();
                                    Toast.makeText(ProductDetail_Activity.this, "Please login here", Toast.LENGTH_LONG).show();
                                    jsonObject = new JSONObject(response.errorBody().string());
                                    CustomProgressbar.hideProgressBar();
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Followuser> call, Throwable t) {
                    CustomProgressbar.hideProgressBar();
                }
            });

    }
    private void RecentViewAdd() {
        CustomProgressbar.showProgressBar(ProductDetail_Activity.this, false);
        RetrofitClient.getClient().RecentViewAdd(userid,user_id_by_post).enqueue(new Callback<RecentViewAdd>() {
            @Override
            public void onResponse(Call<RecentViewAdd> call, Response<RecentViewAdd> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    RecentViewAdd addWishListResponseModle = response.body();
                    if (addWishListResponseModle.getStatus()) {
                        Toast.makeText(ProductDetail_Activity.this, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ProductDetail_Activity.this, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 500) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                CustomProgressbar.hideProgressBar();
                                Toast.makeText(ProductDetail_Activity.this, "Please login here", Toast.LENGTH_LONG).show();
                                jsonObject = new JSONObject(response.errorBody().string());
                                CustomProgressbar.hideProgressBar();
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RecentViewAdd> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
            }
        });

    }
}
