package com.example.peera_000.cashkeeper.Convert_ID;

import android.content.Context;
import android.util.Log;

/**
 * Created by peera_000 on 26/1/2559.
 */
public class ID_Picture {
    private Context context;

    public ID_Picture(Context context) {
        this.context = context;
        int Draw = context.getResources().getIdentifier("category_bill", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_bill=" + Draw);
        int Draw1 = context.getResources().getIdentifier("category_car", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_car=" + Draw1);
        int Draw2 = context.getResources().getIdentifier("category_entertainment", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_entertainment=" + Draw2);
        int Draw3 = context.getResources().getIdentifier("category_food", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_food=" + Draw3);
        int Draw4 = context.getResources().getIdentifier("category_love", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_love=" + Draw4);
        int Draw5 = context.getResources().getIdentifier("category_shopping", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_shopping=" + Draw5);
        int Draw6 = context.getResources().getIdentifier("category_transport", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_transport=" + Draw6);
        int Draw7 = context.getResources().getIdentifier("category_travel", "drawable", context.getPackageName());
        Log.d("Cate_Outcome", "category_travel=" + Draw7);
        int Draw8 = context.getResources().getIdentifier("category_business", "drawable", context.getPackageName());
        Log.d("Cate_Income", "category_business=" + Draw8);
        int Draw9 = context.getResources().getIdentifier("category_extramoney", "drawable", context.getPackageName());
        Log.d("Cate_Income", "category_extramoney=" + Draw9);
        int Draw10 = context.getResources().getIdentifier("category_gift", "drawable", context.getPackageName());
        Log.d("Cate_Income", "category_gift=" + Draw10);
        int Draw11 = context.getResources().getIdentifier("category_salary", "drawable", context.getPackageName());
        Log.d("Cate_Income", "category_salary=" + Draw11);
        int Draw12 = context.getResources().getIdentifier("category_add", "drawable", context.getPackageName());
        Log.d("Cate_Income", "category_add=" + Draw12);

    }

}
