package com.example.peera_000.cashkeeper.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.peera_000.cashkeeper.Outcome;
import com.example.peera_000.cashkeeper.Income;

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
                return new Outcome();
            case 1:
                return new Income();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}

