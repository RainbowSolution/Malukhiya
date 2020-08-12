package com.alpha.malukhiah.notification_pkg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.NotificationListPkg.Datum;
import com.alpha.malukhiah.subcategory_pkg.SubCategory_Activity;

import java.util.ArrayList;
import java.util.List;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.MyViewHolder> {
        public List<Datum> searchList;
        Context context;
        private LayoutInflater mInflater;

        public Notification_Adapter(Context activity, List<Datum> List) {
            searchList = List;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            AppCompatTextView tv_productname,des_id,tv_time;

            public MyViewHolder(View view) {
                super(view);
                tv_productname = view.findViewById(R.id.id_username);
                des_id = view.findViewById(R.id.des_id);
                tv_time = view.findViewById(R.id.tv_time);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final Datum detail = searchList.get(position);
            holder.tv_productname.setText(detail.getUserFullname());
            holder.des_id.setText(detail.getMessage());
            holder.tv_time.setText(detail.getDate());

        }

        @Override
        public int getItemCount() {
            return searchList.size();
        }
    }