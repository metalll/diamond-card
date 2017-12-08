package com.nsd.diamondcard.RESTfullAPI.Notifications;


import com.nsd.diamondcard.DBLayerControllers.DBNotifationsKeys;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.Model.NotificationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * Created by nsd on 07.12.17.
 */
@RequestMapping(value = "/API")
@RestController()
public class RestNoficationsController {

    @Autowired
    DBNotifationsKeys keysManager;

    @Autowired
    DBUser userService;

    @RequestMapping(value = "/subscribe",method = RequestMethod.POST)
    public String subscribe(@RequestParam("key")String key) {
        try {
            if (key == null && key.equals("")) {
                return "BAD";
            }
        } catch (NullPointerException e) {
            return "BAD";
        }

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String userName = currentAuth.getName();


        long userId = userService.getUser(userName).getUserID();

        NotificationKey keyN = keysManager.getNoficationKeyWithUserId(userId);

        if (keyN != null) {

            keyN.setKey(key);
            keysManager.updateNotificationKey(keyN);
            return "OK";
        } else {

            keyN = new NotificationKey();
            keyN.setUserId(userId);
            keyN.setKey(key);
            return "OK";
        }
    }

    @RequestMapping(value = "/unsubscribe",method = RequestMethod.POST)
    public String unsubscribe(){
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String userName = currentAuth.getName();
        long userId = userService.getUser(userName).getUserID();
        try {
            NotificationKey keyN = keysManager.getNoficationKeyWithUserId(userId);
            keysManager.removeNotificationKey(keyN);
        } catch (Exception e) {

        }
        return "OK";
    }

}
