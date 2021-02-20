package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.model.DBManager;
import com.ostap.komplikevych.webshop.model.ImageConverter;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.ProductDetail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The type ProductDetailDao.
 *
 * @author Ostap Komplikevych
 */
public class ProductDetailDao {

    private static final String SQL_CREATE_PRODUCT_DETAIL;
    private static final String SQL_READ_PRODUCT_DETAIL_BY_ID;
    private static final String SQL_READ_PRODUCT_DETAIL_BY_PRODUCT_ID;
    private static final String SQL_UPDATE_PRODUCT_DETAIL;
    private static final String SQL_DELETE_PRODUCT_DETAIL;
    private static final String SQL_SEARCH_PRODUCT_DETAIL_BY_NAME;

    static {
        SQL_CREATE_PRODUCT_DETAIL = Const.getProperty("sql.create_product_details");
        SQL_READ_PRODUCT_DETAIL_BY_ID = Const.getProperty("sql.read_product_details_by_id");
        SQL_SEARCH_PRODUCT_DETAIL_BY_NAME = Const.getProperty("sql.search_product_detail_by_name");
        SQL_READ_PRODUCT_DETAIL_BY_PRODUCT_ID = Const.getProperty("sql.read_product_details_by_product_id");
        SQL_UPDATE_PRODUCT_DETAIL = Const.getProperty("sql.update_product_details");
        SQL_DELETE_PRODUCT_DETAIL = Const.getProperty("sql.delete_product_details");
    }

    public Integer createProductDetail(ProductDetail entity, InputStream photo1, InputStream photo2, InputStream photo3) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_PRODUCT_DETAIL);

            pstmt.setString(1, entity.getNameUa());
            pstmt.setString(2, entity.getColorUa());
            pstmt.setString(3, entity.getSizeUa());
            pstmt.setString(4, entity.getAboutUa());
            pstmt.setString(5, entity.getNameEn());
            pstmt.setString(6, entity.getColorEn());
            pstmt.setString(7, entity.getSizeEn());
            pstmt.setString(8, entity.getAboutEn());

            String defaultPhotoPath = Const.RESOURCE_IMAGE_PATH + "camera.png";

            pstmt.setBlob(9, ImageConverter.setDefaultImageBytesIfStreamNull(
                    photo1, defaultPhotoPath));
            pstmt.setBlob(10, ImageConverter.setDefaultImageBytesIfStreamNull(
                    photo2, defaultPhotoPath));
            pstmt.setBlob(11, ImageConverter.setDefaultImageBytesIfStreamNull(
                    photo3, defaultPhotoPath));

            pstmt.setInt(12, entity.getProductId());

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

    public ProductDetail readProductDetailByDetailId(Integer productDetailId) {
        ProductDetail productDetail = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<ProductDetail> mapper = new ProductDetailMapper();
            pstmt = con.prepareStatement(SQL_READ_PRODUCT_DETAIL_BY_ID);
            pstmt.setLong(1, productDetailId);
            productDetail = mapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return productDetail;
    }

    public ProductDetail readProductDetailByProductId(Integer productId) {
        ProductDetail productDetail = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<ProductDetail> mapper = new ProductDetailMapper();
            pstmt = con.prepareStatement(SQL_READ_PRODUCT_DETAIL_BY_PRODUCT_ID);
            pstmt.setLong(1, productId);
            productDetail = mapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return productDetail;
    }

    public void updateProductDetail(ProductDetail entity, InputStream photo1, InputStream photo2, InputStream photo3) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_PRODUCT_DETAIL);

            pstmt.setString(1, entity.getNameUa());
            pstmt.setString(2, entity.getColorUa());
            pstmt.setString(3, entity.getSizeUa());
            pstmt.setString(4, entity.getAboutUa());
            pstmt.setString(5, entity.getNameEn());
            pstmt.setString(6, entity.getColorEn());
            pstmt.setString(7, entity.getSizeEn());
            pstmt.setString(8, entity.getAboutEn());

            String defaultPhotoPath = Const.RESOURCE_IMAGE_PATH + "camera.png";

            pstmt.setBlob(9, ImageConverter.setDefaultImageBytesIfStreamNull(
                    photo1, defaultPhotoPath));
            pstmt.setBlob(10, ImageConverter.setDefaultImageBytesIfStreamNull(
                    photo2, defaultPhotoPath));
            pstmt.setBlob(11, ImageConverter.setDefaultImageBytesIfStreamNull(
                    photo3, defaultPhotoPath));

            pstmt.setInt(12, entity.getProductId());

            pstmt.setInt(13, entity.getId());

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

    public void deleteProductDetail(ProductDetail entity) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<ProductDetail> mapper = new ProductDetailMapper();
            pstmt = con.prepareStatement(SQL_DELETE_PRODUCT_DETAIL);
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

    static class ProductDetailMapper implements EntityMapper<ProductDetail> {

        @Override
        public ProductDetail mapRow(ResultSet rs) {
            ProductDetail productDetail = null;
            try {
                if (rs.next()) {
                    productDetail = new ProductDetail();
                    productDetail.setNameEn(rs.getString(Fields.PRODUCT_DETAIL_NAME_EN));
                    productDetail.setNameUa(rs.getString(Fields.PRODUCT_DETAIL_NAME_UA));
                    productDetail.setColorEn(rs.getString(Fields.PRODUCT_DETAIL_COLOR_EN));
                    productDetail.setColorUa(rs.getString(Fields.PRODUCT_DETAIL_COLOR_UA));
                    productDetail.setSizeEn(rs.getString(Fields.PRODUCT_DETAIL_SIZE_EN));
                    productDetail.setSizeUa(rs.getString(Fields.PRODUCT_DETAIL_SIZE_UA));
                    productDetail.setAboutEn(rs.getString(Fields.PRODUCT_DETAIL_ABOUT_EN));
                    productDetail.setAboutUa(rs.getString(Fields.PRODUCT_DETAIL_ABOUT_UA));


                    String photo1 = ImageConverter.convertBlobToBase64StringImage(
                            rs.getBlob(Fields.PRODUCT_DETAIL_PHOTO_1),
                            ImageConverter.MAX_IMAGE_SIZE_1MB);
                    productDetail.setPhoto1(photo1);

                    String photo2 = ImageConverter.convertBlobToBase64StringImage(
                            rs.getBlob(Fields.PRODUCT_DETAIL_PHOTO_2),
                            ImageConverter.MAX_IMAGE_SIZE_1MB);
                    productDetail.setPhoto2(photo2);

                    String photo3 = ImageConverter.convertBlobToBase64StringImage(
                            rs.getBlob(Fields.PRODUCT_DETAIL_PHOTO_3),
                            ImageConverter.MAX_IMAGE_SIZE_1MB);
                    productDetail.setPhoto3(photo3);

                    productDetail.setProductId(rs.getInt(Fields.PRODUCT_DETAIL_PRODUCT_ID));
                }
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return productDetail;
        }
    }
}
