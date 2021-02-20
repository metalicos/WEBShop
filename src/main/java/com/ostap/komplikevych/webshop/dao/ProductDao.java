package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.model.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type ProductDao.
 *
 * @author Ostap Komplikevych
 */
public class ProductDao {

    private static final String SQL_CREATE_PRODUCT;
    private static final String SQL_READ_PRODUCT_BY_ID;
    private static final String SQL_UPDATE_PRODUCT;
    private static final String SQL_DELETE_PRODUCT;
    private static final String SQL_READ_ALL_PRODUCTS_IN_SHOP;
    private static final String SQL_READ_ALL_PRODUCTS_IN_CATEGORY;

    static {
        SQL_CREATE_PRODUCT = Const.getProperty("sql.create_product");
        SQL_READ_PRODUCT_BY_ID = Const.getProperty("sql.read_product_by_id");
        SQL_READ_ALL_PRODUCTS_IN_SHOP = Const.getProperty("sql.read_all_products_in_shop");
        SQL_READ_ALL_PRODUCTS_IN_CATEGORY = Const.getProperty("sql.read_all_products_in_category");
        SQL_UPDATE_PRODUCT = Const.getProperty("sql.update_product");
        SQL_DELETE_PRODUCT = Const.getProperty("sql.delete_product");
    }

    public Integer createProduct(Product entity) {
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
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
        return insertedWithId;
    }

    public Product readProductByProductId(Integer id) {
        Product product = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Product> mapper = new ProductMapper();
            pstmt = con.prepareStatement(SQL_READ_PRODUCT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                product = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return product;
    }

    public void updateProduct(Product entity) {
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
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    public void deleteProduct(Product entity) {
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
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
    }

    public List<Product> readAllProductsInCategory(int categoryId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Product> products = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Product> mapper = new ProductMapper();
            pstmt = con.prepareStatement(SQL_READ_ALL_PRODUCTS_IN_CATEGORY);
            pstmt.setInt(1,categoryId);
            rs = pstmt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                products.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return products;
    }

    public List<Product> readAllProductsInShop() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Product> products = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Product> mapper = new ProductMapper();
            pstmt = con.prepareStatement(SQL_READ_ALL_PRODUCTS_IN_SHOP);
            rs = pstmt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                products.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return products;
    }

    static class ProductMapper implements EntityMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs) {
            Product product = null;
            try {
                product = new Product();
                product.setId(rs.getInt(Fields.ID));
                product.setAmount(rs.getInt(Fields.PRODUCT_AMOUNT));
                product.setOrderedAmount(rs.getInt(Fields.PRODUCT_ORDERED_AMOUNT));
                product.setPrice(rs.getBigDecimal(Fields.PRODUCT_PRICE));
                product.setCategoryId(rs.getInt(Fields.PRODUCT_CATEGORY_ID));
                product.setCreateDate(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            }
            return product;
        }
    }
}
