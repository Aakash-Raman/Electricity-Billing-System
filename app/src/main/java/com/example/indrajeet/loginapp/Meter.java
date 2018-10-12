package com.example.indrajeet.loginapp;

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Meter {
    private String id;
    private String month;
    private String meterReading;
    private String editPaymentStatus;
    private String  editPayment;
  //  private int rating;

    public Meter() {

    }

    public Meter(String id, String month, String meterReading, String editPaymentStatus, String editPayment) {
        this.id = id;
        this.month=month;
        this.meterReading = meterReading;
        this.editPaymentStatus = editPaymentStatus;
        this.editPayment = editPayment;
    }

    public String getId() {
        return id;
    }

    public String getMonth(){return month;}

    public String getMeterReading() {
        return meterReading;
    }

    public String getEditPaymentStatus() {
        return editPaymentStatus;
    }

    public String getEditPayment() {
        return editPayment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }

    public void setEditPaymentStatus(String editPaymentStatus) {
        this.editPaymentStatus = editPaymentStatus;
    }

    public void setEditPayment(String editPayment) {
        this.editPayment = editPayment;
    }
}