package com.example.indrajeet.loginapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView electricitylogo,customer,admin,eboard,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        electricitylogo=(CardView)findViewById(R.id.Electricity);
        customer=(CardView)findViewById(R.id.customer);
        admin=(CardView)findViewById(R.id.admin);
        eboard=(CardView)findViewById(R.id.EB);
        register=(CardView)findViewById(R.id.Register);
        electricitylogo.setOnClickListener(this);
        customer.setOnClickListener(this);
        admin.setOnClickListener(this);
        eboard.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId())
        {
            case R.id.Electricity: startActivity(new Intent(this,HelpActivity.class));break;
            case R.id.customer: startActivity(new Intent(this,CustomerActivity.class));break;
            case R.id.admin: startActivity(new Intent(this,CustomerActivity.class));break;
            case R.id.EB: startActivity(new Intent(this,CustomerActivity.class));break;
            case R.id.Register: startActivity(new Intent(this,RegistrationActivity.class));break;

        }
    }
    /*private int counter =5;
    private TextView info,tvlogin,tvForgotPassword;
    private Button Login;
    private EditText Username,Password;


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username=(EditText) findViewById(R.id.etUsername);
        Password=(EditText) findViewById(R.id.etPassword);
         Login=(Button) findViewById(R.id.bLogin);
        info=(TextView) findViewById(R.id.tvInfo);
        tvlogin=(TextView)findViewById(R.id.tvLogin);
        tvForgotPassword=(TextView)findViewById(R.id.tvForgotPassword);
        info.setText("No. of attempts remaining: 5");
        firebaseAuth =FirebaseAuth.getInstance();
      // FirebaseUser user= firebaseAuth.getCurrentUser();


         progressDialog = new ProgressDialog(this);
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        /* if(firebaseUser!=null)
         {
             startActivity(new Intent(MainActivity.this,NavigationActivity.class));
         }*/

       /* Login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {

                                         String username = Username.getText().toString();
                                         String password = Password.getText().toString();

                                         if (username.isEmpty() || password.isEmpty()) {

                                             Toast.makeText(MainActivity.this, "Please enter complete details", Toast.LENGTH_SHORT).show();
                                         } else {

                                             validate(username, password);
                                         }
                                     }
                                 });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));

            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PasswordActivity.class));
            }
        });

    }
    private void validate(String Username,String Password) {
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

       if ((Username.equals("electricityboard@gmail.com")) && (Password.equals("1234EB"))) {
            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ElectricityBoardActivity.class));
        }
        else
        if ((Username.equals("Admin@gmail.com")) && (Password.equals("1234AD"))) {
            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, AdminActivity.class));
        }
        else
            {
            firebaseAuth.signInWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        //Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        // startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                        isemailVerified();

                    } else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        counter--;
                        info.setText("No. of attempts remaining: " + counter);
                        if (counter == 0)
                            Login.setEnabled(false);
                    }
                }
            });
        }
    }
    private void isemailVerified()
    {
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        Boolean emailflag= firebaseUser.isEmailVerified();
        startActivity(new Intent(MainActivity.this, UserActivity.class));
       if(emailflag)
       {
           Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,UserActivity.class));
       }
       else
       {


         Toast.makeText(MainActivity.this,"Please Verify your Email",Toast.LENGTH_SHORT).show();
           //firebaseAuth.signOut();

       }*/
    }


