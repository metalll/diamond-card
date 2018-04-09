package com.nsd.diamondcard.Controller;

import com.nsd.diamondcard.DBLayerControllers.DBNotifationsKeys;
import com.nsd.diamondcard.DBLayerControllers.DBUser;
import com.nsd.diamondcard.DBLayerControllers.NotificatonType;
import com.nsd.diamondcard.Model.NotificationEntity;
import com.nsd.diamondcard.Model.User;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Notification;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class NotificationsController {

    @Autowired
    DBNotifationsKeys notificationStorage;

    @Autowired
    DBUser userStorage;



    public void sendPush(String targetEmail, Map<String,?> payloadInfo, NotificatonType type) {

        User currUser = userStorage.getUser(targetEmail);

        List<NotificationEntity>notificationEntities = notificationStorage.getNoficationKeysWithUserId(currUser.getUserID());

        for (NotificationEntity entity : notificationEntities) {
           if (entity.getUserDeviceType().toLowerCase().equals("ios")) {
               sendiOSPush(payloadInfo,type,entity.getKey());
           } else {
               sendAndroidPush(targetEmail,payloadInfo,type,entity.getKey());
           }
        }


    }

    private void sendiOSPush(Map<String,?> payloadInfo, NotificatonType type,String deviceToken) {

        if (deviceToken != null) {
            ClassLoader classLoader = getClass().getClassLoader();
            try {
                final ApnsClient apnsClient = new ApnsClientBuilder()
                        .setClientCredentials(new File("/app/diamondCard.p12"), "QazWsx321").setApnsServer("api.development.push.apple.com",443).build();

                final SimpleApnsPushNotification pushNotification;


                final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
                payloadBuilder.setAlertBody(this.textFromNotificationType(type));
                payloadBuilder.setSoundFileName("default");
                for (String key : payloadInfo.keySet()) {
                    payloadBuilder.addCustomProperty(key,payloadInfo.get(key));
                }

                final String payload = payloadBuilder.buildWithDefaultMaximumLength();
                final String token = TokenUtil.sanitizeTokenString(deviceToken);
                System.out.println(token);

                pushNotification = new SimpleApnsPushNotification(token, "com.nsd.Diamond-Club", payload);



                final Future<PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture =
                        apnsClient.sendNotification(pushNotification);

                try {
                    final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                            sendNotificationFuture.get();

                    if (pushNotificationResponse.isAccepted()) {
                        System.out.println("Push notification accepted by APNs gateway.");
                    } else {
                        System.out.println("Notification rejected by the APNs gateway: " +
                                pushNotificationResponse.getRejectionReason());

                        if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
                            System.out.println("\t…and the token is invalid as of " +
                                    pushNotificationResponse.getTokenInvalidationTimestamp());
                        }
                    }
                } catch (final ExecutionException e) {
                    System.err.println("Failed to send push notification.");
                    e.printStackTrace();
                }



            } catch (Exception e) {
                e.printStackTrace();


                File f = new File("."); // current directory

                File[] files = f.listFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.print("directory:");
                    } else {
                        System.out.print("     file:");
                    }
                    //  System.out.println(file.getCanonicalPath());
                }


            }
        }
    }


    private void sendAndroidPush(String targetEmail, Map<String,?> payloadInfo, NotificatonType type,String deviceToken){

    }




    private String textFromNotificationType(NotificatonType type) {

        String endResult = "";

        switch (type) {
            case NEW_CASHBACK:
                endResult = "You has a new cashback";
                break;
            case CASHBACK_DID_REMOVED:
                endResult = "You cashback did removed";
                break;
            case CASHBACK_DID_APPROVED:
                endResult = "You cashback did approved.Check your balance";
                break;

        }


        return endResult;

    }

}
