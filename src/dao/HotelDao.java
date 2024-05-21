package dao;

import core.Db;
import entity.Hotel;
import entity.Role;
import entity.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class HotelDao {
    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }
    public ArrayList<Hotel> findAll(){
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        String query = "SELECT * FROM public.hotels";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                hotelArrayList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return hotelArrayList;
    }
    public Hotel findById(int hotelId){
        Hotel obj = null;
        String query = "SELECT * FROM public.hotels WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public Hotel findByName(String hotelName){
        Hotel obj = null;
        String query = "SELECT * FROM public.hotels WHERE hotel_name = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,hotelName);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public boolean save(Hotel hotel){
        String query = "INSERT INTO public.hotels " +
                "(hotel_name, hotel_address, hotel_mail, hotel_phone," +
                " hotel_star, car_park, wifi, pool, fitness, concierge, spa, room_service)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,hotel.getName());
            pr.setString(2,hotel.getAddress());
            pr.setString(3,hotel.getMail());
            pr.setString(4,hotel.getPhone());
            pr.setString(5,hotel.getStar());
            pr.setBoolean(6, hotel.isCarPark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Hotel hotel){
        String query = "UPDATE public.hotels SET hotel_name = ? , hotel_address = ? , hotel_mail = ? ," +
                " hotel_phone = ? , hotel_star = ? , car_park = ? , " +
                "wifi = ? , pool = ? , fitness = ? , concierge = ? , spa = ? , room_service = ? WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getName());;
            pr.setString(2,hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setString(4,hotel.getPhone());
            pr.setString(5,hotel.getStar());
            pr.setBoolean(6,hotel.isCarPark());
            pr.setBoolean(7,hotel.isWifi());
            pr.setBoolean(8,hotel.isPool());
            pr.setBoolean(9,hotel.isFitness());
            pr.setBoolean(10,hotel.isConcierge());
            pr.setBoolean(11,hotel.isSpa());
            pr.setBoolean(12,hotel.isRoomService());
            pr.setInt(13,hotel.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE from public.hotels WHERE hotel_id = ?";

        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public ArrayList<Hotel> query(String query){
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                hotels.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return hotels;
    }

    public Hotel match(ResultSet rs) throws SQLException{
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setAddress(rs.getString("hotel_address"));
        obj.setMail(rs.getString("hotel_mail"));
        obj.setPhone(rs.getString("hotel_phone"));
        obj.setStar(rs.getString("hotel_star"));
        obj.setCarPark(rs.getBoolean("car_park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoomService(rs.getBoolean("room_service"));
        return obj;
    }
}
