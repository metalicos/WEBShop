package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.entity.ShoppingCart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartDao implements Crud<ShoppingCart, Integer> {
    @Override
    public Integer create(ShoppingCart entity) {
        return null;
    }

    @Override
    public ShoppingCart read(Integer id) {
        return null;
    }

    @Override
    public void update(ShoppingCart entity) {

    }

    @Override
    public void delete(ShoppingCart entity) {

    }

    static class ShoppingCartMapper implements EntityMapper<ShoppingCart> {
        @Override
        public ShoppingCart mapRow(ResultSet rs) {
            try {
                ShoppingCart cart = new ShoppingCart();
                cart.setId(rs.getInt(Fields.ID));
                cart.setLastUpdate(rs.getTimestamp(Fields.LAST_UPDATE_TIME).toLocalDateTime());
                return cart;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
