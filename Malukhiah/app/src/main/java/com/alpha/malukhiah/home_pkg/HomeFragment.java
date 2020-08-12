package com.alpha.malukhiah.home_pkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.model.HistoryData;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    View v;
    public ArrayList<HistoryData> catList;
    public ArrayList<HistoryData> bannerList;
    RecyclerView cat_rv,rv_banner;
    private ViewPager vpHomeBanner;
    private TabLayout tlHomeBanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.home_fragment_rv, container, false);
        cat_rv = v.findViewById(R.id.id_rv_category);
        rv_banner = v.findViewById(R.id.rv_banner);

        vpHomeBanner = v.findViewById(R.id.vpHomeBannerId);
        tlHomeBanner = v.findViewById(R.id.tlHomeBannerId);

        bannerList = new ArrayList<>();
        catList = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        cat_rv.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rv_banner.setLayoutManager(linearLayoutManager);

        getWeekendList();
        getBannerList();

        return v;
    }


    public void getBannerList(){
        for(int i=0; i<5; i++){
            bannerList.add(new HistoryData("Food"));
        }
        BannerAdapter adapter = new BannerAdapter(getActivity(), bannerList);
        rv_banner.setAdapter(adapter);
    }


    public void getWeekendList(){
        for(int i=0; i<4; i++){
            catList.add(new HistoryData("Food"));
        }
        Home_Category_Adapter adapter = new Home_Category_Adapter(getActivity(), catList);
        cat_rv.setAdapter(adapter);
    }

}

