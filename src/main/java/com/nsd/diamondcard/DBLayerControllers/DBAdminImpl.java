package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.Admin;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBAdminImpl implements DBAdmin {

    public DBAdminImpl(){
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(),Admin.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),Admin.class);
                //   dao.getConnectionSource().close();
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

        }catch (Exception e){
            System.err.println("Critical Error Create Admin \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateAdmin(Admin Admin) {
        try{
            dao.update(Admin);

        }catch (Exception e){
            System.err.println("Critical Error Update Admin \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeAdmin(Admin Admin) {
        try{
            dao.delete(Admin);

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

        }catch (Exception e){
            System.err.println("Critical Error Get All Admins \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}