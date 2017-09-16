package com.nsd.diamondcard.UI;

import com.nsd.diamondcard.Model.UserRoleEnum;
import com.nsd.diamondcard.Utils.Constants;
import com.nsd.diamondcard.Utils.UserRoleCoverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class UserOfficePage {
    private static final String VIEW_NAME = "user_office";



    @GetMapping("/"+VIEW_NAME)
    public ModelAndView GET(){

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        UserRoleEnum currentRole = UserRoleCoverter.convert(((GrantedAuthority) currentAuth.getAuthorities().toArray()[0]).getAuthority());

        System.out.println(Constants.CONSOLE_ANSI_YELLOW + "Current auth role: "  + Constants.CONSOLE_ANSI_PURPLE + currentRole.name() + Constants.CONSOLE_ANSI_RESET );

        switch (currentRole){
            case ROLE_NONE:
                return new ModelAndView("redirect:/login");
            case ROLE_SUPERADMIN:
                return new ModelAndView("offices/superadmin",new HashMap<>());
            case ROLE_ADMIN:
                return new ModelAndView("offices/admin",new HashMap<>());
            case ROLE_BUYER:
                return new ModelAndView("offices/buyer",new HashMap<>());
            case ROLE_TARGET_BUYER:
                return new ModelAndView("offices/targetbuyer",new HashMap<>());
            case ROLE_MEDIATIOR:
                return new ModelAndView("offices/mediator",new HashMap<>());
            case ROLE_CONTR_AGENT:
                return new ModelAndView("offices/contragent",new HashMap<>());
            default:
                return new ModelAndView("redirect:/");
        }
    }
}