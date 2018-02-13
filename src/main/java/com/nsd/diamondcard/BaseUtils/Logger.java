package com.nsd.diamondcard.BaseUtils;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class Logger {

    public Logger() {}

    private org.slf4j.Logger log = null;

    public void log(String message) {
        if (log == null) {
            log = LoggerFactory.getLogger("application");
        }
        log.debug(message);
    }
}
