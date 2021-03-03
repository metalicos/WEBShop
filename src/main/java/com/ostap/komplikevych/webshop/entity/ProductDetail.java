package com.ostap.komplikevych.webshop.entity;

import java.awt.image.BufferedImage;

public class ProductDetail extends AbstractEntity {
    private String nameUa;
    private String nameEn;
    private String colorUa;
    private String colorEn;
    private String sizeUa;
    private String sizeEn;
    private String aboutUa;
    private String aboutEn;
    /* Images as base64 */
    private String photo1;
    private String photo2;
    private String photo3;
    private int productId;

    public ProductDetail() {
    }

    public ProductDetail(int productId) {
        this.productId = productId;
    }

    public ProductDetail(String nameUa, String nameEn, String colorUa,
                         String colorEn, String sizeUa, String sizeEn,
                         String aboutUa, String aboutEn, String photo1,
                         String photo2, String photo3) {
        this.nameUa = nameUa;
        this.nameEn = nameEn;
        this.colorUa = colorUa;
        this.colorEn = colorEn;
        this.sizeUa = sizeUa;
        this.sizeEn = sizeEn;
        this.aboutUa = aboutUa;
        this.aboutEn = aboutEn;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getColorUa() {
        return colorUa;
    }

    public void setColorUa(String colorUa) {
        this.colorUa = colorUa;
    }

    public String getColorEn() {
        return colorEn;
    }

    public void setColorEn(String colorEn) {
        this.colorEn = colorEn;
    }

    public String getSizeUa() {
        return sizeUa;
    }

    public void setSizeUa(String sizeUa) {
        this.sizeUa = sizeUa;
    }

    public String getSizeEn() {
        return sizeEn;
    }

    public void setSizeEn(String sizeEn) {
        this.sizeEn = sizeEn;
    }

    public String getAboutUa() {
        return aboutUa;
    }

    public void setAboutUa(String aboutUa) {
        this.aboutUa = aboutUa;
    }

    public String getAboutEn() {
        return aboutEn;
    }

    public void setAboutEn(String aboutEn) {
        this.aboutEn = aboutEn;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }
    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "nameUa='" + nameUa + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", colorUa='" + colorUa + '\'' +
                ", colorEn='" + colorEn + '\'' +
                ", sizeUa='" + sizeUa + '\'' +
                ", sizeEn='" + sizeEn + '\'' +
                ", aboutUa='" + aboutUa + '\'' +
                ", aboutEn='" + aboutEn + '\'' +
               // ", photo1=" + photo1 +
                //", photo2=" + photo2 +
               // ", photo3=" + photo3 +
                ", productId=" + productId +
                '}';
    }
}
