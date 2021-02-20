package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.model.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type AccountDao.
 *
 * @author Ostap Komplikevych
 */
public class AccountDao {

    public static final String SQL_READ_ACCOUNT_BY_EMAIL;

    public static final String SQL_READ_ACCOUNT_BY_ID;

    public static final String SQL_UPDATE_ACCOUNT;

    public static final String SQL_CREATE_ACCOUNT;

    private static final String SQL_DELETE_ACCOUNT;

    static {
        SQL_CREATE_ACCOUNT = Const.getProperty("sql.create_account");
        SQL_READ_ACCOUNT_BY_ID = Const.getProperty("sql.read_account_by_id");
        SQL_READ_ACCOUNT_BY_EMAIL = Const.getProperty("sql.read_account_by_email");
        SQL_UPDATE_ACCOUNT = Const.getProperty("sql.update_account");
        SQL_DELETE_ACCOUNT = Const.getProperty("sql.delete_account");
    }


    public Integer createAccount(Account entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_ACCOUNT);
            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getPassword());
            pstmt.setInt(3, entity.getRoleId());
            pstmt.setInt(4, entity.getShoppingCartId());
            pstmt.setInt(5, entity.getAccountStatusId());
            pstmt.executeUpdate();
            insertedWithId = DBManager.getInstance().getLastInsertedId(pstmt);
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
        return insertedWithId;
    }

    public Account readAccountByAccountId(Integer id) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL_READ_ACCOUNT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            account = mapper.mapRow(rs);
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return account;
    }

    public Account readAccountByEmail(String email) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL_READ_ACCOUNT_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            account = mapper.mapRow(rs);
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return account;
    }

    public void updateAccount(Account entity) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            //UPDATE `webshop`.`account`
            // SET `email`= ?, `password`= ?, `role_id`= ?, `shopping_cart_id`=?, `account_status_id`=? WHERE `id` = ?;
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT);
            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getPassword());
            pstmt.setInt(3, entity.getRoleId());
            pstmt.setInt(4, entity.getShoppingCartId());
            pstmt.setInt(5, entity.getAccountStatusId());
            pstmt.setInt(6, entity.getId());

            DBManager.getInstance().getLastInsertedId(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    public void deleteAccount(Account entity) {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ACCOUNT);
            pstmt.setInt(1, entity.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    private static class AccountMapper implements EntityMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs) {
            Account account = null;
            try {
                if (rs.next()) {
                    account = new Account();
                    account.setId(rs.getInt(Fields.ID));
                    account.setEmail(rs.getString(Fields.ACCOUNT_EMAIL));
                    account.setPassword(rs.getString(Fields.ACCOUNT_PASSWORD));
                    account.setCreateTime(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
                    account.setRoleId(rs.getInt(Fields.ACCOUNT_ROLE_ID));
                    account.setShoppingCartId(rs.getInt(Fields.ACCOUNT_SHOPPING_CART_ID));
                    account.setAccountStatusId(rs.getInt(Fields.ACCOUNT_STATUS_ID));
                }
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex);
            } finally {
                DBManager.getInstance().close(rs);
            }
            return account;
        }
    }
}
