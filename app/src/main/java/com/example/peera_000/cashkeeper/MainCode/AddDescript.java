package com.example.peera_000.cashkeeper.MainCode;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
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
import com.example.peera_000.cashkeeper.Map.Map;
import com.example.peera_000.cashkeeper.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddDescript extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
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
    private ImageView ImgMap;
    private ImageView ImgPicmap;
    private TextView TxtDatepicker;
    private EditText edtNote;
    private TextView TxtPlace;
    private int intDate;
    private int intMonth;
    private int intYear;
    private static final int DILOG_ID = 0;
    private static final int REQUEST_CAMERA = 2;
    private static final int GALLERY_PICTURE = 1;
    private static final int PICK_IMAGE_ID = 234;
    private static final int PLACE_PICKER_REQUEST = 4;
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
    Camera mcamera;
    private String mCurrentPhotoPath;
    private GoogleApiClient mGoogleApiClient;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        //ConnectApi
        ConnectGoogleApi();
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
        final String[] items = new String[]{"From Camera", "From SD Card"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, items);
        ImgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //เปิดใช้กล้อง ---->1
               /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String strTimestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String strImgFilename = "IMG_"+strTimestamp+".jpg";
                File file = new File(Environment.getExternalStorageDirectory(),"DCIM/Camera/"+strImgFilename);
                uri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(Intent.createChooser(intent,"Take picture with"),REQUEST_CAMERA);*/
                //จบเปิดใช้กล้อง ---->1

                //เปิดใช้ gallery ---->2
                /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image*//**//**//**//*");
                startActivityForResult(Intent.createChooser(intent
                        , "Select photo from"), 0); */
                //จบเปิดใช้ gallery ---->2

                //สร้าง Dialog เลือกระหว่างกล้องกับ gallery ---->3

                /*AlertDialog.Builder alertBuild = new AlertDialog.Builder(AddDescript.this);
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

                            intent.setType("image*//**//**//**//*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);

                            startActivityForResult(Intent.createChooser(intent, "Complete action using"), GALLERY_PICTURE);
                        }
                    }
                } );

                alertBuild.show();*/
                //จบ เลือกใช้ระหว่างกล้องกับ gallery ---->3


                //-----ใช้ ImagePicker ช่วย-----  ---->4
               /* Intent chooseImageIntent = ImagePicker.getPickImageIntent(getApplicationContext());
                startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);*/
                //----- จบ ImagePicker  ----- ---->4

            SelectPhoto();
            }
        });
        TxtPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker();
               /* Intent intent = new Intent(getApplicationContext(),Map.class);
                startActivity(intent);*/
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }//OnCreate
    public void SelectPhoto(){
        final CharSequence[]item ={getString(R.string.Take_Photo),getString(R.string.Choose_from_gallery),getString(R.string.Cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_image_intent_text);
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (item[which].equals(getString(R.string.Take_Photo))){
                    /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);*/
                    dispatchTakePictureIntent();
                }else if (item[which].equals(getString(R.string.Choose_from_gallery))){
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent,"Select File"),GALLERY_PICTURE);
                }else if (item[which].equals(getString(R.string.Cancel))){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_CAMERA);
            }
        }
    }
    public File createImageFile() throws IOException {
        // Create an image file name
        /*String strTimestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String strImgFilename = "PocketM_"+strTimestamp+".jpg";
        File file = new File(Environment.getExternalStorageDirectory(),"PocketManagement/Picture/"+strImgFilename);*/
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PocketM_" + timeStamp;
        File storageDir = Environment.getExternalStoragePublicDirectory("PocketManagement/Picture/");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d("AbsolutePath","= "+mCurrentPhotoPath);
        return image;
    }

    public void PlacePicker() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public void BlindWidget() {
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
        TxtPlace = (TextView) findViewById(R.id.TxtPlace);
        TxtPlace.setTypeface(customFont);
    }//BlindWidget

    public void ConnectGoogleApi() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }
    private void setPic() {
        // Get the dimensions of the View
        int targetW = ImgCameratest.getWidth();
        int targetH = ImgCameratest.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        ImgCameratest.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // รับค่าจาก กล้อง ---->1
        /*if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            getContentResolver().notifyChange(uri, null);
            ContentResolver cr = getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(cr, uri);
                ImgCameratest.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext()
                        , uri.getPath(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/ //จบรับค่าจากกล้อง ---->1


        //รับค่าจาก ImagePicker ---->4

        switch (requestCode) {
            /*case PICK_IMAGE_ID:
                Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                ImgCameratest.setImageBitmap(bitmap);
                String strTimestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String strImgFilename = "PocketM_" + strTimestamp + ".jpg";
                File file = new File(Environment.getExternalStorageDirectory(), "PocketManagement/Picture/" + strImgFilename);
                FileOutputStream fOut = null;
                try {
                    fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // TODO use bitmap*/
            case REQUEST_CAMERA:
              /*  Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String strTimestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String strImgFilename = "PocketM_" + strTimestamp + ".jpg";
                File destination = new File(Environment.getExternalStorageDirectory(),
                        "PocketManagement/Picture/" + strImgFilename);
                FileOutputStream fo = null;
                try {
                    fo = new FileOutputStream(destination);
                    thumbnail.compress(Bitmap.CompressFormat.JPEG,100, fo);
                    fo.flush();
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImgCameratest.setImageBitmap(thumbnail);*/
               /* if (resultCode==RESULT_OK){
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                ImgCameratest.setImageBitmap(imageBitmap);
                }*/
                setPic();
                break;
            case GALLERY_PICTURE:
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.MediaColumns.DATA };
                CursorLoader cursorLoader = new CursorLoader(this,selectedImageUri, projection, null, null,
                        null);
                Cursor cursor =cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                ImgCameratest.setImageBitmap(bm);
                break;
            case PLACE_PICKER_REQUEST:
                if (resultCode == RESULT_OK) {
                    Place place = PlacePicker.getPlace(data, this);
                    TxtPlace.setText(place.getName());
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

        //จบรับค่าจาก imagePicker ---->4

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
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onStop() {

        super.onStop();
        mGoogleApiClient.disconnect();
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Cannot ConnectGoogleAPi", Toast.LENGTH_SHORT);
        Log.d("Google Api", "Connection =" + connectionResult);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}//MainClass
