package com.ostap.komplikevych.webshop.dao;

import com.ostap.komplikevych.webshop.DBManager;
import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.AccountDetail;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class AccountDetailDao implements Crud<AccountDetail, Integer> {

    private static final String SQL_CREATE_ACCOUNT_DETAILS =
            "INSERT INTO `webshop`.`account_detail` " +
                    "(`phone`,`zip_code`,`last_update`,`surname_ua`,`first_name_ua`,`patronymic_ua`,`country_ua`," +
                    "`city_ua`,`street_ua`,`building_ua`,`flat_ua`,`surname_en`,`first_name_en`,`patronymic_en`," +
                    "`country_en`,`city_en`,`street_en`,`building_en`,`flat_en`,`account_photo`,`account_id`)"+
            "VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SQL_READ_ACCOUNT_DETAILS_BY_ID =
            "SELECT * FROM `webshop`.`account_detail` " +
                    "WHERE `account_id` = ?";

    private static final String SQL_UPDATE_ACCOUNT_DETAILS =
            "UPDATE `webshop`.`account_detail` " +
                    "SET `phone`= ?,`zip_code`= ?,`last_update`= ?,`surname_ua`= ?,`first_name_ua`= ?,`patronymic_ua`= ?," +
                    "`country_ua`= ?, `city_ua`= ?,`street_ua`= ?,`building_ua`= ?,`flat_ua`= ?,`surname_en`= ?," +
                    "`first_name_en`= ?,`patronymic_en`= ?,`country_en`= ?,`city_en`= ?,`street_en`= ?,`building_en`= ?," +
                    "`flat_en`= ?,`account_photo`= ?,`account_id`= ?" +
                    "WHERE id = ?;";
    private static final String SQL_DELETE_ACCOUNT_DETAILS =
            "DELETE FROM `webshop`.`account_detail` " +
            "WHERE `account_id` = ?";

    @Override
    public Integer create(AccountDetail entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int insertedWithId = -1;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CREATE_ACCOUNT_DETAILS);
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

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage img = entity.getAccountPhoto();
            if(img!=null) {
                ImageIO.write(img, "png", baos);
            }else {
                img = ImageIO.read(new File("src/main/resources/img/account.png"));
                ImageIO.write(img, "png", baos);
            }
            pstmt.setBlob(20, new ByteArrayInputStream(baos.toByteArray()));
            pstmt.setInt(21, entity.getAccountId());

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
    public AccountDetail read(Integer id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AccountDetail accountDetail = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_READ_ACCOUNT_DETAILS_BY_ID);
            pstmt.setInt(1,id);
            AccountDetailMapper accountDetailMapper = new AccountDetailMapper();
            accountDetail = accountDetailMapper.mapRow(pstmt.executeQuery());
        }catch (SQLException ex) {
            Const.logger.error(ex.getMessage());
        }finally {
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(pstmt);
            DBManager.getInstance().close(con);
        }
        return accountDetail;
    }

    @Override
    public void update(AccountDetail entity) {
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
            pstmt.setTimestamp(3, Timestamp.valueOf(entity.getLastUpdate()));
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

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(entity.getAccountPhoto(),"png",baos);
            pstmt.setBlob(20, new ByteArrayInputStream(baos.toByteArray()));

            pstmt.setInt(21, entity.getAccountId());
            pstmt.setInt(22, entity.getId());

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
    public void delete(AccountDetail entity) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_ACCOUNT_DETAILS);
            pstmt.setInt(1,entity.getId());
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

    static class AccountDetailMapper implements EntityMapper<AccountDetail> {

        @Override
        public AccountDetail mapRow(ResultSet rs) {
            try {
                AccountDetail aDetail = new AccountDetail();
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

                Blob blob = rs.getBlob(Fields.ACCOUNT_DETAIL_ACCOUNT_PHOTO);
                aDetail.setAccountPhoto(ImageIO.read(blob.getBinaryStream()));

                aDetail.setAccountId(rs.getInt(Fields.ACCOUNT_DETAIL_ACCOUNT_ID));
                return aDetail;
            } catch (SQLException | IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void main(String[] args) {
        AccountDetailDao dao = new AccountDetailDao();
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setFirstNameEn("Ostap");
        accountDetail.setPatronymicEn("Foder");
        System.out.println(dao.create(accountDetail));
    }
}