package com.example.peera_000.cashkeeper.EditRowOverview;

import android.content.Context;
import android.content.Intent;
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
import com.example.peera_000.cashkeeper.MainCode.Add_category_Outcome;
import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.Outcome_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class EditOutcome extends Fragment {
    //Explicit
    private RecyclerView rvOutcome;
    private OUTCOME_TABLE outcomeTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.outcome, container, false);

        Outcome_Adapter adpOutcome = new Outcome_Adapter(OutcomeData(), getContext());
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

                if (Name.equals(getResources().getString(R.string.Add))) {
                    img.setImageResource(0);
                    Intent intent = new Intent(getActivity(), Add_category_Outcome.class);
                    startActivity(intent);
                }
                SharedPreferences Edsp = getActivity().getSharedPreferences("EditRowOverData",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit_Edsp = Edsp.edit();

                edit_Edsp.putInt("EditOutcomePosition", position);
                edit_Edsp.putString("EditIncomeName", Name);
                edit_Edsp.putInt("EditIncomePhoto", intPhoto);
                edit_Edsp.putString("EditIncomeNameId", NameId);
                edit_Edsp.commit();

                Log.d("OutcomeItemClick", "Item:" + position);

            }
        });
        return v;
    }//OnCreate
    public List<Outcome_data> OutcomeData(){
        List<Outcome_data> OutcomeList = new ArrayList<>();
        outcomeTable = new OUTCOME_TABLE(getActivity());
        Cursor objCursor = outcomeTable.readAllDataOutcomeCate();
        objCursor.moveToPosition(-1);
        while (objCursor.moveToNext()) {

            int StatusIndex =  objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Status);
            String strStatus = objCursor.getString(StatusIndex);

            if (strStatus.equals("N")){
                int NameIdIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NameID);
                String strNameId = objCursor.getString(NameIdIndex);
                int NameIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NAME);
                String strName = objCursor.getString(NameIndex);
                int PhotoIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Photo);
                String strPhoto = objCursor.getString(PhotoIndex);
                int IntPhoto = Integer.parseInt(strPhoto);
                OutcomeList.add(new Outcome_data(IntPhoto, strName, strPhoto, strNameId));
                Log.d("NameID", "=" + strNameId);
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("ReadDataNew", "OUTCOME SUCCESS");
            }else {
                int NameIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NAME);
                String strName = objCursor.getString(NameIndex);
                int NameIdIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NameID);
                String strNameId = objCursor.getString(NameIdIndex);
                String ValueNameId = getString(Integer.parseInt(strNameId));
                int PhotoIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Photo);
                String strPhoto = objCursor.getString(PhotoIndex);
                int IntPhoto = Integer.parseInt(strPhoto);
                OutcomeList.add(new Outcome_data(IntPhoto, ValueNameId, strPhoto, strNameId));
                Log.d("NameID", "=" + strNameId);
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("ReadData", "OUTCOME SUCCESS");
            }

        }
        Cursor Cursor2 = outcomeTable.readAllDataOutcome();
        int NameIndex = Cursor2.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NAME);
        String strName = Cursor2.getString(NameIndex);
        int NameIdIndex = Cursor2.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NameID);
        String strNameId = Cursor2.getString(NameIdIndex);
        String ValueNameId = getString(Integer.parseInt(strNameId));
        int PhotoIndex = Cursor2.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Photo);
        String strPhoto = Cursor2.getString(PhotoIndex);
        int IntPhoto = Integer.parseInt(strPhoto);
        OutcomeList.add(new Outcome_data(IntPhoto, ValueNameId, strPhoto, strNameId));


        return OutcomeList;
    }//OutcomeDataList

    @Override
    public void onResume() {

        super.onResume();
    }
}
