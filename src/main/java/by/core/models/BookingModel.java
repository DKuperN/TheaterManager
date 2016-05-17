package by.core.models;

/**
 * Created by Denis on 16.05.2016.
 */
public class BookingModel {
    UserModel userModel;
    EventModel eventModel;
    boolean isSeatVip;
    int discount;

    public BookingModel(UserModel userModel, EventModel eventModel, boolean isSeatVip, int discount) {
        this.userModel = userModel;
        this.eventModel = eventModel;
        this.isSeatVip = isSeatVip;
        this.discount = discount;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public boolean isSeatVip() {
        return isSeatVip;
    }

    public void setSeatVip(boolean seatVip) {
        isSeatVip = seatVip;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
