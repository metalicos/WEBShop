package com.ostap.komplikevych.webshop.dao;

/**
 * The type Fields that contains names of the tables fields.
 *
 * @author Ostap Komplikevych
 */
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

    // PRODUCT
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_AMOUNT = "amount";
    public static final String PRODUCT_ORDERED_AMOUNT = "ordered_amount";
    public static final String PRODUCT_CATEGORY_ID = "category_id";

    // PRODUCT_IN_CART
    public static final String PRODUCT_IN_CART_PRODUCT_ID = "product_id";
    public static final String PRODUCT_IN_CART_AMOUNT = "product_pcs_at_cart";

    // PRODUCT_IN_ORDER
    public static final String PRODUCT_IN_ORDER_ACCOUNT_ORDER_ID = "account_order_id";
    public static final String PRODUCT_IN_ORDER_PRODUCT_ID = "product_id";
    public static final String PRODUCT_IN_ORDER_PRICE = "price";
    public static final String PRODUCT_IN_ORDER_PRODUCT_AMOUNT = "product_amount";

    // PRODUCT_DETAIL
    public static final String PRODUCT_DETAIL_NAME_UA = "name_ua";
    public static final String PRODUCT_DETAIL_NAME_EN = "name_en";
    public static final String PRODUCT_DETAIL_COLOR_UA = "color_ua";
    public static final String PRODUCT_DETAIL_COLOR_EN = "color_en";
    public static final String PRODUCT_DETAIL_SIZE_UA = "size_ua";
    public static final String PRODUCT_DETAIL_SIZE_EN = "size_en";
    public static final String PRODUCT_DETAIL_ABOUT_UA = "about_ua";
    public static final String PRODUCT_DETAIL_ABOUT_EN = "about_en";
    public static final String PRODUCT_DETAIL_PHOTO_1 = "photo_1";
    public static final String PRODUCT_DETAIL_PHOTO_2 = "photo_2";
    public static final String PRODUCT_DETAIL_PHOTO_3 = "photo_3";
    public static final String PRODUCT_DETAIL_PRODUCT_ID = "product_id";

    // CATEGORY
    public static final String CATEGORY_NAME_EN = "name_en";
    public static final String CATEGORY_NAME_UA = "name_ua";
    public static final String CATEGORY_DESCRIPTION_EN = "description_en";
    public static final String CATEGORY_DESCRIPTION_UA = "description_ua";

    // ACCOUNT_ORDER
    public static final String ACCOUNT_ORDER_ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_ORDER_STATUS_ID = "status_id";
    public static final String ACCOUNT_ORDER_DROPPED_BY_ACCOUNT_ID = "droped_by_account_id";


}
