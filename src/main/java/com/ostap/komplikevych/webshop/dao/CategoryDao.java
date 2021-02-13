package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Category;
import com.ostap.komplikevych.webshop.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao implements Crud<Category, Integer> {

    private static final String SQL_CREATE_CATEGORY =
            "INSERT INTO `webshop`.`category` (`name_ua`,`name_en`,`description_ua`,`description_en`) VALUES (?,?,?,?);";
    private static final String SQL_READ_CATEGORY_BY_ID =
            "SELECT * FROM `webshop`.`category` WHERE `id` = ?;";
    private static final String SQL_UPDATE_CATEGORY =
            "UPDATE `webshop`.`category` SET `name_ua`=?,`name_en`=?,`description_ua`=?,`description_en`=? WHERE `id`=?;";
    private static final String SQL_DELETE_CATEGORY =
            "DELETE FROM `webshop`.`category` WHERE `id` = ?;";

    @Override
    public Integer create(Category entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_CATEGORY);
            pstmt.setString(1, entity.getNameUa());
            pstmt.setString(2, entity.getNameEn());
            pstmt.setString(3, entity.getDescriptionUa());
            pstmt.setString(4, entity.getDescriptionEn());
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
    public Category read(Integer id) {
        Category category = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Category> mapper = new CategoryMapper();
            pstmt = con.prepareStatement(SQL_READ_CATEGORY_BY_ID);
            pstmt.setLong(1, id);
            category = mapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return category;
    }

    @Override
    public void update(Category entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_CATEGORY);
            pstmt.setString(1, entity.getNameUa());
            pstmt.setString(2, entity.getNameEn());
            pstmt.setString(3, entity.getDescriptionUa());
            pstmt.setString(4, entity.getDescriptionEn());
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
    public void delete(Category entity) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Category> mapper = new CategoryMapper();
            pstmt = con.prepareStatement(SQL_DELETE_CATEGORY);
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

    static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            Category category = new Category();
            try {
                if (rs.next()) {
                    category.setId(rs.getInt(Fields.ID));
                    category.setDescriptionEn(rs.getString(Fields.CATEGORY_DESCRIPTION_EN));
                    category.setDescriptionUa(rs.getString(Fields.CATEGORY_DESCRIPTION_UA));
                    category.setNameEn(rs.getString(Fields.CATEGORY_NAME_EN));
                    category.setNameUa(rs.getString(Fields.CATEGORY_NAME_UA));
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return category;
        }
    }
}