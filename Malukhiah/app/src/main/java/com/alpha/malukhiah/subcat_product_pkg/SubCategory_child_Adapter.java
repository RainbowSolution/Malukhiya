package com.alpha.malukhiah.subcat_product_pkg;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.subChildCategoryPozoPkg.SubChildDatum;
import com.alpha.malukhiah.model.subcategoryPozoPkg.SubDatum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCategory_child_Adapter extends RecyclerView.Adapter<SubCategory_child_Adapter.MyViewHolder> {
    List<SubChildDatum> searchList;
    Context context;
    Onclick onclick;
    private LayoutInflater mInflater;
    private int selectedPos = 0;

    public SubCategory_child_Adapter(Context activity, List<SubChildDatum> List, Onclick onclick) {
        searchList = List;
        context = activity;
        this.onclick = onclick;
    }

    public interface Onclick {
        void onEvent(View view, int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        AppCompatTextView tv_productname;
        RelativeLayout line_main;
        View viewas;

        public MyViewHolder(View view) {
            super(view);
            tv_productname = view.findViewById(R.id.tv_productname);
            viewas = view.findViewById(R.id.viewas);
            line_main = view.findViewById(R.id.line_main);
            line_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        notifyItemChanged(selectedPos);
                        selectedPos = getAdapterPosition();
                        onclick.onEvent(v, position);
                        notifyItemChanged(selectedPos);
                       // Toast.makeText(context, "adsa", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        public void changeToSelect(int colorBackground) {
            tv_productname.setTextColor(colorBackground);

        }

        public void changeToSelect1(int colorBackground) {
            viewas.setBackgroundColor(colorBackground);
        }

        @Override
        public void onClick(View v) {
            onclick.onEvent(v, getAdapterPosition());
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_cat_child_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final SubChildDatum detail = searchList.get(position);
        holder.tv_productname.setText(detail.getSubcatName());
        holder.changeToSelect(selectedPos == position ? context.getResources().getColor(R.color.colorPrimary) : Color.BLACK);
        holder.changeToSelect1(selectedPos == position ? context.getResources().getColor(R.color.colorPrimary) : Color.WHITE);

           /* if (position==0){
                holder.viewas.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                holder.tv_productname.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }*/

    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}