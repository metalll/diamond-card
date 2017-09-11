package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.UserRoleEnum;

public interface DBRole {

    UserRoleEnum getRoleWithID(long roleID);
}
