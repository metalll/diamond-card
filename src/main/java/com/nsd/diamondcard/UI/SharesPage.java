package com.nsd.diamondcard.UI;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class SharesPage {
    private static final String VIEW_NAME = "shares";

    @GetMapping("/"+VIEW_NAME)
    public ModelAndView index(){
        Map<String, String> model = new HashMap<>();

        return new ModelAndView(VIEW_NAME, model);
    }
}
