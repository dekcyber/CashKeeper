package com.example.peera_000.cashkeeper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.RowDataAdp;
import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.Rowdata.RowData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Overview extends Fragment {

    //Explicit
    private RecyclerView RvRowdata;
    private RowDataAdp RowDataAdp;
    private CK_TABLE objCK_TABLE;
    private FloatingActionButton fabAd;
    private static final String DESC = "SELECT * FROM CK_TABLE ORDER BY DESC";


    public static Overview newInstance() {
        Overview TabFrag1 = new Overview();

        return TabFrag1;

    }

    public Overview() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.overview, container, false);
        fabAd = (FloatingActionButton) v.findViewById(R.id.fabAd);
        fabAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objintent = new Intent(getActivity(), AdMoney.class);
                startActivity(objintent);
            }
        });
        //int Draw = getResources().getIdentifier("bell","drawable",getContext().getPackageName());
        List<RowData> lRowdata = new ArrayList<>();
        objCK_TABLE = new CK_TABLE(getActivity());
        Cursor CurData = objCK_TABLE.readDESC();
        CurData.moveToPosition(-1);
       /* Calendar cal = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        String d = date.format(cal.getTime());
        String setDat ="2016/01/19";
        Log.d("Date", "="+d);*/
        ArrayList<String> A = new ArrayList<>();
        ArrayList<String> B = new ArrayList<>();
        ArrayList<Integer> arrIntPhoto = new ArrayList<>();
        ArrayList<String> arrStringCate = new ArrayList<>();
        ArrayList<String> arrStringIncome = new ArrayList<>();
        ArrayList<String> arrStringOutcome = new ArrayList<>();
        ArrayList<String> arrStringNote = new ArrayList<>();
        String Check = "";
        String Check1 = "";
        /*A.add("2016/01/24");
        A.add("2016/01/24");
        A.add("2016/01/25");
        A.add("2016/01/25");
        for (int i=0;i<A.size();i++) {
            if (Check.equals(A.get(i))){
                Check =A.get(i);
                Check1=" ";
                B.add(Check1);
                Log.d("ArrayList","DataOfArray"+A.get(i));
                Log.d("ArrayList","NewData="+Check);}
            else {
                Check = A.get(i);
                Check1 = A.get(i);
                B.add(Check1);
                Log.d("ArrayList","DataOfArray"+A.get(i));
                Log.d("ArrayList","NewData="+Check);
            }
        }
        Log.d("ArrayListB","="+B.size());
        for (int j=0;j<B.size();j++){
            Log.d("ArrayListB","Data="+B.get(j));
        }*/


        while (CurData.moveToNext()) {
            int MoneyIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Income);
            String strMoney = CurData.getString(MoneyIndex);

            int OutcomeIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Outcome);
            String strOutmoney = CurData.getString(OutcomeIndex);

            int CateIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_CateID);
            String strCate = CurData.getString(CateIndex);
            String ValueCate = getString(Integer.parseInt(strCate));

            int NoteIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Note);
            String strNote = CurData.getString(NoteIndex);

            int DateIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_InputDate);
            String strDate = CurData.getString(DateIndex);

            int PhotoIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_Photo);
            String strPhoto = CurData.getString(PhotoIndex);
            int Photo = Integer.valueOf(strPhoto);

            if (Check.equals(strDate)) {
                Check = strDate;
                Check1 = " ";
                lRowdata.add(new RowData(Photo, strMoney, Check1, ValueCate, strNote, strOutmoney));
            } else {
                Check = strDate;
                Check1 = strDate;
                lRowdata.add(new RowData(Photo, strMoney, Check1, ValueCate, strNote, strOutmoney));
            }
            //Bitmap bitmapPhoto  = BitmapFactory.decodeByteArray(bytePhoto,0,bytePhoto.length);
            //lRowdata.add(new RowData(Photo, strMoney, strDate, strCate, strNote, strOutmoney));

            /*A.add(strDate);
            arrIntPhoto.add(Photo);
            arrStringIncome.add(strMoney);
            arrStringOutcome.add(strOutmoney);
            arrStringCate.add(strCate);
            arrStringNote.add(strNote);*/

        }
        /*Log.d("ArrayListA","Size Of"+A.size());
        for (int i=0;i<A.size();i++){
            if (Check.equals(A.get(i))){
                Check = A.get(i);
                Check1=" ";
                B.add(Check1);
            }else {
                Check =A.get(i);
                Check1=A.get(i);
                B.add(Check1);
            }

        }
        for (int j=0;j<B.size();j++){
            lRowdata.add(new RowData(arrIntPhoto.get(j),arrStringIncome.get(j), B.get(j),arrStringCate.get(j)
                    ,arrStringNote.get(j),arrStringOutcome.get(j)));
            Log.d("ArrayListB", "Data = " + B.get(j));
        }*/

        RowDataAdp = new RowDataAdp(lRowdata, getContext());
        RvRowdata = (RecyclerView) v.findViewById(R.id.RvRowdata);
        RvRowdata.setLayoutManager(new LinearLayoutManager(getContext()));
        RvRowdata.setHasFixedSize(true);
        RvRowdata.setAdapter(RowDataAdp);
        RvRowdata.setOnScrollListener(onScrollListener);
/*
        int income1 = getResources().getIdentifier("Business","string",getActivity().getPackageName());
        int income2 = getResources().getIdentifier("Extra_income","string",getActivity().getPackageName());
        int income3 = getResources().getIdentifier("Gifts","string",getActivity().getPackageName());
        int income4 = getResources().getIdentifier("Salary", "string", getActivity().getPackageName());
        Log.d("IncomeId","Business="+income1);
        Log.d("IncomeId","Extra_income="+income2);
        Log.d("IncomeId","Gifts="+income3);
        Log.d("IncomeId","Salary="+income4);
        int outcome1 = getResources().getIdentifier("Bill","string",getActivity().getPackageName());
        int outcome2 = getResources().getIdentifier("Car","string",getActivity().getPackageName());
        int outcome3 = getResources().getIdentifier("Entertain","string",getActivity().getPackageName());
        int outcome4 = getResources().getIdentifier("Food", "string", getActivity().getPackageName());
        int outcome5 = getResources().getIdentifier("Love", "string", getActivity().getPackageName());
        int outcome6 = getResources().getIdentifier("Shopping", "string", getActivity().getPackageName());
        int outcome7 = getResources().getIdentifier("Transport", "string", getActivity().getPackageName());
        int outcome8 = getResources().getIdentifier("Travel", "string", getActivity().getPackageName());
        Log.d("OutcomeId","Bill="+outcome1);
        Log.d("OutcomeId","Car="+outcome2);
        Log.d("OutcomeId","Entertain="+outcome3);
        Log.d("OutcomeId","Food="+outcome4);
        Log.d("OutcomeId","Love="+outcome5);
        Log.d("OutcomeId", "Shopping=" + outcome6);
        Log.d("OutcomeId","Transport="+outcome7);
        Log.d("OutcomeId","Travel="+outcome8);

*/
        return v;
    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };


}
