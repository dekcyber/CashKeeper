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
import com.example.peera_000.cashkeeper.R;
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

        View v = inflater.inflate(R.layout.outcome, container, false);
        List<Outcome_data> OutcomeList = new ArrayList<>();
        outcomeTable = new OUTCOME_TABLE(getActivity());
        Cursor objCursor = outcomeTable.readAllDataOutcome();
        objCursor.moveToPosition(-1);
        while (objCursor.moveToNext()) {
            int NameIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NAME);
            String strName = objCursor.getString(NameIndex);
            int NameIdIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NameID);
            String strNameId = objCursor.getString(NameIdIndex);
            String ValueNameId = getString(Integer.parseInt(strNameId));
            Log.d("NameID", "=" + strNameId);
            int PhotoIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Photo);
            String strPhoto = objCursor.getString(PhotoIndex);

            Log.d("PhotoID", "=" + strPhoto);
            int IntPhoto = Integer.parseInt(strPhoto);
            OutcomeList.add(new Outcome_data(IntPhoto, ValueNameId, strPhoto, strNameId));
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
            public String strPhotoImg;
            public String strNameid;

            @Override
            public void onItemClick(View view, int position, String Name, String IdPic, String NameId) {
                this.intPositionOutcome = position;
                this.strNameOutcome = Name;
                this.strPhotoImg = IdPic;
                this.strNameid = NameId;

                img = (ImageView) getActivity().findViewById(R.id.SelectImg);

                Log.d("Position", "is" + position);
                Log.d("Name", "is" + Name);
                Log.d("Photo", "is" + IdPic);
                Log.d("NameId", "is" + NameId);
                intPhoto = Integer.parseInt(IdPic);
                img.setImageResource(intPhoto);

                SharedPreferences sp = getActivity().getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                SharedPreferences outsp = getActivity().getSharedPreferences("OutcomeData", Context.MODE_PRIVATE);
                SharedPreferences.Editor outcomeeditor = outsp.edit();

                outcomeeditor.putInt("OutcomePosition", position);
                outcomeeditor.putString("OutcomeName", Name);
                editor.putInt("IncomePhoto", intPhoto);
                outcomeeditor.putString("OutcomeNameId", NameId);
                outcomeeditor.commit();
                editor.commit();

                Log.d("IncomeItemClick", "Item:" + position);

            }
        });
        return v;


    }

    @Override
    public void onResume() {

        super.onResume();
    }
}
