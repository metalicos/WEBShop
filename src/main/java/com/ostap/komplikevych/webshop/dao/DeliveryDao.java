package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Delivery;
import com.ostap.komplikevych.webshop.model.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDao {

    private static final String SQL_CREATE_DELIVERY;
    private static final String SQL_READ_DELIVERY_BY_ID;
    public static final String SQL_READ_ALL_DELIVERIES;
    private static final String SQL_UPDATE_DELIVERY;
    private static final String SQL_DELETE_DELIVERY_BY_ID;

    static {
        SQL_CREATE_DELIVERY = Const.getProperty("sql.create_delivery");
        SQL_READ_DELIVERY_BY_ID = Const.getProperty("sql.read_delivery_by_id");
        SQL_READ_ALL_DELIVERIES = Const.getProperty("sql.read_all_deliveries");
        SQL_UPDATE_DELIVERY = Const.getProperty("sql.update_delivery");
        SQL_DELETE_DELIVERY_BY_ID = Const.getProperty("sql.delete_delivery_by_id");
    }

    public Integer createDelivery(Delivery entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_DELIVERY);
            pstmt.setString(1, entity.getNameUa());
            pstmt.setString(2, entity.getNameEn());
            pstmt.setString(3, entity.getAverageDeliveryTime());
            pstmt.setBigDecimal(4, entity.getDeliveryPrice());
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

    public Delivery readDeliveryByDeliveryId(Integer id) {
        Delivery delivery = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Delivery> mapper = new DeliveryMapper();
            pstmt = con.prepareStatement(SQL_READ_DELIVERY_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                delivery = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return delivery;
    }

    public void updateDelivery(Delivery entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_DELIVERY);
            pstmt.setString(1, entity.getNameUa());
            pstmt.setString(2, entity.getNameEn());
            pstmt.setString(3, entity.getAverageDeliveryTime());
            pstmt.setBigDecimal(4, entity.getDeliveryPrice());
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

    public void deleteDelivery(Delivery entity) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_DELIVERY_BY_ID);
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

    public List<Delivery> readAllDeliveries() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Delivery> deliveries = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Delivery> mapper = new DeliveryDao.DeliveryMapper();
            pstmt = con.prepareStatement(SQL_READ_ALL_DELIVERIES);
            rs = pstmt.executeQuery();
            deliveries = new ArrayList<>();
            while (rs.next()) {
                deliveries.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return deliveries;
    }


    static class DeliveryMapper implements EntityMapper<Delivery> {

        @Override
        public Delivery mapRow(ResultSet rs) {
            Delivery delivery = null;
            try {
                delivery = new Delivery();
                delivery.setId(rs.getInt(Fields.ID));
                delivery.setNameUa(rs.getString(Fields.DELIVERY_NAME_UA));
                delivery.setNameEn(rs.getString(Fields.DELIVERY_NAME_EN));
                delivery.setAverageDeliveryTime(rs.getString(Fields.DELIVERY_AVERAGE_DELIVERY_TIME));
                delivery.setDeliveryPrice(rs.getBigDecimal(Fields.DELIVERY_DELIVERY_PRICE));
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            }
            return delivery;
        }
    }
}
