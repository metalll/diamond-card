package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Mediator;
import com.nsd.diamondcard.Model.NotificationKey;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

/**
 * Created by nsd on 07.12.17.
 */
@Service
public class DBNotificationsKeysImpl implements DBNotifationsKeys {

    private Dao<NotificationKey,Long> dao;

    public DBNotificationsKeysImpl() {
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(), NotificationKey.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),NotificationKey.class);
                dao.getConnectionSource().close();
            }
        } catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void createNotificationKey(NotificationKey NotificationKey) {
        try {
           dao.createOrUpdate(NotificationKey);

        } catch (Exception e) {
            System.out.println("Notifaction DB error create -> stack ->::" + e.getLocalizedMessage());
        }
    }

    @Override
    public void updateNotificationKey(NotificationKey NotificationKey) {
        try {
            dao.update(NotificationKey);
            dao.getConnectionSource().close();
        } catch (Exception e) {
            System.out.println("Notifaction DB error update -> stack ->::" + e.getLocalizedMessage());

        }
    }

    public List<NotificationKey> getNoficationKeysWithUserId(long userId) {
        List<NotificationKey> retVal = null;
        try {
            List<NotificationKey> retList = dao.queryForEq("userId",Long.parseLong(String.valueOf(userId)));
            if (retList.size() > 0) {
                retVal = retList;
            }
        } catch (Exception e) {
            System.out.println("Notifaction DB error get -> stack ->::" + e.getLocalizedMessage());
        }
        return retVal;
    }

    @Override
    public void removeNotificationKey(NotificationKey NotificationKey) {
        try {
            dao.delete(NotificationKey);
            dao.getConnectionSource().close();
        } catch (Exception e) {
            System.out.println("Notifaction DB error get -> stack ->::" + e.getLocalizedMessage());
        }
    }
}
