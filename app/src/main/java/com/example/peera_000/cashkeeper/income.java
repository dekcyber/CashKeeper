package com.example.peera_000.cashkeeper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.peera_000.cashkeeper.Adapter.Income_Adapter;
import com.example.peera_000.cashkeeper.Rowdata.income_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class Income extends Fragment {
    //Explicit
    RecyclerView rvIncome;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.income,container,false);
        Income_Adapter adpIncome = new Income_Adapter(AddlistIncome(),getContext());
        rvIncome = (RecyclerView) v.findViewById(R.id.income_rv);
        rvIncome.setHasFixedSize(true);
        rvIncome.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvIncome.setAdapter(adpIncome);

        return v;



    }

    public List<income_data> AddlistIncome(){
        List<income_data> incomelist = new ArrayList<>();
        String[] incomeName ={"Food","Travel","Bill","Car","Entertainment","Love","Shopping","Transport"};
        String[] incomePic = {"R.drawable.food","R.drawable.category_airplane","R.drawable.category_bill"
                ,"R.drawable.category_car","R.drawable.category_entertainment","R.drawable.category_love"
                ,"R.drawable.category_shopping","R.drawable.category_transport"};
        for (int i=0;i<incomeName.length;i++){
            income_data in_data = new income_data(incomePic[i],incomeName[i]);
            incomelist.add(in_data);
        }

        return incomelist;
    }
}
