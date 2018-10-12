package com.example.indrajeet.loginapp;

/**
 * Created by indrajeet on 12/03/18.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MeterList extends ArrayAdapter<Meter> {
    private Activity context;
    List<Meter> tracks;

    public MeterList(Activity context, List<Meter> tracks) {
        super(context, R.layout.list_layout, tracks);
        this.context = context;
        this.tracks = tracks;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPaymentStatus = (TextView) listViewItem.findViewById(R.id.textViewPaymentStatus);
        TextView  textViewPayment= (TextView) listViewItem.findViewById(R.id.textViewPayment);
        TextView  textViewMonth =(TextView)listViewItem.findViewById(R.id.textViewMonth);

        Meter track = tracks.get(position);
       // ConsumerMeter consumer = artists.get
        textViewName.setText("Meter Reading - "+track.getMeterReading());
        textViewPaymentStatus.setText("Payment Status- "+track.getEditPaymentStatus());
        textViewPayment.setText("Made Payment -  "+track.getEditPayment());
        textViewMonth.setText("Month        -  "+track.getMonth());
        //textViewRating.setText(String.valueOf(track.getRating()));

        return listViewItem;
    }
}