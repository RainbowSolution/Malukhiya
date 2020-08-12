package com.alpha.malukhiah.followers_pkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.FollowerPkg.Datum;
import com.alpha.malukhiah.model.HistoryData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Followers_Adapter extends RecyclerView.Adapter<Followers_Adapter.MyViewHolder> {
    public List<Datum> searchList;
    Context context;
    private LayoutInflater mInflater;

    public Followers_Adapter(Context activity, List<Datum> List) {
        searchList = List;
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_productname;
        CircleImageView civDoctListId;
        public MyViewHolder(View view) {
            super(view);
            tv_productname = view.findViewById(R.id.tv_productname);
            civDoctListId = view.findViewById(R.id.civDoctListId);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Datum detail = searchList.get(position);
        /*holder.tv_productname.setText(detail.getOrdername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SubCategory_Activity.class));
            }
        });*/
        holder.tv_productname.setText(searchList.get(position).getUserFullname());
        if (searchList.get(position).getUserImage().isEmpty()) {
        } else {
            Picasso.with(context)
                    .load(RetrofitClient.PROFILE + searchList.get(position).getUserImage())
                    .resize(200, 200)
                    .into(holder.civDoctListId);
        }
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}