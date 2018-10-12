package com.example.indrajeet.loginapp;

/**
 * Created by indrajeet on 29/03/18.
 */

public class FeedbackSetter {

    private  String rating;
    private  String consumerid;
    public FeedbackSetter()
    {

    }

    public FeedbackSetter(String consumerid,String rating){
        this.rating=rating;
        this.consumerid=consumerid;
    }

    public String getRating() {
        return rating;
    }

    public String getConsumerid() {
        return consumerid;
    }
}
