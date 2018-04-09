package com.nsd.diamondcard.RESTfullAPI.Notifications;


import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBNotifationsKeys;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.Model.JSONResponce;
import com.nsd.diamondcard.Model.NotificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.gcj.GCJSerializationInstantiator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/subscribe",method = RequestMethod.POST)
    public String subscribe(@RequestParam("key")String key, @RequestParam("device")String userDeviceType, @RequestParam("development")String development,@RequestParam("deviceUUID")String deviceUUIUD) {
        try {
            if (key == null || key.equals("")) {
                Gson gson = new Gson();
                JSONResponce jsonResponce = new JSONResponce(false,null);
                return gson.toJson(jsonResponce);
            }
        } catch (NullPointerException e) {
            Gson gson = new Gson();
            JSONResponce jsonResponce = new JSONResponce(false,null);
            return gson.toJson(jsonResponce);
        }


        System.out.println("key = " + key);

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String userName = currentAuth.getName();


        long userId = userService.getUser(userName).getUserID();

        NotificationEntity keyN = null;

        for (NotificationEntity tempKey : keysManager.getNoficationKeysWithUserId(userId)) {

            if (tempKey.getKey().equals(key)) {
                keyN = tempKey;
                break;
            }

        }


        if (keyN == null) {
            keyN = new NotificationEntity();
        }

        keyN.setUserId(userId);
        keyN.setKey(key);
        keyN.setDevelopment(development);
        keyN.setUserDeviceId(deviceUUIUD);
        keyN.setUserDeviceType(userDeviceType);
        keysManager.createNotificationKey(keyN);
        Gson gson = new Gson();
        JSONResponce jsonResponce = new JSONResponce(true,null);
        return gson.toJson(jsonResponce);
    }



    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/unsubscribe",method = RequestMethod.POST)
    public String unsubscribe(){
//        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
//        String userName = currentAuth.getName();
//        long userId = userService.getUser(userName).getUserID();
//        try {
//
//        } catch (Exception e) {
//
//        }

        Gson gson = new Gson();
        JSONResponce jsonResponce = new JSONResponce(true,null);
        return gson.toJson(jsonResponce);
    }

}
