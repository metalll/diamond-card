package com.nsd.diamondcard.RESTfullAPI.API;


import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBBuyer;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.Model.Buyer;
import com.nsd.diamondcard.Model.JSONRequest;
import com.nsd.diamondcard.Model.UserRoleEnum;
import com.nsd.diamondcard.Utils.Constants;
import com.nsd.diamondcard.Utils.UserRoleCoverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;

import static com.nsd.diamondcard.Model.UserRoleEnum.ROLE_NONE;


@RequestMapping(value = "/API")
@RestController()
public class RestBuyer {

    @Autowired
    DBBuyer buyerService;

    @Autowired
    DBUser userService;


    @RequestMapping(value = "/buyer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(Principal principal) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        if(UserRoleCoverter.convert(((GrantedAuthority) currentAuth.getAuthorities().toArray()[0]).getAuthority()) == ROLE_NONE){
            Gson gson = new Gson();
            JSONRequest request = new JSONRequest();
            request.setData(new ArrayList());
            request.setStatus("BAD");
            return gson.toJson(request);
        }

        Gson gson = new Gson();

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserRoleEnum currentRole = UserRoleCoverter.convert(((GrantedAuthority) currentAuth.getAuthorities().toArray()[0]).getAuthority());

        String currUserName = user.getUsername();

        System.out.println(Constants.CONSOLE_ANSI_YELLOW + "Current auth role: "  + Constants.CONSOLE_ANSI_PURPLE + currentRole.name() + Constants.CONSOLE_ANSI_RESET );

        JSONRequest request = new JSONRequest();
        request.setData(new ArrayList());

        com.nsd.diamondcard.Model.User tUser = userService.getUser(currUserName);
        switch (currentRole){
            case ROLE_NONE:
                request.setStatus("BAD");
                break;
            default:
                request.setStatus("OK");
                tUser.setPasswd("");
                tUser.setBillingCardNum("");
                request.getData().add(tUser);
                request.getData().add(currentRole.name());
        }

    }

}
