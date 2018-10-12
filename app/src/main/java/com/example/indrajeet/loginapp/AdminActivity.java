package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    //private Button logout;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
       // logout =(Button) findViewById(R.id.bLogout);
        firebaseAuth=FirebaseAuth.getInstance();
        //logout.setOnClickListener(new View.OnClickListener() {
           /* @Override
            public void onClick(View v) {
                Logout();
            }
        });*/
    }
    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.Adminprofile:
            {
                startActivity(new Intent(this,ViewFeedbackActivity.class));
                break;
            }
            case R.id.GenerateBill:
            {
                startActivity(new Intent(this,BillGenActivity.class));
                break;
            }
            case R.id.adminlogout:
            {
                Logout();
                break;
            }
        }
        return true;
    }
}
