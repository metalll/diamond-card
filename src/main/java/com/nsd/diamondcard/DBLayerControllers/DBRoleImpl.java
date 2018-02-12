package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.User;
import com.nsd.diamondcard.Model.UserRole;
import com.nsd.diamondcard.Model.UserRoleEnum;
import com.nsd.diamondcard.Utils.Constants;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.net.URI;
import java.util.List;

@Service
public class DBRoleImpl implements DBRole {



    private Dao<UserRole,Long> dao;

    public DBRoleImpl(){
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(),UserRole.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),UserRole.class);
                //   dao.getConnectionSource().close();
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
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Get UserRole With email \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue!=null?resultValue.getRole():null;
    }

    @Override
    public void createRole(UserRole role) {
        try {
            dao.create(role);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Create UserRole \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateRole(UserRole role) {
        try{
            dao.update(role);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update UserRole \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void removeRole(UserRole role) {
        try{
            dao.delete(role);
            dao.getConnectionSource().close();
        }catch (Exception e){
            System.err.println("Critical Error Update UserRole \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }
}
