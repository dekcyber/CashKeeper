package com.example.peera_000.cashkeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.peera_000.cashkeeper.Adapter.Outcome_Adapter;
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
        while (objCursor.moveToNext()) {
            int NameIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NAME);
            String strName = objCursor.getString(NameIndex);
            int PhotoIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Photo);
            String strPhoto = objCursor.getString(PhotoIndex);
            int IntPhoto = Integer.parseInt(strPhoto);
            OutcomeList.add(new Outcome_data(IntPhoto, strName));
            Log.d("ReadData", "OUTCOME SUCCESS");
        }
        Outcome_Adapter adpOutcome = new Outcome_Adapter(OutcomeList, getContext());
        rvOutcome = (RecyclerView) v.findViewById(R.id.outcome_rv);
        rvOutcome.setHasFixedSize(true);
        rvOutcome.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvOutcome.setAdapter(adpOutcome);
        adpOutcome.SetOnClickListener(new Outcome_Adapter.OnItemClickListener() {
            //Explicit to getValue
            public String strNameOutcome;
            public int intPositionOutcome;
            public ImageView img;
            public int intPhoto;

            @Override
            public void onItemClick(View view, int position, String Name) {
                this.intPositionOutcome = position;
                this.strNameOutcome = Name;
                img = (ImageView) getActivity().findViewById(R.id.SelectImg);
                switch (Name) {
                    case "Bill":
                        intPhoto = getResources().getIdentifier("category_bill", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;
                    case "Car":
                        intPhoto = getResources().getIdentifier("category_car", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;
                    case "Entertainment":
                        intPhoto = getResources().getIdentifier("category_entertainment", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;
                    case "Food":
                        intPhoto = getResources().getIdentifier("category_food", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;
                    case "Love":
                        intPhoto = getResources().getIdentifier("category_love", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;
                    case "Shopping":
                        intPhoto = getResources().getIdentifier("category_shopping", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;
                    case "Transport":
                        intPhoto = getResources().getIdentifier("category_transport", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;
                    case "Travel":
                        intPhoto = getResources().getIdentifier("category_travel", "drawable", getActivity().getPackageName());
                        img.setImageResource(intPhoto);
                        break;

                }
                SharedPreferences outsp = getActivity().getSharedPreferences("OutcomeData", Context.MODE_PRIVATE);
                SharedPreferences.Editor outcomeeditor = outsp.edit();

                outcomeeditor.putInt("OutcomePosition", position);
                outcomeeditor.putString("OutcomeName", Name);
                outcomeeditor.putInt("OutcomePhoto", intPhoto);
                outcomeeditor.commit();

                Log.d("IncomeItemClick", "Item:" + position);

            }
        });
        return v;



    }
}
