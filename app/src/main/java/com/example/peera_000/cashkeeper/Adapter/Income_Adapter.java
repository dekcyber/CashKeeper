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
    OnItemClickListener IncomeItemClick;

    public Income_Adapter(List<Income_data> incomeList, Context context) {
        IncomeList = incomeList;
        this.context = context;
    }

    @Override
    public IncomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_customlayout, parent, false);

        return new IncomeHolder(v);
    }

    @Override
    public void onBindViewHolder(IncomeHolder holder, int position) {
        Income_data income = IncomeList.get(position);
        holder.IncomeTxt.setText(income.getIncomeName());
        Picasso.with(context).load(income.getIncomePic()).into(holder.IncomeImg);
        holder.IncomeidTxt.setText(income.getIncomeIDpic());
        holder.IncomeNameid.setText(income.getIncomeNameId());
    }

    @Override
    public int getItemCount() {
        return IncomeList.size();
    }

    public class IncomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Explicit
        private ImageView IncomeImg;
        private TextView IncomeTxt;
        private TextView IncomeidTxt;
        private TextView IncomeNameid;
        public IncomeHolder(View itemView) {
            super(itemView);
            IncomeImg = (ImageView) itemView.findViewById(R.id.income_img);
            IncomeTxt = (TextView) itemView.findViewById(R.id.income_txt);
            IncomeidTxt = (TextView) itemView.findViewById(R.id.income_id_txt);
            IncomeNameid = (TextView) itemView.findViewById(R.id.income_name_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (IncomeItemClick != null) {
                IncomeItemClick.onItemClick(v, getLayoutPosition(), String.valueOf(IncomeTxt.getText()), String.valueOf(IncomeidTxt.getText())
                        , String.valueOf(IncomeNameid.getText()));

            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String Name, String IDPhoto, String Nameid);
    }

    public void SetOnClickListener(final OnItemClickListener ItemClickListener) {
        this.IncomeItemClick = ItemClickListener;

    }
}
