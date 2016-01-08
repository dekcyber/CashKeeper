package com.example.peera_000.cashkeeper.Adapter;

import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.RowData;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by peera_000 on 31/12/2558.
 */
public class RowDataAdp extends RecyclerView.Adapter<RowDataAdp.RowDataHolder> {
    //Explicit
    private List<RowData> lRowData;

    public RowDataAdp(List<RowData> lRow) {
        lRowData = lRow;

    }//Constructor

    public void AddListRowData(RowData rowdata){
        lRowData.add(rowdata);
        notifyDataSetChanged();

    }
    @Override
    public RowDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data, parent, false);


        return new RowDataHolder(view);
    }

    @Override
    public void onBindViewHolder(RowDataHolder holder, int position) {

                RowData rwRowData = lRowData.get(position);
                holder.Img.setImageBitmap(rwRowData.getPhoto());
                holder.TxDate.setText(rwRowData.getDate());
                holder.TxMoney.setText(rwRowData.getMoney());
                holder.TxCate.setText(rwRowData.getCate());
        Log.d("RecyblerView", "Blinding"+ position);
    }

    @Override
    public int getItemCount() {
        return lRowData.size();
    }

    public class RowDataHolder extends RecyclerView.ViewHolder {
        //Explicit
        private ImageView Img;
        private TextView TxMoney;
        private TextView TxDate;
        private TextView TxCate;

        public RowDataHolder(View itemView) {
            super(itemView);
            Log.d("RecyblerView", "Matching");
            Typeface customFont = Typeface.createFromAsset(itemView.getContext().getAssets(),"font/paaymaay_regular.ttf");
            //Matching
            Img = (ImageView) itemView.findViewById(R.id.Img);
            TxMoney = (TextView) itemView.findViewById(R.id.TxtMoney);
            TxDate = (TextView) itemView.findViewById(R.id.TxtDate);
            TxCate = (TextView) itemView.findViewById(R.id.TxtCate);
            //SetFont
            TxMoney.setTypeface(customFont);
            TxCate.setTypeface(customFont);
            TxDate.setTypeface(customFont);

        }
    }
}//MainClass
