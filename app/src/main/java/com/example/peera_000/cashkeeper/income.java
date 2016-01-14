package com.example.peera_000.cashkeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.Income_Adapter;
import com.example.peera_000.cashkeeper.Rowdata.Income_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class Income extends Fragment {
    //Explicit
    private RecyclerView rvIncome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.income,container,false);
        final Income_Adapter income_adapter = new Income_Adapter(AddListIncome(),getContext());
        rvIncome = (RecyclerView) v.findViewById(R.id.income_rv);
        rvIncome.setHasFixedSize(true);
        rvIncome.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvIncome.setAdapter(income_adapter);
        income_adapter.SetOnClickListener(new Income_Adapter.OnItemClickListener() {
            //Explicit to getValue
            public String strNameIncome;
            public int intPositionIncome;

            @Override
            public void onItemClick(View view, int position, String Name) {
                this.strNameIncome = Name;
                this.intPositionIncome = position;

                SharedPreferences sp = getActivity().getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("IncomePosition", position);
                editor.putString("IncomeName", Name);
                editor.commit();

                Log.d("IncomeItemClick", "Item:" + position);
            }
        });
        return v;
    }//OnCreate

    //AddDataIncome
    public List<Income_data> AddListIncome(){
        List<Income_data> income_datas = new ArrayList<>();
        String[] incomeName = {"Business","Extra income","Gifts","Salary"};
        int[] incomePic = {R.drawable.category_extramoney,R.drawable.category_business,R.drawable.category_gift
        ,R.drawable.category_salary};
        for (int i=0;i<incomeName.length;i++){
            Income_data incomeAdddata = new Income_data(incomePic[i],incomeName[i]);
            income_datas.add(incomeAdddata);

        }

        return income_datas;
    }
}//MainClass
