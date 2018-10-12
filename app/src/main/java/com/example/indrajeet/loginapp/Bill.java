package com.example.indrajeet.loginapp;

/**
 * Created by indrajeet on 13/03/18.
 */

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class  Bill {
    private String id;
    private String name;
    private String consumerGenre;
    private String bill;

    public Bill() {

    }

    public Bill(String id, String name,String consumerGenre,String bill) {
         this.bill= bill;
         this.name = name;
        this.id = id;
        this.consumerGenre = consumerGenre;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getConsumerGenre() {
        return consumerGenre;
    }

    public String getBill() {
        return bill;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConsumerGenre(String consumerGenre) {
        this.consumerGenre = consumerGenre;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }
}