package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.nsd.diamondcard.Model.Activity.Activity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBActivityImpl implements DBActivity {

    public DBActivityImpl(){
        try {


            Class.forName("org.postgresql.Driver");}
        catch (Exception e) {
            e.printStackTrace();
        }
        try {

            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String Activityname = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            dao = DaoManager.createDao(new JdbcConnectionSource(dbUrl,Activityname,password),Activity.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),Activity.class);
                dao.getConnectionSource().closeQuietly();
            }
        }catch (Exception e){e.printStackTrace();}

    };

    private Dao<Activity,Long> dao;

    @Override
    public void validateActivity(Activity Activity){

    }

    @Override
    public void createActivity(Activity Activity) {
        try {
            dao.create(Activity);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Create Activity \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateActivity(Activity Activity) {
        try{
            dao.update(Activity);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update Activity \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeActivity(Activity Activity) {
        try{
            dao.delete(Activity);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update Activity \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public Activity getActivity(String email) {
        return null;
    }

    @Override
    public Activity getActivity(long id) {
        Activity resultValue = null;
        try{
            resultValue = dao.queryForId(id);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get Activity With id \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public List<Activity> getAllActivitys() {
        List <Activity> resultList = null;
        try {
            resultList = dao.queryForAll();
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get All Activitys \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}