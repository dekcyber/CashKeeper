package com.example.peera_000.cashkeeper.Graph;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.MainCode.Overview;
import com.example.peera_000.cashkeeper.R;

public class Graph extends Fragment {
    TextView Graph;

    public static Overview newInstance() {
        Overview TabFrag2 = new Overview();
        return TabFrag2;
    }

    public Graph() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.graph, container, false);


        return v;
    }


}
