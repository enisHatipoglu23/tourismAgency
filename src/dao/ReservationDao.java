package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection con;

    public ReservationDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Reservation> findAll(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservations";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                reservations.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return reservations;
    }
    public Reservation findById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservations WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservations " +
                "(reservation_room_id, check_in, check_out, total_price," +
                " guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone)" +
                " values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,reservation.getRoomId());
            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));
            pr.setDouble(4, reservation.getTotalPrice());
            pr.setInt(5, reservation.getGuestCount());
            pr.setString(6, reservation.getGuestName());
            pr.setString(7, reservation.getGuestCitizenId());
            pr.setString(8, reservation.getGuestMail());
            pr.setString(9, reservation.getGuestPhone());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservations SET guest_name = ? , guest_citizen_id = ? , guest_mail = ? ," +
                " guest_phone = ? WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, reservation.getGuestName());
            pr.setString(2,reservation.getGuestCitizenId());
            pr.setString(3, reservation.getGuestMail());
            pr.setString(4,reservation.getGuestPhone());

            pr.setInt(5,reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE from public.reservations WHERE reservation_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public Reservation match(ResultSet rs) throws SQLException{
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("reservation_id"));
        obj.setRoomId(rs.getInt("reservation_room_id"));
        obj.setCheckInDate(LocalDate.parse(rs.getString("check_in")));
        obj.setCheckOutDate(LocalDate.parse(rs.getString("check_out")));
        obj.setTotalPrice(rs.getDouble("total_price"));
        obj.setGuestCount(rs.getInt("guest_count"));
        obj.setGuestName(rs.getString("guest_name"));
        obj.setGuestCitizenId(rs.getString("guest_citizen_id"));
        obj.setGuestMail(rs.getString("guest_mail"));
        obj.setGuestPhone(rs.getString("guest_phone"));
        return obj;
    }
}
