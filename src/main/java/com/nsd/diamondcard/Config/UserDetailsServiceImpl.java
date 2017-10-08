package com.nsd.diamondcard.Config;


import com.nsd.diamondcard.DBLayerControllers.DBRole;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DBUser userService;

    @Autowired
    private DBRole userRole;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.getUser(s);


        Set<GrantedAuthority> roles = new HashSet<>();
        if(user!=null) {
            roles.add(new SimpleGrantedAuthority(userRole.getRoleWithUserID(user.getUserID()).name()));
        }else{
            throw new UsernameNotFoundException("bad auth");
        }

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPasswd(),
                        roles);




        return userDetails;
    }
}
