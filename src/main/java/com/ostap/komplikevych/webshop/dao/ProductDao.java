package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao implements Crud<Product, Integer> {
    private static final String SQL_CREATE_PRODUCT =
            "INSERT INTO `webshop`.`product` (`price`,`amount`,`ordered_amount`,`category_id`) VALUES (?,?,?,?);";
    private static final String SQL_READ_PRODUCT_BY_ID =
            "SELECT * FROM `webshop`.`product` WHERE `id` = ?;";
    private static final String SQL_UPDATE_PRODUCT =
            "UPDATE `webshop`.`product` SET `price`=?,`amount`=?,`ordered_amount`=?,`category_id`=? WHERE `id`=?;";
    private static final String SQL_DELETE_PRODUCT =
            "DELETE FROM `webshop`.`product` WHERE `id` = ?;";

    @Override
    public Integer create(Product entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_PRODUCT);
            pstmt.setBigDecimal(1, entity.getPrice());
            pstmt.setInt(2, entity.getAmount());
            pstmt.setInt(3, entity.getOrderedAmount());
            pstmt.setInt(4, entity.getCategoryId());
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
    public Product read(Integer id) {
        Product product = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Product> mapper = new ProductMapper();
            pstmt = con.prepareStatement(SQL_READ_PRODUCT_BY_ID);
            pstmt.setLong(1, id);
            product = mapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return product;
    }

    @Override
    public void update(Product entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_PRODUCT);
            pstmt.setBigDecimal(1, entity.getPrice());
            pstmt.setInt(2, entity.getAmount());
            pstmt.setInt(3, entity.getOrderedAmount());
            pstmt.setInt(4, entity.getCategoryId());
            pstmt.setInt(5, entity.getId());
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
    public void delete(Product entity) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Product> mapper = new ProductMapper();
            pstmt = con.prepareStatement(SQL_DELETE_PRODUCT);
            pstmt.setLong(1, entity.getId());
            pstmt.executeQuery();
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
    }

    static class ProductMapper implements EntityMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs) {
            Product product = new Product();
            try {
                if (rs.next()) {
                    product.setId(rs.getInt(Fields.ID));
                    product.setAmount(rs.getInt(Fields.PRODUCT_AMOUNT));
                    product.setOrderedAmount(rs.getInt(Fields.PRODUCT_ORDERED_AMOUNT));
                    product.setPrice(rs.getBigDecimal(Fields.PRODUCT_PRICE));
                    product.setCategoryId(rs.getInt(Fields.PRODUCT_CATEGORY_ID));
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return product;
        }
    }
}