package com.alpha.malukhiah.subcat_product_pkg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.CategoryProductPkgModel.Datum;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg.AddWishListResponseModle;
import com.alpha.malukhiah.model.wishilistModlePkg.addWishilIstModlePkg.AddWishlistData;
import com.alpha.malukhiah.model.wishilistModlePkg.removeWishListPkg.DeleteWishListResponseModle;
import com.alpha.malukhiah.product_detail_pkg.ProductDetail_Activity;
import com.alpha.malukhiah.utility.AppSession;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.Constants;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kitchen_Adapter extends RecyclerView.Adapter<Kitchen_Adapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Datum> datum_List12;
    private List<Datum> datum_List;
    String userid;
    public Kitchen_Adapter(FragmentActivity activity, List<Datum> datum_List12) {
        this.context = activity;
        this.datum_List = datum_List12;
        this.datum_List12 = datum_List12;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.product_item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        userid = AppSession.getStringPreferences(context, Constants.USER_ID);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tv_productname.setText(datum_List.get(position).getTitle());
        holder.tv_location.setText(datum_List.get(position).getLocation());
        holder.tv_price.setText("$"+datum_List.get(position).getPrice());
        if (datum_List.get(position).getImages().isEmpty()) {
        } else {
            Picasso.with(context)
                    .load(RetrofitClient.PRODUCT_IMAGE_URL + datum_List.get(position).getImages())
                    .resize(200, 200)
                    .into(holder.id_categorypic);
        }
        if (datum_List.get(position).getFavStatus().equalsIgnoreCase("1")) {
            holder.iv_fav_not_fill.setVisibility(View.GONE);
            holder.iv_fav_fill.setVisibility(View.VISIBLE);
        } else {
            holder.iv_fav_not_fill.setVisibility(View.VISIBLE);
            holder.iv_fav_fill.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ProductDetail_Activity.class);
                intent.putExtra("child_category_id",datum_List.get(position).getChildSubcatId());
                intent.putExtra("product_id",datum_List.get(position).getPostId());
                intent.putExtra("title",datum_List.get(position).getTitle());
                intent.putExtra("location",datum_List.get(position).getLocation());
                intent.putExtra("price",datum_List.get(position).getPrice());
                intent.putExtra("user_id_by_post",datum_List.get(position).getUserId());
                intent.putExtra("category_name",datum_List.get(position).getSubcatName());
                intent.putExtra("follower_status",datum_List.get(position).getFollowerStatus());
                intent.putExtra("images",datum_List.get(position).getImages());
                intent.putExtra("description",datum_List.get(position).getDescription());
                intent.putExtra("Fav_status",datum_List.get(position).getFavStatus());
                intent.putExtra("user_number",datum_List.get(position).getUserPhone());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.iv_fav_not_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isNetAvailable(context)) {
                    addTowishList(context, holder.iv_fav_not_fill, holder.iv_fav_fill, datum_List.get(position).getPostId());
                } else {
                    Toast.makeText(context, "Check Network Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.iv_fav_fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckNetwork.isNetAvailable(context)) {
                    removeTowishList(context, holder.iv_fav_not_fill, holder.iv_fav_fill, datum_List.get(position).getPostId());
                } else {
                    Toast.makeText(context, "Check Network Connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return datum_List == null ? 0 : datum_List.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_productname,tv_location,tv_price;
        AppCompatImageView id_categorypic,iv_fav_not_fill,iv_fav_fill;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_productname = itemView.findViewById(R.id.tv_productname);
            tv_location = itemView.findViewById(R.id.tv_location);
            id_categorypic = itemView.findViewById(R.id.id_userpic);
            tv_price = itemView.findViewById(R.id.tv_price);
            iv_fav_not_fill = itemView.findViewById(R.id.iv_fav_not_fill);
            iv_fav_fill = itemView.findViewById(R.id.iv_fav_fill);
        }

    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datum_List = datum_List12;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : datum_List12) {
                        if (row.getSubcatName().toLowerCase().contains(charString.toLowerCase()) || row.getLocation().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    datum_List = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datum_List;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datum_List = (ArrayList<Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
