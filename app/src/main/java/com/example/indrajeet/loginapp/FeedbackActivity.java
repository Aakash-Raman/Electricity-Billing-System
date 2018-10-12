package com.example.indrajeet.loginapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackActivity extends AppCompatActivity {
    RatingBar rbfeedback;
    TextView tvfeedback;
    FirebaseDatabase mFirebaseDatabase;
    private  FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog progressDialog;
     DatabaseReference myRef,rootRef,yourRef;
    String rate,consumerid,consumername,consumeremail,consumeradd,consumerage,consumerPhone;;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        rbfeedback = (RatingBar) findViewById(R.id.rbfeedback);

        tvfeedback = (TextView) findViewById(R.id.tvfeedback);
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        String userID = mAuth.getUid();
       rootRef = FirebaseDatabase.getInstance().getReference();
       yourRef = rootRef.child("Users").child(userID);


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                consumerid= dataSnapshot.child("userId").getValue(String.class);
                progressDialog.dismiss();


                //String number = dataSnapshot.child("userName").getValue(String.class);
                //String price = dataSnapshot.child("userAddress").getValue(String.class);

                //Log.d("TAG", name + " / " + number + " / " + price);
                tvfeedback.setText("Give rating to us\n"+"Consumer Id: " + consumerid);

                //tvname.setText("Name: "+number);
                //tvaddress.setText("Address: "+price);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        yourRef.addListenerForSingleValueEvent(eventListener);


        rbfeedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = String.valueOf(rating);
                FeedbackSetter fb = new FeedbackSetter(consumerid,rate);
                myRef= FirebaseDatabase.getInstance().getReference("Feedbacks").child(consumerid);
                myRef.setValue(fb);
                Toast.makeText(FeedbackActivity.this,"Thanks for giving feedback",Toast.LENGTH_LONG).show();

            }
        });


    }




}
