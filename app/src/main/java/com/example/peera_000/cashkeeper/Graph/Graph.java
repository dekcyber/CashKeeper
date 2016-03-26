package com.example.peera_000.cashkeeper.Graph;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.MainCode.Overview;
import com.example.peera_000.cashkeeper.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.io.LineNumberReader;
import java.util.ArrayList;

public class Graph extends Fragment {
    private BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private float[] yData = {5, 10, 15, 30, 40};
    private String[] xData = {"Sony", "Huawei", "LG", "Apple", "Samsung"};

    public static Overview newInstance() {
        Overview TabFrag2 = new Overview();
        return TabFrag2;
    }

    public Graph() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.graph, container, false);

        mChart = (BarChart) v.findViewById(R.id.chart);

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
        return v;
    }

    private void setData() {
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        for (int i = 0; i < yData.length; i++) {
            yVals.add(new BarEntry(yData[i], i));

        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < xData.length; i++) {
            xVals.add(xData[i]);
        }
        BarDataSet dataSet = new BarDataSet(yVals, "Make Share");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        BarData data = new BarData(xVals,dataSet);
        mChart.setData(data);
        mChart.invalidate();

    }


}
