package com.example.peera_000.cashkeeper.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
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
    OnItemClickListener OutcomeItemClick;

    public Outcome_Adapter(List<Outcome_data> Outcomelist, Context context) {
        this.Outcomelist = Outcomelist;
        this.context = context;
    }

    @Override
    public OutcomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.outcome_customlayout, parent, false);

        return new OutcomeHolder(v);
    }

    @Override
    public void onBindViewHolder(OutcomeHolder holder, int position) {
        Outcome_data Outcome = Outcomelist.get(position);
        holder.OutTxt.setText(Outcome.getOutcomeName());
        Picasso.with(context).load(Outcome.getOutcomePic()).into(holder.OutImg);
        holder.OutidTxt.setText(Outcome.getOutcomeIDpic());
        holder.OutNameid.setText(Outcome.getOutcomeNameId());

    }

    @Override
    public int getItemCount() {
        return Outcomelist.size();
    }


    public class OutcomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Explicit
        private ImageView OutImg;
        private TextView OutTxt;
        private TextView OutidTxt;
        private TextView OutNameid;

        public OutcomeHolder(View itemView) {
            super(itemView);
            OutImg = (ImageView) itemView.findViewById(R.id.outcome_img);
            OutTxt = (TextView) itemView.findViewById(R.id.outcome_txt);
            OutidTxt = (TextView) itemView.findViewById(R.id.Outcome_id_txt);
            OutNameid = (TextView) itemView.findViewById(R.id.outcome_name_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (OutcomeItemClick != null) {
                OutcomeItemClick.onItemClick(v, getLayoutPosition(), String.valueOf(OutTxt.getText())
                        , String.valueOf(OutidTxt.getText()), String.valueOf(OutNameid.getText()));

            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String Name, String Idpic, String NameId);
    }

    public void SetOnClickListener(final OnItemClickListener ItemClickListener) {
        this.OutcomeItemClick = ItemClickListener;

    }
}
