package com.example.indrajeet.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPaymentActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef, databasebill;
    Spinner spinnermonth;
    TextView tvid, tvname, tvaddress, tvcname, tvpayment, tvmonth;
    String name;
    Button bpView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);


        spinnermonth = (Spinner) findViewById(R.id.spinnerMonth);
        tvid = (TextView) findViewById(R.id.tvpId);
        tvname = (TextView) findViewById(R.id.tvpname);
        tvaddress = (TextView) findViewById(R.id.tvpaddress);
        tvcname = (TextView) findViewById(R.id.tvpcname);
        tvpayment = (TextView) findViewById(R.id.tvpayment);
        tvmonth = (TextView) findViewById(R.id.tvpmonth);
        bpView = (Button) findViewById(R.id.bpView);
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
                tvid.setText("Consumer Id: "+name);
                tvname.setText("Name: "+number);
                tvaddress.setText("Address: "+price);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        yourRef.addListenerForSingleValueEvent(eventListener);


        bpView.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                viewPaymentStatus();
            }
        });
    }

    public void viewPaymentStatus()
    {
        String month = spinnermonth.getSelectedItem().toString();
//        tvmonth.setText(month);

        DatabaseReference rootRef2 = FirebaseDatabase.getInstance().getReference();
        DatabaseReference yourRef2 = rootRef2.child("Meter Payment Details").child(name).child(month);
        ValueEventListener eventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String meterReading = dataSnapshot.child("meterReading").getValue(String.class);
                String paymentStatus = dataSnapshot.child("editPaymentStatus").getValue(String.class);
                String payment = dataSnapshot.child("editPayment").getValue(String.class);

                //Log.d("TAG", name + " / " + number + " / " + price);
                tvcname.setText("Meter Reading : "+meterReading);
                tvmonth.setText("Payment Status : "+paymentStatus);
                tvpayment.setText("Made Payment : "+payment);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        };
        yourRef2.addListenerForSingleValueEvent(eventListener2);

    }

}
