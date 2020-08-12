package com.alpha.malukhiah.home_pkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.bannerPozoPkg.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder> {
    public List<Datum> datumList;
    Context context;
    private LayoutInflater mInflater;

    public BannerAdapter(Context activity) {
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSlideId;

        public MyViewHolder(View view) {
            super(view);
            ivSlideId = view.findViewById(R.id.ivSlideId);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_banner_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(RetrofitClient.BANNER_URL + datumList.get(position).getProductUrl())
                .into(holder.ivSlideId);
    }


    public void addCategoryList(List<Datum> datumList) {
        this.datumList = datumList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datumList == null ? 0 : datumList.size();
    }
}