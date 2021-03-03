package com.ostap.komplikevych.webshop.entity;

import java.time.LocalDateTime;

public class AccountDetail extends AbstractEntity {

    private String phone;
    private int zipCode;
    private LocalDateTime lastUpdate;
    private String surnameUa;
    private String firstNameUa;
    private String patronymicUa;
    private String countryUa;
    private String cityUa;
    private String streetUa;
    private String buildingUa;
    private String flatUa;
    private String surnameEn;
    private String firstNameEn;
    private String patronymicEn;
    private String countryEn;
    private String cityEn;
    private String streetEn;
    private String buildingEn;
    private String flatEn;
    private String accountPhoto;
    private int accountId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSurnameUa() {
        return surnameUa;
    }

    public void setSurnameUa(String surnameUa) {
        this.surnameUa = surnameUa;
    }

    public String getFirstNameUa() {
        return firstNameUa;
    }

    public void setFirstNameUa(String firstNameUa) {
        this.firstNameUa = firstNameUa;
    }

    public String getPatronymicUa() {
        return patronymicUa;
    }

    public void setPatronymicUa(String patronymicUa) {
        this.patronymicUa = patronymicUa;
    }

    public String getCountryUa() {
        return countryUa;
    }

    public void setCountryUa(String countryUa) {
        this.countryUa = countryUa;
    }

    public String getCityUa() {
        return cityUa;
    }

    public void setCityUa(String cityUa) {
        this.cityUa = cityUa;
    }

    public String getStreetUa() {
        return streetUa;
    }

    public void setStreetUa(String streetUa) {
        this.streetUa = streetUa;
    }

    public String getBuildingUa() {
        return buildingUa;
    }

    public void setBuildingUa(String buildingUa) {
        this.buildingUa = buildingUa;
    }

    public String getFlatUa() {
        return flatUa;
    }

    public void setFlatUa(String flatUa) {
        this.flatUa = flatUa;
    }

    public String getSurnameEn() {
        return surnameEn;
    }

    public void setSurnameEn(String surnameEn) {
        this.surnameEn = surnameEn;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getPatronymicEn() {
        return patronymicEn;
    }

    public void setPatronymicEn(String patronymicEn) {
        this.patronymicEn = patronymicEn;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getStreetEn() {
        return streetEn;
    }

    public void setStreetEn(String streetEn) {
        this.streetEn = streetEn;
    }

    public String getBuildingEn() {
        return buildingEn;
    }

    public void setBuildingEn(String buildingEn) {
        this.buildingEn = buildingEn;
    }

    public String getFlatEn() {
        return flatEn;
    }

    public void setFlatEn(String flatEn) {
        this.flatEn = flatEn;
    }

    public String getAccountPhoto() {
        return accountPhoto;
    }

    public void setAccountPhoto(String accountPhoto) {
        this.accountPhoto = accountPhoto;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "AccountDetail{" +
                "phone='" + phone + '\'' +
                ", zipCode=" + zipCode +
                ", lastUpdate=" + lastUpdate +
                ", surnameUa='" + surnameUa + '\'' +
                ", firstNameUa='" + firstNameUa + '\'' +
                ", patronymicUa='" + patronymicUa + '\'' +
                ", countryUa='" + countryUa + '\'' +
                ", cityUa='" + cityUa + '\'' +
                ", streetUa='" + streetUa + '\'' +
                ", buildingUa='" + buildingUa + '\'' +
                ", flatUa='" + flatUa + '\'' +
                ", surnameEn='" + surnameEn + '\'' +
                ", firstNameEn='" + firstNameEn + '\'' +
                ", patronymicEn='" + patronymicEn + '\'' +
                ", countryEn='" + countryEn + '\'' +
                ", cityEn='" + cityEn + '\'' +
                ", streetEn='" + streetEn + '\'' +
                ", buildingEn='" + buildingEn + '\'' +
                ", flatEn='" + flatEn + '\'' +
                //  ", accountPhoto=" + accountPhoto +
                ", accountId=" + accountId +
                '}';
    }
}
