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

import com.example.peera_000.cashkeeper.Adapter.Income_Adapter;
import com.example.peera_000.cashkeeper.Database.INCOME_TABLE;
import com.example.peera_000.cashkeeper.MainCode.Add_category_Income;
import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.Income_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class EditIncome extends Fragment {
    //Explicit
    private RecyclerView rvIncome;
    private INCOME_TABLE incomeTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.income, container, false);


        Income_Adapter income_adapter = new Income_Adapter(ListIncomeData(), getContext());
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

               SharedPreferences Edsp = getActivity().getSharedPreferences("EditRowOverData",Context.MODE_PRIVATE);
               SharedPreferences.Editor edit_Edsp = Edsp.edit();

                edit_Edsp.putInt("EditIncomePosition", position);
                edit_Edsp.putString("EditIncomeName", Name);
                Log.d("Edit", "CateName = " + Name);
                edit_Edsp.putInt("EditIncomePhoto", intPhoto);
                edit_Edsp.putString("EditIncomeNameId", Nameid);
                Log.d("Edit", "CateNameID = " + Nameid);
                edit_Edsp.commit();

                if (Name.equals(getResources().getString(R.string.Add))){
                    img.setImageResource(0);
                    Intent intent = new Intent(getActivity(), Add_category_Income.class);
                    startActivity(intent);
                }

                //Log.d("IncomeItemClick", "Item:" + intPhoto);

            }
        });
        return v;
    }//OnCreate
    public List<Income_data> ListIncomeData(){
        List<Income_data> income_datas = new ArrayList<>();
        incomeTable = new INCOME_TABLE(getContext());
        Cursor objCursor = incomeTable.readAllDataCateIncome();
        objCursor.moveToPosition(-1);
        while (objCursor.moveToNext()) {
            int StatusIndex =  objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_Status);
            String strStatus = objCursor.getString(StatusIndex);

            if (strStatus.equals("N")){
                int NameIdIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NameID);
                String strNameId = objCursor.getString(NameIdIndex);
                int NameIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NAME);
                String strName = objCursor.getString(NameIndex);
                int PhotoIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_Photo);
                String strPhoto = objCursor.getString(PhotoIndex);
                int IntPhoto = Integer.parseInt(strPhoto);
                income_datas.add(new Income_data(IntPhoto, strName, strPhoto, strNameId));
                Log.d("NameID", "=" + strNameId);
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("ReadDataNew", "INCOME SUCCESS");
            }else {
                int NameIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NAME);
                String strName = objCursor.getString(NameIndex);
                int NameIdIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NameID);
                String strNameId = objCursor.getString(NameIdIndex);
                String ValueNameId = getString(Integer.parseInt(strNameId));
                int PhotoIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_Photo);
                String strPhoto = objCursor.getString(PhotoIndex);
                int IntPhoto = Integer.parseInt(strPhoto);
                income_datas.add(new Income_data(IntPhoto, ValueNameId, strPhoto, strNameId));
                Log.d("NameID", "=" + strNameId);
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("ReadData", "INCOME SUCCESS");
            }

        }
        Cursor Cursor2 = incomeTable.readAllDataIncome();
        int NameIndex = Cursor2.getColumnIndex(INCOME_TABLE.COLUMNIN_NAME);
        String strName = Cursor2.getString(NameIndex);
        int NameIdIndex = Cursor2.getColumnIndex(INCOME_TABLE.COLUMNIN_NameID);
        String strNameId = Cursor2.getString(NameIdIndex);
        String ValueNameId = getString(Integer.parseInt(strNameId));
        int PhotoIndex = Cursor2.getColumnIndex(INCOME_TABLE.COLUMNIN_Photo);
        String strPhoto = Cursor2.getString(PhotoIndex);
        int IntPhoto = Integer.parseInt(strPhoto);
        income_datas.add(new Income_data(IntPhoto, ValueNameId, strPhoto, strNameId));

        return income_datas;
    }

}//MainClass
