package com.nsd.diamondcard.RESTfullAPI.Notifications;


import com.nsd.diamondcard.DBLayerControllers.DBNotifationsKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private DBNotifationsKeys keys;


    @RequestMapping(value = "/subscribe",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String subscribe(@RequestParam("key")String key){




        return "BAD";
    }

    @RequestMapping(value = "/unsubscribe",method = RequestMethod.POST)
    public String unsubscribe(){

        return "OK";
    }

}
