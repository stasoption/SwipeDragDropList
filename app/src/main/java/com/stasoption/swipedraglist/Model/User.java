package com.stasoption.swipedraglist.Model;

/**
 * Created by Stas on 21.04.2017.
 */

public class User {

    private String mName, mMail;
    private String mAge;
    private boolean mStatus;

    public User(String name, String mMail, String age, boolean status){
        setName(name);
        setMail(mMail);
        setAge(age);
        setStatus(status);
    }

    public void setName(String name){
        mName = name;
    }
    public String getName(){
        return mName;
    }


    public void setMail(String addresss){
        mMail = addresss;
    }
    public String getMail(){
        return mMail;
    }

    public void setStatus(boolean status){
        mStatus = status;
    }
    public boolean getStatus(){
        return mStatus;
    }

    public void setAge(String age){
        mAge = age;
    }
    public String getAge(){
        return mAge;
    }

    @Override
    public String toString() {
        return getName() + " " + getMail() + " " + getAge();
    }
}
