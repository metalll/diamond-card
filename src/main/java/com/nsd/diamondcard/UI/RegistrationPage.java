package com.nsd.diamondcard.UI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class RegistrationPage {

    private static final String VIEW_NAME = "reg";

    @GetMapping("/"+VIEW_NAME)
    public ModelAndView GET(){
        Map<String, String> model = new HashMap<>();

        return new ModelAndView(VIEW_NAME, model);
    }

    @PostMapping("/"+VIEW_NAME)
    public String POST(@RequestParam("email") String email,
                       @RequestParam("pass") String pass
                       ){
        String JSONResult = "";



        return JSONResult;
    }
}
