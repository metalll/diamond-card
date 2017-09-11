package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.UserRoleEnum;
import org.springframework.stereotype.Service;

@Service
public class DBRoleImpl implements DBRole {

    public DBRoleImpl(){


    }

    @Override
    public UserRoleEnum getRoleWithID(long roleID) {
            return UserRoleEnum.ROLE_USER;
    }
}
