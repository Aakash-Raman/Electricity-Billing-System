package com.example.indrajeet.loginapp;

/**
 * Created by indrajeet on 13/03/18.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class BillList extends ArrayAdapter<Bill> {
    private Activity context;
    List<Bill> bills;

    public BillList(Activity context, List<Bill> bills) {
        super(context, R.layout.layout_artist_list, bills);
        this.context = context;
        this.bills = bills;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.utv1);
        TextView textViewid = (TextView) listViewItem.findViewById(R.id.utv2);
        TextView textViewmonth = (TextView) listViewItem.findViewById(R.id.utv1);
        TextView textViewbill= (TextView) listViewItem.findViewById(R.id.utv2);


        Bill bill = bills.get(position);
        textViewName.setText(bill.getName());
        textViewid.setText(bill.getId());
        textViewmonth.setText(bill.getConsumerGenre());
        textViewbill.setText(bill.getBill());


        return listViewItem;
    }
}