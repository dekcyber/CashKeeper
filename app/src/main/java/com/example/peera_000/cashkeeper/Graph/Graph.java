package com.example.peera_000.cashkeeper.Graph;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.MainCode.Overview;
import com.example.peera_000.cashkeeper.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Graph extends Fragment {
    TextView Graph;
    private PieChart mChart;
    private float[] ydata = {5, 10, 15, 30, 40};
    private String[] xdata = {"Sony", "Huawei", "LG", "Apple", "Samsung"};
    private RelativeLayout relaGraph;

    public static Overview newInstance() {
        Overview TabFrag2 = new Overview();
        return TabFrag2;
    }

    public Graph() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.graph, container, false);
        relaGraph = (RelativeLayout) v.findViewById(R.id.RelaGraph);
        mChart = new PieChart(getContext());
        //ใส่กราฟใน layout
        relaGraph.addView(mChart);
        relaGraph.setBackgroundColor(Color.LTGRAY);
        //ตั้งค่ากราฟ
        mChart.setUsePercentValues(true);
        mChart.setDescription("SmartPhone Market Share");
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (e == null) {
                    return;
                }
                Snackbar.make(v, xdata[e.getXIndex()] + " = " + e.getVal() + "%", Snackbar.LENGTH_SHORT);

            }

            @Override
            public void onNothingSelected() {

            }
        });

        //เพิ่มข้อมูล
        addData();

        //Customize Legends
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

        return v;
    }

    private void addData() {
        ArrayList<Entry> yValsl = new ArrayList<Entry>();
        for (int i = 0; i < ydata.length; i++){
            yValsl.add(new Entry(ydata[i], i));}

        ArrayList<String> xVals = new ArrayList<String>();
        for (int j = 0; j < xdata.length; j++){
            xVals.add(xdata[j]);}

        PieDataSet dataSet = new PieDataSet(yValsl,"Market Share");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(c);
        }
        for (int c: ColorTemplate.JOYFUL_COLORS){
            colors.add(c);
        }
        for (int c: ColorTemplate.COLORFUL_COLORS){
            colors.add(c);
        }
        for (int c: ColorTemplate.LIBERTY_COLORS){
            colors.add(c);
        }
        for (int c: ColorTemplate.PASTEL_COLORS){
            colors.add(c);
        }
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        //instantiate pie data object now
        PieData data = new PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(14);
        data.setValueTextColor(R.color.Gray);

        mChart.setData(data);

        //use all highlight
        mChart.highlightValue(null);

        // update pie chart
        mChart.invalidate();



    }
}
