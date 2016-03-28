package com.example.peera_000.cashkeeper.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Valkiery-X on 28/3/2559.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {

    Typeface font = Typeface.createFromAsset(getContext().getAssets(),
            "font/paaymaay_regular.ttf");
    public SpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(font);
        view.setTextSize(20);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setTypeface(font);
        view.setTextSize(20);
        return view;
    }
}
