package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.Mediator;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBMediatorImpl implements DBMediator {

    public DBMediatorImpl(){
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(),Mediator.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),Mediator.class);
                //   dao.getConnectionSource().close();
            }
        }catch (Exception e){e.printStackTrace();}
    };

    private Dao<Mediator,Long> dao;

    @Override
    public void validateMediator(Mediator Mediator){

    }

    @Override
    public void createMediator(Mediator Mediator) {
        try {
            dao.create(Mediator);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Create Mediator \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateMediator(Mediator Mediator) {
        try{
            dao.update(Mediator);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update Mediator \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeMediator(Mediator Mediator) {
        try{
            dao.delete(Mediator);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update Mediator \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public Mediator getMediator(String login) {
        return null;
    }


    @Override
    public Mediator getMediator(long id) {
        Mediator resultValue = null;
        try{
            resultValue = dao.queryForId(id);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get Mediator With id \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public List<Mediator> getAllMediators() {
        List <Mediator> resultList = null;
        try {
            resultList = dao.queryForAll();
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get All Mediators \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}