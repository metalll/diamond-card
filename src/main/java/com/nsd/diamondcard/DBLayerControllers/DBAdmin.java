package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.Admin;

import java.util.List;

public interface DBAdmin {
    void createAdmin(Admin Admin);
    void updateAdmin(Admin Admin);
    void removeAdmin(Admin Admin);
    Admin getAdmin(String login);
    Admin getAdmin(long id);
    List<Admin> getAllAdmins();
    void validateAdmin(Admin Admin);
}
