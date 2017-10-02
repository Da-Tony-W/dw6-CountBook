package com.example.android.dw6_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tony on 2017/10/1.
 */

public class Counter {
    private String counterName;
    private Date date;
    private String comment;
    private int initialValue;
    private int value;      // current value of the counter

    public Counter(String counterName, String comment, int initialValue) {
        this.counterName = counterName;
        this.comment = comment;
        this.initialValue = initialValue;

        this.date = new Date();


    }

    public void setInitialValue(int initialValue){
        if (value >= 0){
            this.initialValue = initialValue;
            setDate();
        }

    }

    public int getInitialValue(){
        return this.initialValue;
    }

    public void setValue(int value){
        if (value >= 0){
            this.value = value;
            setDate();
        }
    }

    public int getValue(){
        return this.value;
    }

    public void inc(){
        ++this.value;
        setDate();
    }

    public void dec(){
        if (value > 0){
            --this.value;
            setDate();
        }
    }


    public void setName(String counterName){
        this.counterName = counterName;
    }

    public String getName(){
        return this.counterName;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }



    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    @Override
    public String toString(){
        // reference https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android
        String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        return counterName + ", value:" + value + ", date:"+ d +", ";

    }

}
