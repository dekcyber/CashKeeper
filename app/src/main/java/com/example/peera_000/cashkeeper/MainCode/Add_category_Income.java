package com.example.peera_000.cashkeeper.MainCode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.example.peera_000.cashkeeper.Adapter.Income_Adapter;
import com.example.peera_000.cashkeeper.Database.INCOME_TABLE;
import com.example.peera_000.cashkeeper.R;
import com.example.peera_000.cashkeeper.Rowdata.Income_data;

import java.util.ArrayList;
import java.util.List;

public class Add_category_Income extends AppCompatActivity {
    private RecyclerView rvIncome;
    private ImageView imgIncome;
    private EditText edtAdcateIn;
    private Toolbar ToolbarAdcate;
    private String strEdtcateIn;
    private SharedPreferences CateInShared;
    private SharedPreferences.Editor SharedInEdit;
    private Income_Adapter IncomAdp;
    private INCOME_TABLE income_table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category__income);
        CateInShared = getSharedPreferences("IncomeCate",MODE_PRIVATE);
        SharedInEdit = CateInShared.edit();
        BlindWidget();
        setSupportActionBar(ToolbarAdcate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ToolbarAdcate.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        IncomAdp = new Income_Adapter(ListIncome(),getApplicationContext());
        rvIncome.setHasFixedSize(true);
        rvIncome.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        rvIncome.setAdapter(IncomAdp);
        IncomAdp.SetOnClickListener(new Income_Adapter.OnItemClickListener() {
            //Explicit
            private int position;
            private String Name;
            private String Idpic;
            private String NameId;
            private int intPic;
            @Override
            public void onItemClick(View view, int position, String Name, String IDPhoto, String Nameid) {
                this.position = position;
                this.Name = Name;
                this.Idpic = IDPhoto;
                this.NameId = NameId;
                SharedInEdit.putString("IdPicIn", Idpic);
                SharedInEdit.commit();
                intPic = Integer.parseInt(Idpic);
                imgIncome.setImageResource(intPic);
            }
        });
    }//OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney,menu);
        MenuItem item = menu.findItem(R.id.AddmoneyNext);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }//สร้างเมนู

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        strEdtcateIn = edtAdcateIn.getText().toString();
        String Pic = CateInShared.getString("IdPicIn",null);
        int MenuItem = item.getItemId();
        switch (MenuItem){
            case R.id.OK:
                income_table.AddCateIncome(strEdtcateIn,Pic,"0","N");
                Intent intent = new Intent(getApplicationContext(),AdMoney.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }//เลือกเมนู
    public List<Income_data> ListIncome(){
        List<Income_data> income_datas = new ArrayList<>();
        income_table = new INCOME_TABLE(getApplicationContext());
        Cursor objCursor = income_table.readAllDataCateIncome();
        objCursor.moveToPosition(-1);
        while (objCursor.moveToNext()) {
            int StatusIndex =  objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_Status);
            String strStatus = objCursor.getString(StatusIndex);

            if (strStatus.equals("N")){
                int NameIdIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NameID);
                String strNameId = objCursor.getString(NameIdIndex);
                int NameIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NAME);
                String strName = objCursor.getString(NameIndex);
                int PhotoIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_Photo);
                String strPhoto = objCursor.getString(PhotoIndex);
                int IntPhoto = Integer.parseInt(strPhoto);
                income_datas.add(new Income_data(IntPhoto, strName, strPhoto, strNameId));
                Log.d("NameID", "=" + strNameId);
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("ReadDataNew", "INCOME SUCCESS");
            }else {
                int NameIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NAME);
                String strName = objCursor.getString(NameIndex);
                int NameIdIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_NameID);
                String strNameId = objCursor.getString(NameIdIndex);
                String ValueNameId = getString(Integer.parseInt(strNameId));
                int PhotoIndex = objCursor.getColumnIndex(INCOME_TABLE.COLUMNIN_Photo);
                String strPhoto = objCursor.getString(PhotoIndex);
                int IntPhoto = Integer.parseInt(strPhoto);
                income_datas.add(new Income_data(IntPhoto, ValueNameId, strPhoto, strNameId));
                Log.d("NameID", "=" + strNameId);
                Log.d("PhotoID", "=" + strPhoto);
                Log.d("ReadData", "INCOME SUCCESS");
            }

        }
        Cursor Cursor2 = income_table.readAllDataIncome();
        int NameIndex = Cursor2.getColumnIndex(INCOME_TABLE.COLUMNIN_NAME);
        String strName = Cursor2.getString(NameIndex);
        int NameIdIndex = Cursor2.getColumnIndex(INCOME_TABLE.COLUMNIN_NameID);
        String strNameId = Cursor2.getString(NameIdIndex);
        String ValueNameId = getString(Integer.parseInt(strNameId));
        int PhotoIndex = Cursor2.getColumnIndex(INCOME_TABLE.COLUMNIN_Photo);
        String strPhoto = Cursor2.getString(PhotoIndex);
        int IntPhoto = Integer.parseInt(strPhoto);
        income_datas.add(new Income_data(IntPhoto, ValueNameId, strPhoto, strNameId));

        return income_datas;
    }

    private void BlindWidget() {
        rvIncome = (RecyclerView) findViewById(R.id.RvAddIncate);
        imgIncome = (ImageView) findViewById(R.id.SelectImgCateIncome);
        edtAdcateIn = (EditText) findViewById(R.id.edtAdCateIncome);
        ToolbarAdcate = (Toolbar) findViewById(R.id.toolbarAdCateIn);
    }//BlindWidget

}//MainClass
