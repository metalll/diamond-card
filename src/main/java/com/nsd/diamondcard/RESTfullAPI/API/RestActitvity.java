package com.nsd.diamondcard.RESTfullAPI.API;

import com.google.gson.Gson;
import com.nsd.diamondcard.DBLayerControllers.*;
import com.nsd.diamondcard.Model.*;
import com.nsd.diamondcard.Model.Activity.Activity;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import io.netty.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SAAJResult;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.concurrent.ExecutionException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class RequestStruct {
    public RequestStruct() {
    }

    private String email;
    private String passwordHash;
    private String userCashbackCard;
    private String userCashbackValue;

    public void setEmail(String email) {
        this.email = email;
    }

    void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    void setUserCashbackCard(String userCashbackCard) {
        this.userCashbackCard = userCashbackCard;
    }

    void setUserCashbackValue(String userCashbackValue) {
        this.userCashbackValue = userCashbackValue;
    }

    String getEmail() {
        return email;
    }

    String getPasswordHash() {
        return passwordHash;
    }

    String getUserCashbackCard() {
        return userCashbackCard;
    }

    String getUserCashbackValue() {
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
    private DBBuyer userBuyerService;

    @Autowired
    private DBContrAgent contrAgentService;

    @Autowired
    private DBActivity activityService;

    @Autowired
    private DBNotifationsKeys notifationsKeysService;

    // 1-C cashback request
    // header X-AUTH = ReqBody data hash with hmacSha1 (RFC2104HMAC)

    @RequestMapping(value = "/pushcare", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody String reqBody, @RequestHeader(value = "X-AUTH") String hash) {

        boolean isValidReq = true;

        String calulatedHash = null;
        try {
            calulatedHash = calculateRFC2104HMAC(reqBody, "XCV-2345-ER-VXC-OxB-212");

            if (!hash.equals(calulatedHash)) {
                isValidReq = false;
            }
        } catch (Exception e) {
            isValidReq = false;
        }


        Gson gson = new Gson();

        RequestStruct request = gson.fromJson(reqBody, RequestStruct.class);

        User contrAgent = userService.getUser(request.getEmail());
        if (contrAgent != null) {
            isValidReq = isValidReq && contrAgent.getPasswd().equals(request.getPasswordHash());
        }

        User targetUser = userService.getUserWithCard(request.getUserCashbackCard());

        if (isValidReq && (targetUser != null) && (contrAgent != null) && (request.getUserCashbackValue() != null)) {
            Activity activity = new Activity();
            activity.setInitiatorId(contrAgent.getUserID());
            activity.setTargetId(targetUser.getUserID());
            activity.setOperationValue(request.getUserCashbackValue());
            activity.setType("CASHB");
            //   activity.setData(dateFormat.format(new Date()));
            //    activity.setEndData("14");
            activity.setActiveOperation(true);
            activity.setSuccessComplete(false);
            activityService.createActivity(activity);


            JSONResponce jsonResponce = new JSONResponce(true, null);
            return gson.toJson(jsonResponce);
        } else {
            JSONResponce jsonResponce = new JSONResponce(false, null);

            return gson.toJson(jsonResponce);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTR_AGENT')")
    @RequestMapping(value = "/activity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestParam("userCashCard") String userCashCard, @RequestParam("type") String type, @RequestParam("value") String value , HttpServletResponse response) {
        Gson gson = new Gson();
        try {


            Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
            long targetUserId = userService.getUserWithCard(userCashCard).getUserID();
            long initiatorId = userService.getUser(currentAuth.getName()).getUserID();
            ContrAgent initiatorContrAgent = contrAgentService.getContrAgentWithForeign(initiatorId);

            Buyer targetBuyer = userBuyerService.getBuyerWithForeign(targetUserId);
            float shadow = targetBuyer.getShadowBalance();
            float preparedOperationValue = initiatorContrAgent.getPercent() * Float.parseFloat(value);

            Activity activity = new Activity();
            activity.setInitiatorId(initiatorId);
            activity.setTargetId(targetUserId);
            activity.setOperationValue(value);
            activity.setType(type);
            activity.setDate(System.currentTimeMillis());
            activity.setDateOffset(initiatorContrAgent.getMillisecondToAppruveCashback());
            activity.setPreparedTagetOperationValue(String.valueOf(preparedOperationValue));
            activity.setActiveOperation(false);
            activity.setSuccessComplete(false);
            activityService.createActivity(activity);
            userBuyerService.updateBuyer(targetBuyer);

            JSONResponce jsonResponce = new JSONResponce(true, null);
            return gson.toJson(jsonResponce);
        } catch (Exception e) {
            JSONResponce jsonResponce = new JSONResponce(false, null);
            response.setStatus(406);
            return gson.toJson(jsonResponce);
        }

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/activity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String get() {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUser(currentAuth.getName());

        Gson gson = new Gson();
        JSONRequest request = new JSONRequest();
        request.setStatus("OK");
        request.setData(new HashMap());
        List<Activity> requestList = activityService.getAllActivitys();
        if (requestList != null) {
            List<Activity> filteredList = new ArrayList<>();

            for (Activity item : requestList) {
                if (
                        ((item.getTargetId() == currentUser.getUserID())
                                || (item.getInitiatorId() == currentUser.getUserID()))) {

                    filteredList.add(item);

                }
            }
            //noinspection unchecked
            request.getData().put("activites", filteredList);
        }

        return gson.toJson(request);
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/activity/accept", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String acceptCashBack(@RequestParam String cashbackId, @RequestParam String acceptCode) {


        return "";
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
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }
}
