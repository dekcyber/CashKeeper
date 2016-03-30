package com.example.peera_000.cashkeeper.Graph;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarChart_Graph extends Fragment {
    //Explicit
    private BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private float[] yData = {5, 10, 15, 30, 40};
    private String[] xData = {"Sony", "Huawei", "LG", "Apple", "Samsung"};
    private CK_TABLE objCK_Table ;
    private ArrayList<String>arrayListCate;
    private ArrayList<Integer> arrayListMoney;
    private Button buttonTest;

    public static BarChart_Graph newInstance(){
        BarChart_Graph barChart_graph = new BarChart_Graph();


        return barChart_graph;
    }


    public BarChart_Graph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bar_chart__graph,container,false);
        mChart = (BarChart) v.findViewById(R.id.chart);
        objCK_Table = new CK_TABLE(getContext());
        arrayListCate = objCK_Table.PickCateForGraph();
        arrayListMoney = objCK_Table.PickMoneyForGraph();
        mChart.notifyDataSetChanged();
        mChart.invalidate();
        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(0);
        xAxis.setDrawGridLines(false);

        mChart.getAxisLeft().setDrawGridLines(false);

        // add a nice and smooth animation
        mChart.animateY(2500);

        mChart.getLegend().setEnabled(false);
        setData();
        Log.d("ArrayList", "Size = " + arrayListCate.size());
        return v;
    }//Oncreate

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void setData() {
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        for (int i = 0; i < arrayListMoney.size(); i++) {
            yVals.add(new BarEntry(arrayListMoney.get(i),i));

        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < arrayListCate.size(); i++) {
            xVals.add(arrayListCate.get(i));
        }
        BarDataSet dataSet = new BarDataSet(yVals, "Make Share");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        BarData data = new BarData(xVals,dataSet);
        mChart.setData(data);
        mChart.invalidate();

    }//SetData

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onPause() {
        super.onPause();

    }
}//MainClass
