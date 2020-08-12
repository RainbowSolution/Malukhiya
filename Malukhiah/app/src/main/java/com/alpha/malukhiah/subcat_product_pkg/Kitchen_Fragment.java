package com.alpha.malukhiah.subcat_product_pkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alpha.malukhiah.Constant;
import com.alpha.malukhiah.R;
import com.alpha.malukhiah.model.HistoryData;

import java.util.ArrayList;

public class Kitchen_Fragment extends Fragment {
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rvCurrentBooking;
    private Kitchen_Adapter currentBooking_adapter;
    private Constant constant;
    private ArrayList<HistoryData> datumList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_rv, null);
        constant = new Constant(getActivity());
        init(rootView);

        datumList = new ArrayList<>();
       // getWeekendList();
        return rootView;
    }

    private void init(View view) {
        rvCurrentBooking = view.findViewById(R.id.id_rv_product);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvCurrentBooking.setLayoutManager(gridLayoutManager);
    }



//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//        }
//    }


}

