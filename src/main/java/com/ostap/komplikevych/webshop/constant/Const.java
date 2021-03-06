package com.ostap.komplikevych.webshop.constant;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class contains constants.
 *
 * @author Ostap Komplikevych
 */
public class Const {


    private static final String PROPERTIES = "application.properties";
    public static final Logger logger = LogManager.getLogger(Const.class.getName());
    public static final String JSP_PAGE_LOCATION = "/WEB-INF/";
    public static final String REDIRECT_SPLITTER = "!~!";
    public static final String REDIRECT = "redirect:" + REDIRECT_SPLITTER;


    public static final String PAGE_LOGIN = JSP_PAGE_LOCATION + "login.jsp";
    public static final String PAGE_PROFILE = JSP_PAGE_LOCATION + "profile.jsp";
    public static final String PAGE_REGISTRATION = JSP_PAGE_LOCATION + "registration.jsp";
    public static final String PAGE_PRODUCT_INFO = JSP_PAGE_LOCATION + "product-info.jsp";
    public static final String PAGE_CREATE_PRODUCT = JSP_PAGE_LOCATION + "create-product.jsp";
    public static final String PAGE_SHOPPING_CART = JSP_PAGE_LOCATION + "shopping-cart.jsp";
    public static final String PAGE_ORDERS = JSP_PAGE_LOCATION + "orders.jsp";
    public static final String PAGE_CREATE_CATEGORY = JSP_PAGE_LOCATION + "create-category.jsp";
    public static final String PAGE_MAKE_ORDER = JSP_PAGE_LOCATION + "make-order.jsp";
    public static final String PAGE_USER_ORDERS = JSP_PAGE_LOCATION + "users-orders.jsp";
    public static final String PAGE_USERS = JSP_PAGE_LOCATION + "users.jsp";

    public static final String PAGE_HOME = JSP_PAGE_LOCATION + "home.jsp";
    public static final String PAGE_ERROR_PERMISSION_DENIED = "error-permission-denied.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    public static final String COMMAND_MY_ORDERS = "my-orders";

    public static final String RESOURCE_IMAGE_PATH = "/design/img/";

    public static String getProperty(String key) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(PROPERTIES);
            Properties properties = new Properties();
            properties.load(input);
            String property = properties.getProperty(key);
            // logger.trace("Property GET: " + property);
            return property;
        } catch (IOException ex) {
            logger.fatal(ex);
            return null;
        }
    }
}
