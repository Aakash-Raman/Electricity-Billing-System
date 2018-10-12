package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

/**
 * Created by User on 2/8/2017.
 */

public class MeterForBillActivity extends AppCompatActivity {
    Button buttonAddTrack;
    EditText editTextTrackName;
    Spinner spinnerGenre;
    TextView textViewArtist, tv;
    ListView listViewMeters;
    String gettingID,gettingName;
    DatabaseReference databaseMeter,databaseBill;

    List<Meter> meters;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_for_bill);

        Intent intent = getIntent();
        databaseMeter = FirebaseDatabase.getInstance().getReference("Meter Payment Details").child(intent.getStringExtra(BillGenActivity.CONSUMER_ID));
        databaseBill = FirebaseDatabase.getInstance().getReference("Electricity Bill").child(intent.getStringExtra(BillGenActivity.CONSUMER_ID));
        buttonAddTrack = (Button) findViewById(R.id.buttonAddTrackC);
        editTextTrackName = (EditText) findViewById(R.id.editTextNameC);

        textViewArtist = (TextView) findViewById(R.id.textViewConsumer);
        tv = (TextView) findViewById(R.id.textView5);

        listViewMeters = (ListView) findViewById(R.id.listViewMeters);
        spinnerGenre = (Spinner) findViewById(R.id.spinnerGenresC);


        meters = new ArrayList<>();

        textViewArtist.setText(intent.getStringExtra(BillGenActivity.CONSUMER_NAME));
        gettingID = intent.getStringExtra(BillGenActivity.CONSUMER_ID);
        gettingName=intent.getStringExtra(BillGenActivity.CONSUMER_NAME);
        tv.setText("ConsumerID - " + gettingID);


        buttonAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveTrack();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseMeter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                meters.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Meter track = postSnapshot.getValue(Meter.class);
                    meters.add(track);
                }
                MeterList trackListAdapter = new MeterList(MeterForBillActivity.this, meters);
                listViewMeters.setAdapter(trackListAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveTrack() {

        String genre = spinnerGenre.getSelectedItem().toString();
        String d= editTextTrackName.getText().toString();

        if (!TextUtils.isEmpty(d)) {
            double meter=Double.parseDouble(d);

            String id = databaseBill.push().getKey();





            BillGenerator bg = new BillGenerator(meter);
            double Bill = bg.main();
            String bill=String.valueOf(Bill);
            Bill track = new Bill(id,gettingName,genre,bill);
            databaseBill.child(genre).setValue(track);
            Intent intent =new Intent(MeterForBillActivity.this,ViewBillForAdminActivity.class);
            intent.putExtra("USER_ID",gettingID);
            intent.putExtra("USER_NAME",gettingName);
            intent.putExtra("MONTH",genre);
            intent.putExtra("BILL",bill);
            startActivity(intent);

             Toast.makeText(this, "Bill generated", Toast.LENGTH_LONG).show();
             editTextTrackName.setText("");
        }
         else{
            Toast.makeText(this, "Please enter Meter Reading", Toast.LENGTH_LONG).show();
        }

    }
}
