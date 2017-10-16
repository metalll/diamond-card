package com.nsd.diamondcard.Controller;


import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;

import java.util.Timer;
import java.util.TimerTask;

public class AplyerTimer{

private static volatile AplyerTimer instance = null;



public static AplyerTimer getInstance(){
    AplyerTimer localInstance = instance;
    if (localInstance == null) {
        synchronized (AplyerTimer.class) {
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new AplyerTimer();
                instance.start();
            }
        }
    }
    return localInstance;
}

private Timer timer = null;

private void start() {
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            //Todo APPLY EXPIRED TASKS
        }
    },0,(60000 * 60));


}



}
