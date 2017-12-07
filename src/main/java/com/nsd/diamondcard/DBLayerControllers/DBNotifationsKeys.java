package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.NotificationKey;

/**
 * Created by nsd on 07.12.17.
 */
public interface DBNotifationsKeys {
    void createNotificationKey(NotificationKey NotificationKey);
    void updateNotificationKey(NotificationKey NotificationKey);
    void removeNotificationKey(NotificationKey NotificationKey);
    NotificationKey getNoficationKeyWithUserId(long userId);
}
