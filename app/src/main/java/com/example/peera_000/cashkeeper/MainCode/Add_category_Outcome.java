package com.example.peera_000.cashkeeper.MainCode;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.peera_000.cashkeeper.Adapter.Outcome_Adapter;
import com.example.peera_000.cashkeeper.Database.OUTCOME_TABLE;
import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.Outcome_data;

import java.util.ArrayList;
import java.util.List;

public class Add_category_Outcome extends AppCompatActivity {
    //Explicit
    private Toolbar tbAdcate;
    private ImageView imgAdcate;
    private EditText edtAdcate;
    private RecyclerView rvAdCate;
    private Outcome_Adapter adpOutcome;
    private OUTCOME_TABLE outcomeTable;
    private String strEdtcateOut;
    private SharedPreferences CateOutShared;
    private SharedPreferences.Editor SharedOutEdit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_outcome);
        BlindWidget();
        CateOutShared = getSharedPreferences("CateOut",MODE_PRIVATE);
        SharedOutEdit = CateOutShared.edit();
        setSupportActionBar(tbAdcate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tbAdcate.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adpOutcome = new Outcome_Adapter(ListAddCateOut(), getApplicationContext());
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
                SharedOutEdit.putString("IdPicOut", Idpic);
                SharedOutEdit.commit();
                intPic = Integer.parseInt(Idpic);
                imgAdcate.setImageResource(intPic);

            }
        });


    }//OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney, menu);
        MenuItem item = menu.findItem(R.id.AddmoneyNext);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }//CreateOptionMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        strEdtcateOut = edtAdcate.getText().toString();
        String Pic = CateOutShared.getString("IdPicOut", null);
        Log.d("OUTCOME_ID", "ID_PIC=" + Pic);
        int menuId = item.getItemId();
        switch (menuId){
            case R.id.OK:
                outcomeTable.AddCateOutcome(strEdtcateOut,Pic,"0","N");
                break;

        }
        return super.onOptionsItemSelected(item);
    }//ตอนเลือกเมนู

    public List<Outcome_data> ListAddCateOut(){
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
            int StatusIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Status);
            String strStatus = objCursor.getString(StatusIndex);
            int PhotoIndex = objCursor.getColumnIndex(OUTCOME_TABLE.COLUMNOUTCOME_Photo);
            String strPhoto = objCursor.getString(PhotoIndex);
            int IntPhoto = Integer.parseInt(strPhoto);
            if (strStatus.equals("N")){
                OutcomeList.add(new Outcome_data(IntPhoto,strName, strPhoto, strNameId));
                Log.d("ReadDataNew", "OUTCOME SUCCESS");
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("NameID", "=" + strNameId);
            }else {
                OutcomeList.add(new Outcome_data(IntPhoto, ValueNameId, strPhoto, strNameId));
                Log.d("ReadData", "OUTCOME SUCCESS");
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("NameID", "=" + strNameId);
            }

        }
        return OutcomeList;
    }//ListAddCategoryOutcome

    private void BlindWidget() {
        tbAdcate = (Toolbar) findViewById(R.id.toolbarAdCate);
        imgAdcate = (ImageView) findViewById(R.id.SelectImgCate);
        edtAdcate = (EditText) findViewById(R.id.edtAdCate);
        rvAdCate = (RecyclerView) findViewById(R.id.RvAddOutcate);
    }//BlindWidget
}//MainClass
