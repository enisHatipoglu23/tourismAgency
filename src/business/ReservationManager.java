package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;
import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;
    public ReservationManager(){
        this.reservationDao = new ReservationDao();
    }
    public ArrayList<Reservation> findAll(){
        return reservationDao.findAll();
    }

    public boolean save(Reservation reservation){
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation){
        return this.reservationDao.update(reservation);
    }

    public Reservation getById(int id){
        return this.reservationDao.findById(id);
    }

    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " id numaralı reservasyon bulunamadı. ");
            return false;
        }
        return this.reservationDao.delete(id);
    }
}
