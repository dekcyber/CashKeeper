package com.example.peera_000.cashkeeper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.Income;
import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.income_data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class Income_Adapter extends RecyclerView.Adapter<Income_Adapter.IncomeHolder> {
    //Explicit
    private List<income_data> incomelist;
    private Context context;
    public Income_Adapter(List<income_data> incomelist, Context context) {
        this.incomelist = incomelist;
        this.context = context;
    }

    @Override
    public IncomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_customlayout,parent,false);

        return new IncomeHolder(v);
    }

    @Override
    public void onBindViewHolder(IncomeHolder holder, int position) {
        income_data income = incomelist.get(position);
        holder.txtIncome.setText(income.getIncomeName());
        Picasso.with(context).load(income.getIncomePic()).into(holder.imgIncome);

    }

    @Override
    public int getItemCount() {
        return incomelist.size();
    }


    public class IncomeHolder extends RecyclerView.ViewHolder {
        //Explicit
        private ImageView imgIncome;
        private TextView txtIncome;

        public IncomeHolder(View itemView) {
            super(itemView);
            imgIncome = (ImageView) itemView.findViewById(R.id.income_img);
            txtIncome = (TextView) itemView.findViewById(R.id.income_txt);

        }
    }
}
