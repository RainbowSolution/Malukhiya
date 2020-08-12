package com.alpha.malukhiah.myAdv_pkg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.category_pkg.Category_Adapter;
import com.alpha.malukhiah.model.HistoryData;

import java.util.ArrayList;

public class MyAdv_Activity extends AppCompatActivity {

    public ArrayList<HistoryData> catList;
    RecyclerView myadv_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_adv);
        myadv_rv = findViewById(R.id.id_rv_myAdv);
        catList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        myadv_rv.setLayoutManager(linearLayoutManager);
        getWeekendList();
    }
    public void getWeekendList(){
        for(int i=0; i<4; i++){
            catList.add(new HistoryData("Food"));
        }
        MyAdv_Adapter adapter = new MyAdv_Adapter(this, catList);
        myadv_rv.setAdapter(adapter);
    }
}
