package entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private int guestCount;
    private String guestName;
    private String guestCitizenId;
    private String guestMail;
    private String guestPhone;
    public Reservation() {
    }

    public Reservation(int id, int roomId, LocalDate checkInDate, LocalDate checkOutDate,
                       double totalPrice, int guestCount, String guestName, String guestCitizenId, String guestMail, String guestPhone) {
        this.id = id;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.guestCount = guestCount;
        this.guestName = guestName;
        this.guestCitizenId = guestCitizenId;
        this.guestMail = guestMail;
        this.guestPhone = guestPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestCitizenId() {
        return guestCitizenId;
    }

    public void setGuestCitizenId(String guestCitizenId) {
        this.guestCitizenId = guestCitizenId;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
}
