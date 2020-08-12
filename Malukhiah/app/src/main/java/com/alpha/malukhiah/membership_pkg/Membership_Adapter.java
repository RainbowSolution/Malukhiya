package com.alpha.malukhiah.membership_pkg;

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
import com.alpha.malukhiah.model.memberShipPkg.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Membership_Adapter extends RecyclerView.Adapter<Membership_Adapter.MyViewHolder> {
    List<Datum> datumList;
    Context context;
    private LayoutInflater mInflater;
    private AppCompatImageView id_userpic;

    public Membership_Adapter(Context activity) {
        context = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_MemberShipTitle, tvExp;
        AppCompatImageView id_userpic, ivMemberShipSmallIconId;

        public MyViewHolder(View view) {
            super(view);
            tvExp = view.findViewById(R.id.tvExpId);
            tv_MemberShipTitle = view.findViewById(R.id.tv_MemberShipTitleId);
            id_userpic = view.findViewById(R.id.id_userpic);
            ivMemberShipSmallIconId = view.findViewById(R.id.ivMemberShipSmallIconId);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.membership_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_MemberShipTitle.setText(datumList.get(position).getName());
        holder.tvExp.setText(datumList.get(position).getExp());
        Picasso.with(context)
                .load(RetrofitClient.MEMBERSHIP_URL + datumList.get(position).getImage())
                .resize(200, 200)
                .into(holder.id_userpic);

        Picasso.with(context)
                .load(RetrofitClient.MEMBERSHIP_URL + datumList.get(position).getImage())
                .resize(200, 200)
                .into(holder.ivMemberShipSmallIconId);
    }

    public void addMemberShipList(List<Datum> datumList) {
        this.datumList = datumList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datumList == null ? 0 : datumList.size();
    }
}