package com.alpha.malukhiah.category_pkg;

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
import com.alpha.malukhiah.subcategory_pkg.SubCategory_Activity;

import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.MyViewHolder> {
        public ArrayList<HistoryData> searchList;
        Context context;
        private LayoutInflater mInflater;

        public Category_Adapter(Context activity, ArrayList<HistoryData> List) {
            searchList = List;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            AppCompatTextView tv_productname;

            public MyViewHolder(View view) {
                super(view);
                tv_productname = view.findViewById(R.id.tv_productname);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_cat_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final HistoryData detail = searchList.get(position);

            holder.tv_productname.setText(detail.getOrdername());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, SubCategory_Activity.class));
                }
            });

        }

        @Override
        public int getItemCount() {
            return searchList.size();
        }
    }