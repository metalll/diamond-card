package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.Buyer;
import com.nsd.diamondcard.Model.User;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBBuyerImpl implements DBBuyer {

    public DBBuyerImpl(){
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(),Buyer.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),Buyer.class);

            }
        }catch (Exception e){e.printStackTrace();}

    };

    private Dao<Buyer,Long> dao;

    @Override
    public void validateBuyer(Buyer Buyer){

    }

    @Override
    public void createBuyer(Buyer Buyer) {
        try {
            dao.create(Buyer);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Create Buyer \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateBuyer(Buyer Buyer) {
        try{
            dao.update(Buyer);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update Buyer \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeBuyer(Buyer Buyer) {
        try{
            dao.delete(Buyer);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update Buyer \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public Buyer getBuyer(String email) {
//
        return null;
    }


    @Override
    public Buyer getBuyerWithForeign(long foreignId) {
        Buyer resultValue = null;
        try{
            List<Buyer> requestList = dao.queryForEq("foreignId",foreignId);
            if (requestList.size() > 0 && requestList.size() < 2) {
                resultValue = requestList.get(0);
            }
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get foreign With email \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public Buyer getBuyer(long id) {
        Buyer resultValue = null;
        try{
            resultValue = dao.queryForId(id);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get Buyer With id \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public List<Buyer> getAllBuyers() {
        List <Buyer> resultList = null;
        try {
            resultList = dao.queryForAll();
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get All Buyers \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}