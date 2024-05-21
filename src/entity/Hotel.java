package entity;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String mail;
    private String phone;
    private String star;
    private boolean carPark;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;
    private boolean spa;
    private boolean roomService;
    public Hotel() {

    }
    public Hotel(int id, String name, String address, String mail, String phone, String star, boolean carPark,
                 boolean wifi, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean roomService) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.star = star;
        this.carPark = carPark;
        this.wifi = wifi;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.roomService = roomService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isCarPark() {
        return carPark;
    }

    public void setCarPark(boolean carPark) {
        this.carPark = carPark;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }
}
