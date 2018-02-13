package com.nsd.diamondcard.Controller;

import com.nsd.diamondcard.DBLayerControllers.DBActivity;
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

    @Scheduled(cron = "0 0 20 * * *")
    public void acceptCashbaks() {






    }
}
