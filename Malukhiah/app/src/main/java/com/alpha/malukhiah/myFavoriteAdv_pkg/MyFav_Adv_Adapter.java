package com.alpha.malukhiah.myFavoriteAdv_pkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;

import com.alpha.malukhiah.model.MyFavAdvListPkg.Datum;
import com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg.AddWishListResponseModle;
import com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg.AddWishlistData;
import com.alpha.malukhiah.model.wishilistModlePkg.removeWishListPkg.DeleteWishListResponseModle;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFav_Adv_Adapter extends RecyclerView.Adapter<MyFav_Adv_Adapter.MyViewHolder> {
    public List<Datum> searchList;
    Context context;
    private LayoutInflater mInflater;
    String userid;
    public MyFav_Adv_Adapter(Context activity, List<Datum> List) {
        searchList = List;
        context = activity;
        userid = AppSession.getStringPreferences(context, Constants.USER_ID);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_productname,tv_categoryid,tv_description,tv_price,tv_date;
        AppCompatImageView iv_fav_not_fill,iv_fav_fill;
        RoundedImageView iv_product;
        public MyViewHolder(View view) {
            super(view);
            tv_productname = view.findViewById(R.id.tv_productname);
            tv_categoryid = view.findViewById(R.id.tv_categoryid);
            tv_description = view.findViewById(R.id.tv_description);
            tv_date = view.findViewById(R.id.tv_date);
            iv_product = view.findViewById(R.id.iv_product);
            tv_price = view.findViewById(R.id.tv_price);
            iv_fav_not_fill = view.findViewById(R.id.iv_fav_not_fill);
            iv_fav_fill = view.findViewById(R.id.iv_fav_fill);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_fav_adv_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Datum detail = searchList.get(position);
        /*holder.tv_productname.setText(detail.getOrdername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SubCategory_Activity.class));
            }
        });*/
        holder.tv_productname.setText(searchList.get(position).getPostdetails().getTitle());
        holder.tv_description.setText(searchList.get(position).getPostdetails().getDescription());
        holder.tv_price.setText("$"+searchList.get(position).getPostdetails().getPrice());
        holder.tv_categoryid.setText(searchList.get(position).getCat_name());
        holder.tv_date.setText(searchList.get(position).getPostdetails().getDateTime());
        if (searchList.get(position).getPostdetails().getImages().isEmpty()) {
        } else {
            Picasso.with(context)
                    .load(RetrofitClient.PRODUCT_IMAGE_URL + searchList.get(position).getPostdetails().getImages())
                    .resize(200, 200)
                    .into(holder.iv_product);
        }

        if (searchList.get(position).getFavStatus().equalsIgnoreCase("1")) {
            holder.iv_fav_not_fill.setVisibility(View.GONE);
            holder.iv_fav_fill.setVisibility(View.VISIBLE);
        } else {
            holder.iv_fav_not_fill.setVisibility(View.VISIBLE);
            holder.iv_fav_fill.setVisibility(View.GONE);
        }

        holder.iv_fav_not_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isNetAvailable(context)) {
                    addTowishList(context, holder.iv_fav_not_fill, holder.iv_fav_fill, searchList.get(position).getPostId());
                } else {
                    Toast.makeText(context, "Check Network Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.iv_fav_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isNetAvailable(context)) {
                    removeTowishList(context, holder.iv_fav_not_fill, holder.iv_fav_fill, searchList.get(position).getPostId());
                } else {
                    Toast.makeText(context, "Check Network Connection", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    private void addTowishList(final Context context, final AppCompatImageView unfill, final AppCompatImageView fill, String productId) {
        CustomProgressbar.showProgressBar(context, false);
        RetrofitClient.getClient().addWishlist(userid, productId).enqueue(new Callback<AddWishListResponseModle>() {
            @Override
            public void onResponse(Call<AddWishListResponseModle> call, Response<AddWishListResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    AddWishListResponseModle addWishListResponseModle = response.body();
                    AddWishlistData addWishlistData;
                    if (addWishListResponseModle.getStatus()) {
                        addWishlistData = addWishListResponseModle.getAddWishlistData();
                        unfill.setVisibility(View.GONE);
                        fill.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(context, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (response.code() == 500) {
                        if (!false) {
                            JSONObject jsonObject = null;
                            try {
                                CustomProgressbar.hideProgressBar();
                                Toast.makeText(context, "Please login here", Toast.LENGTH_LONG).show();
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

    private void removeTowishList(final Context context, final AppCompatImageView unfill, final AppCompatImageView fill, String productId) {
        CustomProgressbar.showProgressBar(context, false);
        RetrofitClient.getClient().deleteWishlists(userid, productId).enqueue(new Callback<DeleteWishListResponseModle>() {
            @Override
            public void onResponse(Call<DeleteWishListResponseModle> call, Response<DeleteWishListResponseModle> response) {
                if (response.isSuccessful()) {
                    CustomProgressbar.hideProgressBar();
                    DeleteWishListResponseModle addWishListResponseModle = response.body();
                    if (addWishListResponseModle.getStatus()) {
                        unfill.setVisibility(View.VISIBLE);
                        fill.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(context, addWishListResponseModle.getMessage(), Toast.LENGTH_LONG).show();
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
}