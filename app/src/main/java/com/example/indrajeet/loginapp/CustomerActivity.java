package com.example.indrajeet.loginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerActivity extends AppCompatActivity {

    private int counter =5;
    private TextView info,tvlogin,tvForgotPassword;
    private Button Login;
    private EditText Username,Password;


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

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

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = Username.getText().toString();
                String password = Password.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {

                    Toast.makeText(CustomerActivity.this, "Please enter complete details", Toast.LENGTH_SHORT).show();
                } else {

                    validate(username, password);
                }
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerActivity.this,RegistrationActivity.class));

            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerActivity.this,PasswordActivity.class));
            }
        });

    }
    private void validate(String Username,String Password) {
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        if ((Username.equals("electricityboard@gmail.com")) && (Password.equals("1234EB"))) {
            Toast.makeText(CustomerActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CustomerActivity.this, ElectricityBoardActivity.class));
        }
        else
        if ((Username.equals("Admin@gmail.com")) && (Password.equals("1234AD"))) {
            Toast.makeText(CustomerActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CustomerActivity.this, AdminActivity.class));
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
                        Toast.makeText(CustomerActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(CustomerActivity.this, UserActivity.class));
        if(emailflag)
        {
            Toast.makeText(CustomerActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CustomerActivity.this,UserActivity.class));
        }
        else
        {


            Toast.makeText(CustomerActivity.this,"Please Verify your Email",Toast.LENGTH_SHORT).show();
            //firebaseAuth.signOut();

        }
    }
}
