package com.alpha.malukhiah.home_pkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alpha.malukhiah.R;
import com.alpha.malukhiah.apiPkg.RetrofitClient;
import com.alpha.malukhiah.model.HistoryData;
import com.alpha.malukhiah.model.bannerPozoPkg.BannerListPozo;
import com.alpha.malukhiah.model.categoryPozoPkg.CategoryPozo;
import com.alpha.malukhiah.utility.CheckNetwork;
import com.alpha.malukhiah.utility.CustomProgressbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    View v;
    public ArrayList<HistoryData> catList;
    public ArrayList<HistoryData> bannerList;
    RecyclerView cat_rv, rv_banner;
    private ViewPager vpHomeBanner;
    private TabLayout tlHomeBanner;
    Home_Category_Adapter adapter;
    BannerAdapter bannerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.home_fragment_rv, container, false);
        cat_rv = v.findViewById(R.id.id_rv_category);
        rv_banner = v.findViewById(R.id.rv_banner);
        vpHomeBanner = v.findViewById(R.id.vpHomeBannerId);
        tlHomeBanner = v.findViewById(R.id.tlHomeBannerId);
        bannerList = new ArrayList<>();
        catList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        cat_rv.setLayoutManager(gridLayoutManager);
        adapter = new Home_Category_Adapter(getActivity());
        cat_rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rv_banner.setLayoutManager(linearLayoutManager);
        bannerAdapter = new BannerAdapter(getActivity());
        rv_banner.setAdapter(bannerAdapter);
        if (CheckNetwork.isNetAvailable(getActivity())) {
            getbanner();
            getCategory();
        } else {

        }

        return v;
    }


    public void getCategory() {
        CustomProgressbar.showProgressBar(getActivity(), false);
        (RetrofitClient.getClient().getCategory()).enqueue(new Callback<CategoryPozo>() {
            @Override
            public void onResponse(Call<CategoryPozo> call, Response<CategoryPozo> response) {
                if (response.isSuccessful()) {
                    try {
                        CustomProgressbar.hideProgressBar();
                        CategoryPozo categoryPozo = response.body();
                        if (categoryPozo.getStatus()) {
                            adapter.addCategoryList(categoryPozo.getData());
                        } else {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<CategoryPozo> call, Throwable t) {
                CustomProgressbar.hideProgressBar();
                Toast.makeText(getActivity(), getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getbanner() {
        (RetrofitClient.getClient().getBanner()).enqueue(new Callback<BannerListPozo>() {
            @Override
            public void onResponse(Call<BannerListPozo> call, Response<BannerListPozo> response) {
                if (response.isSuccessful()) {
                    try {

                        BannerListPozo bannerListPozo = response.body();
                        if (bannerListPozo.getStatus()) {
                            bannerAdapter.addCategoryList(bannerListPozo.getData());
                        } else {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<BannerListPozo> call, Throwable t) {

                Toast.makeText(getActivity(), getString(R.string.server_errors), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

