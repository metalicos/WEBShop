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

public class AccountOrderDao implements Crud<AccountOrder, Integer> {

    private static final String SQL_CREATE_ACCOUNT_ORDER =
            "INSERT INTO `webshop`.`account_order` (`account_id`,`status_id`) VALUES (?,?);";
    private static final String SQL_READ_ACCOUNT_ORDER =
            "SELECT * FROM `webshop`.`account_order` WHERE id = ?";
    private static final String SQL_UPDATE_ACCOUNT_ORDER =
            "UPDATE `webshop`.`account_order` SET `account_id`= ?, `status_id`=?, `droped_by_account_id`=?  WHERE id = ?;";
    private static final String SQL_DELETE_ACCOUNT_ORDER =
            "DELETE FROM `webshop`.`account_order` WHERE id = ?;";

    public static final String SQL_CREATE_PRODUCT_ACCOUNT_ORDER =
            "INSERT INTO webshop.account_order_has_product (`account_order_id`, `product_id`,`price`,`product_amount`,`create_time`,`last_update`) VALUES (?,?,?,?,?,?);";

    public static final String SQL_READ_ALL_PRODUCTS_BY_ACCOUNT_ORDER_ID =
            "SELECT * FROM webshop.account_order AS a_order \n" +
                    "\n" +
                    "INNER JOIN webshop.account_order_has_product AS order_h_product\n" +
                    "\n" +
                    "ON a_order.id = order_h_product.account_order_id \n" +
                    "\n" +
                    "WHERE a_order.id = ?;";

    public static final String SQL_READ_PRODUCT_FROM_ACCOUNT_ORDER_BY_ID =
            "SELECT * FROM webshop.account_order_has_product WHERE `account_order_id` = ? AND `product_id` = ?;";

    public static final String SQL_UPDATE_PRODUCT_IN_ACCOUNT_ORDER =
            "UPDATE webshop.account_order_has_product SET price=?,product_amount=? WHERE account_order_id=? AND product_id=?;";

    public static final String SQL_DELETE_ALL_PRODUCTS_IN_ACCOUNT_ORDER =
            "DELETE FROM webshop.account_order_has_product WHERE account_order_id = ?;";

    public static final String SQL_DELETE_PRODUCT_IN_ACCOUNT_ORDER =
            "DELETE FROM webshop.account_order_has_product WHERE `account_order_id` = ? AND `product_id` = ?;";

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
            Const.logger.error(ex.getMessage());
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
            Const.logger.error(ex.getMessage());
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
            Const.logger.error(ex.getMessage());
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
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    static class AccountOrderMapper implements EntityMapper<AccountOrder> {

        @Override
        public AccountOrder mapRow(ResultSet rs) {
            AccountOrder order = new AccountOrder();
            try {
                if (rs.next()) {
                    order.setId(rs.getInt(Fields.ID));
                    order.setCreateTime(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
                    order.setAccountId(rs.getInt(Fields.ACCOUNT_ORDER_ACCOUNT_ID));
                    order.setStatusId(rs.getInt(Fields.ACCOUNT_ORDER_STATUS_ID));
                    order.setDroppedByAccountId(rs.getInt(Fields.ACCOUNT_ORDER_DROPPED_BY_ACCOUNT_ID));
                    order.setProducts(readProductsInOrder(order.getId()));
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return order;
        }
    }

    static class ProductInOrderMapper implements EntityMapper<ProductInOrder> {

        @Override
        public ProductInOrder mapRow(ResultSet rs) {
            ProductInOrder productInOrder = new ProductInOrder();
            try {
                productInOrder.setProductAmount(rs.getInt(Fields.PRODUCT_IN_ORDER_PRODUCT_AMOUNT));
                productInOrder.setPrice(rs.getBigDecimal(Fields.PRODUCT_IN_ORDER_PRICE));
                productInOrder.setCreateTime(rs.getTimestamp(Fields.CREATE_TIME).toLocalDateTime());
                productInOrder.setLastUpdateTime(rs.getTimestamp(Fields.LAST_UPDATE_TIME).toLocalDateTime());
                ProductDao productDao = new ProductDao();
                Product product = productDao.read(rs.getInt(Fields.PRODUCT_IN_ORDER_PRODUCT_ID));
                productInOrder.setProduct(product);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex.getMessage());
            }
            return productInOrder;
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
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return products;
    }


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
            Const.logger.error(ex.getMessage());
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
            Const.logger.error(ex.getMessage());
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
            Const.logger.error(ex.getMessage());
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
            Const.logger.error(ex.getMessage());
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
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return productInOrder;
    }

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
