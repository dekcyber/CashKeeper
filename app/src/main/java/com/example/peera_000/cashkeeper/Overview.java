package com.example.peera_000.cashkeeper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peera_000.cashkeeper.Adapter.RowDataAdp;
import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.Rowdata.RowData;

import java.util.ArrayList;
import java.util.List;


public class Overview extends Fragment {

    //Explicit
     private RecyclerView RvRowdata;
     private RowDataAdp RowDataAdp;
     private CK_TABLE objCK_TABLE;
     private FloatingActionButton  fabAd;



    public static Overview newInstance(){
            Overview TabFrag1 = new Overview();

            return TabFrag1;

        }
    public Overview(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.overview, container, false);
        fabAd = (FloatingActionButton) v.findViewById(R.id.fabAd);
        fabAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objintent = new Intent(getActivity(),AdMoney.class);
                startActivity(objintent);
            }
        });
        //int Draw = getResources().getIdentifier("bell","drawable",getContext().getPackageName());
        List<RowData> lRowdata = new ArrayList<>();
        objCK_TABLE = new CK_TABLE(getActivity());
        Cursor CurData = objCK_TABLE.readAllData();
        CurData.moveToPosition(-1);
        while (CurData.moveToNext()){
           int MoneyIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Income);
            String strMoney = CurData.getString(MoneyIndex);

            int CateIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Cate);
            String strCate = CurData.getString(CateIndex);

            int NoteIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Note);
            String strNote = CurData.getString(NoteIndex);
            int DateIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_InputDate);
            String strDate = CurData.getString(DateIndex);

            int PhotoIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Photo);
            String strPhoto = CurData.getString(PhotoIndex);
            int Photo = Integer.valueOf(strPhoto);
            //Bitmap bitmapPhoto  = BitmapFactory.decodeByteArray(bytePhoto,0,bytePhoto.length);
            lRowdata.add(new RowData(Photo, strMoney, strDate, strCate, strNote));
        }
        RowDataAdp = new RowDataAdp(lRowdata,getContext());
        RvRowdata = (RecyclerView) v.findViewById(R.id.RvRowdata);
        RvRowdata.setLayoutManager(new LinearLayoutManager(getContext()));
        RvRowdata.setHasFixedSize(true);
        RvRowdata.setAdapter(RowDataAdp);
        return v;
    }


}
