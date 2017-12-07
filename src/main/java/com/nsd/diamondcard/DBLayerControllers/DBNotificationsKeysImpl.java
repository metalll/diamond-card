package com.nsd.diamondcard.DBLayerControllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nsd.diamondcard.Model.Mediator;
import com.nsd.diamondcard.Model.NotificationKey;

import java.net.URI;

/**
 * Created by nsd on 07.12.17.
 */
public class DBNotificationsKeysImpl implements DBNotifationsKeys {

    private Dao<NotificationKey,Long> dao;

    public DBNotificationsKeysImpl() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String Mediatorname = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            dao = DaoManager.createDao(new JdbcConnectionSource(dbUrl,Mediatorname,password), NotificationKey.class);
            if(!dao.isTableExists()){
                TableUtils.createTable(dao.getConnectionSource(),Mediator.class);
                dao.getConnectionSource().close();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void createNotificationKey(NotificationKey NotificationKey) {
        try {
           dao.create(NotificationKey);
           dao.getConnectionSource().close();
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

    public NotificationKey getNoficationKeyWithUserId(long userId) {
        return null;
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
