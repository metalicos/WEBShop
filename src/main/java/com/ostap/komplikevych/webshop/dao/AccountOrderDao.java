package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.AccountOrder;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.entity.ProductInOrder;
import com.ostap.komplikevych.webshop.model.DBManager;

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
public class AccountOrderDao {

    private static final String SQL_CREATE_ACCOUNT_ORDER;
    private static final String SQL_READ_ACCOUNT_ORDER;
    private static final String SQL_UPDATE_ACCOUNT_ORDER;
    private static final String SQL_DELETE_ACCOUNT_ORDER;

    public static final String SQL_CREATE_PRODUCT_ACCOUNT_ORDER;
    public static final String SQL_READ_ALL_PRODUCTS_BY_ACCOUNT_ORDER_ID;
    public static final String SQL_READ_PRODUCT_FROM_ACCOUNT_ORDER_BY_ID;
    public static final String SQL_UPDATE_PRODUCT_IN_ACCOUNT_ORDER;
    public static final String SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER;
    public static final String SQL_DELETE_PRODUCT_IN_ACCOUNT_ORDER;
    private static final String SQL_READ_ALL_ACCOUNT_ORDERS_BY_ACCOUNT_ID;
    private static final String SQL_READ_ALL_ACCOUNT_ORDERS;

    static {
        SQL_CREATE_ACCOUNT_ORDER = Const.getProperty("sql.create_account_order");
        SQL_READ_ACCOUNT_ORDER = Const.getProperty("sql.read_account_order");
        SQL_READ_ALL_ACCOUNT_ORDERS_BY_ACCOUNT_ID = Const.getProperty("sql.read_all_account_orders_by_account_id");
        SQL_READ_ALL_ACCOUNT_ORDERS = Const.getProperty("sql.read_all_account_orders");
        SQL_UPDATE_ACCOUNT_ORDER = Const.getProperty("sql.update_account_order");
        SQL_DELETE_ACCOUNT_ORDER = Const.getProperty("sql.delete_account_order");

        SQL_CREATE_PRODUCT_ACCOUNT_ORDER = Const.getProperty("sql.create_product_in_order");
        SQL_READ_ALL_PRODUCTS_BY_ACCOUNT_ORDER_ID = Const.getProperty("sql.read_all_products_in_order");
        SQL_READ_PRODUCT_FROM_ACCOUNT_ORDER_BY_ID = Const.getProperty("sql.read_product_from_order_by_id");
        SQL_UPDATE_PRODUCT_IN_ACCOUNT_ORDER = Const.getProperty("sql.update_product_in_order");
        SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER = Const.getProperty("sql.delete_all_products_in_order");
        SQL_DELETE_PRODUCT_IN_ACCOUNT_ORDER = Const.getProperty("sql.delete_product_in_order");
    }

    public Integer createAccountOrder(AccountOrder entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_ACCOUNT_ORDER);

            pstmt.setInt(1, entity.getAccountId());
            pstmt.setBigDecimal(2, entity.getTotalOrderPrice());
            pstmt.setInt(3, entity.getDeliveryId());
            pstmt.setInt(4, entity.getStatusId());
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

    public AccountOrder readAccountOrderByAccountOrderId(Integer id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        AccountOrder accountOrder = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ACCOUNT_ORDER);
            pstmt.setInt(1, id);
            EntityMapper<AccountOrder> mapper = new AccountOrderMapper();
            rs = pstmt.executeQuery();
            if (rs.next()) {
                accountOrder = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return accountOrder;
    }

    public List<AccountOrder> readAllAccountOrdersByAccountId(Integer accountId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        List<AccountOrder> orders = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ALL_ACCOUNT_ORDERS_BY_ACCOUNT_ID);
            pstmt.setInt(1, accountId);
            EntityMapper<AccountOrder> mapper = new AccountOrderMapper();
            rs = pstmt.executeQuery();
            orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return orders;
    }

    public List<AccountOrder> readAllAccountOrders() {
        Connection con = null;
        PreparedStatement pstmt = null;
        List<AccountOrder> orders = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ALL_ACCOUNT_ORDERS);
            EntityMapper<AccountOrder> mapper = new AccountOrderMapper();
            rs = pstmt.executeQuery();
            orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return orders;
    }

    public void updateAccountOrder(AccountOrder entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT_ORDER);
/*
            sql.create_account_order=INSERT INTO `webshop`.`account_order`

            (`account_id`, `total_order_price`, `delivery_id`, `status_id`) VALUES (?,?,?,?);

            sql.read_account_order=SELECT * FROM `webshop`.`account_order` WHERE id = ?;
            sql.update_account_order=UPDATE `webshop`.`account_order` SET

            `account_id`= ?, `total_order_price`, `delivery_id`, `status_id`=?, `droped_by_account_id`=?  WHERE id = ?;

            sql.delete_account_order=DELETE FROM `webshop`.`account_order` WHERE id = ?;
             */

            pstmt.setInt(1, entity.getAccountId());
            pstmt.setBigDecimal(2, entity.getTotalOrderPrice());
            pstmt.setInt(3, entity.getDeliveryId());
            pstmt.setInt(4, entity.getStatusId());
            pstmt.setInt(5, entity.getDroppedByAccountId());
            pstmt.setInt(6, entity.getId());

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

    public void deleteAccountOrder(AccountOrder entity) {
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

    public static void deleteAllProductsInOrder(int orderId, int droppedByAccountId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER);
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();

            AccountOrderDao aod = new AccountOrderDao();
            AccountOrder accountOrder = aod.readAccountOrderByAccountOrderId(orderId);
            accountOrder.setDroppedByAccountId(droppedByAccountId);
            aod.updateAccountOrder(accountOrder);

        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

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


    static class AccountOrderMapper implements EntityMapper<AccountOrder> {

        @Override
        public AccountOrder mapRow(ResultSet rs) {
            AccountOrder order = null;
            try {
                order = new AccountOrder();
                order.setId(rs.getInt(Fields.ID));
                order.setCreateTime(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
                order.setAccountId(rs.getInt(Fields.ACCOUNT_ORDER_ACCOUNT_ID));
                order.setStatusId(rs.getInt(Fields.ACCOUNT_ORDER_STATUS_ID));
                order.setTotalOrderPrice(rs.getBigDecimal(Fields.ACCOUNT_ORDER_TOTAL_ORDER_PRICE));
                order.setDeliveryId(rs.getInt(Fields.ACCOUNT_ORDER_DELIVERY_ID));
                order.setDroppedByAccountId(rs.getInt(Fields.ACCOUNT_ORDER_DROPPED_BY_ACCOUNT_ID));
                order.setProducts(readProductsInOrder(order.getId()));
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            }
            return order;
        }
    }

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
                Product product = productDao.readProductByProductId(rs.getInt(Fields.PRODUCT_IN_ORDER_PRODUCT_ID));
                productInOrder.setProduct(product);
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            }
            return productInOrder;
        }
    }


    public static void main(String[] args) {
        AccountOrderDao orderDao = new AccountOrderDao();
        AccountOrder ao = new AccountOrder();
        ao.setStatusId(1);
        ao.setAccountId(5);

        int orderId = orderDao.createAccountOrder(ao);

        Const.logger.info("CHECKING DB DAO");
        Const.logger.debug(orderId);

        Const.logger.debug("\n########### DELETE ALL PRODUCTS IN ORDER ##################");
        deleteAllProductsInOrder(orderId, 2);
        Const.logger.debug(orderDao.readAccountOrderByAccountOrderId(orderId));

        Const.logger.debug("\n########### CREATE 10 PRODUCTS IN ORDER 1 ##################");
        for (int i = 1; i <= 10; i++) {
            ProductDao productDao = new ProductDao();
            createProductInOrder(orderId, new ProductInOrder(productDao.readProductByProductId(i),
                    new BigDecimal(32), 22, LocalDateTime.now(), LocalDateTime.now()));
        }
        Const.logger.debug(orderDao.readAccountOrderByAccountOrderId(orderId));

        Const.logger.debug("\n########### UPDATE 10 PRODUCTS IN ORDER 1 ##################");
        for (int i = 1; i <= 10; i++) {
            ProductDao productDao = new ProductDao();

            updateProductInOrder(orderId, new ProductInOrder(productDao.readProductByProductId(i),
                    new BigDecimal(999), 34, LocalDateTime.now(), LocalDateTime.now()));
        }
        Const.logger.debug(orderDao.readAccountOrderByAccountOrderId(orderId));

        Const.logger.debug("\n########### DELETE 5 PRODUCTS IN ORDER 1 ##################");
        for (int i = 6; i <= 10; i++) {
            deleteProductInOrder(orderId, i);
        }
        Const.logger.debug(orderDao.readAccountOrderByAccountOrderId(orderId));
    }
}
