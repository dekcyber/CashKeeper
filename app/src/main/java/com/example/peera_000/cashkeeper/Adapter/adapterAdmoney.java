package com.example.peera_000.cashkeeper.Adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import com.example.peera_000.cashkeeper.Income;
import com.example.peera_000.cashkeeper.Outcome;
import com.example.peera_000.cashkeeper.R;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class AdapterAdmoney extends FragmentStatePagerAdapter {
    public AdapterAdmoney(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Income();
            case 1:
                return new Outcome();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}

