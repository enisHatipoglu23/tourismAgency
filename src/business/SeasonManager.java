package business;

import dao.SeasonDao;
import entity.Season;
import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }

    public boolean save(Season season){
        return this.seasonDao.save(season);
    }
    public ArrayList<Season> findSeasonByHotelID(int seasonHotelId){
        return this.seasonDao.findSeasonsByHotelID(seasonHotelId);
    }
    public Season findSeasonByID(int seasonID){
        return this.seasonDao.findSeasonByID(seasonID);
    }

    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }

}
