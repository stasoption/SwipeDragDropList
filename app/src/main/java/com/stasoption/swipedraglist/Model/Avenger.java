package com.stasoption.swipedraglist.Model;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;

import com.stasoption.swipedraglist.R;

import java.util.ArrayList;

/**
 * @author Stas Averin
 */

public class Avenger {

    @DrawableRes
    private int mAvatar;

    private String mName;

    private String mMail;

    private String mAge;

    private boolean mStatus;

    public Avenger(@DrawableRes int avatar, String name, String mMail, String age, boolean status){
        setAvatar(avatar);
        setName(name);
        setMail(mMail);
        setAge(age);
        setStatus(status);
    }

    public int getAvatar() {
        return mAvatar;
    }
    public void setAvatar(int avatar) {
        mAvatar = avatar;
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

    public static ArrayList<Avenger> getAvengers(){
        ArrayList<Avenger> avengers = new ArrayList<>();
        avengers.add(new Avenger(R.drawable.america,"Steve Rogers", "america@gmail.com", "41 years", true));
        avengers.add(new Avenger(R.drawable.thor,"Thor", "thor@gmail.com", "34 years", true));
        avengers.add(new Avenger(R.drawable.hulk,"Bruce Banner", "hulk@gmail.com", "46 years", false));
        avengers.add(new Avenger(R.drawable.black_widow,"Natasha Romanoff", "black_widow@gmail.com", "35 years", false));
        avengers.add(new Avenger(R.drawable.iron_manpng,"Tony Stark",  "iron_man@gmail.com", "50 years", true));
        avengers.add(new Avenger(R.drawable.loki,"Loki", "loki@gmail.com", "45 years", false));
        return avengers;
    }
}
