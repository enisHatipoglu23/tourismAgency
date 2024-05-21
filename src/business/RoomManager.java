package business;


import dao.RoomDao;
import entity.Room;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RoomManager {
    private final RoomDao roomDao;
    public RoomManager(){
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll(){
        return roomDao.findAll();
    }

    public boolean save(Room room){
        return this.roomDao.save(room);
    }

public boolean update(Room room){
    return this.roomDao.update(room);
}

public boolean delete(int id){
        return this.roomDao.delete(id);
    }
    public Room getById(int id){
        return this.roomDao.findById(id);
    }
    public ArrayList<Room> filterNew(String name, String address, Date start, Date finish){
        StringBuilder query = new StringBuilder("SELECT * FROM public.rooms r " +
                "JOIN public.hotels h ON r.room_hotel_id = h.hotel_id " +
                "JOIN public.seasons s ON r.room_season_id = s.season_id");

        ArrayList<String> conditions = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            conditions.add(" h.hotel_name LIKE '%" + name + "%'");
        }
        if (address != null && !address.isEmpty()) {
            conditions.add(" h.hotel_address LIKE '%" + address + "%'");
        }
        if (start != null && finish != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            conditions.add(" s.start_date <= '" + sdf.format(start) + "'");
            conditions.add(" s.finish_date >= '" + sdf.format(finish) + "'");
        }

        if (!conditions.isEmpty()) {
            query.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        return this.roomDao.query(query.toString());
    }

    public ArrayList<Room> findRoomByHotelId(int hotelId){
        return this.roomDao.findRoomByHotelId(hotelId);
    }

    public boolean updateRoomStock(int roomStock, Room room){
        return this.roomDao.updateRoomStock(roomStock, room);
    }

}
