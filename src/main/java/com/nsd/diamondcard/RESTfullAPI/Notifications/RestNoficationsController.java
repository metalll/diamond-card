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
import java.io.StringReader;

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
    public String subscribe(@RequestParam("key")String key, @RequestParam("device")String userDeviceType, @RequestParam("development")String development,@RequestParam("deviceUUID")String deviceUUIUD) {
        try {
            if (key == null && key.equals("")) {
                return "BAD";
            }
        } catch (NullPointerException e) {
            return "BAD";
        }


        System.out.println("key = " + key);

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String userName = currentAuth.getName();


        long userId = userService.getUser(userName).getUserID();

        NotificationKey keyN = null;

        for (NotificationKey tempKey : keysManager.getNoficationKeysWithUserId(userId)) {

            if (tempKey.getKey().equals(key)) {
                keyN = tempKey;
                break;
            }

        }


        if (keyN == null) {
            keyN = new NotificationKey();
        }

        keyN.setUserId(userId);
        keyN.setKey(key);
        keyN.setDevelopment(development);
        keyN.setUserDeviceType(userDeviceType);
        keysManager.createNotificationKey(keyN);
        return "OK";
    }

    @RequestMapping(value = "/unsubscribe",method = RequestMethod.POST)
    public String unsubscribe(){
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String userName = currentAuth.getName();
        long userId = userService.getUser(userName).getUserID();
        try {

        } catch (Exception e) {

        }

        return "OK";
    }

}
