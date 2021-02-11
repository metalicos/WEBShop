package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.entity.ShoppingCartHasProduct;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartHasProductDao implements Crud<ShoppingCartHasProduct, Integer> {
    @Override
    public Integer create(ShoppingCartHasProduct entity) {
        return null;
    }

    @Override
    public ShoppingCartHasProduct read(Integer id) {
        return null;
    }

    @Override
    public void update(ShoppingCartHasProduct entity) {

    }

    @Override
    public void delete(ShoppingCartHasProduct entity) {

    }

    static class ShoppingCartHasProductMapper implements EntityMapper<ShoppingCartHasProduct> {

        @Override
        public ShoppingCartHasProduct mapRow(ResultSet rs) {
            try {
                ShoppingCartHasProduct hasProduct = new ShoppingCartHasProduct();
                hasProduct.setShoppingCartId(rs.getInt(Fields.SHOPPING_CART_HAS_PRODUCT_SHOPPING_CART_ID));
                hasProduct.setProductId(rs.getInt(Fields.SHOPPING_CART_HAS_PRODUCT_PRODUCT_ID));
                return hasProduct;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}