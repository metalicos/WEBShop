package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.ShoppingCart;

import java.sql.*;
import java.time.LocalDateTime;

public class ShoppingCartDao implements Crud<ShoppingCart, Integer> {
    private static final String SQL_CREATE_SHOPPING_CART;
    private static final String SQL_READ_SHOPPING_CART;
    private static final String SQL_UPDATE_SHOPPING_CART;
    private static final String SQL_DELETE_SHOPPING_CART;

    static {
        SQL_CREATE_SHOPPING_CART = Const.getProperty("sql.create_shopping_cart");
        SQL_READ_SHOPPING_CART = Const.getProperty("sql.read_shopping_cart");
        SQL_UPDATE_SHOPPING_CART = Const.getProperty("sql.update_shopping_cart");
        SQL_DELETE_SHOPPING_CART = Const.getProperty("sql.delete_shopping_cart");
    }

    @Override
    public Integer create(ShoppingCart entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_SHOPPING_CART);
            pstmt.setTimestamp(1, Timestamp.valueOf(entity.getLastUpdate()));
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
    public ShoppingCart read(Integer id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ShoppingCart shoppingCart = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_SHOPPING_CART);
            pstmt.setInt(1, id);
            ShoppingCartMapper shoppingCartMapper = new ShoppingCartMapper();
            shoppingCart = shoppingCartMapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
        return shoppingCart;
    }

    @Override
    public void update(ShoppingCart entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_SHOPPING_CART);
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(2, entity.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    @Override
    public void delete(ShoppingCart entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ShoppingCart shoppingCart = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_SHOPPING_CART);
            pstmt.setInt(1, entity.getId());
            ShoppingCartMapper shoppingCartMapper = new ShoppingCartMapper();
            shoppingCart = shoppingCartMapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    static class ShoppingCartMapper implements EntityMapper<ShoppingCart> {
        @Override
        public ShoppingCart mapRow(ResultSet rs) {
            ShoppingCart cart = new ShoppingCart();
            try {
                if (rs.next()) {
                    cart.setId(rs.getInt(Fields.ID));
                    cart.setLastUpdate(rs.getTimestamp(Fields.LAST_UPDATE_TIME).toLocalDateTime());
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return cart;
        }
    }
}
