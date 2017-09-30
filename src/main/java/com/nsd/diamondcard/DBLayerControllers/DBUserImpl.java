package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.User;
import com.nsd.diamondcard.Model.UserRole;
import com.nsd.diamondcard.Utils.Constants;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBUserImpl implements DBUser {

    public DBUserImpl(){
        try {


            Class.forName("org.postgresql.Driver");}
            catch (Exception e) {
            e.printStackTrace();
        }
        try {

            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            dao = DaoManager.createDao(new JdbcConnectionSource(dbUrl,username,password),User.class);
             if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),User.class);
                dao.getConnectionSource().closeQuietly();
            }
        }catch (Exception e){e.printStackTrace();}

    };

    private Dao<User,Long>dao;

    @Override
    public void validateUser(User user){

    }

    @Override
    public void createUser(User user) {
        try {
            dao.create(user);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Create User \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateUser(User user) {
        try{
            dao.update(user);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update User \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeUser(User user) {
        try{
            dao.delete(user);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update User \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public User getUser(String email) {
        User resultValue = null;
        try{
            List<User> requestList = dao.queryForEq(User.COLUMN_EMAIL,email);
            if (requestList.size() > 0 && requestList.size() < 2) {
                resultValue = requestList.get(0);
            }
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get User With email \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public User getUser(long id) {
        User resultValue = null;
        try{
            resultValue = dao.queryForId(id);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get User With id \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public List<User> getAllUsers() {
        List <User> resultList = null;
        try {
            resultList = dao.queryForAll();
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get All Users \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}