package com.ostap.komplikevych.webshop;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con1 = DBManager.getConnection();
        con1.prepareStatement("select * from account;");

    }
}
