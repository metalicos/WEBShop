package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Account;

import java.sql.*;

public class AccountDao implements Crud<Account, Integer> {

    public static final String SQL_FIND_ACCOUNT_BY_EMAIL =
            "SELECT * " +
                    "FROM `webshop`.`account` " +
                    "WHERE `email` = ?";
    public static final String SQL_FIND_ACCOUNT_BY_ID =
            "SELECT * " +
                    "FROM `webshop`.`account` " +
                    "WHERE `id` = ?";
    public static final String SQL_UPDATE_ACCOUNT =
            "UPDATE `webshop`.`account` " +
                    "SET `email`= ?, `password`= ?, `role_id`= ?" +
                    "  WHERE `id` = ?";

    public static final String SQL_CREATE_ACCOUNT =
            "INSERT INTO `webshop`.`account` (`email`, `password`, `create_time`, `role_id`, `shopping_cart_id`) " +
                    "VALUES (?,?,?,?,?);";
    private static final String SQL_DELETE_ACCOUNT =
            "DELETE FROM `webshop`.`account` WHERE `id` = ?";


    @Override
    public Integer create(Account entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_ACCOUNT);
            // (`email`, `password`, `create_time`, `role_id`, `shopping_cart_id`)
            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getPassword());
            pstmt.setTimestamp(3, Timestamp.valueOf(entity.getCreateTime()));
            pstmt.setInt(4, entity.getRoleId());
            pstmt.setInt(5, entity.getShoppingCartId());
            pstmt.executeUpdate();
            insertedWithId = DBManager.getInstance().getLastInsertedId(pstmt);
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
        return insertedWithId;
    }

    @Override
    public Account read(Integer id) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Account> mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL_FIND_ACCOUNT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return account;
    }

    public Account read(String email) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Account> mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL_FIND_ACCOUNT_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return account;
    }

    @Override
    public void update(Account entity) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT);
            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getPassword());
            pstmt.setInt(3, entity.getRoleId());
            pstmt.setInt(4, entity.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    @Override
    public void delete(Account entity) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ACCOUNT);
            pstmt.setInt(1, entity.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    private static class AccountMapper implements EntityMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs) {
            try {
                Account account = new Account();
                account.setId(rs.getInt(Fields.ID));
                account.setEmail(rs.getString(Fields.ACCOUNT_EMAIL));
                account.setPassword(rs.getString(Fields.ACCOUNT_PASSWORD));
                account.setCreateTime(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
                account.setRoleId(rs.getInt(Fields.ACCOUNT_ROLE_ID));
                account.setShoppingCartId(rs.getInt(Fields.ACCOUNT_SHOPPING_CART_ID));
                return account;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
