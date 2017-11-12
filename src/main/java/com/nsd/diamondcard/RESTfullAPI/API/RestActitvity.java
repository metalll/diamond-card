package com.nsd.diamondcard.RESTfullAPI.API;

import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.DBActivity;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.nsd.diamondcard.Model.JSONRequest;
import com.nsd.diamondcard.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.soap.SAAJResult;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class RequestStruct {
    private String userName;
    private String email;
    private String passwordHash;
    private String userCashbackCard;
    private String userCashbackValue;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getUserCashbackCard() {
        return userCashbackCard;
    }

    public String getUserCashbackValue() {
        return userCashbackValue;
    }
}

@RequestMapping(value = "/API")
@RestController()
public class RestActitvity {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Autowired
    private DBUser userService;

    @Autowired
    private DBActivity activityService;

    // 1-C cashback request
    // header X-AUTH = ReqBody data hash with hmacSha1 (RFC2104HMAC)

    @RequestMapping(value = "/puscare",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody String reqBody,@RequestHeader(value = "X-AUTH") String hash) {

        boolean isValidReq = true;

        String calulatedHash = null;
        try {
            calulatedHash = calculateRFC2104HMAC(reqBody,"simple_key");
        }catch (Exception e) {
            isValidReq = false;
        }

        Gson gson = new Gson();

        RequestStruct request = gson.fromJson(reqBody,RequestStruct.class);

        User contrAgent = userService.getUser(request.getEmail());
        if (contrAgent != null) {
            isValidReq = isValidReq && contrAgent.getPasswd().equals(request.getPasswordHash());
        }

        User targetUser = userService.getUserWithCard(request.getUserCashbackCard());

        if (isValidReq && (targetUser != null) && (contrAgent !=null) && (calulatedHash != null) && (request.getUserCashbackValue()!= null)) {
            Activity activity = new Activity();
            activity.setInitiatorId(contrAgent.getUserID());
            activity.setTargetId(targetUser.getUserID());
            activity.setOperationValue(request.getUserCashbackValue());
            activity.setType("CASHB");
            activity.setData(dateFormat.format(new Date()));
            activity.setEndData("14");
            activity.setActiveOperation(true);
            activity.setSuccessComplete(false);
            activityService.createActivity(activity);

            JSONRequest request1 = new JSONRequest();
            request1.setStatus("OK");
            request1.setData(new ArrayList());
            return gson.toJson(request1);
        } else {
            JSONRequest jsonReq = new JSONRequest();
            jsonReq.setData(new ArrayList());
            jsonReq.setStatus("BAD");
            return gson.toJson(jsonReq);
        }
    }

    @RequestMapping(value = "/activity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestParam("userCashCard") String userCashCard,@RequestParam("type") String type,@RequestParam ("value") String value) {
        try {
            Gson gson = new Gson();

            Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
            Activity activity = new Activity();

            activity.setInitiatorId(userService.getUser(currentAuth.getName()).getUserID());
            activity.setTargetId(userService.getUserWithCard(userCashCard).getUserID());
            activity.setOperationValue(value);
            activity.setType(type);
            activity.setData(dateFormat.format(new Date()));
            activity.setEndData("14");
            activity.setActiveOperation(true);
            activity.setSuccessComplete(false);

            activityService.createActivity(activity);

            JSONRequest request = new JSONRequest();
            request.setStatus("OK");
            request.setData(new ArrayList());
            return gson.toJson(request);
        }catch (Exception e) {
            Gson gson = new Gson();
            JSONRequest jsonReq = new JSONRequest();
            jsonReq.setData(new ArrayList());
            jsonReq.setStatus("BAD");
            return gson.toJson(jsonReq);

        }

    }

    @RequestMapping(value = "/activity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String get() {
        Gson gson = new Gson();
        JSONRequest request = new JSONRequest();
        request.setStatus("OK");
        request.setData(new ArrayList());
        List<Activity>requestList = activityService.getAllActivitys();
        if (requestList!=null) {
            request.getData().add(requestList);
        }
        return gson.toJson(request);
    }

    @RequestMapping(value = "/update/activity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update() {

        return "";
    }

/*-------------------------------Hash calculator --------------------------------------*/

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException,InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }
}
