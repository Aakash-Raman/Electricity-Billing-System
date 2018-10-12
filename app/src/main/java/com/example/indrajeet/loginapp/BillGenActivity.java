package com.example.indrajeet.loginapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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

public class BillGenActivity extends AppCompatActivity {

    //we will use these constants later to pass the artist name and id to another activity
    public static final String CONSUMER_NAME = "net.simplifiedcoding.firebasedatabaseexample.artistname";
    public static final String CONSUMER_ID = "net.simplifiedcoding.firebasedatabaseexample.artistid";

    //view objects
   EditText editTextName;

    Button buttonAddConsumer;
    ListView listViewConsumers;

    //a list to store all the artist from firebase database
    List<UserProfile> consumers;

    //our database reference object
    DatabaseReference databaseConsumers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_gen);

        databaseConsumers = FirebaseDatabase.getInstance().getReference("Users");

        //getting views
        editTextName = (EditText) findViewById(R.id.editTextNameC);

        listViewConsumers = (ListView) findViewById(R.id.listViewConsumers);

       buttonAddConsumer = (Button) findViewById(R.id.buttonAddConsumer);

        //list to store consumers
        consumers = new ArrayList<>();
        listViewConsumers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserProfile consumer = consumers.get(i);


                Intent intent = new Intent(getApplicationContext(), MeterForBillActivity.class);

                //putting consumer name and id to intent
                intent.putExtra(CONSUMER_ID, consumer.getUserId());
                intent.putExtra(CONSUMER_NAME, consumer.getUserName());

                //starting the activity with intent
                startActivity(intent);
            }
        });

        //adding an onclicklistener to button
        buttonAddConsumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addConsumer()
                //the method is defined below
                //this method is actually performing the write operation
                addConsumer();
            }
        });

        listViewConsumers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserProfile consumer = consumers.get(i);
                showUpdateDeleteDialog(consumer.getUserId(), consumer.getUserName());
                return true;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseConsumers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous consumer list
                consumers.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    UserProfile consumer = postSnapshot.getValue(UserProfile.class);
                    //adding artist to the list
                    consumers.add(consumer);
                }

                //creating adapter
                 UserProfileList consumerAdapter = new UserProfileList(BillGenActivity.this, consumers);
                //attaching adapter to the listview
                listViewConsumers.setAdapter(consumerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
   private boolean updateArtist(String id, String name) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(id);

        //updating artist
        UserProfile consumer = new UserProfile(id, name);
        dR.setValue(consumer);
        Toast.makeText(getApplicationContext(), "Consumer Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private void showUpdateDeleteDialog(final String consumerId, String consumerName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextNameC);
        //  final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinnerGenresC);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle(consumerName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                //   String genre = spinnerGenre.getSelectedItem().toString();
                if (!TextUtils.isEmpty(name)) {
                    updateArtist(consumerId, name);
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        deleteConsumer(consumerId);
                        b.dismiss();
                    }
                });

            }
        });
    }
    private boolean deleteConsumer(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(id);

        //removing artist
        dR.removeValue();

        //getting the tracks reference for the specified artist
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("Users").child(id);

        //removing all tracks
        drTracks.removeValue();
        Toast.makeText(getApplicationContext(), "Consumer Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    /*
    * This method is saving a new artist to the
    * Firebase Realtime Database
    * */
    private void addConsumer() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        //  String genre = spinnerGenre.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseConsumers.push().getKey();

            //creating an Artist Object
            UserProfile consumer = new UserProfile(id, name);


            databaseConsumers.child(id).setValue(consumer);

            //setting edit text to blank again
            editTextName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Consumer added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}