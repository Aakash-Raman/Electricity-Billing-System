package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ElectricityBoardActivity extends AppCompatActivity {

        private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_board);
        firebaseAuth=FirebaseAuth.getInstance();

    }
    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(ElectricityBoardActivity.this, MainActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId())
         {

             case R.id.provide_MR:
             {
                 startActivity(new Intent(this,MeterReadingActivity.class));
                 break;
             }
             case R.id.logoutEB:
             {
                 Logout();
                 break;
             }

         }
        return true;
    }
}
