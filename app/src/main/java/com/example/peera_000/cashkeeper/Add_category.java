package com.example.peera_000.cashkeeper;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.peera_000.cashkeeper.Adapter.Outcome_Adapter;
import com.example.peera_000.cashkeeper.Database.OUTCOME_TABLE;
import com.example.peera_000.cashkeeper.Rowdata.Outcome_data;

import java.util.ArrayList;
import java.util.List;

public class Add_category extends AppCompatActivity {
    //Explicit
    private Toolbar tbAdcate;
    private ImageView imgAdcate;
    private EditText edtAdcate;
    private RecyclerView rvAdCate;
    private Outcome_Adapter adpOutcome;
    private OUTCOME_TABLE outcomeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        BlindWidget();
        setSupportActionBar(tbAdcate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tbAdcate.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        List<Outcome_data> OutcomeList = new ArrayList<>();
        outcomeTable = new OUTCOME_TABLE(getApplicationContext());
        Cursor objCursor = outcomeTable.readAllDataOutcomeCate();
        objCursor.moveToPosition(-1);
        while (objCursor.moveToNext()) {
            int NameIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NAME);
            String strName = objCursor.getString(NameIndex);
            int NameIdIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_NameID);
            String strNameId = objCursor.getString(NameIdIndex);
            String ValueNameId = getString(Integer.parseInt(strNameId));
            Log.d("NameID", "=" + strNameId);
            int PhotoIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Photo);
            String strPhoto = objCursor.getString(PhotoIndex);

            Log.d("PhotoID", "=" + strPhoto);
            int IntPhoto = Integer.parseInt(strPhoto);
            OutcomeList.add(new Outcome_data(IntPhoto, ValueNameId, strPhoto, strNameId));
            Log.d("ReadData", "OUTCOME SUCCESS");
        }
        adpOutcome = new Outcome_Adapter(OutcomeList, getApplicationContext());
        rvAdCate.setHasFixedSize(true);
        rvAdCate.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        rvAdCate.setAdapter(adpOutcome);
        adpOutcome.SetOnClickListener(new Outcome_Adapter.OnItemClickListener() {
            //Explicit
            private int position;
            private String Name;
            private String Idpic;
            private String NameId;
            private int intPic;

            @Override
            public void onItemClick(View view, int position, String Name, String Idpic, String NameId) {
                this.position = position;
                this.Name = Name;
                this.Idpic = Idpic;
                this.NameId = NameId;

                intPic = Integer.parseInt(Idpic);
                imgAdcate.setImageResource(intPic);

            }
        });


    }//OnCreate

    private void BlindWidget() {
        tbAdcate = (Toolbar) findViewById(R.id.toolbarAdCate);
        imgAdcate = (ImageView) findViewById(R.id.SelectImgCate);
        edtAdcate = (EditText) findViewById(R.id.edtAdCate);
        rvAdCate = (RecyclerView) findViewById(R.id.RvAddOutcate);
    }//BlindWidget
}//MainClass
