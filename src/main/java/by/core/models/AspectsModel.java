package by.core.models;

import java.util.Date;
import java.sql.Time;

public class AspectsModel {
    int aspectID;
    String counterName;
    int counterQuantity;
    Date counterDateTime;
    Time finalCounterTime;

    public AspectsModel(int aspectID, String counterName, int counterQuantity, Date counterDateTime, Time finalCounterTime) {
        this.aspectID = aspectID;
        this.counterName = counterName;
        this.counterQuantity = counterQuantity;
        this.counterDateTime = counterDateTime;
        this.finalCounterTime = finalCounterTime;
    }
    public AspectsModel(String counterName, int counterQuantity, Date counterDateTime, Time finalCounterTime) {
        this.counterName = counterName;
        this.counterQuantity = counterQuantity;
        this.counterDateTime = counterDateTime;
        this.finalCounterTime = finalCounterTime;
    }

    public int getAspectID() {
        return aspectID;
    }

    public void setAspectID(int aspectID) {
        this.aspectID = aspectID;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public int getCounterQuantity() {
        return counterQuantity;
    }

    public void setCounterQuantity(int counterQuantity) {
        this.counterQuantity = counterQuantity;
    }

    public Date getCounterDateTime() {
        return counterDateTime;
    }

    public void setCounterDateTime(Date counterDateTime) {
        this.counterDateTime = counterDateTime;
    }

    public Time getFinalCounterTime() {
        return finalCounterTime;
    }

    public void setFinalCounterTime(Time finalCounterTime) {
        this.finalCounterTime = finalCounterTime;
    }
}
