package business;
import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager(){
        this.hotelDao = new HotelDao();
    }
    public ArrayList<Hotel> findAll(){
        return hotelDao.findAll();
    }

    public boolean save(Hotel hotel){
        return this.hotelDao.save(hotel);
    }

    public boolean update(Hotel hotel){
        if(getById(hotel.getId()) == null){
            Helper.showMsg(hotel.getId() + " id numaralı otel bulunamadı. ");
            return false;
        }
        return this.hotelDao.update(hotel);
    }
    public Hotel getById(int id){
        return this.hotelDao.findById(id);
    }
    public Hotel getByName(String name){
        return this.hotelDao.findByName(name);
    }

    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " id numaralı kullanıcı bulunamadı. ");
            return false;
        }
        return this.hotelDao.delete(id);
    }
    public ArrayList<Hotel> filter(String address){
        String query = "SELECT * FROM public.hotels";
        ArrayList<String> whereList = new ArrayList<>();
        if(!address.isEmpty()){
            whereList.add(" hotel_address LIKE '" + address + "%'");
        }

        if(!whereList.isEmpty()){
            String whereQuery = String.join("AND", whereList);
            query += " WHERE " + whereQuery;
        }
        return this.hotelDao.query(query);


    }
}
