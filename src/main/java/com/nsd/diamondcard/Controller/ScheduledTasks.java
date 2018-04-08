package com.nsd.diamondcard.Controller;

import com.nsd.diamondcard.DBLayerControllers.DBActivity;
import com.nsd.diamondcard.DBLayerControllers.DBBuyer;
import com.nsd.diamondcard.DBLayerControllers.DBNotifationsKeys;
import com.nsd.diamondcard.Model.Activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Autowired
    DBActivity activityService;

    @Autowired
    DBNotifationsKeys notifationsKeys;

    @Autowired
    DBBuyer buyerService;

    @Scheduled(cron = "0 0 20 * * *")
    public void acceptCashbaks() {

        for (Activity activity : activityService.getAllActivitys()) {
            processingActivity(activity);
        }
    }

    private void processingActivity(Activity activity) {

        if (!activity.isActiveOperation()) {
            return;
        }

        if (!activity.isActiveOperation() && activity.isSuccessComplete()) {
            return;
        }
        long timestamp = System.currentTimeMillis();
        if (activity.isActiveOperation() && !activity.isSuccessComplete()) {
            if ((activity.getDate() + activity.getDateOffset()) >= timestamp) {
                acceptActivity(activity);
            }
        }
    }

    private void acceptActivity(Activity activity) {
        activity.setSuccessComplete(true);
        activity.setActiveOperation(false);

        // buyerService.getBuyer()

        // Todo accept cahback


        activityService.updateActivity(activity);
    }
}