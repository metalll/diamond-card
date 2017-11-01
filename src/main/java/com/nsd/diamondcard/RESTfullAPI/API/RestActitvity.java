package com.nsd.diamondcard.RESTfullAPI.API;

import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBActivity;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.JSONRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/API")
@RestController()
public class RestActitvity {


    @Autowired
    DBActivity activityService;


    @RequestMapping(value = "/activity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String add() {

        JSONRequest request = new JSONRequest();
        request.setStatus("OK");
        request.setData(new ArrayList());




        return "";
    }

    @RequestMapping(value = "/activity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String get() {
        Gson gson = new Gson();
        JSONRequest request = new JSONRequest();
        request.setStatus("OK");
        request.setData(new ArrayList());
        List<Activity>requestList = activityService.getAllActivitys();
        if (requestList!=null) {
            request.getData().add(requestList);
        }


        return gson.toJson(request);
    }

    @RequestMapping(value = "/update/activity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update() {


        return "";
    }


}
