package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.ProductDetail;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * The type ProductDetailDao.
 *
 * @author Ostap Komplikevych
 */
public class ProductDetailDao implements Crud<ProductDetail, Integer> {

    private static final String SQL_CREATE_PRODUCT_DETAIL;
    private static final String SQL_READ_PRODUCT_DETAIL_BY_ID;
    private static final String SQL_UPDATE_PRODUCT_DETAIL;
    private static final String SQL_DELETE_PRODUCT_DETAIL;

    static {
        SQL_CREATE_PRODUCT_DETAIL = Const.getProperty("sql.create_product_details");
        SQL_READ_PRODUCT_DETAIL_BY_ID = Const.getProperty("sql.read_product_details_by_id");
        SQL_UPDATE_PRODUCT_DETAIL = Const.getProperty("sql.update_product_details");
        SQL_DELETE_PRODUCT_DETAIL = Const.getProperty("sql.delete_product_details");
    }

    private ByteArrayOutputStream getImageAsByteArrayOutputStream(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (img != null) {
            ImageIO.write(img, "png", baos);
        } else {
            img = ImageIO.read(new File("src/main/resources/img/camera.png"));
            ImageIO.write(img, "png", baos);
        }
        return baos;
    }

    @Override
    public Integer create(ProductDetail entity) {
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

            ByteArrayOutputStream baos = getImageAsByteArrayOutputStream(entity.getPhoto1());
            pstmt.setBlob(9, new ByteArrayInputStream(baos.toByteArray()));

            baos = getImageAsByteArrayOutputStream(entity.getPhoto2());
            pstmt.setBlob(10, new ByteArrayInputStream(baos.toByteArray()));

            baos = getImageAsByteArrayOutputStream(entity.getPhoto3());
            pstmt.setBlob(11, new ByteArrayInputStream(baos.toByteArray()));

            pstmt.setInt(12, entity.getProductId());

            pstmt.executeUpdate();
            insertedWithId = DBManager.getInstance().getLastInsertedId(pstmt);
        } catch (SQLException | IOException ex) {
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
    public ProductDetail read(Integer id) {
        ProductDetail productDetail = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            EntityMapper<ProductDetail> mapper = new ProductDetailMapper();
            pstmt = con.prepareStatement(SQL_READ_PRODUCT_DETAIL_BY_ID);
            pstmt.setLong(1, id);
            productDetail = mapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
        return productDetail;
    }

    @Override
    public void update(ProductDetail entity) {
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

            ByteArrayOutputStream baos = getImageAsByteArrayOutputStream(entity.getPhoto1());
            pstmt.setBlob(9, new ByteArrayInputStream(baos.toByteArray()));

            baos = getImageAsByteArrayOutputStream(entity.getPhoto2());
            pstmt.setBlob(10, new ByteArrayInputStream(baos.toByteArray()));

            baos = getImageAsByteArrayOutputStream(entity.getPhoto3());
            pstmt.setBlob(11, new ByteArrayInputStream(baos.toByteArray()));

            pstmt.setInt(12, entity.getProductId());

            pstmt.setInt(13, entity.getId());

            pstmt.executeUpdate();
        } catch (SQLException | IOException ex) {
            DBManager.getInstance().rollback(con);
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().commit(con);
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(pstmt);
        }
    }

    @Override
    public void delete(ProductDetail entity) {
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
            Const.logger.error(ex.getMessage());
        } finally {
            DBManager.getInstance().close(con);
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
        }
    }

    /**
     * The type ProductDetailMapper.
     */
    static class ProductDetailMapper implements EntityMapper<ProductDetail> {

        @Override
        public ProductDetail mapRow(ResultSet rs) {
            ProductDetail productDetail = new ProductDetail();
            try {
                if (rs.next()) {
                    productDetail.setNameEn(rs.getString(Fields.PRODUCT_DETAIL_NAME_EN));
                    productDetail.setNameUa(rs.getString(Fields.PRODUCT_DETAIL_NAME_UA));
                    productDetail.setColorEn(rs.getString(Fields.PRODUCT_DETAIL_COLOR_EN));
                    productDetail.setColorUa(rs.getString(Fields.PRODUCT_DETAIL_COLOR_UA));
                    productDetail.setSizeEn(rs.getString(Fields.PRODUCT_DETAIL_SIZE_EN));
                    productDetail.setSizeUa(rs.getString(Fields.PRODUCT_DETAIL_SIZE_UA));
                    productDetail.setAboutEn(rs.getString(Fields.PRODUCT_DETAIL_ABOUT_EN));
                    productDetail.setAboutUa(rs.getString(Fields.PRODUCT_DETAIL_ABOUT_UA));

                    Blob blob = rs.getBlob(Fields.PRODUCT_DETAIL_PHOTO_1);
                    productDetail.setPhoto1(ImageIO.read(blob.getBinaryStream()));
                    blob = rs.getBlob(Fields.PRODUCT_DETAIL_PHOTO_2);
                    productDetail.setPhoto2(ImageIO.read(blob.getBinaryStream()));
                    blob = rs.getBlob(Fields.PRODUCT_DETAIL_PHOTO_3);
                    productDetail.setPhoto3(ImageIO.read(blob.getBinaryStream()));

                    productDetail.setProductId(rs.getInt(Fields.PRODUCT_DETAIL_PRODUCT_ID));
                }
            } catch (SQLException | IOException ex) {
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return productDetail;
        }
    }
}
