package com.ostap.komplikevych.webshop.constant;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Const {
    private static final String DB_PROPERTIES = "application.properties";
    public static final Logger logger = LogManager.getLogger(Const.class.getName());

    public static String getProperty(String key) {
        Properties props = null;
        try (FileInputStream fis = new FileInputStream(DB_PROPERTIES)) {
            props = new Properties();
            props.load(fis);
        } catch (IOException e) {
            Const.logger.error(e.getMessage());
        }
        if(props!=null) {
            return props.getProperty(key);
        }
        return null;
    }


}
