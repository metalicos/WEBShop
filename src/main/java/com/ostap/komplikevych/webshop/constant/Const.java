package com.ostap.komplikevych.webshop.constant;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Const {//WEB-INF\classes\com\ostap\komplikevych\webshop\constant\
    private static final String PROPERTIES = "application.properties";
    public static final Logger logger = LogManager.getLogger(Const.class.getName());

    public static final String PAGE_PATH_LOGIN = "/WEB-INF/jsp/login/login.jsp";
    public static final String PAGE_PATH_PROFILE = "/WEB-INF/jsp/profile/profile.jsp";
    public static final String PAGE_PATH_REGISTRATION = "/WEB-INF/jsp/registration/registration.jsp";

    public static final String PAGE_PATH_SHOPPING_CART = "/WEB-INF/jsp/shopping-cart/shopping-cart.jsp";
    public static final String PAGE_PATH_ORDERS = "/WEB-INF/jsp/orders/orders.jsp";

    public static final String PAGE_PATH_ERROR = "error.jsp";
    public static final String PAGE_PATH_HOME = "index.jsp";

    public static final String COMMAND_LOGIN = "login";
    public static final String COMMAND_OPEN_LOGIN_PAGE = "open-login-page";

    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_OPEN_REGISTER_PAGE = "open-register-page";

    public static final String COMMAND_LOGOUT = "logout";
    public static final String COMMAND_MY_CART = "my-cart";
    public static final String COMMAND_MY_ORDERS = "my-orders";

    public static String getProperty(String key) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(PROPERTIES);
            Properties properties = new Properties();
            properties.load(input);
            String property = properties.getProperty(key);
            logger.info("Property GET: " +  property);
            return property;
        } catch (IOException ex) {
            logger.fatal(ex);
            return null;
        }
    }
}
