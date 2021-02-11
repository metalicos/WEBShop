package com.ostap.komplikevych.webshop.dao;

public class Fields {

    public static final String ID = "id";
    public static final String LAST_UPDATE_TIME = "last_update";
    public static final String CREATE_TIME = "create_time";

    // ACCOUNT
    public static final String ACCOUNT_EMAIL = "email";
    public static final String ACCOUNT_PASSWORD = "password";
    public static final String ACCOUNT_ROLE_ID = "role_id";
    public static final String ACCOUNT_SHOPPING_CART_ID = "shopping_cart_id";

    // ACCOUNT_DETAIL
    public static final String ACCOUNT_DETAIL_PHONE = "phone";
    public static final String ACCOUNT_DETAIL_ZIP_CODE = "zip_code";
    public static final String ACCOUNT_DETAIL_SURNAME_UA = "surname_ua";
    public static final String ACCOUNT_DETAIL_SURNAME_EN = "surname_en";
    public static final String ACCOUNT_DETAIL_FIRST_NAME_UA = "first_name_ua";
    public static final String ACCOUNT_DETAIL_FIRST_NAME_EN = "first_name_en";
    public static final String ACCOUNT_DETAIL_PATRONYMIC_UA = "patronymic_ua";
    public static final String ACCOUNT_DETAIL_PATRONYMIC_EN = "patronymic_en";
    public static final String ACCOUNT_DETAIL_COUNTRY_UA = "country_ua";
    public static final String ACCOUNT_DETAIL_COUNTRY_EN = "country_en";
    public static final String ACCOUNT_DETAIL_CITY_UA = "city_ua";
    public static final String ACCOUNT_DETAIL_CITY_EN = "city_en";
    public static final String ACCOUNT_DETAIL_STREET_UA = "street_ua";
    public static final String ACCOUNT_DETAIL_STREET_EN = "street_en";
    public static final String ACCOUNT_DETAIL_BUILDING_UA = "building_ua";
    public static final String ACCOUNT_DETAIL_BUILDING_EN = "building_en";
    public static final String ACCOUNT_DETAIL_FLAT_UA = "flat_ua";
    public static final String ACCOUNT_DETAIL_FLAT_EN = "flat_en";
    public static final String ACCOUNT_DETAIL_ACCOUNT_PHOTO = "account_photo";
    public static final String ACCOUNT_DETAIL_ACCOUNT_ID = "account_id";

    // SHOPPING_CART_HAS_PRODUCT
    public static final String SHOPPING_CART_HAS_PRODUCT_SHOPPING_CART_ID = "shopping_cart_id";
    public static final String SHOPPING_CART_HAS_PRODUCT_PRODUCT_ID = "product_id";

    // PRODUCT
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_AMOUNT = "amount";
    public static final String PRODUCT_ORDERED_AMOUNT = "ordered_amount";
    public static final String PRODUCT_CATEGORY_ID = "category_id";

    // ACCOUNT_ORDER
    public static final String ACCOUNT_ORDER_ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_ORDER_STATUS_ID = "status_id";
    public static final String ACCOUNT_ORDER_DROPPED_BY_ACCOUNT_ID = "droped_by_account_id";



}