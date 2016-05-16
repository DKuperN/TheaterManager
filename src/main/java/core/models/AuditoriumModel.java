package core.models;

public class AuditoriumModel {

    private String auditoriumName;

    //private int auditoriumId;

    private int auditoriumCapacity;

    private int[] auditoriumVIPSeats;

    private int placeNumber;

    private boolean isSeatVIP;

    private boolean isAuditoriumFree;

    public AuditoriumModel(String name, int capacity, int[] vipSeatses) {
        this.auditoriumName = name;
        this.auditoriumCapacity = capacity;
        this.auditoriumVIPSeats = vipSeatses;
    }

    public boolean isAuditoriumFree() {
        return isAuditoriumFree;
    }

    public int[] getAuditoriumVIPSeats() {
        return auditoriumVIPSeats;
    }

    public void setAuditoriumVIPSeats(int[] auditoriumVIPSeats) {
        this.auditoriumVIPSeats = auditoriumVIPSeats;
    }

    public void setAuditoriumFree(boolean auditoriumFree) {
        isAuditoriumFree = auditoriumFree;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

//    public int getAuditoriumId() {
//        return auditoriumId;
//    }

//    public void setAuditoriumId(int auditoriumId) {
//        this.auditoriumId = auditoriumId;
//    }

    public int getAuditoriumCapacity() {
        return auditoriumCapacity;
    }

    public void setAuditoriumCapacity(int auditoriumCapacity) {
        this.auditoriumCapacity = auditoriumCapacity;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public boolean isSeatVIP() {
        return isSeatVIP;
    }

    public void setSeatVIP(boolean seatVIP) {
        isSeatVIP = seatVIP;
    }
}
