package com.example.peera_000.cashkeeper.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.peera_000.cashkeeper.EditRowOverview.EditIncome;
import com.example.peera_000.cashkeeper.EditRowOverview.EditOutcome;
import com.example.peera_000.cashkeeper.MainCode.Income;
import com.example.peera_000.cashkeeper.MainCode.Outcome;

/**
 * Created by peera_000 on 10/1/2559.
 */
public class EditAdapterAdmoney extends FragmentStatePagerAdapter {
    public EditAdapterAdmoney(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new EditOutcome();
            case 1:
                return new EditIncome();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


}

