package com.alpha.malukhiah.myAdv_pkg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.category_pkg.Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.MyAdvListPkg.Datum;
import com.alpha.malukhiah.subcategory_pkg.SubCategory_Activity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdv_Adapter extends RecyclerView.Adapter<MyAdv_Adapter.MyViewHolder> {
    public List<Datum> searchList;
    Context context;
    private LayoutInflater mInflater;
    PopupMenu popup;

    public MyAdv_Adapter(Context activity, List<Datum> List) {
        searchList = List;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_productname,tv_categoryid,tv_description,tv_price,tv_date;
        AppCompatImageView iv_menu;
        RoundedImageView iv_product;
        public MyViewHolder(View view) {
            super(view);
            tv_productname = view.findViewById(R.id.tv_productname);
            iv_menu = view.findViewById(R.id.iv_menu);
            tv_categoryid = view.findViewById(R.id.tv_categoryid);
            tv_description = view.findViewById(R.id.tv_description_Id);
            tv_date = view.findViewById(R.id.tv_date);
            iv_product = view.findViewById(R.id.iv_product);
            tv_price = view.findViewById(R.id.tv_price);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_adv_row, parent, false);
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
        holder.iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup  = new PopupMenu(context, v);
                popup.setGravity(Gravity.END);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_item:
                                return true;
                            case R.id.delete_item:
                                return true;
                            case R.id.promote_item:
                                return true;
                            case R.id.report_item:
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}