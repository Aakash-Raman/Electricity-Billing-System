package com.example.indrajeet.loginapp;




import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MeterReadingActivity extends AppCompatActivity {

    //we will use these constants later to pass the artist name and id to another activity
    public static final String ARTIST_NAME = "consumername";
    public static final String ARTIST_ID = "consumerid";

    //view objects
   // EditText editTextName;

    //Button buttonAddArtist;
    ListView listViewArtists;

    //a list to store all the artist from firebase database
    List<UserProfile> artists;

    //our database reference object
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_reading);

        //getting the reference of artists node
       // databaseArtists = FirebaseDatabase.getInstance().getReference("Users Edited by Electricity Board");
        databaseArtists = FirebaseDatabase.getInstance().getReference("Users");

        //getting views
       // editTextName = (EditText) findViewById(R.id.editTextName);

        listViewArtists = (ListView) findViewById(R.id.listViewArtists);

       // buttonAddArtist = (Button) findViewById(R.id.buttonAddArtist);

        //list to store artists
        artists = new ArrayList<>();


        //adding an onclicklistener to button


        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserProfile artist = artists.get(position);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), AddMeterPaymentActivity.class);
                //putting artist name and id to intent
                intent.putExtra(ARTIST_ID, artist.getUserId());
                intent.putExtra(ARTIST_NAME, artist.getUserName());

                //starting the activity with intent
                startActivity(intent);


            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                artists.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    UserProfile artist = postSnapshot.getValue(UserProfile.class);
                    //adding artist to the list
                    artists.add(artist);
                }

                //creating adapter
                UserProfileList artistAdapter = new UserProfileList(MeterReadingActivity.this, artists);
                //attaching adapter to the listview
                listViewArtists.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}