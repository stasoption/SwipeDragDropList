package com.stasoption.swipedraglist.Model;

/**
 * Created by Stas on 21.04.2017.
 */

public class User {

    private String mFirstName, mLastName, mMail;
    private int mAge, mStatus;

    public User(String firstName, String lastName, String mMail, int age, int status){
        setFirstName(firstName);
        setLastName(lastName);
        setMail(mMail);
        setAge(age);
        setStatus(status);
    }

    public void setFirstName(String name){
        mFirstName = name;
    }
    public String getFirstName(){
        return mFirstName;
    }

    public void setLastName(String name){
        mLastName = name;
    }
    public String getLastName(){
        return mLastName;
    }

    public void setMail(String addresss){
        mMail = addresss;
    }
    public String getMail(){
        return mMail;
    }

    public void setStatus(int status){
        mStatus = status;
    }
    public int getStatus(){
        return mStatus;
    }

    public void setAge(int age){
        mAge = age;
    }
    public int getAge(){
        return mAge;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getMail() + " " + getAge();
    }
}
