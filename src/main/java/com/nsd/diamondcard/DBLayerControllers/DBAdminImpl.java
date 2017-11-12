package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Admin;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBAdminImpl implements DBAdmin {

    public DBAdminImpl(){
        try {
            Class.forName("org.postgresql.Driver");}
        catch (Exception e) {
            e.printStackTrace();
        }
        try {

            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String Adminname = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            dao = DaoManager.createDao(new JdbcConnectionSource(dbUrl,Adminname,password),Admin.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),Admin.class);
                dao.getConnectionSource().closeQuietly();
            }
        }catch (Exception e){e.printStackTrace();}

    };

    private Dao<Admin,Long> dao;

    @Override
    public void validateAdmin(Admin Admin){

    }

    @Override
    public void createAdmin(Admin Admin) {
        try {
            dao.create(Admin);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Create Admin \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateAdmin(Admin Admin) {
        try{
            dao.update(Admin);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update Admin \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeAdmin(Admin Admin) {
        try{
            dao.delete(Admin);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update Admin \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public Admin getAdmin(String email) {

        return null;
    }

    @Override
    public Admin getAdmin(long id) {
        Admin resultValue = null;
        try{
            resultValue = dao.queryForId(id);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get Admin With id \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List <Admin> resultList = null;
        try {
            resultList = dao.queryForAll();
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get All Admins \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}