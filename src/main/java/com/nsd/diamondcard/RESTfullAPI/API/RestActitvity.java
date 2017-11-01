package com.nsd.diamondcard.RESTfullAPI.API;

import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBActivity;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.JSONRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "/API")
@RestController()
public class RestActitvity {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    DBUser userService;

    @Autowired
    DBActivity activityService;

    @RequestMapping(value = "/activity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestParam("userCashCard") String userCashCard,@RequestParam("type") String type,@RequestParam ("value") String value) {
        Gson gson = new Gson();

       Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        Activity activity = new Activity();

        activity.setInitiatorId(userService.getUser(currentAuth.getName()).getUserID());
        activity.setTargetId(userService.getUserWithCard(userCashCard).getUserID());
        activity.setOperationValue(value);
        activity.setType(type);
        activity.setData(dateFormat.format(new Date()));
        activity.setEndData("unknown");
        activity.setActiveOperation(true);
        activity.setSuccessComplete(false);

        activityService.createActivity(activity);

        JSONRequest request = new JSONRequest();
        request.setStatus("OK");
        request.setData(new ArrayList());

        return gson.toJson(request);
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
