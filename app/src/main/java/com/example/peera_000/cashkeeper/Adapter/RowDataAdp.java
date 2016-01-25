package com.example.peera_000.cashkeeper.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.RowData;
import com.squareup.picasso.Picasso;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;


import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by peera_000 on 31/12/2558.
 */
public class RowDataAdp extends RecyclerView.Adapter<RowDataAdp.RowDataHolder> {
    //Explicit
    private List<RowData> lRowData;
    private Context context;

    public RowDataAdp(List<RowData> lRow, Context context) {
        lRowData = lRow;
        this.context = context;
    }//Constructor

    public void AddListRowData(RowData rowdata) {
        lRowData.add(rowdata);
        notifyDataSetChanged();

    }

    @Override
    public RowDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data, parent, false);


        return new RowDataHolder(view);
    }

    @Override
    public void onBindViewHolder(RowDataHolder holder, final int position) {
        RowData rwRowData = lRowData.get(position);
        //holder.Img.setImageResource(rwRowData.getPhoto());
        Picasso.with(context).load(rwRowData.getPhoto()).into(holder.Img);
        holder.TxDate.setText(rwRowData.getDate());
        holder.TxMoney.setText(rwRowData.getMoney());
        holder.TxCate.setText(rwRowData.getCate());
        holder.TxNote.setText(rwRowData.getNote());
        holder.TxtOutMoney.setText(rwRowData.getMoneyOut());


        Log.d("RecyblerViewOverview", "BlindingOverview" + position);
    }

    @Override
    public int getItemCount() {
        return lRowData.size();
    }

    public class RowDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Explicit
        private ImageView Img;
        private TextView TxMoney;
        private TextView TxDate;
        private TextView TxCate;
        private TextView TxNote;
        private TextView TxtOutMoney;

        public RowDataHolder(View itemView) {
            super(itemView);
            Log.d("RecyblerViewOverview", "Matching" + getLayoutPosition());
            Typeface customFont = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/paaymaay_regular.ttf");
            //Matching
            Img = (ImageView) itemView.findViewById(R.id.Img);
            TxMoney = (TextView) itemView.findViewById(R.id.TxtMoney);
            TxDate = (TextView) itemView.findViewById(R.id.TxtDate);
            TxCate = (TextView) itemView.findViewById(R.id.TxtCate);
            TxNote = (TextView) itemView.findViewById(R.id.TxtNote);
            TxtOutMoney = (TextView) itemView.findViewById(R.id.TxtMoneyOut);
            TxtOutMoney.setTextColor(context.getResources().getColor(R.color.Crimson));
            TxMoney.setTextColor(context.getResources().getColor(R.color.MediumSeaGreen));
            //SetFont
            TxMoney.setTypeface(customFont);
            TxCate.setTypeface(customFont);
            TxDate.setTypeface(customFont);
            TxNote.setTypeface(customFont);
            TxtOutMoney.setTypeface(customFont);
            TxCate.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "You click:" + getLayoutPosition() + " " + TxCate.getText() + " ", Toast.LENGTH_SHORT).show();
        }
    }
}//MainClass
