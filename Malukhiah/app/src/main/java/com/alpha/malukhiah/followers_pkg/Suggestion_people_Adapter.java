package com.alpha.malukhiah.followers_pkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.model.HistoryData;

import java.util.ArrayList;

public class Suggestion_people_Adapter extends RecyclerView.Adapter<Suggestion_people_Adapter.MyViewHolder> {
    public ArrayList<HistoryData> searchList;
    Context context;
    private LayoutInflater mInflater;

    public Suggestion_people_Adapter(Context activity, ArrayList<HistoryData> List) {
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_ppl_row, parent, false);
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

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}