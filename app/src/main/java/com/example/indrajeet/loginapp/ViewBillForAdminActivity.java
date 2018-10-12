package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBillForAdminActivity extends AppCompatActivity {

    TextView textViewArtist, tv,tv2,tv3;
    // ListView listViewTracks;
    String getID;
    DatabaseReference databaseTracks;

    // List<Bill> tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        Intent intent = getIntent();


        databaseTracks = FirebaseDatabase.getInstance().getReference("Electricity Bill");
        //.child(intent.getStringExtra(MeterForBillActivity.USER_ID));
        textViewArtist = (TextView) findViewById(R.id.textViewConsumer);
        tv = (TextView) findViewById(R.id.textView5);
        tv2= (TextView) findViewById(R.id.textView6);
        tv3= (TextView) findViewById(R.id.textView7);
        // listViewTracks = (ListView) findViewById(R.id.listViewTracksC);


        //  tracks = new ArrayList<>();

        // textViewArtist.setText(intent.getStringExtra(BillGenActivity.CONSUMER_NAME ));
        String id = intent.getStringExtra("USER_ID");
        String month = intent.getStringExtra("MONTH");
        String name = intent.getStringExtra("USER_NAME");
        String bill = intent.getStringExtra("BILL");
        textViewArtist.setText(id);
        // gettingID=intent.getStringExtra(BillGenActivity.CONSUMER_ID);
        tv.setText("month- " + month);
        tv2.setText("Consumer Name- " + name);
        tv3.setText("bill- " + bill+" Rs..");
    }
}








