package com.example.indrajeet.loginapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by indrajeet on 29/03/18.
 */

public class FeedbackSetterList extends ArrayAdapter<FeedbackSetter> {
    private Activity context;
    List<FeedbackSetter> feedbacks;

    public FeedbackSetterList(Activity context, List<FeedbackSetter> feedbacks) {
        super(context, R.layout.list_feedback,feedbacks);
        this.context = context;
        this.feedbacks = feedbacks;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_feedback, null, true);

        TextView textViewid = (TextView) listViewItem.findViewById(R.id.tvcid);
        TextView textViewRating= (TextView) listViewItem.findViewById(R.id.tvrating);

        FeedbackSetter track = feedbacks.get(position);
        // ConsumerMeter consumer = artists.get
        textViewid.setText(" Consumer Id- "+track.getConsumerid());
        textViewRating.setText("Rating        -  "+track.getRating());

        return listViewItem;
    }
}
