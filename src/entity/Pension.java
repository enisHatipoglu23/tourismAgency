package entity;

public class Pension {
    private int id;
    private int hotelId;
    private String pensionType;

    public Pension(int id, int hotelId, String pensionType) {
        this.id = id;
        this.hotelId = hotelId;
        this.pensionType = pensionType;
    }

    public Pension() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }
}
