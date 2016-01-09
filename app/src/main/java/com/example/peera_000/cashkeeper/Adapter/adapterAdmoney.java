package com.example.peera_000.cashkeeper.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.peera_000.cashkeeper.ViewPagerAdapter;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class adapterAdmoney extends FragmentStatePagerAdapter {
    public adapterAdmoney(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
