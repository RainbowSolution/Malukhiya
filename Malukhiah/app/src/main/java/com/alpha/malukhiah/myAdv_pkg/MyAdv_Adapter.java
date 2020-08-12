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
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.category_pkg.Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.subcategory_pkg.SubCategory_Activity;

import java.util.ArrayList;

public class MyAdv_Adapter extends RecyclerView.Adapter<MyAdv_Adapter.MyViewHolder>{
    public ArrayList<HistoryData> searchList;
    Context context;
    private LayoutInflater mInflater;
    PopupMenu popup;
    public MyAdv_Adapter(Context activity, ArrayList<HistoryData> List) {
        searchList = List;
        context = activity;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_productname;
        AppCompatImageView  iv_add_address;
        public MyViewHolder(View view) {
            super(view);
            tv_productname = view.findViewById(R.id.tv_productname);
            iv_add_address = view.findViewById(R.id.iv_add_address);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_adv_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final HistoryData detail = searchList.get(position);
        /*holder.tv_productname.setText(detail.getOrdername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SubCategory_Activity.class));
            }
        });*/
        holder.iv_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity mainAct = (Activity) context;
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
                                Toast.makeText(context, "asas", Toast.LENGTH_SHORT).show();
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