package dao;

import core.Db;
import entity.Role;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;

    public UserDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<User> findAll(){
        ArrayList<User> userArrayList = new ArrayList<>();
        String query = "SELECT * FROM public.users";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                userArrayList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userArrayList;
    }
    public User findByLogin(String username, String password){
        User obj = null;
        String query = "SELECT * FROM public.users WHERE username = ? AND password = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public ArrayList<User> findAllAdmins(){
        ArrayList<User> adminArrayList = new ArrayList<>();
        String query = "SELECT * FROM public.users WHERE user_role = 'ADMIN'";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                adminArrayList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return adminArrayList;
    }

    public ArrayList<User> findAllEmployees(){
        ArrayList<User> employeeArrayList = new ArrayList<>();
        String query = "SELECT * FROM users WHERE user_role = 'EMPLOYEE'";
        try{
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                employeeArrayList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeArrayList;
    }
    public boolean save(User user){
        String query = "INSERT INTO public.users (username, password, user_role) values (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,user.getUsername());
            pr.setString(2,user.getPassword());
            pr.setString(3,user.getRole().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User findById(int userId){
        User obj = null;
        String query = "SELECT * FROM public.users WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,userId);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public boolean update(User user){
        String query = "UPDATE public.users SET username = ? , password = ? , user_role = ? WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2,user.getPassword());
            pr.setString(3, user.getRole().toString());
            pr.setInt(4,user.getUserId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int id){
        String query = "DELETE from public.users WHERE user_id = ?";

        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public ArrayList<User> query(String query){
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                users.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public User match(ResultSet rs) throws SQLException{
        User obj = new User();
        obj.setUserId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("username"));
        obj.setPassword(rs.getString("password"));
        obj.setRole(Role.valueOf(rs.getString("user_role")));
        return obj;
    }
}
