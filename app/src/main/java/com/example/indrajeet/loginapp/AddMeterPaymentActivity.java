package com.example.indrajeet.loginapp;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class AddMeterPaymentActivity extends AppCompatActivity {

    Button buttonAddTrack;
    EditText editTextTrackName,editPaymentStatus,editPayment;

    TextView textViewArtist;
    ListView listViewTracks;
    Spinner spinnerGenre;
    DatabaseReference databaseTracks;

    List<Meter> tracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meter_payment);

        Intent intent = getIntent();


        databaseTracks = FirebaseDatabase.getInstance().getReference("Meter Payment Details").child(intent.getStringExtra(MeterReadingActivity.ARTIST_ID));
        spinnerGenre = (Spinner) findViewById(R.id.spinnerGenres);
        buttonAddTrack = (Button) findViewById(R.id.buttonAddTrack);
        editTextTrackName = (EditText) findViewById(R.id.editTextTrackName);
        editPaymentStatus = (EditText) findViewById(R.id.editPaymentStatus);
        editPayment = (EditText) findViewById(R.id.editPayment);
        textViewArtist = (TextView) findViewById(R.id.textViewArtist);
        listViewTracks = (ListView) findViewById(R.id.listViewTracks);

        tracks = new ArrayList<>();
        String gettingID=intent.getStringExtra(MeterReadingActivity.ARTIST_ID);

        textViewArtist.setText(intent.getStringExtra(MeterReadingActivity.ARTIST_NAME ));
                //+'('+gettingID+')');



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

        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tracks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Meter track = postSnapshot.getValue(Meter.class);
                    tracks.add(track);
                }
                MeterList trackListAdapter = new MeterList(AddMeterPaymentActivity.this, tracks);
                listViewTracks.setAdapter(trackListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveTrack() {
        String trackName = editTextTrackName.getText().toString().trim();
        String  paymentStatus = editPaymentStatus.getText().toString().trim();
        String payment = editPayment.getText().toString().trim();
        String genre = spinnerGenre.getSelectedItem().toString();
       // int rating = seekBarRating.getProgress();
        if (!TextUtils.isEmpty(trackName)) {
            String id  = databaseTracks.push().getKey();

            Meter track = new Meter(id, genre,trackName,paymentStatus,payment);
            databaseTracks.child(genre).setValue(track);
            Toast.makeText(this, "Meter and payment details saved", Toast.LENGTH_LONG).show();
            editTextTrackName.setText("");
            editPaymentStatus.setText("");
            editPayment.setText("");
        } else {
            Toast.makeText(this, "Please enter complete details", Toast.LENGTH_LONG).show();
        }
    }
}