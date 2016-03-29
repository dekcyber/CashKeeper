package com.example.peera_000.cashkeeper.MainCode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.peera_000.cashkeeper.Adapter.RowDataAdp;
import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.RowData;

import com.daimajia.swipe.util.Attributes;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class Overview extends Fragment {

    //Explicit
    private RecyclerView RvRowdata;
    private RowDataAdp RowDataAdp;
    private CK_TABLE objCK_TABLE;
    private FloatingActionButton fabAd;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View v;
    private Handler handler;
    private SharedPreferences sp,Edsp;
    private SharedPreferences Outsp;
    private SharedPreferences.Editor Outeditor;
    private SharedPreferences.Editor editor,edit_Edsp;
    private static final String DESC = "SELECT * FROM CK_TABLE ORDER BY DESC";


    public static Overview newInstance() {
        Overview TabFrag1 = new Overview();

        return TabFrag1;

    }

    public Overview() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.overview, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.OverSwiprefesh);
        //----เริ่มประกาศ SharedPreferences เพื่อ Clear ค่าต่างๆทั้งหมด-----
        sp = getActivity().getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
        Outsp = getActivity().getSharedPreferences("OutcomeData", Context.MODE_PRIVATE);
        editor = sp.edit();
        Outeditor = Outsp.edit();
        Edsp = getActivity().getSharedPreferences("EditRowOverData",Context.MODE_PRIVATE);
        edit_Edsp = Edsp.edit();
        edit_Edsp.clear();
        edit_Edsp.commit();
        editor.clear();
        editor.commit();
        Outeditor.clear();
        Outeditor.commit();
        //---จบการ Clear SharePreference----

        fabAd = (FloatingActionButton) v.findViewById(R.id.fabAd);
        fabAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objintent = new Intent(getActivity(), AdMoney.class);
                startActivity(objintent);
            }
        });
        //int Draw = getResources().getIdentifier("bell","drawable",getContext().getPackageName());
       setAndShowData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //RowDataAdp.closeAllItems();
                        setAndShowData();
                        RowDataAdp.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1000);


            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,R.color.Crimson);

        return v;
    }//Oncreate

    public void setAndShowData(){
        List<RowData> lRowdata = new ArrayList<>();
        objCK_TABLE = new CK_TABLE(getActivity());
        Cursor CurData = objCK_TABLE.readASC();
        CurData.moveToPosition(-1);
        String Check = "";
        String Check1 = "";
        String strSumIncome = null;
        String Checkd="";
        Double sumIncome = 0.0;
        CurData.moveToPosition(-1);
        String strSum = null;
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

            int PhotoIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_CPhoto);
            String strPhoto = CurData.getString(PhotoIndex);
            int Photo = Integer.valueOf(strPhoto);

            int IdIndex = CurData.getColumnIndex(CK_TABLE.COLUMN_ID);
            int intId = CurData.getInt(IdIndex);
            String strId = Integer.toString(intId);

            if (Check.equals(strDate)) {
                Check = strDate;
                Check1 = " ";
                strSum = null;
                //Log.d("TestValue","SUM"+" "+"DATE"+strDate+"="+sumIncome);
                lRowdata.add(new RowData(Photo, strMoney, Check1, ValueCate, strNote, strOutmoney,strSum,strId));
                //Log.d("SumIncome", "วันที่เท่ากัน" + strSumIncome);
            } else {

                Check = strDate;
                Check1 = strDate;
                int Value = objCK_TABLE.SumMoneyOfDay(strDate);
                strSum = strSumIncome;
                lRowdata.add(new RowData(Photo, strMoney, Check1, ValueCate, strNote, strOutmoney,String.valueOf(Value),strId));
                //Log.d("SumIncome", "วันที่ไม่เท่ากัน" + strSumIncome);
            }


        }

        RowDataAdp = new RowDataAdp(lRowdata,getContext());
        RvRowdata = (RecyclerView) v.findViewById(R.id.RvRowdata);
        RvRowdata.setLayoutManager(new LinearLayoutManager(getContext()));
        RvRowdata.setHasFixedSize(true);
        RvRowdata.setAdapter(RowDataAdp);
        RvRowdata.setOnScrollListener(onScrollListener);
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
