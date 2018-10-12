package com.example.indrajeet.loginapp;

/**
 * Created by indrajeet on 13/03/18.
 */

public class UserProfile {
    private String userId;
    private String userAddress;
    private String userEmail;
    private String userName;
    private String userAge;
    private  String userPhone;


    public UserProfile(){
    }

    public UserProfile( String userId,String userName,String age,String phone, String userAddress,String userEmail) {
        this.userId= userId;
        this.userName = userName;
        this.userAge=age;
        this.userPhone=phone;
        this.userAddress = userAddress;
        this.userEmail = userEmail;


    }
   

    public UserProfile(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }


    public  String getUserId(){ return userId;}
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddres) {
        this.userAddress = userAddres;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }


}
