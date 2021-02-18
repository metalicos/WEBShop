package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type CategoryDao.
 */
public class CategoryDao implements Crud<Category, Integer> {

    private static final String SQL_CREATE_CATEGORY;
    private static final String SQL_READ_CATEGORY_BY_ID;
    private static final String SQL_UPDATE_CATEGORY;
    private static final String SQL_DELETE_CATEGORY;

    static {
        SQL_CREATE_CATEGORY = Const.getProperty("sql.create_category");
        SQL_READ_CATEGORY_BY_ID = Const.getProperty("sql.read_category_by_id");
        SQL_UPDATE_CATEGORY = Const.getProperty("sql.update_category");
        SQL_DELETE_CATEGORY = Const.getProperty("sql.delete_category");
    }

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
            Const.logger.error(ex);
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
            Const.logger.error(ex);
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
            Const.logger.error(ex);
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
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
    }

    /**
     * The type CategoryMapper.
     */
    static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            Category category = null;
            try {
                if (rs.next()) {
                    category = new Category();
                    category.setId(rs.getInt(Fields.ID));
                    category.setDescriptionEn(rs.getString(Fields.CATEGORY_DESCRIPTION_EN));
                    category.setDescriptionUa(rs.getString(Fields.CATEGORY_DESCRIPTION_UA));
                    category.setNameEn(rs.getString(Fields.CATEGORY_NAME_EN));
                    category.setNameUa(rs.getString(Fields.CATEGORY_NAME_UA));
                }
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return category;
        }
    }
}
