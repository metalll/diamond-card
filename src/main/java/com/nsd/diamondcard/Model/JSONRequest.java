package com.nsd.diamondcard.Model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nsd on 08.10.17.
 */
public class JSONRequest {
    private String status;
    private Map data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
