package com.alpha.malukhiah.recently_search_pkg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.MyRecentViewPkg.Datum;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecentlySearch_Adapter extends RecyclerView.Adapter<RecentlySearch_Adapter.MyViewHolder> implements Filterable {
    public List<Datum> datum_List12;
    public List<Datum> datum_List;
    Context context;
    private LayoutInflater mInflater;
    public RecentlySearch_Adapter(Context activity, List<Datum> datum_List12) {
        this.datum_List = datum_List12;
        this.datum_List12 = datum_List12;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tv_productname,tv_categoryid,tv_description,tv_price,tv_call;
        RoundedImageView iv_product;
        public MyViewHolder(View view) {
            super(view);
            tv_productname = view.findViewById(R.id.tv_productname);
            tv_categoryid = view.findViewById(R.id.tv_categoryid);
            tv_description = view.findViewById(R.id.tv_description);
            iv_product = view.findViewById(R.id.iv_product);
            tv_price = view.findViewById(R.id.tv_price);
            tv_call = view.findViewById(R.id.tv_call);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_search_adv_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_productname.setText(datum_List.get(position).getTitle());
        holder.tv_description.setText(datum_List.get(position).getDescription());
        holder.tv_categoryid.setText(datum_List.get(position).getCat_name());
        holder.tv_price.setText("$"+datum_List.get(position).getPrice());
        if (datum_List.get(position).getImages().isEmpty()) {
        } else {
            Picasso.with(context)
                    .load(RetrofitClient.PRODUCT_IMAGE_URL + datum_List.get(position).getImages())
                    .resize(200, 200)
                    .into(holder.iv_product);
        }
        holder.tv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = ("tel:" + datum_List.get(position).getUser_phone());
                Intent mIntent = new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse(number));
                context.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datum_List == null ? 0 : datum_List.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datum_List = datum_List12;
                } else {
                    List<Datum> filteredList = new ArrayList<>();
                    for (Datum row : datum_List12) {
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) || row.getLocation().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    datum_List = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datum_List;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datum_List = (ArrayList<Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}