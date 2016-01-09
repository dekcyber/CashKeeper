package com.example.peera_000.cashkeeper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Graph extends Fragment {
    TextView Graph;
    public static Overview newInstance(){
        Overview TabFrag2 = new Overview();
        return TabFrag2;
    }
    public Graph(){}
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_2,container,false);


        return v;
    }


}
