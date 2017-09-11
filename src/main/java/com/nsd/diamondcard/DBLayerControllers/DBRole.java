package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.User;
import com.nsd.diamondcard.Model.UserRole;
import com.nsd.diamondcard.Model.UserRoleEnum;

public interface DBRole {
    UserRoleEnum getRoleWithUserID(long id);
    void createRole(UserRole role);
    void updateRole(UserRole role);
    void removeRole(UserRole role);
}
