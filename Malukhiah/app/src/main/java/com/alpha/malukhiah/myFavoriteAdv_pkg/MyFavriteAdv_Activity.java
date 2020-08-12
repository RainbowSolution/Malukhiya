package com.alpha.malukhiah.myFavoriteAdv_pkg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.myAdv_pkg.MyAdv_Adapter;

import java.util.ArrayList;

public class MyFavriteAdv_Activity extends AppCompatActivity {
    public ArrayList<HistoryData> catList;
    RecyclerView myfavorideAdv_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfavorite_adv);
        myfavorideAdv_rv = findViewById(R.id.id_rv_myfavorideAdv);
        catList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        myfavorideAdv_rv.setLayoutManager(linearLayoutManager);
        getWeekendList();
    }
    public void getWeekendList(){
        for(int i=0; i<4; i++){
            catList.add(new HistoryData("Food"));
        }
        MyAdv_Adapter adapter = new MyAdv_Adapter(this, catList);
        myfavorideAdv_rv.setAdapter(adapter);
    }
}
