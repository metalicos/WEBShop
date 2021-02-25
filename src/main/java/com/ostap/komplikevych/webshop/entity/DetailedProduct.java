package com.ostap.komplikevych.webshop.entity;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.dao.CategoryDao;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.dao.ProductDetailDao;
import com.ostap.komplikevych.webshop.localization.Language;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class DetailedProduct {
    private int id;
    private LocalDateTime createDate;
    private BigDecimal price;
    private int availableNumber;
    private int orderedNumber;
    private String categoryName;
    private String name;
    private String color;
    private String size;
    private String about;
    /* Images as base64 */
    private String photo1;
    private String photo2;
    private String photo3;

    public DetailedProduct(int productId, Language language) {
        ProductDao productDao = new ProductDao();
        ProductDetailDao productDetailDao = new ProductDetailDao();
        CategoryDao categoryDao = new CategoryDao();

        Product product = productDao.readProductByProductId(productId);
        ProductDetail productDetail = productDetailDao.readProductDetailByProductId(product.getId());
        Const.logger.info("productDetail = " + productDetail);
        Category category = categoryDao.readCategoryByCategoryId(product.getCategoryId());
        this.id = productId;
        this.createDate = product.getCreateDate();
        this.price = product.getPrice();
        this.availableNumber = (product.getAmount() - product.getOrderedAmount());
        this.orderedNumber = product.getOrderedAmount();
        if (language == Language.UA) {
            this.categoryName = category.getNameUa();
            this.name = productDetail.getNameUa();
            this.color = productDetail.getColorUa();
            this.size = productDetail.getSizeUa();
            this.about = productDetail.getAboutUa();
        } else {
            this.categoryName = category.getNameEn();
            this.name = productDetail.getNameEn();
            this.color = productDetail.getColorEn();
            this.size = productDetail.getSizeEn();
            this.about = productDetail.getAboutEn();
        }
        this.photo1 = productDetail.getPhoto1();
        this.photo2 = productDetail.getPhoto2();
        this.photo3 = productDetail.getPhoto3();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

    public int getOrderedNumber() {
        return orderedNumber;
    }

    public void setOrderedNumber(int orderedNumber) {
        this.orderedNumber = orderedNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailedProduct that = (DetailedProduct) o;
        return id == that.id &&
                availableNumber == that.availableNumber &&
                orderedNumber == that.orderedNumber &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(price, that.price) &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color) &&
                Objects.equals(size, that.size) &&
                Objects.equals(about, that.about) &&
                Objects.equals(photo1, that.photo1) &&
                Objects.equals(photo2, that.photo2) &&
                Objects.equals(photo3, that.photo3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, price, availableNumber, orderedNumber, categoryName, name, color, size, about, photo1, photo2, photo3);
    }

    @Override
    public String toString() {
        return "DetailedProduct{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", price=" + price +
                ", availableNumber=" + availableNumber +
                ", orderedNumber=" + orderedNumber +
                ", categoryName='" + categoryName + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", about='" + about + '\'' +
                ", photo1='" + photo1 + '\'' +
                ", photo2='" + photo2 + '\'' +
                ", photo3='" + photo3 + '\'' +
                '}';
    }
}
