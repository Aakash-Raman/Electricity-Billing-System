package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordActivity extends AppCompatActivity {
  private EditText email;
  private Button resetpassword;
  private  FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        email=(EditText)findViewById(R.id.etPasswordForgot);
        resetpassword=(Button)findViewById(R.id.bPasswordForgot);
         firebaseAuth= FirebaseAuth.getInstance();
         resetpassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String useremail=email.getText().toString().trim();
                 if(useremail.isEmpty())
                 {
                     Toast.makeText(PasswordActivity.this,"Please fill your registered Email",Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful())
                             {

                                 Toast.makeText(PasswordActivity.this,"Password Reset email sent",Toast.LENGTH_SHORT).show();
                                 finish();
                                 startActivity(new Intent(PasswordActivity.this,MainActivity.class));
                             }
                             else
                             {

                                 Toast.makeText(PasswordActivity.this,"Error in sending Reset Password Email",Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }
             }
         });

    }

}
