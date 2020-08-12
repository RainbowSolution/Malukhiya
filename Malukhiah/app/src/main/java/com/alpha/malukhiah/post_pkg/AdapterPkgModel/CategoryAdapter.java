package com.alpha.malukhiah.post_pkg.AdapterPkgModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.model.categoryPozoPkg.Datum;


import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    List<Datum> sectorInfos;
    LayoutInflater inflter;
    String comesFrom;

    public CategoryAdapter(Context applicationContext, List<Datum> sectorList, String come) {
        this.context = applicationContext;
        this.sectorInfos = sectorList;
        this.comesFrom = come;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return sectorInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.dropdown_spinner_item, null);
        TextView names = (TextView) view.findViewById(R.id.tv_spinner_text);
        if(comesFrom.equals("title")){
            names.setText(sectorInfos.get(i).getTitle());
        }
        else {
            names.setText(sectorInfos.get(i).getTitle());
        }
        return view;
    }
}