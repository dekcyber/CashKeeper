package com.example.peera_000.cashkeeper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.Income_data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class Income_Adapter extends RecyclerView.Adapter<Income_Adapter.IncomeHolder> {
    //Explicit
    private List<Income_data> IncomeList;
    private Context context;

    public Income_Adapter(List<Income_data> incomeList, Context context) {
        IncomeList = incomeList;
        this.context = context;
    }

    @Override
    public IncomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_customlayout,parent,false);

        return new IncomeHolder(v);
    }

    @Override
    public void onBindViewHolder(IncomeHolder holder, int position) {
        Income_data income = IncomeList.get(position);
        holder.IncomeTxt.setText(income.getIncomeName());
        Picasso.with(context).load(income.getIncomePic()).into(holder.IncomeImg);

    }

    @Override
    public int getItemCount() {
        return IncomeList.size();
    }

    public class IncomeHolder extends RecyclerView.ViewHolder {
        //Explicit
        private ImageView IncomeImg;
        private TextView IncomeTxt;

        public IncomeHolder(View itemView) {
            super(itemView);
            IncomeImg = (ImageView) itemView.findViewById(R.id.income_img);
            IncomeTxt = (TextView) itemView.findViewById(R.id.income_txt);
        }
    }
}
