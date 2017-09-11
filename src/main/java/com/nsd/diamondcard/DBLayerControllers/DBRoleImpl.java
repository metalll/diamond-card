package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.UserRole;
import com.nsd.diamondcard.Model.UserRoleEnum;
import com.nsd.diamondcard.Utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBRoleImpl implements DBRole {

    private Dao<UserRole,Long> dao;

    public DBRoleImpl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {

            dao = DaoManager.createDao(new JdbcConnectionSource(Constants.MYSQL_URL,Constants.MYSQL_LOGIN,Constants.MYSQL_PASSWD),UserRole.class);

            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),UserRole.class);
                dao.getConnectionSource().closeQuietly();
            }
        }catch (Exception e){e.printStackTrace();}


    }


    @Override
    public UserRoleEnum getRoleWithUserID(long id) {
        UserRole resultValue = null;
        try{
            List<UserRole> requestList = dao.queryForEq(UserRole.COLUMN_FOREIGN_USER_ID,id);
            if (requestList.size() > 0 && requestList.size() < 2) {
                resultValue = requestList.get(0);
            }
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get UserRole With email \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue!=null?resultValue.getRole():null;
    }

    @Override
    public void createRole(UserRole role) {
        try {
            dao.create(role);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Create UserRole \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateRole(UserRole role) {
        try{
            dao.update(role);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update UserRole \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeRole(UserRole role) {
        try{
            dao.delete(role);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update UserRole \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }
}
