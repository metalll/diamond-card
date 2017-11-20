package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.ContrAgent;
import com.nsd.diamondcard.Model.ContrAgent;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBContrAgentImpl implements DBContrAgent {

    public DBContrAgentImpl(){
        try {


            Class.forName("org.postgresql.Driver");}
        catch (Exception e) {
            e.printStackTrace();
        }
        try {

            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String ContrAgentname = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            dao = DaoManager.createDao(new JdbcConnectionSource(dbUrl,ContrAgentname,password),ContrAgent.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),ContrAgent.class);
                dao.getConnectionSource().closeQuietly();
            }
        }catch (Exception e){e.printStackTrace();}

    };

    private Dao<ContrAgent,Long> dao;

    @Override
    public void validateContrAgent(ContrAgent ContrAgent){

    }

    @Override
    public void createContrAgent(ContrAgent ContrAgent) {
        try {
            dao.create(ContrAgent);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Create ContrAgent \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public void updateContrAgent(ContrAgent ContrAgent) {
        try{
            dao.update(ContrAgent);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update ContrAgent \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public ContrAgent getContrAgentWithForeign(long foreignId) {
        ContrAgent resultValue = null;
        try{
            List<ContrAgent> requestList = dao.queryForEq("foreingId",foreignId);
            if (requestList.size() > 0 && requestList.size() < 2) {
                resultValue = requestList.get(0);
            }
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get ContrAgent With foreign \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public void removeContrAgent(ContrAgent ContrAgent) {
        try{
            dao.delete(ContrAgent);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Update ContrAgent \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
    }

    @Override
    public ContrAgent getContrAgent(String email) {
       return null;
    }

    @Override
    public ContrAgent getContrAgent(long id) {
        ContrAgent resultValue = null;
        try{
            resultValue = dao.queryForId(id);
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get ContrAgent With id \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultValue;
    }

    @Override
    public List<ContrAgent> getAllContrAgents() {
        List <ContrAgent> resultList = null;
        try {
            resultList = dao.queryForAll();
            dao.getConnectionSource().closeQuietly();
        }catch (Exception e){
            System.err.println("Critical Error Get All ContrAgents \n -> stackTrace \n" + e.getLocalizedMessage() );
        }
        return resultList;
    }
}