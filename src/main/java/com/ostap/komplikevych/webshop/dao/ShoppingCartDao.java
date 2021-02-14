package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.entity.ProductInCart;
import com.ostap.komplikevych.webshop.entity.ShoppingCart;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type ShoppingCartDao.
 *
 * @author Ostap Komplikevych
 */
public class ShoppingCartDao implements Crud<ShoppingCart, Integer> {
    private static final String SQL_CREATE_SHOPPING_CART;
    private static final String SQL_READ_SHOPPING_CART;
    private static final String SQL_UPDATE_SHOPPING_CART;
    private static final String SQL_DELETE_SHOPPING_CART;

    /**
     * The constant SQL_CREATE_PRODUCT_IN_CART.
     */
    public static final String SQL_CREATE_PRODUCT_IN_CART;
    /**
     * The constant SQL_READ_ALL_PRODUCTS_BY_CART_ID.
     */
    public static final String SQL_READ_ALL_PRODUCTS_BY_CART_ID;
    /**
     * The constant SQL_READ_PRODUCT_FROM_CART_BY_ID.
     */
    public static final String SQL_READ_PRODUCT_FROM_CART_BY_ID;
    /**
     * The constant SQL_UPDATE_PRODUCT_IN_CART.
     */
    public static final String SQL_UPDATE_PRODUCT_IN_CART;
    /**
     * The constant SQL_DELETE_ALL_PRODUCTS_IN_CART.
     */
    public static final String SQL_DELETE_ALL_PRODUCTS_IN_CART;
    /**
     * The constant SQL_DELETE_PRODUCT_IN_CART.
     */
    public static final String SQL_DELETE_PRODUCT_IN_CART;

    static {
        SQL_CREATE_SHOPPING_CART = Const.getProperty("sql.create_shopping_cart");
        SQL_READ_SHOPPING_CART = Const.getProperty("sql.read_shopping_cart");
        SQL_UPDATE_SHOPPING_CART = Const.getProperty("sql.update_shopping_cart");
        SQL_DELETE_SHOPPING_CART = Const.getProperty("sql.delete_shopping_cart");

        SQL_CREATE_PRODUCT_IN_CART = Const.getProperty("sql.create_product_in_cart");
        SQL_READ_ALL_PRODUCTS_BY_CART_ID = Const.getProperty("sql.read_all_products_by_cart_id");
        SQL_READ_PRODUCT_FROM_CART_BY_ID = Const.getProperty("sql.read_product_from_cart_by_id");
        SQL_UPDATE_PRODUCT_IN_CART = Const.getProperty("sql.update_product_in_cart");
        SQL_DELETE_ALL_PRODUCTS_IN_CART = Const.getProperty("sql.delete_all_products_in_cart");
        SQL_DELETE_PRODUCT_IN_CART = Const.getProperty("sql.delete_product_in_cart");
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
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_SHOPPING_CART);
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

    /**
     * The type ShoppingCartMapper.
     */
    static class ShoppingCartMapper implements EntityMapper<ShoppingCart> {
        @Override
        public ShoppingCart mapRow(ResultSet rs) {
            ShoppingCart cart = new ShoppingCart();
            try {
                if (rs.next()) {
                    cart.setId(rs.getInt(Fields.ID));
                    cart.setLastUpdate(rs.getTimestamp(Fields.LAST_UPDATE_TIME).toLocalDateTime());
                    cart.setProducts(readProductsInCart(cart.getId()));
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return cart;
        }
    }

    /**
     * The type ProductInCartMapper.
     */
    static class ProductInCartMapper implements EntityMapper<ProductInCart> {

        @Override
        public ProductInCart mapRow(ResultSet rs) {
            ProductInCart productInCart = new ProductInCart();
            try {
                productInCart.setAmount(rs.getInt(Fields.PRODUCT_IN_CART_AMOUNT));
                ProductDao productDao = new ProductDao();
                Product product = productDao.read(rs.getInt(Fields.PRODUCT_IN_CART_PRODUCT_ID));
                productInCart.setProduct(product);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex.getMessage());
            }
            return productInCart;
        }
    }

    /**
     * Read products in cart list.
     *
     * @param cartId the cart id
     * @return the list
     */
    public static List<ProductInCart> readProductsInCart(int cartId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductInCart> products = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ALL_PRODUCTS_BY_CART_ID);
            pstmt.setInt(1, cartId);
            rs = pstmt.executeQuery();
            EntityMapper<ProductInCart> mapper = new ProductInCartMapper();
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

    /**
     * Delete all products in cart.
     *
     * @param cartId the cart id
     */
    public static void deleteAllProductsInCart(int cartId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ALL_PRODUCTS_IN_CART);
            pstmt.setInt(1, cartId);
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

    /**
     * Create product in cart.
     *
     * @param cartId    the cart id
     * @param productId the product id
     * @param amount    the amount
     * @return the integer
     */
    public static Integer createProductInCart(int cartId, int productId, int amount) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_PRODUCT_IN_CART);
            pstmt.setInt(1, cartId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, amount);
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

    /**
     * Delete product in cart.
     *
     * @param cartId    the cart id
     * @param productId the product id
     */
    public static void deleteProductInCart(int cartId, int productId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_PRODUCT_IN_CART);
            pstmt.setInt(1, cartId);
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

    /**
     * Update product in cart.
     *
     * @param cartId       the cart id
     * @param productId    the product id
     * @param updateAmount the update amount
     */
    public static void updateProductInCart(int cartId, int productId, int updateAmount) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_PRODUCT_IN_CART);
            pstmt.setInt(1, updateAmount);
            pstmt.setInt(2, cartId);
            pstmt.setInt(3, productId);
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

    /**
     * Get product in cart.
     *
     * @param cartId    the cart id
     * @param productId the product id
     * @return the product in cart
     */
    public static ProductInCart readProductInCart(int cartId, int productId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ProductInCart productInCart = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_PRODUCT_FROM_CART_BY_ID);
            pstmt.setInt(1, cartId);
            pstmt.setInt(2, productId);
            EntityMapper<ProductInCart> mapper = new ProductInCartMapper();
            rs = pstmt.executeQuery();
            if (rs.next()) {
                productInCart = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(rs);
        }
        return productInCart;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ShoppingCartDao cartDao = new ShoppingCartDao();
        System.out.println("\n########### DELETE ALL PRODUCTS IN CART ##################");
        deleteAllProductsInCart(1);
        System.out.println(cartDao.read(1));

        System.out.println("\n########### CREATE 10 PRODUCTS IN CART 1 ##################");
        for (int i = 1; i <= 10; i++) {
            createProductInCart(1, i, 5);
        }
        System.out.println(cartDao.read(1));

        System.out.println("\n########### UPDATE 10 PRODUCTS IN CART 1 ##################");
        for (int i = 1; i <= 10; i++) {
            ProductInCart productInCart = new ProductInCart();
            productInCart.setAmount(2);
            updateProductInCart(1, i, 2);
        }
        System.out.println(cartDao.read(1));

        System.out.println("\n########### DELETE 5 PRODUCTS IN CART 1 ##################");
        for (int i = 6; i <= 10; i++) {
            deleteProductInCart(1, i);
        }
        System.out.println(cartDao.read(1));
    }
}
