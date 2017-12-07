package com.nsd.diamondcard.RESTfullAPI;

import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBBuyer;
import com.nsd.diamondcard.DBLayerControllers.DBRole;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by nsd on 13.11.17.
 */

@RestController
@RequestMapping()
public class Register {

    @Autowired
    DBUser userService;

    @Autowired
    DBBuyer buyerService;

    @Autowired
    DBRole userRole;

    @PostMapping("/Reg")
    public String register(@RequestParam String email,@RequestParam String pass,@RequestParam String rePass) {
        try {

            if (userService.getUser(email)!=null) {
                JSONRequest request = new JSONRequest();
                request.setStatus("USER_IS_EXIST");
                request.setData(new ArrayList());
                return new Gson().toJson(request);
            }

            String uuid = UUID.randomUUID().toString().replace("-", "");
            User user = new User();
            user.setEmail(email);
            user.setPasswd(stringSha1Hash(pass));
            user.setCashbackCardNumber(uuid);
            user.setBillingCardNum("null");

            userService.createUser(user);

            Buyer buyer = new Buyer();
            buyer.setBalance(0.0f);
            buyer.setForeignAdminId(-1);
            buyer.setForeignId(user.getUserID());
            buyer.setIsActive("YES");
            buyer.setPercent(-1.0f);

            buyerService.createBuyer(buyer);

            UserRole role = new UserRole();
            role.setRole(UserRoleEnum.ROLE_BUYER);
            role.setUserID(String.valueOf(buyer.getId()));

            userRole.createRole(role);


            JSONRequest request = new JSONRequest();
            request.setStatus("OK");
            request.setData(new ArrayList());
            return new Gson().toJson(request);
        } catch (Exception e) {
            JSONRequest request = new JSONRequest();
            request.setStatus("BAD");
            request.setData(new ArrayList());
            return new Gson().toJson(request);

        }
    }

    /*------------------ Sha1 Hash ------------ */

    public static String stringSha1Hash(String source) {
        return new ShaPasswordEncoder().encodePassword(source,null);
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                    Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
}
