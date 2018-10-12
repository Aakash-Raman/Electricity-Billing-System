package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFeedbackActivity extends AppCompatActivity {

    //we will use these constants later to pass the artist name and id to another activity
    public static final String ARTIST_NAME = "consumername";
    public static final String ARTIST_ID = "consumerid";

    //view objects
    // EditText editTextName;

    //Button buttonAddArtist;
    ListView listViewfeedbacks;

    //a list to store all the artist from firebase database
    List<FeedbackSetter> artists;

    //our database reference object
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        //getting the reference of artists node
        // databaseArtists = FirebaseDatabase.getInstance().getReference("Users Edited by Electricity Board");
        databaseArtists = FirebaseDatabase.getInstance().getReference("Feedbacks");

        //getting views
        // editTextName = (EditText) findViewById(R.id.editTextName);

        listViewfeedbacks = (ListView) findViewById(R.id.listViewFeedbacks);

        // buttonAddArtist = (Button) findViewById(R.id.buttonAddArtist);

        //list to store artists
        artists = new ArrayList<>();


        //adding an onclicklistener to button




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
                     FeedbackSetter artist = postSnapshot.getValue(FeedbackSetter.class);
                    //adding artist to the list
                    artists.add(artist);
                }

                //creating adapter
                FeedbackSetterList artistAdapter = new FeedbackSetterList(ViewFeedbackActivity.this, artists);
                //attaching adapter to the listview
                listViewfeedbacks.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
