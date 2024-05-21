package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;
    public PensionManager(){
        this.pensionDao = new PensionDao();
    }
    public boolean save(Pension pension){
        return this.pensionDao.save(pension);
    }
    public Pension getById(int id){
        return this.pensionDao.findById(id);
    }
    public ArrayList<Pension> findPensionByHotelId(int hotelId){
        return this.pensionDao.findPensionByHotelId(hotelId);
    }
    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }
    public boolean update(Pension pension){
        if(this.getById(pension.getId()) == null){
            Helper.showMsg(pension.getId() + " id'li pansiyon bulunamadı. ");
            return false;
        }
        return this.pensionDao.update(pension);
    }
}
