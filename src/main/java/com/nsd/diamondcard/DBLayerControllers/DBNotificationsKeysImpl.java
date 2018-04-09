package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.NotificationEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nsd on 07.12.17.
 */
@Service
public class DBNotificationsKeysImpl implements DBNotifationsKeys {

    private Dao<NotificationEntity,Long> dao;

    public DBNotificationsKeysImpl() {
        try {
            dao = DaoManager.createDao(DBConnectionFactory.getSource(), NotificationEntity.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),NotificationEntity.class);
                dao.getConnectionSource().close();
            }
        } catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void createNotificationKey(NotificationEntity NotificationEntity) {
        try {
           dao.createOrUpdate(NotificationEntity);

        } catch (Exception e) {
            System.out.println("Notifaction DB error create -> stack ->::" + e.getLocalizedMessage());
        }
    }

    @Override
    public void updateNotificationKey(NotificationEntity NotificationEntity) {
        try {
            dao.update(NotificationEntity);
            dao.getConnectionSource().close();
        } catch (Exception e) {
            System.out.println("Notifaction DB error update -> stack ->::" + e.getLocalizedMessage());

        }
    }

    public List<NotificationEntity> getNoficationKeysWithUserId(long userId) {
        List<NotificationEntity> retVal = null;
        try {
            List<NotificationEntity> retList = dao.queryForEq("userId",Long.parseLong(String.valueOf(userId)));
            if (retList.size() > 0) {
                retVal = retList;
            }
        } catch (Exception e) {
            System.out.println("Notifaction DB error get -> stack ->::" + e.getLocalizedMessage());
        }

        if (retVal == null) {
            retVal = new ArrayList<>();
        }

        return retVal;
    }

    @Override
    public void removeNotificationKey(NotificationEntity NotificationEntity) {
        try {
            dao.delete(NotificationEntity);
            dao.getConnectionSource().close();
        } catch (Exception e) {
            System.out.println("Notifaction DB error get -> stack ->::" + e.getLocalizedMessage());
        }
    }
}
