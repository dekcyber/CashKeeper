package com.example.peera_000.cashkeeper.Graph;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PieChart_Graph extends Fragment {
    //Explicit
    PieChart pieChart;
    private CK_TABLE objCK_Table ;
    private ArrayList<String> arrayListCate;
    private ArrayList<Integer> arrayListMoney;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static PieChart_Graph newInstance(){
        PieChart_Graph pieChart_graph = new PieChart_Graph();


        return pieChart_graph;
    }
    public PieChart_Graph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pie_chart__graph,container,false);
        pieChart = (PieChart) view.findViewById(R.id.PieChart);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.PieSwiprefesh);
        objCK_Table = new CK_TABLE(getContext());
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("");
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        //pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(54f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        //pieChart.setOnChartValueSelectedListener(this);

        setData();
        Log.d("ArrayList", "Size = " + arrayListCate.size());



        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);

        l.setYOffset(0f);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //RowDataAdp.closeAllItems();
                        arrayListCate.clear();
                        arrayListMoney.clear();
                        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
                        setData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }

        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary, R.color.Crimson);
        return view;
    }//Oncreate

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setData() {

        arrayListCate = objCK_Table.PickCateForGraph();
        arrayListMoney = objCK_Table.PickMoneyForGraph();

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < arrayListMoney.size(); i++) {
            yVals.add(new Entry(arrayListMoney.get(i),i));

        }
        pieChart.notifyDataSetChanged();

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < arrayListCate.size(); i++) {
            xVals.add(arrayListCate.get(i));
        }
        pieChart.notifyDataSetChanged();

        PieDataSet dataSet = new PieDataSet(yVals,"Category");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<Integer>();


       /* for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);*/

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        /*for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
*/
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        PieData data = new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(R.color.Wheat);
        pieChart.setData(data);
        pieChart.highlightValues(null);

        pieChart.invalidate();

    }//SetData

    @Override
    public void onResume() {
        super.onResume();
        arrayListCate.clear();
        arrayListMoney.clear();
    }
}//MainClass
