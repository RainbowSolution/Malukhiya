package com.alpha.malukhiah.subcategory_pkg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.home_pkg.Home_Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;

import java.util.ArrayList;

public class SubCategory_Activity extends AppCompatActivity {
    public ArrayList<HistoryData> catList;
    RecyclerView cat_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_rv);
        cat_rv = findViewById(R.id.id_rv_category);
        catList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        cat_rv.setLayoutManager(gridLayoutManager);
        getWeekendList();
    }
    public void getWeekendList(){
        for(int i=0; i<7; i++){
            catList.add(new HistoryData("Sub cat of Food"));
        }
        SubCategory_Adapter adapter = new SubCategory_Adapter(this, catList);
        cat_rv.setAdapter(adapter);
    }
}
