package com.alpha.malukhiah.home_pkg;

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
import com.alpha.malukhiah.category_pkg.Category_Activity;
import com.alpha.malukhiah.model.categoryPozoPkg.Datum;
import com.alpha.malukhiah.post_pkg.AdapterPkgModel.SubCategoryAdapter;
import com.alpha.malukhiah.subcategory_pkg.SubCategory_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_Category_Adapter extends RecyclerView.Adapter<Home_Category_Adapter.MyViewHolder> {
    List<Datum> datumList;
    Context context;
    private LayoutInflater mInflater;

    public Home_Category_Adapter(Context activity) {
        //this.datumList = datumList;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Datum detail = datumList.get(position);
        holder.tv_productname.setText(detail.getTitle());

        Picasso.with(context)
                .load(RetrofitClient.CATEGORY_IMAGE_URL + datumList.get(position).getImage())
                .resize(200, 200)
                .into(holder.id_categorypic);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Category_Activity.class));
            }
        });

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