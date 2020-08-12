package com.alpha.malukhiah.MyPurchase_pkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;

import com.alpha.malukhiah.model.MypurchasePkg.Datum;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyPurchhase_Adv_Adapter extends RecyclerView.Adapter<MyPurchhase_Adv_Adapter.MyViewHolder> {
    public List<Datum> searchList;
    Context context;
    private LayoutInflater mInflater;

    public MyPurchhase_Adv_Adapter(Context activity, List<Datum> List) {
        searchList = List;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_productname,tv_categoryid,tv_description,tv_price,tv_date;
        RoundedImageView iv_product;
        public MyViewHolder(View view) {
            super(view);
            tv_productname = view.findViewById(R.id.tv_productname);
            tv_categoryid = view.findViewById(R.id.tv_categoryid);
            tv_description = view.findViewById(R.id.tv_description);
            tv_date = view.findViewById(R.id.tv_date);
            iv_product = view.findViewById(R.id.iv_product);
            tv_price = view.findViewById(R.id.tv_price);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_purchase_adv_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Datum detail = searchList.get(position);
        holder.tv_productname.setText(searchList.get(position).getTitle());
        holder.tv_categoryid.setText(searchList.get(position).getCategoryData().getTitle());
        holder.tv_description.setText(searchList.get(position).getDescription());
        holder.tv_price.setText("$"+searchList.get(position).getPrice());
        holder.tv_date.setText(searchList.get(position).getDateTime());
        if (searchList.get(position).getImages().isEmpty()) {
        } else {
            Picasso.with(context)
                    .load(RetrofitClient.PRODUCT_IMAGE_URL + searchList.get(position).getImages())
                    .resize(200, 200)
                    .into(holder.iv_product);
        }

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}