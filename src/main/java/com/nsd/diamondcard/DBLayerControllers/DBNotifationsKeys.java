package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.NotificationKey;

import java.util.List;

/**
 * Created by nsd on 07.12.17.
 */
public interface DBNotifationsKeys {
    void createNotificationKey(NotificationKey NotificationKey);
    void updateNotificationKey(NotificationKey NotificationKey);
    void removeNotificationKey(NotificationKey NotificationKey);
    List<NotificationKey> getNoficationKeysWithUserId(long userId);
}
