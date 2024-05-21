package entity;

public class Room {
    private int id;
    private int hotelId;
    private int pensionId;
    private int seasonId;
    private String type;
    private int stock;
    private double adultPrice;
    private double childPrice;
    private int bedCapacity;
    private int squareMeter;
    private boolean television;
    private boolean minibar;
    private boolean gameConsole;
    private boolean cashBox;
    private boolean projection;
    public Room() {
    }

    public Room(int id, int hotelId, int pensionId, int seasonId, String type,
                int stock, double adultPrice, double childPrice,
                int bedCapacity, int squareMeter, boolean television, boolean minibar,
                boolean gameConsole, boolean cashBox, boolean projection) {
        this.id = id;
        this.hotelId = hotelId;
        this.pensionId = pensionId;
        this.seasonId = seasonId;
        this.type = type;
        this.stock = stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.bedCapacity = bedCapacity;
        this.squareMeter = squareMeter;
        this.television = television;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.cashBox = cashBox;
        this.projection = projection;
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

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(int squareMeter) {
        this.squareMeter = squareMeter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isCashBox() {
        return cashBox;
    }

    public void setCashBox(boolean cashBox) {
        this.cashBox = cashBox;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", hotelId=" + hotelId +
                ", pensionId=" + pensionId +
                ", seasonId=" + seasonId +
                ", type='" + type + '\'' +
                ", stock=" + stock +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice +
                ", bedCapacity=" + bedCapacity +
                ", squareMeter=" + squareMeter +
                ", television=" + television +
                ", minibar=" + minibar +
                ", gameConsole=" + gameConsole +
                ", cashBox=" + cashBox +
                ", projection=" + projection;
    }
}
