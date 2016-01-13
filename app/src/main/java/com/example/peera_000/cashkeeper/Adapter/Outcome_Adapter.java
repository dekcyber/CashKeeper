package com.example.peera_000.cashkeeper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.Outcome_data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by peera_000 on 13/1/2559.
 */
public class Outcome_Adapter extends RecyclerView.Adapter<Outcome_Adapter.OutcomeHolder> {
    //Explicit
    private List<Outcome_data> Outcomelist;
    private Context context;
    public Outcome_Adapter(List<Outcome_data> Outcomelist, Context context) {
        this.Outcomelist = Outcomelist;
        this.context = context;
    }

    @Override
    public OutcomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.outcome_customlayout,parent,false);

        return new OutcomeHolder(v);
    }

    @Override
    public void onBindViewHolder(OutcomeHolder holder, int position) {
      Outcome_data Outcome = Outcomelist.get(position);
        holder.OutTxt.setText(Outcome.getOutcomeName());
        Picasso.with(context).load(Outcome.getOutcomePic()).into(holder.OutImg);
        
    }

    @Override
    public int getItemCount() {
        return Outcomelist.size();
    }


    public class OutcomeHolder extends RecyclerView.ViewHolder {
        //Explicit
        private ImageView OutImg;
        private TextView OutTxt;
        public OutcomeHolder(View itemView) {
            super(itemView);

            OutImg = (ImageView) itemView.findViewById(R.id.outcome_img);
            OutTxt = (TextView) itemView.findViewById(R.id.outcome_txt);

        }

    }
}
