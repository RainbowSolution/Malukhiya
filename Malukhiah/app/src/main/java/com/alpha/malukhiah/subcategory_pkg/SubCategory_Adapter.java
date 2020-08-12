package com.alpha.malukhiah.subcategory_pkg;

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
import com.alpha.malukhiah.model.subcategoryPozoPkg.SubDatum;
import com.alpha.malukhiah.subcat_product_pkg.SubCat_Product_Activity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubCategory_Adapter extends RecyclerView.Adapter<SubCategory_Adapter.MyViewHolder> {
         List<SubDatum> searchList;
        Context context;
        private LayoutInflater mInflater;

        public SubCategory_Adapter(Context activity, List<SubDatum> List) {
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
            final SubDatum detail = searchList.get(position);
            holder.tv_productname.setText(detail.getSubcatName());
            if (searchList.get(position).getSubcatImg().isEmpty()) {
            } else {
                Picasso.with(context)
                        .load(RetrofitClient.SUB_CATEGORY_IMAGE_URL + searchList.get(position).getSubcatImg())
                        .resize(200, 200)
                        .into(holder.id_categorypic);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,SubCat_Product_Activity.class);
                    intent.putExtra("sub_category",searchList.get(position).getSubCatId());
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