package com.alpha.malukhiah.category_pkg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.home_pkg.Home_Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;

import java.util.ArrayList;

public class Category_Activity extends AppCompatActivity {
    public ArrayList<HistoryData> catList;
    RecyclerView cat_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_rv);

        cat_rv = findViewById(R.id.id_rv_category);
        catList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        cat_rv.setLayoutManager(linearLayoutManager);

        getWeekendList();
    }
    public void getWeekendList(){
        for(int i=0; i<4; i++){
            catList.add(new HistoryData("Food"));
        }
        Category_Adapter adapter = new Category_Adapter(this, catList);
        cat_rv.setAdapter(adapter);
    }
}
