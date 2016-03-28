package com.example.peera_000.cashkeeper.EditRowOverview;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.MainActivity;
import com.example.peera_000.cashkeeper.MainCode.AddDescript;
import com.example.peera_000.cashkeeper.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DeflaterOutputStream;

public class EditDescript extends AppCompatActivity {
    //Explicit

    private EditText edtMonney;
    private Toolbar toolbar;
    private ImageView img;
    private TextView TxtDatepicker;
    private EditText edtNote;
    private TextView txtPlace;
    private ImageView ImgPathPhotoDes;
    private ImageView ImgPathPhotoTest;
    private int intDate;
    private int intMonth;
    private int intYear;
    private static final int DILOG_ID = 0;
    private static final int PLACE_PICKER_REQUEST = 3;
    private static final int REQUEST_CAMERA = 1;
    private static final int GALLERY_PICTURE = 2;
    private String mCurrentPhotoPath;
    private static DatePickerDialog.OnDateSetListener Datesetpicker;
    private String Money;
    private String MoneyComp;
    private String Category;
    private String Note;
    private String Date;
    private String Place;
    private String strPlace;
    private String strEditPlace;
    private String strPathPhoto;
    private CharSequence NamePlace;
    private int imgPhoto;
    private CK_TABLE ck_table;
    double douMoney;
    private String Nameid;
    private Typeface customFont;
    private SharedPreferences Edsp;
    private SharedPreferences.Editor edit_Edsp;
    private String strID;
    private String strCate;
    private String strCateId;
    private int intPhoto;
    private String PathPhoto ;
    private AddDescript addDescript = new AddDescript();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        Edsp = getSharedPreferences("EditRowOverData", MODE_PRIVATE);
        edit_Edsp = Edsp.edit();
        BlindWidget();
        CallRowdataTotext();
        CalendarEdit();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Money = edtMonney.getText().toString();
                Date = TxtDatepicker.getText().toString();
                edit_Edsp.putString("DateN", Date);
                edit_Edsp.putString("IncomeM", Money);//SharedPreference ตัวนี้เพราะตอนกลับมาค่อยเช็คกับหมวดอีกที
                Log.d("EditRowOverData", "Money = " + Money);
                Note = edtNote.getText().toString();
                edit_Edsp.putString("Note", Note);
                Place = txtPlace.getText().toString();
                edit_Edsp.putString("EditPlace", Place);
                edit_Edsp.commit();
                Log.d("EditRowOverData", "Note = " + Note);
                Intent intent = new Intent(EditDescript.this, EditmoneyCate.class);
                startActivity(intent);
            }
        });
        txtPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker();
            }
        });
        ImgPathPhotoDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPhoto();
            }
        });


    }//OnCreate
    public void SelectPhoto(){
        final CharSequence[]item ={getString(R.string.Take_Photo),getString(R.string.Choose_from_gallery),getString(R.string.Cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_image_intent_text);
        builder.setItems(item, new DialogInterface.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(DialogInterface dialog, int which) {
                if (item[which].equals(getString(R.string.Take_Photo))) {
                    /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CAMERA);*/
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        dispatchTakePictureIntent();
                    } else {
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA);
                    }
                } else if (item[which].equals(getString(R.string.Choose_from_gallery))) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Select File"), GALLERY_PICTURE);
                } else if (item[which].equals(getString(R.string.Cancel))) {
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
        Log.d("AbsolutePath", "= " + mCurrentPhotoPath);
        return image;
    }
    private File setUpPhotoFile() throws IOException {

        File f = createImageFile();
        mCurrentPhotoPath = f.getAbsolutePath();
        Log.d("AbsolutePath","= "+mCurrentPhotoPath);
        return f;
    }
    private void setPic() {
        int targetW = ImgPathPhotoTest.getWidth();
        int targetH = ImgPathPhotoTest.getHeight();

		/* Get the size of the image */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

		/* Figure out which way needs to be reduced less */
        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

		/* Set bitmap options to scale the image decode target */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

		/* Associate the Bitmap to the ImageView */
        ImgPathPhotoTest.setImageBitmap(bitmap);
        ImgPathPhotoTest.setVisibility(View.VISIBLE);
        edit_Edsp.putString("EditPathPhoto", mCurrentPhotoPath);
        edit_Edsp.commit();
    }
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
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

    public void CallRowdataTotext() {
        Bundle bundle = getIntent().getExtras();
        strID = bundle.getString("ID");
        Log.d("Edit", "RowId = " + strID);
        edit_Edsp.putString("EditRowId", strID);
        //edit_Edsp.commit();
        Cursor objReadrowCursor = ck_table.readRowOfId(strID);

        int IncomeMIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Income);
        String strIncomeM = objReadrowCursor.getString(IncomeMIndex);
        edit_Edsp.putString("CheckCate", strIncomeM);
        edit_Edsp.commit();
        //Log.d("Edit", "MoneyIn = " + strIncomeM);
        int OutcomeMIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Outcome);
        String strOutcomeM = objReadrowCursor.getString(OutcomeMIndex);
        //Log.d("Edit", "MoneyOut = " + strOutcomeM);
        if (strIncomeM == null) {
            Money = strOutcomeM;

            Log.d("Edit", "MoneyOut = " + strOutcomeM);
        } else {
            Money = strIncomeM;
            Log.d("Edit", "MoneyIn = " + strIncomeM);
        }
        MoneyComp = Edsp.getString("IncomeMChange", null);
        Log.d("Edit", "MoneyChange = " + MoneyComp);
        if (MoneyComp != null) {
            Money = MoneyComp;
            Log.d("Edit", "MoneyChange = " + Money);
        }
        edtMonney.setText(Money);

        int NoteIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Note);
        String strNote = objReadrowCursor.getString(NoteIndex);
        Note = Edsp.getString("Note", null);
        if (Note != null) {
            edtNote.setText(Note);
        } else {
            if (strNote != null) {
                edtNote.setText(strNote);
                edit_Edsp.putString("Note", strNote);
            }
        }
        int DateIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_InputDate);
        String strDate = objReadrowCursor.getString(DateIndex);
        edit_Edsp.putString("Date", String.valueOf(strDate));
        edit_Edsp.commit();
        String DateN = Edsp.getString("DateN", null);
        Date = Edsp.getString("Date", null);
        Log.d("Edit", "DateNew = " + Date);
        if (Date == DateN) {

            TxtDatepicker.setText(Date);
            Log.d("Edit", "Date = " + Date);
        } else {
            if (DateN != null) {
                Date = DateN;
                TxtDatepicker.setText(Date);
            }
            TxtDatepicker.setText(Date);
            Log.d("Edit", "DateNew = " + Date);
        }
        int ImgIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_CPhoto);
        String strImg = objReadrowCursor.getString(ImgIndex);
        imgPhoto = Integer.parseInt(strImg);
        edit_Edsp.putInt("Photo", imgPhoto);
        int CateIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Cate);
        strCate = objReadrowCursor.getString(CateIndex);
        int CateIdIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_CateID);
        strCateId = objReadrowCursor.getString(CateIdIndex);

        intPhoto = Edsp.getInt("EditIncomePhoto", 0);
        if (intPhoto != 0) {
            strCate = Edsp.getString("EditIncomeName", null);
            Log.d("Edit", "CateName = " + strCate);
            strCateId = Edsp.getString("EditIncomeNameId", null);
            Log.d("Edit", "CateNameID = " + strCateId);
            imgPhoto = intPhoto;
            edit_Edsp.putInt("Photo", imgPhoto);
        }
        img.setImageResource(imgPhoto);

        int PlaceIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Place);
        strPlace = objReadrowCursor.getString(PlaceIndex);
        Place = Edsp.getString("EditPlace",null);
        if (Place!=null){
            txtPlace.setText(Place);
            strEditPlace = Place;
        }else {
            if (strPlace!=null){
                txtPlace.setText(strPlace);
                strEditPlace = strPlace;
            }
        }

        int PathPhotoIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_PathPhoto);
        strPathPhoto = objReadrowCursor.getString(PathPhotoIndex);
        Log.d("AbsolutePath","EditPath = "+ strPathPhoto);
        PathPhoto = Edsp.getString("EditPathPhoto",null);
        if (PathPhoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(PathPhoto);
            ImgPathPhotoTest.setImageBitmap(bitmap);
        }else {
            if (strPathPhoto!=null){
                Bitmap bitmap = BitmapFactory.decodeFile(strPathPhoto);
                ImgPathPhotoTest.setImageBitmap(bitmap);
            }
        }

        edit_Edsp.commit();

    }//CallRowOfId


    public void BlindWidget() {
        ck_table = new CK_TABLE(this);
        img = (ImageView) findViewById(R.id.SelectImg);
        edtMonney = (EditText) findViewById(R.id.edtAdmoney);
        edtMonney.setTypeface(customFont);
        toolbar = (Toolbar) findViewById(R.id.Toobar_Descrip);
        TxtDatepicker = (TextView) findViewById(R.id.TxtDatePicker);
        edtNote = (EditText) findViewById(R.id.edtNote);
        txtPlace = (TextView) findViewById(R.id.TxtPlace);
        ImgPathPhotoDes = (ImageView) findViewById(R.id.ImgCameraDes);
        ImgPathPhotoTest = (ImageView) findViewById(R.id.ImgCameraTest);
        txtPlace.setTypeface(customFont);
        edtNote.setTypeface(customFont);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
    }//BlindWidget

    public void CalendarEdit() {
        Calendar calendar = Calendar.getInstance();
        intYear = calendar.get(Calendar.YEAR);
        intMonth = calendar.get(Calendar.MONTH);
        intDate = calendar.get(Calendar.DAY_OF_MONTH);
        TxtDatepicker.setTypeface(customFont);
        //TxtDatepicker.setText(intYear + "/" + (intMonth + 1) + "/" + intDate);


        Datesetpicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                intYear = year;
                intMonth = monthOfYear + 1;
                intDate = dayOfMonth;
                TxtDatepicker.setText(intYear + "/" + intMonth + "/" + intDate);
                edit_Edsp.putString("Date", String.valueOf(TxtDatepicker));
                Log.d("EditRowOverData", "Date" + TxtDatepicker);
            }
        };

    }
    private void handleBigCameraPhoto() {

        if (mCurrentPhotoPath != null) {
            Log.d("AbsolutePath","handleBigCameraPhoto= "+mCurrentPhotoPath);
            setPic();
            galleryAddPic();
            mCurrentPhotoPath = null;
        }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney, menu);
        MenuItem item = menu.findItem(R.id.AddmoneyNext);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                dispatchTakePictureIntent();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PLACE_PICKER_REQUEST:
                if (resultCode == RESULT_OK) {
                    Place place = PlacePicker.getPlace(data, this);
                    NamePlace = place.getName();
                    txtPlace.setText(NamePlace);

                }
                break;
            case REQUEST_CAMERA:
                handleBigCameraPhoto();
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
                ImgPathPhotoTest.setImageBitmap(bm);
                PathPhoto = selectedImagePath;
                edit_Edsp.putString("EditPathPhoto",selectedImagePath);
                edit_Edsp.commit();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Date = TxtDatepicker.getText().toString();
        String strPlace;
        if (NamePlace!=null) {
            strPlace = NamePlace.toString();
        }else {
            strPlace = null;
        }
        if (Date == null) {
            Date = Edsp.getString("Date", null);
        }
        Note = edtNote.getText().toString();
        if (Note == null) {
            Note = Edsp.getString("Note", null);
        }
        Double douMoney = Double.parseDouble(Money);
        String strcheckM = edtMonney.getText().toString();
        Double douCheckM = Double.parseDouble(strcheckM);
        if (douMoney != douCheckM) {
            douMoney = douCheckM;
        }

        String strPhoto = Integer.toString(imgPhoto);
        String strRowId = strID;
        int intChecktab = Edsp.getInt("EditCheckTab", 0);
        Log.d("Edit", "CheckTab = " + intChecktab);
        switch (item.getItemId()) {

            case R.id.OK:
                if (intChecktab == 2) {
                    ck_table.EditRowDataIncome(Date, strCate, strCateId, Note, douMoney, strPhoto,strPlace,null,strRowId);
                    Toast.makeText(this, "CLick", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    edit_Edsp.clear();
                    edit_Edsp.commit();
                    startActivity(intent);
                } else if (intChecktab == 1 || intChecktab == 0) {
                    ck_table.EditRowDataOutcome(Date, strCate, strCateId, Note, douMoney, strPhoto,strPlace,null, strRowId);
                    Toast.makeText(this, "CLick", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    edit_Edsp.clear();
                    edit_Edsp.commit();
                    startActivity(intent);
                }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostResume() {

        super.onPostResume();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}//MainClass
