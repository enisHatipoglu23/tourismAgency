package dao;

import core.Db;
import entity.*;

import java.sql.*;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;

    public RoomDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<Room> findAll(){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.rooms";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                roomList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return roomList;
    }
    public boolean updateRoomStock(int roomStock, Room room){
        String query = "UPDATE rooms\n" +
                "SET stock = ?\n" +
                "WHERE room_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,roomStock);
            pr.setInt(2,room.getId());
            ResultSet rs = pr.executeQuery();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public ArrayList<Room> findRoomByHotelId(int selectedHotelId){
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM public.rooms WHERE room_hotel_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,selectedHotelId);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                rooms.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rooms;
    }

    public ArrayList<Room> query(String query){
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                rooms.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rooms;
    }

    public boolean save(Room room){
        String query = "INSERT INTO public.rooms (room_hotel_id, room_pension_id, room_season_id, room_type, stock, adult_price, child_price, bed_capacity, " +
                "square_meter, television, minibar, game_console, cash_box, projection) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,room.getHotelId());
            pr.setInt(2,room.getPensionId());
            pr.setInt(3,room.getSeasonId());
            pr.setString(4,room.getType());
            pr.setInt(5,room.getStock());
            pr.setDouble(6,room.getAdultPrice());
            pr.setDouble(7,room.getChildPrice());
            pr.setInt(8,room.getBedCapacity());
            pr.setInt(9,room.getSquareMeter());
            pr.setBoolean(10,room.isTelevision());
            pr.setBoolean(11,room.isMinibar());
            pr.setBoolean(12,room.isGameConsole());
            pr.setBoolean(13,room.isCashBox());
            pr.setBoolean(14,room.isProjection());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Room room){
        String query = "UPDATE public.rooms SET room_hotel_id = ? , room_pension_id = ? , room_season_id = ? ," +
                " room_type = ? , stock = ? , adult_price = ? , " +
                "child_price = ? , bed_capacity = ? , square_meter = ? , television = ? , minibar = ? , game_console = ?, cash_box = ?, projection = ? WHERE room_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getPensionId());
            pr.setInt(3, room.getSeasonId());
            pr.setString(4,room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6,room.getAdultPrice());
            pr.setDouble(7,room.getChildPrice());
            pr.setInt(8,room.getBedCapacity());
            pr.setInt(9,room.getSquareMeter());
            pr.setBoolean(10,room.isTelevision());
            pr.setBoolean(11,room.isMinibar());
            pr.setBoolean(12,room.isGameConsole());
            pr.setBoolean(13,room.isCashBox());
            pr.setBoolean(14,room.isProjection());
            pr.setInt(15,room.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE from public.rooms WHERE room_id = ?";

        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Room findById(int hotelId){
        Room obj = null;
        String query = "SELECT * FROM public.rooms WHERE room_id = ?";
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

    public Room match(ResultSet rs) throws SQLException{
        Room obj = new Room();
        obj.setId(rs.getInt("room_id"));
        obj.setHotelId(rs.getInt("room_hotel_id"));
        obj.setPensionId(rs.getInt("room_pension_id"));
        obj.setSeasonId(rs.getInt("room_season_id"));
        obj.setType(rs.getString("room_type"));
        obj.setStock(rs.getInt("stock"));
        obj.setAdultPrice(rs.getDouble("adult_price"));
        obj.setChildPrice(rs.getDouble("child_price"));
        obj.setBedCapacity(rs.getInt("bed_capacity"));
        obj.setSquareMeter(rs.getInt("square_meter"));
        obj.setTelevision(rs.getBoolean("television"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGameConsole(rs.getBoolean("game_console"));
        obj.setCashBox(rs.getBoolean("cash_box"));
        obj.setProjection(rs.getBoolean("projection"));
        return obj;
    }

}
