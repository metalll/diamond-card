package com.nsd.diamondcard.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class Reg {

    @PostMapping("/regBuyer")
    //registration bueyer with standart POST
    public String POST(@RequestParam String w){

        return "";
    }







}
