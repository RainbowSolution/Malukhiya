package com.alpha.malukhiah.chat_pkg;

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

import java.util.ArrayList;

public class Main_Chat_Adapter extends RecyclerView.Adapter<Main_Chat_Adapter.MyViewHolder> {
        public ArrayList<HistoryData> searchList;
        Context context;
        private LayoutInflater mInflater;

        public Main_Chat_Adapter(Context activity, ArrayList<HistoryData> List) {
            searchList = List;
            context = activity;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            AppCompatTextView tv_productname;

            public MyViewHolder(View view) {
                super(view);

            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_row, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            final HistoryData detail = searchList.get(position);

        }

        @Override
        public int getItemCount() {
            return searchList.size();
        }
    }