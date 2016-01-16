package com.example.peera_000.cashkeeper;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peera_000.cashkeeper.Adapter.Outcome_Adapter;
import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.Database.INCOME_TABLE;
import com.example.peera_000.cashkeeper.Database.OUTCOME_TABLE;
import com.example.peera_000.cashkeeper.Rowdata.Outcome_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class Outcome extends Fragment {
    //Explicit
    private RecyclerView rvOutcome;
    private OUTCOME_TABLE outcomeTable;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.outcome,container,false);
        List<Outcome_data> OutcomeList = new ArrayList<>();
        outcomeTable = new OUTCOME_TABLE(getActivity());
        Cursor objCursor = outcomeTable.readAllDataOutcome();
        objCursor.moveToPosition(-1);
        Outcome_Adapter adpOutcome = new Outcome_Adapter(OutcomeList, getContext());
        rvOutcome = (RecyclerView) v.findViewById(R.id.outcome_rv);
        rvOutcome.setHasFixedSize(true);
        rvOutcome.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvOutcome.setAdapter(adpOutcome);
        return v;



    }
}
