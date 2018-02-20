package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import com.nsd.diamondcard.BaseUtils.Logger;
import com.nsd.diamondcard.Model.Activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class DBActivityImpl implements DBActivity {

    @Autowired
    private Logger logger;

    public DBActivityImpl() {
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(), Activity.class);
            if (!dao.isTableExists()) {
                TableUtils.createTable(dao.getConnectionSource(), Activity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Dao<Activity, Long> dao;

    @Override
    public void validateActivity(Activity activity) {

    }

    @Override
    public void createActivity(Activity activity) {
        try {
            dao.create(activity);
        } catch (Exception e) {
            logger.log("DBActivityImpl -> createActivity -> " + e.getMessage());
        }
    }

    @Override
    public void updateActivity(Activity activity) {
        try {
            dao.update(activity);
        } catch (Exception e) {
            logger.log("DBActivityImpl -> updateActivity -> " + e.getMessage());
        }
    }

    @Override
    public void removeActivity(Activity activity) {
        try {
            dao.delete(activity);
        } catch (Exception e) {
            logger.log("DBActivityImpl -> removeActivity -> " + e.getMessage());
        }
    }

    @Override
    public Activity getActivity(long id) {
        Activity resultValue = null;
        try {
            resultValue = dao.queryForId(id);
        } catch (Exception e) {
            logger.log("DBActivityImpl -> getActivity -> " + e.getMessage());
        }
        return resultValue;
    }

    @Override
    public List<Activity> getAllActivitys() {
        List<Activity> resultList = null;
        try {
            resultList = dao.queryForAll();
        } catch (Exception e) {
            logger.log("DBActivityImpl -> getActivity -> " + e.getMessage());
        }
        return resultList;
    }
}