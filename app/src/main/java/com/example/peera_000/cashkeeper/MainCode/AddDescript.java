package com.example.peera_000.cashkeeper.MainCode;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.ImagePicker;
import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.MainActivity;
import com.example.peera_000.cashkeeper.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddDescript extends AppCompatActivity {
    //Explicit
    private SharedPreferences sp;
    private SharedPreferences Outsp;
    private SharedPreferences.Editor Outeditor;
    private SharedPreferences.Editor editor;
    private EditText edtMonney;
    private Toolbar toolbar;
    private ImageView img;
    private ImageView ImgCamera;
    private ImageView ImgCameratest;
    private TextView TxtDatepicker;
    private EditText edtNote;
    private int intDate;
    private int intMonth;
    private int intYear;
    private static final int DILOG_ID = 0;
    private int REQUEST_CAMERA = 2;
    private int GALLERY_PICTURE = 1;
    private static final int PICK_IMAGE_ID = 234;
    private Uri uri;
    private static DatePickerDialog.OnDateSetListener Datesetpicker;
    private String Money;
    private String MoneyComp;
    private String Category;
    private String Note;
    private String Date;
    private int imgPhoto;
    private CK_TABLE ck_table;
    double douMoney;
    private String Nameid;
    private Typeface customFont;
    private Uri mImageCaptureUri;
    private ImageView mImageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        sp = getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
        Outsp = getSharedPreferences("OutcomeData", Context.MODE_PRIVATE);
        editor = sp.edit();
        Outeditor = Outsp.edit();
        Money = sp.getString("Money", null);
        //BlindWidget
        BlindWidget();
        //int photoOut = Outsp.getInt("OutcomePhoto", -1);
        int photoIn = sp.getInt("IncomePhoto", -1);
        Log.d("Photo", "=" + photoIn);
            img.setImageResource(photoIn);
            imgPhoto = photoIn;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Log.d("Note", "=" + Note);
            }
        });
        Calendar calendar = Calendar.getInstance();
        intYear = calendar.get(Calendar.YEAR);
        intMonth = calendar.get(Calendar.MONTH);
        intDate = calendar.get(Calendar.DAY_OF_MONTH);
        TxtDatepicker.setTypeface(customFont);
        TxtDatepicker.setText(intYear + "/" + (intMonth + 1) + "/" + intDate);
        Datesetpicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                intYear = year;
                intMonth = monthOfYear + 1;
                intDate = dayOfMonth;
                TxtDatepicker.setText(intYear + "/" + intMonth + "/" + intDate);

            }
        };
        final String [] items           = new String [] {"From Camera", "From SD Card"};
        final ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,items);
        ImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String strTimestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String strImgFilename = "IMG_"+strTimestamp+".jpg";
                File file = new File(Environment.getExternalStorageDirectory(),"DCIM/Camera/"+strImgFilename);
                uri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(Intent.createChooser(intent,"Take picture with"),REQUEST_CAMERA);*/

                /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image*//**//**//**//*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select photo from"), 0);

                AlertDialog.Builder alertBuild = new AlertDialog.Builder(AddDescript.this);
                alertBuild.setTitle("Select Image");
                alertBuild.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            String strTimestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                            String strImgFilename = "IMG_"+strTimestamp+".jpg";
                            File file = new File(Environment.getExternalStorageDirectory(),"DCIM/Camera/"+strImgFilename);
                            uri = Uri.fromFile(file);

                            try {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                                intent.putExtra("return-data", true);

                                startActivityForResult(intent, REQUEST_CAMERA);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            dialog.cancel();
                        } else {
                            Intent intent = new Intent();

                            intent.setType("image*//**//*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);

                            startActivityForResult(Intent.createChooser(intent, "Complete action using"), GALLERY_PICTURE);
                        }
                    }
                } );

                alertBuild.show();*/
                Intent chooseImageIntent = ImagePicker.getPickImageIntent(getApplicationContext());
                startActivityForResult(chooseImageIntent,PICK_IMAGE_ID);
            }
        });

    }//OnCreate

    public void BlindWidget(){
        edtMonney = (EditText) findViewById(R.id.edtAdmoney);
        edtMonney.setTypeface(customFont);
        toolbar = (Toolbar) findViewById(R.id.Toobar_Descrip);
        TxtDatepicker = (TextView) findViewById(R.id.TxtDatePicker);
        edtNote = (EditText) findViewById(R.id.edtNote);
        edtNote.setTypeface(customFont);
        edtMonney.setText(Money);
        MoneyComp = edtMonney.getText().toString();
        img = (ImageView) findViewById(R.id.SelectImg);
        ImgCamera = (ImageView) findViewById(R.id.ImgCameraDes);
        ImgCameratest = (ImageView) findViewById(R.id.ImgCameraTest);

    }//BlindWidget

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK_IMAGE_ID :
                Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                ImgCameratest.setImageBitmap(bitmap);
                // TODO use bitmap
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney, menu);
        MenuItem item = menu.findItem(R.id.AddmoneyNext);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Note = edtNote.getText().toString();
        Date = TxtDatepicker.getText().toString();
        Money = edtMonney.getText().toString();
        String strImgPhoto = Integer.toString(imgPhoto);
        int CheckIncome = sp.getInt("IncomePosition", -1);
        int CheckTab = sp.getInt("CheckTab", 0);

        ck_table = new CK_TABLE(getApplicationContext());
        if (MoneyComp.equals(Money)) {
            Money = sp.getString("Money", null);
            Log.d("Money", "=" + Money);
        }
        if (CheckIncome == -1) {
            Category = Outsp.getString("OutcomeName", null);
            Nameid = Outsp.getString("OutcomeNameId", null);
            Log.d("Name", "=" + Category);
        } else {
            Category = sp.getString("IncomeName", null);
            Nameid = sp.getString("IncomeNameId", null);
            Log.d("Name", "=" + Category);
        }
        douMoney = Double.parseDouble(Money);
        Log.d("CheckTab", "=" + CheckTab);
        editor.clear();
        editor.commit();
        switch (item.getItemId()) {
            case R.id.OK:

                if (CheckTab == 2) {
                    ck_table.addNewValuesIncome(Date, Category, Nameid, "Dekcyber", Note, douMoney, strImgPhoto);
                    Intent In = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(In);
                    Log.d("Date", "=" + Date);
                    Log.d("Note", "=" + Note);
                    Log.d("photo", "=" + imgPhoto);
                    Log.d("CheckTab", "=" + CheckTab);
                } else if (CheckTab == 0) {
                    ck_table.addNewValuesOutcome(Date, Category, Nameid, "Dekcyber", Note, douMoney, strImgPhoto);
                    Intent In = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(In);
                    Log.d("Date", "=" + Date);
                    Log.d("Note", "=" + Note);
                    Log.d("photo", "=" + imgPhoto);
                    Log.d("CheckTab", "=" + CheckTab);

                }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new DatePickerDialog(this, Datesetpicker, intYear, intMonth, intDate);


        }
        return null;
    }

    public void ShowDialog(View view) {
        showDialog(DILOG_ID);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //--- Comment ไว้เพื่อไปเอาไปเคลียหน้า Overview
        /*Outeditor.remove("OutcomePhoto");
        Outeditor.commit();
        editor.remove("IncomePhoto");
        editor.commit();
        editor.remove("CheckTab");
        editor.commit();
        Outeditor.remove("OutcomeNameId");
        Outeditor.commit();
        editor.remove("IncomeNameId");
        editor.commit();*/
    }
}//MainClass
