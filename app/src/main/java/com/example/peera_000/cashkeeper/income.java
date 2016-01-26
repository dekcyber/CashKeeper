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

import com.example.peera_000.cashkeeper.Adapter.Income_Adapter;
import com.example.peera_000.cashkeeper.Database.INCOME_TABLE;
import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.Income_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class Income extends Fragment {
    //Explicit
    private RecyclerView rvIncome;
    private INCOME_TABLE incomeTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.income, container, false);

        List<Income_data> income_datas = new ArrayList<>();
        incomeTable = new INCOME_TABLE(getContext());
        Cursor objCursor = incomeTable.readAllDataIncome();
        objCursor.moveToPosition(-1);
        while (objCursor.moveToNext()) {
            int NameIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NAME);
            String strName = objCursor.getString(NameIndex);
            int NameIdIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NameID);
            String strNameid = objCursor.getString(NameIdIndex);
            String ValueNameid = getString(Integer.parseInt(strNameid));
            int PhotoIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_Photo);
            String strPhoto = objCursor.getString(PhotoIndex);
            int IntPhoto = Integer.parseInt(strPhoto);
            income_datas.add(new Income_data(IntPhoto, ValueNameid, strPhoto, strNameid));
            Log.d("ReadData", "INCOME SUCCESS");
        }
        Income_Adapter income_adapter = new Income_Adapter(income_datas, getContext());
        rvIncome = (RecyclerView) v.findViewById(R.id.income_rv);
        rvIncome.setHasFixedSize(true);
        rvIncome.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvIncome.setAdapter(income_adapter);
        income_adapter.SetOnClickListener(new Income_Adapter.OnItemClickListener() {
            //Explicit to getValue
            public String strNameIncome;
            public int intPositionIncome;
            public int intPhoto;
            public String strPhotoImg;
            public String strNameid;
            @Override
            public void onItemClick(View view, int position, String Name, String Photo, String Nameid) {
                this.strNameIncome = Name;
                this.intPositionIncome = position;
                this.strPhotoImg = Photo;
                this.strNameid = Nameid;

                ImageView img = (ImageView) getActivity().findViewById(R.id.SelectImg);
                Log.d("Position", "is" + position);
                Log.d("Name", "is" + Name);
                Log.d("Photo", "is" + Photo);
                Log.d("NameId", "is" + Nameid);
                intPhoto = Integer.parseInt(Photo);
                img.setImageResource(intPhoto);

                SharedPreferences sp = getActivity().getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("IncomePosition", position);
                editor.putString("IncomeName", Name);
                editor.putInt("IncomePhoto", intPhoto);
                editor.putString("IncomeNameId", Nameid);
                editor.commit();

                //Log.d("IncomeItemClick", "Item:" + intPhoto);

            }
        });
        return v;
    }//OnCreate


}//MainClass
