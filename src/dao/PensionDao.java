package dao;

import core.Db;
import entity.Pension;

import java.sql.*;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con;

    public PensionDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<Pension> findAll(){
        ArrayList<Pension> pensionArrayList = new ArrayList<>();
        String query = "SELECT * FROM public.pensions";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                pensionArrayList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return pensionArrayList;
    }
    public ArrayList<Pension> findPensionByHotelId(int selectedHotelId){
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM public.pensions WHERE pension_hotel_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,selectedHotelId);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                pensionList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return pensionList;
    }
    public boolean save(Pension pension){
        String query = "INSERT INTO public.pensions " +
                "(pension_hotel_id, pension_type)" +
                " values (?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,pension.getHotelId());
            pr.setString(2,pension.getPensionType());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public Pension findById(int pensionId){
        Pension obj = null;
        String query = "SELECT * FROM public.pensions WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,pensionId);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    public boolean update(Pension pension){
        String query = "UPDATE public.hotels SET pension_hotel_id = ? , pension_type = ? WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension.getHotelId());;
            pr.setString(2,pension.getPensionType());
            pr.setInt(3,pension.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Pension match(ResultSet rs) throws SQLException{
        Pension obj = new Pension();
        obj.setId(rs.getInt("pension_id"));
        obj.setHotelId(rs.getInt("pension_hotel_id"));
        obj.setPensionType(rs.getString("pension_type"));
        return obj;
    }

}
