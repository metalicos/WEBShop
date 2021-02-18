package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Account;

import java.sql.*;

/**
 * The type AccountDao.
 *
 * @author Ostap Komplikevych
 */
public class AccountDao implements Crud<Account, Integer> {

    /**
     * The constant SQL_READ_ACCOUNT_BY_EMAIL.
     */
    public static final String SQL_READ_ACCOUNT_BY_EMAIL;
    /**
     * The constant SQL_READ_ACCOUNT_BY_ID.
     */
    public static final String SQL_READ_ACCOUNT_BY_ID;
    /**
     * The constant SQL_UPDATE_ACCOUNT.
     */
    public static final String SQL_UPDATE_ACCOUNT;
    /**
     * The constant SQL_CREATE_ACCOUNT.
     */
    public static final String SQL_CREATE_ACCOUNT;
    /**
     * The constant SQL_DELETE_ACCOUNT.
     */
    private static final String SQL_DELETE_ACCOUNT;

    static {
        SQL_READ_ACCOUNT_BY_ID = Const.getProperty("sql.read_account_by_id");
        SQL_READ_ACCOUNT_BY_EMAIL = Const.getProperty("sql.read_account_by_email");
        SQL_UPDATE_ACCOUNT = Const.getProperty("sql.update_account");
        SQL_CREATE_ACCOUNT = Const.getProperty("sql.create_account");
        SQL_DELETE_ACCOUNT = Const.getProperty("sql.delete_account");
    }

    @Override
    public Integer create(Account entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_ACCOUNT);

            pstmt.setString(1, entity.getEmail());
            pstmt.setString(2, entity.getPassword());
            pstmt.setTimestamp(3, Timestamp.valueOf(entity.getCreateTime()));
            pstmt.setInt(4, entity.getRoleId());
            pstmt.setInt(5, entity.getShoppingCartId());
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

    @Override
    public Account read(Integer id) {
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
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return account;
    }

    /**
     * Read account by email.
     *
     * @param email the email
     * @return the account
     */
    public Account read(String email) {
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
            if (rs.next()) {
                account = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
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
            Const.logger.error(ex);
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
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    /**
     * The type AccountMapper.
     */
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
