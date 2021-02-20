package com.ostap.komplikevych.webshop.model;

import com.ostap.komplikevych.webshop.constant.Const;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;

public class DBManager {
    private static final BasicDataSource ds = new BasicDataSource();
    private static final DBManager instance = new DBManager();

    static {
        try {
            ds.setDriverClassName(Const.getProperty("db.driver.classname"));
            ds.setUrl(Const.getProperty("db.url"));
            ds.setUsername(Const.getProperty("db.user"));
            ds.setPassword(Const.getProperty("db.password"));
            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);
            ds.setDefaultAutoCommit(false);
            Const.logger.info(ds);
        } catch (Exception ex) {
            Const.logger.fatal(ex);
        }
    }

    private DBManager() {
    }

    public static DBManager getInstance() {
        return instance;
    }

    public static Connection getConnectionFromDriverManager() throws SQLException {
        String url;
        String user;
        String password;
        try {
            url = Const.getProperty("db.url");
            user = Const.getProperty("db.user");
            password = Const.getProperty("db.password");
        } catch (Exception ex) {
            Const.logger.error(ex);
            return null;
        }
        return DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void commit(Connection con) {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        }
    }

    public int getLastInsertedId(Statement st) {
        int insertedWithId = -1;
        if (st != null) {
            ResultSet rs = null;
            try {
                rs = st.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    insertedWithId = rs.getInt(1);
                } else {
                    Const.logger.error("Last inserted ID not found.");
                }
            } catch (Exception ex) {
                Const.logger.error(ex);
            } finally {
                close(rs);
            }
        }
        return insertedWithId;
    }

    public void rollback(Connection con) {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        }
    }

    public void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ex) {
                Const.logger.error(ex);
            }
        }
    }

}
