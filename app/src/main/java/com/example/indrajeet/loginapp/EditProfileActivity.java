package com.example.indrajeet.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    private EditText Username, Age, Phone, Address;
    private Button editp;
    private FirebaseAuth firebaseAuth;
    String name, age, address, phone, consumerid, consumername, consumeremail, consumeradd, consumerage, consumerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setupUIviews();
        firebaseAuth = FirebaseAuth.getInstance();
        editp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = Username.getText().toString();
                age = Age.getText().toString();
                phone = Phone.getText().toString();
                address = Address.getText().toString().trim();

                // FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseAuth = FirebaseAuth.getInstance();
                String userID = firebaseAuth.getUid();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference yourRef = rootRef.child("Users").child(userID);
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        consumerid = dataSnapshot.child("userId").getValue(String.class);
                        consumername = dataSnapshot.child("userName").getValue(String.class);
                        consumeradd = dataSnapshot.child("userAddress").getValue(String.class);
                        consumeremail = dataSnapshot.child("userEmail").getValue(String.class);
                        consumerage = dataSnapshot.child("userAge").getValue(String.class);
                        consumerPhone = dataSnapshot.child("userPhone").getValue(String.class);


                       /* //Log.d("TAG", name + " / " + number + " / " + price);
                        tvid.setText("Consumer Id: "+name);
                        tvname.setText("Name: "+number);
                        tvaddress.setText("Address: "+price);*/
                        if (name.isEmpty() || age.isEmpty() || phone.isEmpty() || address.isEmpty())
                            Toast.makeText(EditProfileActivity.this, "Please enter complete details", Toast.LENGTH_SHORT).show();
                        else {
                            UserProfile userProfile = new UserProfile(consumerid, name, age, phone, address, consumeremail);
                            yourRef.setValue(userProfile);
                            Toast.makeText(EditProfileActivity.this, "Profile edited", Toast.LENGTH_LONG).show();
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                };
                yourRef.addListenerForSingleValueEvent(eventListener);

                // DatabaseReference myRef = firebaseDatabase.getReference("Users");
                // String id = myRef.push().getKey();
                //String id =firebaseAuth.getUid();


            }
        });

    }

    private void setupUIviews() {
        Username = (EditText) findViewById(R.id.etuserName);
        Age = (EditText) findViewById(R.id.etPassword);
        Phone = (EditText) findViewById(R.id.etEmailid);
        Address = (EditText) findViewById(R.id.eteditAddress);
        editp = (Button) findViewById(R.id.bedit);
    }


}
