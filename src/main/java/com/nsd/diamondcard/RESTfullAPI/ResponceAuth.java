package com.nsd.diamondcard.RESTfullAPI;

import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBBuyer;
import com.nsd.diamondcard.DBLayerControllers.DBContrAgent;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.DBLayerControllers.DBUserImpl;
import com.nsd.diamondcard.Model.JSONRequest;
import com.nsd.diamondcard.Model.UserRoleEnum;
import com.nsd.diamondcard.Utils.Constants;
import com.nsd.diamondcard.Utils.UserRoleCoverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.nsd.diamondcard.Model.UserRoleEnum.ROLE_BUYER;
import static com.nsd.diamondcard.Model.UserRoleEnum.ROLE_CONTR_AGENT;
import static com.nsd.diamondcard.Model.UserRoleEnum.ROLE_NONE;

/**
 * Created by nsd on 08.10.17.
 */

@RestController
@RequestMapping
public class ResponceAuth {

    public static final String RESPONCE_AUTH_PATH = "/auth";

    @Autowired
    private DBUser userService;

    @Autowired
    private DBBuyer userBuyerService;

    @Autowired
    private DBContrAgent contrAgentService;

    @PostMapping(RESPONCE_AUTH_PATH)
    public String responceGet(){

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        if(UserRoleCoverter.convert(((GrantedAuthority) currentAuth.getAuthorities().toArray()[0]).getAuthority()) == ROLE_NONE){
            Gson gson = new Gson();
            JSONRequest request = new JSONRequest();
            request.setData(new HashMap());
            request.setStatus("BAD");
            return gson.toJson(request);
        }

        Gson gson = new Gson();

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserRoleEnum currentRole = UserRoleCoverter.convert(((GrantedAuthority) currentAuth.getAuthorities().toArray()[0]).getAuthority());

        String currUserName = user.getUsername();

        System.out.println("Current auth role: "  + Constants.CONSOLE_ANSI_PURPLE + currentRole.name());

        JSONRequest request = new JSONRequest();
        request.setData(new HashMap());

        com.nsd.diamondcard.Model.User tUser = userService.getUser(currUserName);
        switch (currentRole){

            case ROLE_NONE:
                request.setStatus("BAD");
                break;
            default:
                request.setStatus("OK");
                tUser.setPasswd("");
                tUser.setBillingCardNum("");
                request.getData().put("user",tUser);
                request.getData().put("role",currentRole.name());
        }

        if (currentRole == ROLE_BUYER) {
            request.getData().put("fullInfo",userBuyerService.getBuyerWithForeign(tUser.getUserID()));
        }
        if (currentRole == ROLE_CONTR_AGENT) {
            request.getData().put("fullInfo",contrAgentService.getContrAgentWithForeign(tUser.getUserID()));

        }
        return gson.toJson(request);
    }
}
