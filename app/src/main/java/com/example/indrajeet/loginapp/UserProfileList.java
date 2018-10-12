package com.example.indrajeet.loginapp;

/**
 * Created by indrajeet on 13/03/18.
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class UserProfileList extends ArrayAdapter<UserProfile> {
    private Activity context;
    List<UserProfile> consumers;

    public UserProfileList(Activity context, List<UserProfile> consumers) {
        super(context, R.layout.layout_artist_list, consumers);
        this.context = context;
        this.consumers = consumers;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.utv1);
        TextView textViewid = (TextView) listViewItem.findViewById(R.id.utv2);
        TextView textViewemail = (TextView) listViewItem.findViewById(R.id.utv3);
      //  TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        UserProfile consumer = consumers.get(position);


        textViewName.setText(consumer.getUserName());

         textViewid.setText(consumer.getUserId());
         textViewemail.setText(consumer.getUserEmail());

        return listViewItem;
    }

}
