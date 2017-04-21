package ru.a3technology.swipedraglist;

/**
 * Created by Stas on 21.04.2017.
 */

public class User {

    private String mFirstName, mLastName;
    private int mAge;

    public User(String firstName, String lastName, int age){
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
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

    public void setAge(int age){
        mAge = age;
    }
    public int getAge(){
        return mAge;
    }
}
