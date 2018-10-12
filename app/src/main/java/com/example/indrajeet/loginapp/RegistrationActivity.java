package com.example.indrajeet.loginapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
public class RegistrationActivity extends AppCompatActivity {


    private EditText Username,Password,Email,Address,Age,Phone;
    private TextView tvRegister;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    String name,email,address,password,age,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIviews();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    String email=Email.getText().toString().trim();
                    String password=Password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendEmailVerification();
                            }
                            else {
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,CustomerActivity.class));
            }
        });
    }

    private void setupUIviews()
    {
        Username=(EditText)findViewById(R.id.etuserName);
        Password=(EditText)findViewById(R.id.etPassword);
        Email=(EditText)findViewById(R.id.etEmailid);
        Address=(EditText)findViewById(R.id.etuserAddress);
        Age=(EditText)findViewById(R.id.etage);
        Phone=(EditText)findViewById(R.id.etPhoneNo);
        tvRegister=(TextView)findViewById(R.id.tvRegister);
        signup=(Button)findViewById(R.id.bedit);
    }
    private Boolean validate()
    {   Boolean valid=false;
        name=Username.getText().toString();
        password=Password.getText().toString();
         email=Email.getText().toString();
        address =Address.getText().toString();
        age=Age.getText().toString();
        phone =Phone.getText().toString();
        if(name.isEmpty() || password.isEmpty() ||email.isEmpty()||address.isEmpty()||age.isEmpty()||phone.isEmpty())
        {
            Toast.makeText(this,"Please enter complete details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            valid=true;

        }
      return valid;
    }
    private void sendEmailVerification()
    {
        final FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {     progressDialog.setMessage("Registering...");
              progressDialog.show();

            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {   sendUserData();
                         progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, "successfully Registered,Verification mail sent", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this, "Verification mail hasn't been sent!", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void sendUserData(){

        Intent intent = getIntent();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Users").child(firebaseAuth.getUid());
       // DatabaseReference myRef = firebaseDatabase.getReference("Users");
        String id = myRef.push().getKey();
        //String id =firebaseAuth.getUid();
        UserProfile userProfile = new UserProfile(id, name,age,phone,address,email);
        myRef.setValue(userProfile);
    }
}
