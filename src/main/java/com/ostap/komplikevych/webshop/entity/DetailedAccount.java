package com.ostap.komplikevych.webshop.entity;

import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.dao.AccountDetailDao;

import java.time.LocalDateTime;

public class DetailedAccount {
    private int id;
    private String email;
    private String password;
    private LocalDateTime createTime;
    private Role role;
    private AccountStatus accountStatus;
    private int shoppingCartId;

    private String phone;
    private int zipCode;

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

    public DetailedAccount() {

    }

    public DetailedAccount(int accountId) {
        AccountDao accountDao = new AccountDao();
        AccountDetailDao accountDetailDao = new AccountDetailDao();
        Account account = accountDao.readAccountByAccountId(accountId);
        AccountDetail accountDetail = accountDetailDao.readAccountDetailByAccountId(accountId);

        this.id = account.getId();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.createTime = account.getCreateTime();
        this.role = Role.getRole(account);
        this.accountStatus = AccountStatus.getAccountStatus(account);
        this.shoppingCartId = account.getShoppingCartId();

        this.surnameUa = accountDetail.getSurnameUa();
        this.firstNameUa = accountDetail.getFirstNameUa();
        this.patronymicUa = accountDetail.getPatronymicUa();
        this.countryUa = accountDetail.getCountryUa();
        this.cityUa = accountDetail.getCityUa();
        this.streetUa = accountDetail.getStreetUa();
        this.buildingUa = accountDetail.getBuildingUa();
        this.flatUa = accountDetail.getFlatUa();

        this.surnameEn = accountDetail.getSurnameEn();
        this.firstNameEn = accountDetail.getFirstNameEn();
        this.patronymicEn = accountDetail.getPatronymicEn();
        this.countryEn = accountDetail.getCountryEn();
        this.cityEn = accountDetail.getCityEn();
        this.streetEn = accountDetail.getStreetEn();
        this.buildingEn = accountDetail.getBuildingEn();
        this.flatEn = accountDetail.getFlatEn();

        this.phone = accountDetail.getPhone();
        this.zipCode = accountDetail.getZipCode();
        this.accountPhoto = accountDetail.getAccountPhoto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

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

    @Override
    public String toString() {
        return "DetailedAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", role=" + role +
                ", accountStatus=" + accountStatus +
                ", shoppingCartId=" + shoppingCartId +
                ", phone='" + phone + '\'' +
                ", zipCode=" + zipCode +
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
                ", accountPhoto='" + accountPhoto + '\'' +
                '}';
    }
}
