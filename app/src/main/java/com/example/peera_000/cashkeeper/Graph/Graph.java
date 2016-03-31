package com.example.peera_000.cashkeeper.Graph;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.Adapter.SpinnerAdapter;
import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.MainCode.Overview;
import com.example.peera_000.cashkeeper.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph extends Fragment {
    //Explicit
    private BarChart_Graph FragBarChart;
    private PieChart_Graph FragPieChart;
    private android.support.v4.app.FragmentTransaction fragTran;
    private android.support.v4.app.FragmentTransaction fragTranP;
    private android.support.v4.app.FragmentManager fragmentManager;
    private Spinner spinnerTypeGraph;
    private ArrayAdapter<CharSequence> arrayAdapterGraph;
    private SpinnerAdapter spinnerAdapter;
    private TextView TxtGraphType;
    private Typeface customFont;

    public static Overview newInstance() {
        Overview TabFrag2 = new Overview();
        return TabFrag2;
    }

    public Graph() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.graph, container, false);
        customFont = Typeface.createFromAsset(v.getContext().getAssets(), "font/paaymaay_regular.ttf");
        spinnerTypeGraph = (Spinner) v.findViewById(R.id.SpinnerGraph);
        TxtGraphType = (TextView) v.findViewById(R.id.TxtTypeGraph);
        TxtGraphType.setTypeface(customFont);
        spinnerAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_item
                , Arrays.asList(getResources().getStringArray(R.array.GraphType_Array)));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /*arrayAdapterGraph = ArrayAdapter.createFromResource(getContext(),R.array.GraphType_Array,android.R.layout.simple_spinner_item);
        arrayAdapterGraph.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        spinnerTypeGraph.setAdapter(spinnerAdapter);
        FragBarChart = new BarChart_Graph();
        FragPieChart = new PieChart_Graph();
        fragmentManager = getFragmentManager();

        spinnerTypeGraph.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    fragTran = fragmentManager.beginTransaction();
                    fragTran.replace(R.id.FragRelaGraph, FragBarChart);
                    fragTran.commit();
                    
                }else if(position ==1){
                    getFragmentManager().beginTransaction().remove(FragBarChart).commit();
                    fragTranP = fragmentManager.beginTransaction();
                    fragTranP.replace(R.id.FragRelaGraph,FragPieChart);
                    fragTranP.commit();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }


}
