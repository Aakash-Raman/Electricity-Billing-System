package com.example.indrajeet.loginapp;



import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.io.File;
import java.util.List;


/**
 * Created by User on 2/8/2017.
 */

public class ViewBillActivity extends AppCompatActivity {
   // private static final String TAG = "ViewDatabase";
    static final int READ_BLOCK_SIZE = 100;
    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef, databasebill;
    Spinner spinnermonth;
    TextView tvid, tvname, tvaddress, tvcname, tvbill, tvmonth;
    //tvdownload;
    //ListView mListView;
    // List<Bill> tracks;
    String name, bill2,name2,month2;
    Button bView, bdownload;
    private static final String TAG = "PdfCreatorActivity";

    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill);

        // mListView = findViewById(R.id.listViewProfile);
        spinnermonth = (Spinner) findViewById(R.id.spinnerMonth);
        tvid = (TextView) findViewById(R.id.tvId);
        tvname = (TextView) findViewById(R.id.tvname);
        // bdownload=(Button) findViewById(R.id.button_create);
        tvaddress = (TextView) findViewById(R.id.tvaddress);
        tvcname = (TextView) findViewById(R.id.tvcname);
        tvbill = (TextView) findViewById(R.id.tvbill);
        tvmonth = (TextView) findViewById(R.id.tvmonth);
        bView = (Button) findViewById(R.id.bView);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be usable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();



        String userID = mAuth.getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference yourRef = rootRef.child("Users").child(userID);


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("userId").getValue(String.class);
                String number = dataSnapshot.child("userName").getValue(String.class);
                String price = dataSnapshot.child("userAddress").getValue(String.class);

                //Log.d("TAG", name + " / " + number + " / " + price);
                tvid.setText("Consumer Id: " + name);
                tvname.setText("Name: " + number);
                tvaddress.setText("Address: " + price);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        yourRef.addListenerForSingleValueEvent(eventListener);


        bView.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                viewBill();
            }
        });
    }

    //databasebill = FirebaseDatabase.getInstance().getReference("Electricity Bill").child(name);
    public void viewBill() {
        String month = spinnermonth.getSelectedItem().toString();
        // tvmonth.setText(month);

        DatabaseReference rootRef2 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference yourRef2 = rootRef2.child("Electricity Bill").child(name).child(month);
        ValueEventListener eventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 name2 = dataSnapshot.child("name").getValue(String.class);
                bill2 = dataSnapshot.child("bill").getValue(String.class);
                 month2 = dataSnapshot.child("consumerGenre").getValue(String.class);

                //Log.d("TAG", name + " / " + number + " / " + price);
                tvcname.setText("Username :  " + name2);
                tvbill.setText("Electricity Bill : " + bill2+" Rs.");
                tvmonth.setText("Month : " + month2);
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                //startActivity(new Intent(ViewBillActivity.this,PdfCreatorActivity.class));



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        };


        yourRef2.addListenerForSingleValueEvent(eventListener2);
    }



    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }

        pdfFile = new File(docsFolder.getAbsolutePath(),"ElectricityBill.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        document.add(new Paragraph("Consumer ID: " +name));
        document.add(new Paragraph("Consumer Name : " +name2));
        document.add(new Paragraph("Month : " +month2));
        document.add(new Paragraph("Bill : " +bill2));

         Toast.makeText(ViewBillActivity.this,"Bill saved Successfully",Toast.LENGTH_LONG).show();
        document.close();
        //previewPdf();

    }

    /*private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }*/





}







