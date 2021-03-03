package com.ostap.komplikevych.webshop.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Delivery extends AbstractEntity {
    private String nameUa;
    private String nameEn;
    private String averageDeliveryTime;
    private BigDecimal deliveryPrice;

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

    public String getAverageDeliveryTime() {
        return averageDeliveryTime;
    }

    public void setAverageDeliveryTime(String averageDeliveryTime) {
        this.averageDeliveryTime = averageDeliveryTime;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(nameUa, delivery.nameUa) &&
                Objects.equals(nameEn, delivery.nameEn) &&
                Objects.equals(averageDeliveryTime, delivery.averageDeliveryTime) &&
                Objects.equals(deliveryPrice, delivery.deliveryPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameUa, nameEn, averageDeliveryTime, deliveryPrice);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "nameUa='" + nameUa + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", averageDeliveryTime='" + averageDeliveryTime + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                '}';
    }
}
