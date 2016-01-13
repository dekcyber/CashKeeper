package com.example.peera_000.cashkeeper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peera_000.cashkeeper.Adapter.Outcome_Adapter;
import com.example.peera_000.cashkeeper.Rowdata.Outcome_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class Outcome extends Fragment {
    //Explicit
    RecyclerView rvOutcome;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.outcome,container,false);
        Outcome_Adapter adpOutcome = new Outcome_Adapter(AddListOutcome(),getContext());
        rvOutcome = (RecyclerView) v.findViewById(R.id.outcome_rv);
        rvOutcome.setHasFixedSize(true);
        rvOutcome.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvOutcome.setAdapter(adpOutcome);

        return v;



    }

    public List<Outcome_data> AddListOutcome(){
        List<Outcome_data> OutcomeList = new ArrayList<>();
        String[] OutcomeName ={"Bill","Car","Entertainment","Food","Love","Shopping","Transport","Travel"};
        int[] OutcomePic = {R.drawable.category_bill,R.drawable.category_car,R.drawable.category_entertainment
                ,R.drawable.category_food,R.drawable.category_love,R.drawable.category_shopping
                ,R.drawable.category_transport,R.drawable.category_airplane};
        for (int i=0;i<OutcomeName.length;i++){
            Outcome_data out_data = new Outcome_data(OutcomePic[i],OutcomeName[i]);
            OutcomeList.add(out_data);
        }

        return OutcomeList;
    }
}
