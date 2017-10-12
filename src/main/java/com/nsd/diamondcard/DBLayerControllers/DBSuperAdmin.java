package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.SuperAdmin;

import java.util.List;

public interface DBSuperAdmin {
    void createSuperAdmin(SuperAdmin SuperAdmin);
    void updateSuperAdmin(SuperAdmin SuperAdmin);
    void removeSuperAdmin(SuperAdmin SuperAdmin);
    SuperAdmin getSuperAdmin(String login);
    SuperAdmin getSuperAdmin(long id);
    List<SuperAdmin> getAllSuperAdmins();
    void validateSuperAdmin(SuperAdmin SuperAdmin);
}
