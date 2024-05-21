package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;

    public SeasonDao(){
        this.con = Db.getInstance();
    }
    public boolean save(Season season){
        String query = "INSERT INTO public.seasons " +
                "(season_hotel_id, start_date, finish_date)" +
                " values (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public Season findSeasonByID(int seasonID){
        Season obj=null;
        String query = "SELECT * FROM public.seasons WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,seasonID);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public ArrayList<Season> findSeasonsByHotelID(int seasonHotelId){
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.seasons WHERE season_hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,seasonHotelId);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                seasons.add(this.match(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seasons;
    }
    public boolean update(Season season){
        String query = "UPDATE public.seasons SET season_hotel_id = ? , start_date = ? ," +
                " finish_date = ? WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());;
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            pr.setInt(4,season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public ArrayList<Season> findAll(){
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.seasons";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                seasons.add(this.match(rs));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return seasons;
    }

    public Season match(ResultSet rs) throws SQLException{
        Season obj = new Season();
        obj.setId(rs.getInt("season_id"));
        obj.setHotelId(rs.getInt("season_hotel_id"));
        obj.setStartDate(rs.getDate("start_date").toLocalDate());
        obj.setFinishDate((rs.getDate("finish_date")).toLocalDate());
        return obj;
    }

}
