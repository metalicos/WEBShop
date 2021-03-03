package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.model.DBManager;
import com.ostap.komplikevych.webshop.model.ImageConverter;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.AccountDetail;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * The type AccountDetailDao.
 *
 * @author Ostap Komplikevych
 */
public class AccountDetailDao {

    private static final String SQL_CREATE_ACCOUNT_DETAILS;
    private static final String SQL_READ_ACCOUNT_DETAILS_BY_ID;
    private static final String SQL_UPDATE_ACCOUNT_DETAILS;
    private static final String SQL_DELETE_ACCOUNT_DETAILS;
    private static final String SQL_UPDATE_ACCOUNT_PHOTO;

    static {
        SQL_CREATE_ACCOUNT_DETAILS = Const.getProperty("sql.create_account_details");
        SQL_READ_ACCOUNT_DETAILS_BY_ID = Const.getProperty("sql.read_account_details_by_id");
        SQL_UPDATE_ACCOUNT_DETAILS = Const.getProperty("sql.update_account_details");
        SQL_UPDATE_ACCOUNT_PHOTO = Const.getProperty("sql.update_account_photo");
        SQL_DELETE_ACCOUNT_DETAILS = Const.getProperty("sql.delete_account_details");
    }

    public Integer createAccountDetail(AccountDetail entity, InputStream accountPhoto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_ACCOUNT_DETAILS);
            /*
            INSERT INTO `webshop`.`account_detail`
            (`phone`,`zip_code`,`last_update`,`surname_ua`,`first_name_ua`,`patronymic_ua`,`country_ua`,
            `city_ua`,`street_ua`,`building_ua`,`flat_ua`,`surname_en`,`first_name_en`,`patronymic_en`,
            `country_en`,`city_en`,`street_en`,`building_en`,`flat_en`,`account_photo`,`account_id`)
            VALUES
            (?,?,?,?
            ,?,?,?,?
            ,?,?,?,?
            ,?,?,?,?
            ,?,?,?,?,?);
            * */
            pstmt.setString(1, entity.getPhone());
            pstmt.setInt(2, entity.getZipCode());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(4, entity.getSurnameUa());
            pstmt.setString(5, entity.getFirstNameUa());
            pstmt.setString(6, entity.getPatronymicUa());
            pstmt.setString(7, entity.getCountryUa());
            pstmt.setString(8, entity.getCityUa());
            pstmt.setString(9, entity.getStreetUa());
            pstmt.setString(10, entity.getBuildingUa());
            pstmt.setString(11, entity.getFlatUa());
            pstmt.setString(12, entity.getSurnameEn());
            pstmt.setString(13, entity.getFirstNameEn());
            pstmt.setString(14, entity.getPatronymicEn());
            pstmt.setString(15, entity.getCountryEn());
            pstmt.setString(16, entity.getCityEn());
            pstmt.setString(17, entity.getStreetEn());
            pstmt.setString(18, entity.getBuildingEn());
            pstmt.setString(19, entity.getFlatEn());

            String defaultPhotoPath = Const.RESOURCE_IMAGE_PATH + "account.png";

            pstmt.setBlob(20, ImageConverter.setDefaultImageBytesIfStreamNull(
                    accountPhoto, defaultPhotoPath));

            pstmt.setInt(21, entity.getAccountId());

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

    public AccountDetail readAccountDetailByAccountId(Integer id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        AccountDetail accountDetail = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ACCOUNT_DETAILS_BY_ID);
            pstmt.setInt(1, id);
            AccountDetailMapper accountDetailMapper = new AccountDetailMapper();
            accountDetail = accountDetailMapper.mapRow(pstmt.executeQuery());
        } catch (SQLException ex) {
            Const.logger.error(ex);
        } finally {
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(con);
        }
        return accountDetail;
    }

    public void updateAccountDetail(AccountDetail entity) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT_DETAILS);
            /*
            "(`phone`,`zip_code`,`last_update`,`surname_ua`,`first_name_ua`,`patronymic_ua`,`country_ua`," +
                    "`city_ua`,`street_ua`,`building_ua`,`flat_ua`,`surname_en`,`first_name_en`,`patronymic_en`," +
                    "`country_en`,`city_en`,`street_en`,`building_en`,`flat_en`,`account_photo`,`account_id`)"+
            * */
            pstmt.setString(1, entity.getPhone());
            pstmt.setInt(2, entity.getZipCode());
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setString(4, entity.getSurnameUa());
            pstmt.setString(5, entity.getFirstNameUa());
            pstmt.setString(6, entity.getPatronymicUa());
            pstmt.setString(7, entity.getCountryUa());
            pstmt.setString(8, entity.getCityUa());
            pstmt.setString(9, entity.getStreetUa());
            pstmt.setString(10, entity.getBuildingUa());
            pstmt.setString(11, entity.getFlatUa());
            pstmt.setString(12, entity.getSurnameEn());
            pstmt.setString(13, entity.getFirstNameEn());
            pstmt.setString(14, entity.getPatronymicEn());
            pstmt.setString(15, entity.getCountryEn());
            pstmt.setString(16, entity.getCityEn());
            pstmt.setString(17, entity.getStreetEn());
            pstmt.setString(18, entity.getBuildingEn());
            pstmt.setString(19, entity.getFlatEn());

//            String defaultPhotoPath = Const.RESOURCE_IMAGE_PATH + "account.png";
//
//            pstmt.setBlob(20, ImageConverter.setDefaultImageBytesIfStreamNull(
//                    accountPhoto, defaultPhotoPath));

            pstmt.setInt(20, entity.getAccountId());
            pstmt.setInt(21, entity.getId());

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

    public void updateAccountPhoto(int accountDetailId,InputStream photo) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT_PHOTO);
            pstmt.setBlob(1, photo);
            pstmt.setInt(2, accountDetailId);
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

    public void deleteAccountDetail(AccountDetail entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ACCOUNT_DETAILS);
            pstmt.setInt(1, entity.getId());
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

    static class AccountDetailMapper implements EntityMapper<AccountDetail> {

        @Override
        public AccountDetail mapRow(ResultSet rs) {
            AccountDetail aDetail = null;
            try {
                if (rs.next()) {
                    aDetail = new AccountDetail();
                    aDetail.setId(rs.getInt(Fields.ID));
                    aDetail.setPhone(rs.getString(Fields.ACCOUNT_DETAIL_PHONE));
                    aDetail.setZipCode(rs.getInt(Fields.ACCOUNT_DETAIL_ZIP_CODE));

                    aDetail.setSurnameEn(rs.getString(Fields.ACCOUNT_DETAIL_SURNAME_EN));
                    aDetail.setSurnameUa(rs.getString(Fields.ACCOUNT_DETAIL_SURNAME_UA));
                    aDetail.setFirstNameEn(rs.getString(Fields.ACCOUNT_DETAIL_FIRST_NAME_EN));
                    aDetail.setFirstNameUa(rs.getString(Fields.ACCOUNT_DETAIL_FIRST_NAME_UA));
                    aDetail.setPatronymicEn(rs.getString(Fields.ACCOUNT_DETAIL_PATRONYMIC_EN));
                    aDetail.setPatronymicUa(rs.getString(Fields.ACCOUNT_DETAIL_PATRONYMIC_UA));

                    aDetail.setCountryEn(rs.getString(Fields.ACCOUNT_DETAIL_COUNTRY_EN));
                    aDetail.setCountryUa(rs.getString(Fields.ACCOUNT_DETAIL_COUNTRY_UA));
                    aDetail.setCityEn(rs.getString(Fields.ACCOUNT_DETAIL_CITY_EN));
                    aDetail.setCityUa(rs.getString(Fields.ACCOUNT_DETAIL_CITY_UA));
                    aDetail.setStreetEn(rs.getString(Fields.ACCOUNT_DETAIL_STREET_EN));
                    aDetail.setStreetUa(rs.getString(Fields.ACCOUNT_DETAIL_STREET_UA));
                    aDetail.setBuildingEn(rs.getString(Fields.ACCOUNT_DETAIL_BUILDING_EN));
                    aDetail.setBuildingUa(rs.getString(Fields.ACCOUNT_DETAIL_BUILDING_UA));
                    aDetail.setFlatEn(rs.getString(Fields.ACCOUNT_DETAIL_FLAT_EN));
                    aDetail.setFlatUa(rs.getString(Fields.ACCOUNT_DETAIL_FLAT_UA));

                    String accountPhoto = ImageConverter.convertBlobToBase64StringImage(
                            rs.getBlob(Fields.ACCOUNT_DETAIL_ACCOUNT_PHOTO),
                            ImageConverter.MAX_IMAGE_SIZE_1MB);
                    aDetail.setAccountPhoto(accountPhoto);

                    aDetail.setAccountId(rs.getInt(Fields.ACCOUNT_DETAIL_ACCOUNT_ID));
                }
            } catch (SQLException ex) {
                Const.logger.error(ex);
                throw new IllegalStateException(ex.getMessage());
            } finally {
                DBManager.getInstance().close(rs);
            }
            return aDetail;
        }
    }

}
