package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.AccountOrder;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.entity.ProductInOrder;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type AccountOrderDao.
 *
 * @author Ostap Komplikevych
 */
public class AccountOrderDao implements Crud<AccountOrder, Integer> {

    private static final String SQL_CREATE_ACCOUNT_ORDER;
    private static final String SQL_READ_ACCOUNT_ORDER;
    private static final String SQL_UPDATE_ACCOUNT_ORDER;
    private static final String SQL_DELETE_ACCOUNT_ORDER;

    /**
     * The constant SQL_CREATE_PRODUCT_ACCOUNT_ORDER.
     */
    public static final String SQL_CREATE_PRODUCT_ACCOUNT_ORDER;
    /**
     * The constant SQL_READ_ALL_PRODUCTS_BY_ACCOUNT_ORDER_ID.
     */
    public static final String SQL_READ_ALL_PRODUCTS_BY_ACCOUNT_ORDER_ID;
    /**
     * The constant SQL_READ_PRODUCT_FROM_ACCOUNT_ORDER_BY_ID.
     */
    public static final String SQL_READ_PRODUCT_FROM_ACCOUNT_ORDER_BY_ID;
    /**
     * The constant SQL_UPDATE_PRODUCT_IN_ACCOUNT_ORDER.
     */
    public static final String SQL_UPDATE_PRODUCT_IN_ACCOUNT_ORDER;
    /**
     * The constant SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER.
     */
    public static final String SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER;
    /**
     * The constant SQL_DELETE_PRODUCT_IN_ACCOUNT_ORDER.
     */
    public static final String SQL_DELETE_PRODUCT_IN_ACCOUNT_ORDER;

    static {
        SQL_CREATE_ACCOUNT_ORDER = Const.getProperty("sql.create_account_order");
        SQL_READ_ACCOUNT_ORDER = Const.getProperty("sql.read_account_order");
        SQL_UPDATE_ACCOUNT_ORDER = Const.getProperty("sql.update_account_order");
        SQL_DELETE_ACCOUNT_ORDER = Const.getProperty("sql.delete_account_order");

        SQL_CREATE_PRODUCT_ACCOUNT_ORDER = Const.getProperty("sql.create_product_in_order");
        SQL_READ_ALL_PRODUCTS_BY_ACCOUNT_ORDER_ID = Const.getProperty("sql.read_all_products_in_order");
        SQL_READ_PRODUCT_FROM_ACCOUNT_ORDER_BY_ID = Const.getProperty("sql.read_product_from_order_by_id");
        SQL_UPDATE_PRODUCT_IN_ACCOUNT_ORDER = Const.getProperty("sql.update_product_in_order");
        SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER = Const.getProperty("sql.delete_all_products_in_order");
        SQL_DELETE_PRODUCT_IN_ACCOUNT_ORDER = Const.getProperty("sql.delete_product_in_order");
    }

    @Override
    public Integer create(AccountOrder entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_ACCOUNT_ORDER);

            pstmt.setInt(1, entity.getAccountId());
            pstmt.setInt(2, entity.getStatusId());

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
    public AccountOrder read(Integer id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        AccountOrder accountOrder = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ACCOUNT_ORDER);
            pstmt.setInt(1, id);
            EntityMapper<AccountOrder> mapper = new AccountOrderMapper();
            accountOrder = mapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
        return accountOrder;
    }

    @Override
    public void update(AccountOrder entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT_ORDER);

            pstmt.setInt(1, entity.getAccountId());
            pstmt.setInt(2, entity.getStatusId());
            pstmt.setInt(3, entity.getDroppedByAccountId());
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
    public void delete(AccountOrder entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ACCOUNT_ORDER);
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
     * The type AccountOrderMapper.
     */
    static class AccountOrderMapper implements EntityMapper<AccountOrder> {

        @Override
        public AccountOrder mapRow(ResultSet rs) {
            AccountOrder order = null;
            try {
                if (rs.next()) {
                    order = new AccountOrder();
                    order.setId(rs.getInt(Fields.ID));
                    order.setCreateTime(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
                    order.setAccountId(rs.getInt(Fields.ACCOUNT_ORDER_ACCOUNT_ID));
                    order.setStatusId(rs.getInt(Fields.ACCOUNT_ORDER_STATUS_ID));
                    order.setDroppedByAccountId(rs.getInt(Fields.ACCOUNT_ORDER_DROPPED_BY_ACCOUNT_ID));
                    order.setProducts(readProductsInOrder(order.getId()));
                }
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return order;
        }
    }

    /**
     * The type ProductInOrderMapper.
     */
    static class ProductInOrderMapper implements EntityMapper<ProductInOrder> {

        @Override
        public ProductInOrder mapRow(ResultSet rs) {
            ProductInOrder productInOrder = null;
            try {
                productInOrder = new ProductInOrder();
                productInOrder.setProductAmount(rs.getInt(Fields.PRODUCT_IN_ORDER_PRODUCT_AMOUNT));
                productInOrder.setPrice(rs.getBigDecimal(Fields.PRODUCT_IN_ORDER_PRICE));
                productInOrder.setCreateTime(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
                productInOrder.setLastUpdateTime(rs.getTimestamp(Fields.LAST_UPDATE_TIME).toLocalDateTime());
                ProductDao productDao = new ProductDao();
                Product product = productDao.read(rs.getInt(Fields.PRODUCT_IN_ORDER_PRODUCT_ID));
                productInOrder.setProduct(product);
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            }
            return productInOrder;
        }
    }

    /**
     * Read ProductsInOrderList.
     *
     * @param orderId the order id
     * @return the list
     */
    public static List<ProductInOrder> readProductsInOrder(int orderId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductInOrder> products = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ALL_PRODUCTS_BY_ACCOUNT_ORDER_ID);
            pstmt.setInt(1, orderId);
            rs = pstmt.executeQuery();
            EntityMapper<ProductInOrder> mapper = new ProductInOrderMapper();
            while (rs.next()) {
                products.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return products;
    }

    /**
     * Delete all ProductsInOrder.
     *
     * @param orderId          the order id
     * @param droppedByAccount the dropped by account
     */
    public static void deleteAllProductsInOrder(int orderId, int droppedByAccount) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER);
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();

            AccountOrderDao aod = new AccountOrderDao();
            AccountOrder accountOrder = aod.read(orderId);
            accountOrder.setDroppedByAccountId(droppedByAccount);
            aod.update(accountOrder);

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
     * Create ProductsInOrder integer.
     *
     * @param orderId the order id
     * @param entity  the entity
     * @return the integer
     */
    public static Integer createProductInOrder(int orderId, ProductInOrder entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_PRODUCT_ACCOUNT_ORDER);
            // `account_order_id`, `product_id`,`price`,`product_amount`,`create_time`,`last_update`

            pstmt.setInt(1, orderId);

            pstmt.setInt(2, entity.getProduct().getId());
            pstmt.setBigDecimal(3, entity.getPrice());
            pstmt.setInt(4, entity.getProductAmount());

            pstmt.setTimestamp(5, Timestamp.valueOf(entity.getCreateTime()));
            pstmt.setTimestamp(6, Timestamp.valueOf(entity.getLastUpdateTime()));

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

    /**
     * Delete Product InOrder.
     *
     * @param orderId   the order id
     * @param productId the product id
     */
    public static void deleteProductInOrder(int orderId, int productId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_PRODUCT_IN_ACCOUNT_ORDER);

            pstmt.setInt(1, orderId);
            pstmt.setInt(2, productId);
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
     * Update Product InOrder.
     *
     * @param orderId the order id
     * @param entity  the entity
     */
    public static void updateProductInOrder(int orderId, ProductInOrder entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_PRODUCT_IN_ACCOUNT_ORDER);
            pstmt.setBigDecimal(1, entity.getPrice());
            pstmt.setInt(2, entity.getProductAmount());
            pstmt.setInt(3, orderId);
            pstmt.setInt(4, entity.getProduct().getId());
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
     * Read Product InOrder.
     *
     * @param orderId   the order id
     * @param productId the product id
     * @return the product in order
     */
    public static ProductInOrder readProductInOrder(int orderId, int productId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ProductInOrder productInOrder = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_PRODUCT_FROM_ACCOUNT_ORDER_BY_ID);
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, productId);
            EntityMapper<ProductInOrder> mapper = new ProductInOrderMapper();
            rs = pstmt.executeQuery();
            if (rs.next()) {
                productInOrder = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return productInOrder;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AccountOrderDao orderDao = new AccountOrderDao();
        AccountOrder ao = new AccountOrder();
        ao.setStatusId(1);
        ao.setAccountId(1);
        int id = orderDao.create(ao);
        System.out.println(id);
        System.out.println("\n########### DELETE ALL PRODUCTS IN ORDER ##################");
        deleteAllProductsInOrder(id, 3);
        System.out.println(orderDao.read(id));

        System.out.println("\n########### CREATE 10 PRODUCTS IN ORDER 1 ##################");
        for (int i = 1; i <= 10; i++) {
            ProductDao productDao = new ProductDao();
            createProductInOrder(id, new ProductInOrder(productDao.read(i),
                    new BigDecimal(32), 22, LocalDateTime.now(), LocalDateTime.now()));
        }
        System.out.println(orderDao.read(id));

        System.out.println("\n########### UPDATE 10 PRODUCTS IN ORDER 1 ##################");
        for (int i = 1; i <= 10; i++) {
            ProductDao productDao = new ProductDao();

            updateProductInOrder(id, new ProductInOrder(productDao.read(i),
                    new BigDecimal(999), 34, LocalDateTime.now(), LocalDateTime.now()));
        }
        System.out.println(orderDao.read(id));

        System.out.println("\n########### DELETE 5 PRODUCTS IN ORDER 1 ##################");
        for (int i = 6; i <= 10; i++) {
            deleteProductInOrder(id, i);
        }
        System.out.println(orderDao.read(id));
    }
}
