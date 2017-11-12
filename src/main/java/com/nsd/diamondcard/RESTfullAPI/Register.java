package com.nsd.diamondcard.RESTfullAPI;

import com.nsd.diamondcard.DBLayerControllers.DBBuyer;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nsd on 13.11.17.
 */

@RestController
@RequestMapping
public class Register {

    @Autowired
    DBUser userService;

    @Autowired
    DBBuyer buyerService;


    @PostMapping
    public String register(@RequestParam String email,@RequestParam String pass,@RequestParam String rePass) {






       return null;
    }


}
