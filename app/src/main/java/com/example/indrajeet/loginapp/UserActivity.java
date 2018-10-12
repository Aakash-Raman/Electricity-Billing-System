package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView editProfile, viewbills, viewpaymentStatus, feedback, logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firebaseAuth=FirebaseAuth.getInstance();
        editProfile = (CardView) findViewById(R.id.EditProfile);
        viewbills = (CardView) findViewById(R.id.ViewBills);
        viewpaymentStatus = (CardView) findViewById(R.id.ViewPaymentStatus);
        feedback = (CardView) findViewById(R.id.Feedback);
        logout = (CardView) findViewById(R.id.userLogOut);
        editProfile.setOnClickListener(this);
        viewbills.setOnClickListener(this);
        viewpaymentStatus.setOnClickListener(this);
        feedback.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.EditProfile:
                startActivity(new Intent(this, EditProfileActivity.class));
                break;
            case R.id.ViewBills:
                startActivity(new Intent(this, ViewBillActivity.class));
                break;
            case R.id.ViewPaymentStatus:
                startActivity(new Intent(this, ViewPaymentActivity.class));
                break;
            case R.id.Feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.userLogOut:
                Logout();
                break;

        }
    }
}

/*public class UserActivity extends AppCompatActivity {
    private Button logout;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        logout =(Button) findViewById(R.id.bUserLogout);
        firebaseAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }
    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu,menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.ViewBills:
            {
                startActivity(new Intent(this,ViewBillActivity.class));
                break;
            }
            case R.id.ViewPaymentStatus:
            {
                startActivity(new Intent(this,ViewPaymentActivity.class));
                break;
            }
            case R.id.EditProfile:
            {
                startActivity(new Intent(this,EditProfileActivity.class));
                break;
            }
            case R.id.Feedback:
            {
                startActivity(new Intent(this,FeedbackActivity.class));
                break;
            }
            case R.id.Help:
            {
                startActivity(new Intent(this,HelpActivity.class));
                break;
            }

        }
        return true;
    }
}*/
