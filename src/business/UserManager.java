package business;

import core.Helper;
import dao.UserDao;
import entity.Role;
import entity.User;
import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager(){
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password){
        return userDao.findByLogin(username, password);
    }
    public ArrayList<User> findAll(){
        return userDao.findAll();
    }
    public ArrayList<User> findAllAdmins(){
        return this.userDao.findAllAdmins();
    }

    public ArrayList<User> findAllEmployees(){
        return this.userDao.findAllEmployees();
    }

    public boolean save(User user){
        return this.userDao.save(user);
    }

    public User getById(int id){
        return this.userDao.findById(id);
    }

    public boolean update(User user){
        if(getById(user.getUserId()) == null){
            Helper.showMsg(user.getUserId() + " id numaralı kullanıcı bulunamadı. ");
            return false;
        }
            return this.userDao.update(user);
    }
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " id numaralı kullanıcı bulunamadı. ");
            return false;
        }
        return this.userDao.delete(id);
    }
    public ArrayList<User> filter(String name, Role role){
        String query = "SELECT * FROM public.users";
        ArrayList<String> whereList = new ArrayList<>();
        if(!name.isEmpty()){
            whereList.add("username LIKE '" + name + "%'");
        }

        if(role != null){
            whereList.add(" user_role = '" + role + "'");
        }

        if(!whereList.isEmpty()){
            String whereQuery = String.join("AND", whereList);
            query += " WHERE " + whereQuery;
        }
        return this.userDao.query(query);


    }

}
