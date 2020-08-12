package com.alpha.malukhiah.category_pkg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.categoryPozoPkg.Datum;
import com.alpha.malukhiah.subcategory_pkg.SubCategory_Activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.MyViewHolder> {
        public List<Datum> searchList;
        Context context;
        private LayoutInflater mInflater;

        public Category_Adapter(Context activity, List<Datum> List) {
            searchList = List;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            AppCompatTextView tv_productname;
            AppCompatImageView id_categorypic;

            public MyViewHolder(View view) {
                super(view);
                tv_productname = view.findViewById(R.id.tv_productname);
                id_categorypic = view.findViewById(R.id.id_categorypic);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_cat_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            final Datum detail = searchList.get(position);
            holder.tv_productname.setText(detail.getTitle());
            if (searchList.get(position).getImage().isEmpty()) {
            } else {
                Picasso.with(context)
                        .load(RetrofitClient.CATEGORY_IMAGE_URL + searchList.get(position).getImage())
                        .resize(200, 200)
                        .into(holder.id_categorypic);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,SubCategory_Activity.class);
                    intent.putExtra("category_id",searchList.get(position).getId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return searchList.size();
        }
    }