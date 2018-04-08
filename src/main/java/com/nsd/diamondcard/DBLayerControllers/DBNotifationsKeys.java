package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.NotificationEntity;

import java.util.List;

/**
 * Created by nsd on 07.12.17.
 */
public interface DBNotifationsKeys {
    void createNotificationKey(NotificationEntity NotificationEntity);
    void updateNotificationKey(NotificationEntity NotificationEntity);
    void removeNotificationKey(NotificationEntity NotificationEntity);
    List<NotificationEntity> getNoficationKeysWithUserId(long userId);
}
