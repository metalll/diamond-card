package com.nsd.diamondcard.RESTAlphaClient.RESTAlphaClienUtils;

import java.util.Base64;

public class Utils {

    public static String generateHeaderValueParntesTokenString(String login,String pass) {
        if (login.length() < 1 || pass.length() < 1) {
            return null;
        } else {
            String retVal = null;
            try {
                retVal = Base64.getEncoder().encodeToString((login + ":" + pass).getBytes("utf-8"));
            } catch (Exception e) {
                System.out.println("com.nsd.diamondcard.RESTAlphaClient.RESTAlphaClienUtils -> Utils -> generateHeaderValueParntesTokenString -> line 18" + e.getLocalizedMessage());
            }

            if (retVal !=null) {
                retVal = "Basic " + retVal;
            }

            return retVal;
        }
    }
}
