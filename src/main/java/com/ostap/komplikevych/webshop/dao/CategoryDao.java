package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.Category;
import com.ostap.komplikevych.webshop.model.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type CategoryDao.
 */
public class CategoryDao {

    private static final String SQL_CREATE_CATEGORY;
    private static final String SQL_READ_CATEGORY_BY_ID;
    private static final String SQL_UPDATE_CATEGORY;
    private static final String SQL_DELETE_CATEGORY;
    public static final String SQL_READ_ALL_CATEGORIES;

    static {
        SQL_CREATE_CATEGORY = Const.getProperty("sql.create_category");
        SQL_READ_CATEGORY_BY_ID = Const.getProperty("sql.read_category_by_id");
        SQL_READ_ALL_CATEGORIES = Const.getProperty("sql.read_all_categories");
        SQL_UPDATE_CATEGORY = Const.getProperty("sql.update_category");
        SQL_DELETE_CATEGORY = Const.getProperty("sql.delete_category");
    }

    public Integer createCategory(Category entity) {
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

    public Category readCategoryByCategoryId(Integer id) {
        Category category = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Category> mapper = new CategoryMapper();
            pstmt = con.prepareStatement(SQL_READ_CATEGORY_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category = mapper.mapRow(rs);
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return category;
    }

    public void updateCategory(Category entity) {
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

    public void deleteCategory(Category entity) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
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

    public List<Category> readAllCategories() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Category> categories = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<Category> mapper = new CategoryMapper();
            pstmt = con.prepareStatement(SQL_READ_ALL_CATEGORIES);
            rs = pstmt.executeQuery();
            categories = new ArrayList<>();
            while (rs.next()) {
                categories.add(mapper.mapRow(rs));
            }
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return categories;
    }


    static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            Category category = null;
            try {
                category = new Category();
                category.setId(rs.getInt(Fields.ID));
                category.setDescriptionEn(rs.getString(Fields.CATEGORY_DESCRIPTION_EN));
                category.setDescriptionUa(rs.getString(Fields.CATEGORY_DESCRIPTION_UA));
                category.setNameEn(rs.getString(Fields.CATEGORY_NAME_EN));
                category.setNameUa(rs.getString(Fields.CATEGORY_NAME_UA));
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            }
            return category;
        }
    }
}
