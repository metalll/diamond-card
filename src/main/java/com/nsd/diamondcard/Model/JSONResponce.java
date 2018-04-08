package com.nsd.diamondcard.Model;

import java.util.HashMap;
import java.util.Map;

public class JSONResponce {


    public JSONResponce(boolean status, Map responceData) {


        this.status = status ? "OK" : "ERROR";
        this.responceData = responceData != null ? responceData : new HashMap();

    }


    private String status;
    private Map responceData;


}
