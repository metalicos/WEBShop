package com.ostap.komplikevych.webshop;

import com.ostap.komplikevych.webshop.constant.Const;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static DBManager instance;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        String url = Const.getProperty("db.url");
        String user = Const.getProperty("db.user");
        String password = Const.getProperty("db.password");
        return DriverManager.getConnection(url,user,password);
    }




}
