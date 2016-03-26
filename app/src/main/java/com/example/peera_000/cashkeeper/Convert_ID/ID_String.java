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
        context.getString(income1);
        Log.d("IncomeId", "Business=" + context.getString(income1) + " " + income1);
        Log.d("IncomeId", "Extra_income=" + context.getString(income2)+" "+income2);
        Log.d("IncomeId", "Gifts=" + context.getString(income3)+" "+income3);
        Log.d("IncomeId", "Salary=" + context.getString(income4)+" "+income4);
        Log.d("IncomeId", "Add=" + context.getString(income5)+" "+income5);
        int outcome1 = context.getResources().getIdentifier("Bill", "string", context.getPackageName());
        int outcome2 = context.getResources().getIdentifier("Car", "string", context.getPackageName());
        int outcome3 = context.getResources().getIdentifier("Entertain", "string", context.getPackageName());
        int outcome4 = context.getResources().getIdentifier("Food", "string", context.getPackageName());
        int outcome5 = context.getResources().getIdentifier("Love", "string", context.getPackageName());
        int outcome6 = context.getResources().getIdentifier("Shopping", "string", context.getPackageName());
        int outcome7 = context.getResources().getIdentifier("Transport", "string", context.getPackageName());
        int outcome8 = context.getResources().getIdentifier("Travel", "string", context.getPackageName());
        Log.d("OutcomeId", "Bill=" + context.getString(outcome1)+" "+outcome1);
        Log.d("OutcomeId", "Car=" + context.getString(outcome2)+outcome2);
        Log.d("OutcomeId", "Entertain=" + context.getString(outcome3)+outcome3);
        Log.d("OutcomeId", "Food=" + context.getString(outcome4)+outcome4);
        Log.d("OutcomeId", "Love=" + context.getString(outcome5)+outcome5);
        Log.d("OutcomeId", "Shopping=" + context.getString(outcome6)+outcome6);
        Log.d("OutcomeId", "Transport=" + context.getString(outcome7)+outcome7);
        Log.d("OutcomeId", "Travel=" + context.getString(outcome8)+outcome8);


    }
}
