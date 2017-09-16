package com.nsd.diamondcard.Utils;

import com.nsd.diamondcard.Model.UserRoleEnum;

public class UserRoleCoverter {

    public static String convert(UserRoleEnum role){
        return role.name();
    }

    public static UserRoleEnum convert(String role){

        if(role.equals(UserRoleEnum.ROLE_TARGET_BUYER.name()))
            return UserRoleEnum.ROLE_TARGET_BUYER;

        if(role.equals(UserRoleEnum.ROLE_CONTR_AGENT.name()))
            return UserRoleEnum.ROLE_CONTR_AGENT;

        if(role.equals(UserRoleEnum.ROLE_TARGET_BUYER.name()))
            return UserRoleEnum.ROLE_TARGET_BUYER;

        if(role.equals(UserRoleEnum.ROLE_BUYER.name()))
            return UserRoleEnum.ROLE_BUYER;

        if(role.equals(UserRoleEnum.ROLE_ADMIN.name()))
            return UserRoleEnum.ROLE_ADMIN;

        if(role.equals(UserRoleEnum.ROLE_SUPERADMIN.name()))
            return UserRoleEnum.ROLE_SUPERADMIN;


        return UserRoleEnum.ROLE_NONE;
    }
}
