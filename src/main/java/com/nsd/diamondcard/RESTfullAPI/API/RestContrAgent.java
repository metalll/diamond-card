package com.nsd.diamondcard.RESTfullAPI.API;


import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBContrAgent;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/API")
@RestController()
public class RestContrAgent {

    @Autowired
    private DBContrAgent contrAgentService;


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/contragent/all",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String queryAll() {

        List resultList = contrAgentService.getAllContrAgents();
        HashMap respnceMap = new HashMap();
        respnceMap.put("status","OK");
        respnceMap.put("contrs",resultList);
        Gson gson = new Gson();
        return gson.toJson(respnceMap);
    }

}
