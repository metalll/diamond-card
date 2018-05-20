package com.nsd.diamondcard.UI;


import com.nsd.diamondcard.Controller.NotificationsController;
import com.nsd.diamondcard.Model.NotificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.soap.Addressing;
import java.util.HashMap;
import java.util.Map;

import static com.nsd.diamondcard.DBLayerControllers.NotificatonType.NEW_CASHBACK;

@Controller
@RequestMapping
public class IndexPage {


    @Autowired
    NotificationsController controller;

    private static final String VIEW_NAME = "index";

    @GetMapping("/")
    public ModelAndView index(){
        Map<String, String> model = new HashMap<>();


//        controller.sendPush("alex",new HashMap<>(),NEW_CASHBACK);

        return new ModelAndView(VIEW_NAME, model);
    }
}
