package com.example.peera_000.cashkeeper.Convert_ID;

import android.content.Context;
import android.util.Log;

/**
 * Created by peera_000 on 26/1/2559.
 */
public class ID_String {
    private Context context;

    public ID_String(Context context) {
        this.context = context;
        int income1 = context.getResources().getIdentifier("Business", "string", context.getPackageName());
        int income2 = context.getResources().getIdentifier("Extra_income", "string", context.getPackageName());
        int income3 = context.getResources().getIdentifier("Gifts", "string", context.getPackageName());
        int income4 = context.getResources().getIdentifier("Salary", "string", context.getPackageName());
        int income5 = context.getResources().getIdentifier("Add", "string", context.getPackageName());
        Log.d("IncomeId", "Business=" + income1);
        Log.d("IncomeId", "Extra_income=" + income2);
        Log.d("IncomeId", "Gifts=" + income3);
        Log.d("IncomeId", "Salary=" + income4);
        Log.d("IncomeId", "Add=" + income5);
        int outcome1 = context.getResources().getIdentifier("Bill", "string", context.getPackageName());
        int outcome2 = context.getResources().getIdentifier("Car", "string", context.getPackageName());
        int outcome3 = context.getResources().getIdentifier("Entertain", "string", context.getPackageName());
        int outcome4 = context.getResources().getIdentifier("Food", "string", context.getPackageName());
        int outcome5 = context.getResources().getIdentifier("Love", "string", context.getPackageName());
        int outcome6 = context.getResources().getIdentifier("Shopping", "string", context.getPackageName());
        int outcome7 = context.getResources().getIdentifier("Transport", "string", context.getPackageName());
        int outcome8 = context.getResources().getIdentifier("Travel", "string", context.getPackageName());
        Log.d("OutcomeId", "Bill=" + outcome1);
        Log.d("OutcomeId", "Car=" + outcome2);
        Log.d("OutcomeId", "Entertain=" + outcome3);
        Log.d("OutcomeId", "Food=" + outcome4);
        Log.d("OutcomeId", "Love=" + outcome5);
        Log.d("OutcomeId", "Shopping=" + outcome6);
        Log.d("OutcomeId", "Transport=" + outcome7);
        Log.d("OutcomeId", "Travel=" + outcome8);


    }
}
