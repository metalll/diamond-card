package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.BuyerTarget;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBBuyerTargetImpl implements DBBuyerTarget {

    public DBBuyerTargetImpl(){
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(),BuyerTarget.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),BuyerTarget.class);
                //   dao.getConnectionSource().close();
            }
        }catch (Exception e){e.printStackTrace();}
    };

    private Dao<BuyerTarget,Long> dao;

    @Override
    public void validateBuyerTarget(BuyerTarget BuyerTarget){

    }

    @Override
    public void createBuyerTarget(BuyerTarget BuyerTarget) {
        try {
            dao.create(BuyerTarget);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Create BuyerTarget \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateBuyerTarget(BuyerTarget BuyerTarget) {
        try{
            dao.update(BuyerTarget);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update BuyerTarget \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeBuyerTarget(BuyerTarget BuyerTarget) {
        try{
            dao.delete(BuyerTarget);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update BuyerTarget \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public BuyerTarget getBuyerTarget(String email) {
      return null;
    }

    @Override
    public BuyerTarget getBuyerTarget(long id) {
        BuyerTarget resultValue = null;
        try{
            resultValue = dao.queryForId(id);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get BuyerTarget With id \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public List<BuyerTarget> getAllBuyerTargets() {
        List <BuyerTarget> resultList = null;
        try {
            resultList = dao.queryForAll();
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get All BuyerTargets \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}