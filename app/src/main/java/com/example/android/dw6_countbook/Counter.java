/**
 * Class name: Counter
 * Version 1.1
 * Date 2017/10/1.
 * Counter object
 */

package com.example.android.dw6_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * represent counter, each counter has a name, an initial value, a current value and a comment(can be empty)
 * it also stores the date it last updated
 * counter can be incremented, decremented, or reset
 *
 * @author Tony (Da Wang)
 * @version 1.1
 * @since 1.0
 */


public class Counter {
    private String counterName;
    private Date date;
    private String comment;
    private int initialValue;
    private int value;      // current value of the counter

    /**
     * Constructor for Counter with no comment, not actualy used
     *
     * @param counterName
     * @param initialValue
     */

    public Counter(String counterName, int initialValue) {
        this.counterName = counterName;
        this.initialValue = initialValue;
        this.value = initialValue;
        this.date = new Date();
    }

    /**
     * Constructor for counter,if no comment was added when creating counter, empty string will be used
     *
     * @param counterName
     * @param comment
     * @param initialValue
     */

    public Counter(String counterName, String comment, int initialValue) {
        this.counterName = counterName;
        this.comment = comment;
        this.initialValue = initialValue;
        this.value = initialValue;

        this.date = new Date();
    }

    public int getInitialValue() {
        return this.initialValue;
    }

    /**
     * set initialValue
     *
     * @param initialValue
     */
    public void setInitialValue(int initialValue) {
        if (value >= 0) {
            this.initialValue = initialValue;
            setDate();
        }

    }

    public int getValue() {
        return this.value;
    }

    /**
     * set current value
     *
     * @param value
     */
    public void setValue(int value) {
        if (value >= 0) {
            this.value = value;
            setDate();
        }
    }

    /**
     * increment counter by 1;
     */
    public void inc() {
        ++this.value;
        setDate();
    }

    /**
     * check if counter current value is greater than one, if so, decrement counter by 1
     * TODO:throw exception
     */
    public void dec() {
        if (value > 0) {
            --this.value;
            setDate();
        }
    }

    /**
     * get counter name
     *
     * @return counterName
     */
    public String getName() {
        return this.counterName;
    }

    /**
     * set counter name
     *
     * @param counterName
     */
    public void setName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * comment getter
     *
     * @return
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * set comment string
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    /**
     * set date to current date, called whenever the value of a counter is changed
     */
    public void setDate() {
        this.date = new Date();
    }

    public void reset() {
        this.value = this.initialValue;
        this.date = new Date();
    }

    /**
     * set string to empty strings and ints to 0
     * not really useful, cuz java is a garbage collector
     */
    public void delete(){
        this.value = 0;
        this.initialValue = 0;
        this.comment = "";
        this.counterName = "";
    }

    /**
     * format display String
     *
     * @return
     */
    @Override
    public String toString() {
        // reference https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android
        String d = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        return counterName + ", value:" + value + ", date:" + d + ", " + comment;

    }

}
