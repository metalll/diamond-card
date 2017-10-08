package com.nsd.diamondcard.Model;

import java.util.ArrayList;

/**
 * Created by nsd on 08.10.17.
 */
public class JSONRequest {
    private String status;
    private ArrayList data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }
}
