package com.ostap.komplikevych.webshop.temp;

import com.ostap.komplikevych.webshop.dao.*;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountDetail;
import com.ostap.komplikevych.webshop.entity.Role;
import com.ostap.komplikevych.webshop.entity.ShoppingCart;
import com.ostap.komplikevych.webshop.model.ImageConverter;
import com.ostap.komplikevych.webshop.model.security.MyChipher;

import java.time.LocalDateTime;

public class DBCreator {
    public static void main(String[] args) {
        AccountDao accountDao = new AccountDao();
        AccountDetailDao accountDetailDao = new AccountDetailDao();
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();

        Account[] account = new Account[3];
        String[] pass = {"root", "user", "temp"};
        String[] email = {"root@gmail.com", "user@gmail.com", "temp@gmail.com"};
        Role[] role = {Role.ADMIN, Role.USER, Role.USER};

        for (int i = 0; i < 3; i++) {
            account[i].setEmail(email[i]);
            account[i].setPassword(pass[i]);
            account[i].setRoleId(role[i].getId());
        }

        AccountDetail[] accountDetail = new AccountDetail[3];
        String[] surnameUa = {"Донецький", "Квітка", "Шевченко"};
        String[] firstNameUa = {"Остап", "Орест", "Тарас"};
        String[] patronymicUa = {"Євгенович", "Ярославович", "Григорович"};
        String[] surnameEn = {"Donetskyy", "Kvitka", "Shevchenko"};
        String[] firstNameEn = {"Ostap", "Orest", "Taras"};
        String[] patronymicEn = {"Yevgenovych", "Yaroslavovych", "Grygorovych"};
        String[] countryEn = {"Ukraine", "Ukraine", "Ukraine"};
        String[] cityEn = {"Lviv", "Lviv", "Lviv"};
        String[] streetEn = {"Lubinska Str.", "Lubinska Str.", "Lubinska Str."};
        String[] buildingEn = {"55", "44", "33"};
        String[] flatEn = {"12", "23", "34"};
        String[] countryUa = {"Україна", "Україна", "Україна"};
        String[] cityUa = {"Львів", "Львів", "Львів"};
        String[] streetUa = {"вул. Любінська", "вул. Любінська", "вул. Любінська"};
        String[] buildingUa = {"55", "44", "33"};
        String[] flatUa = {"12", "23", "34"};
        int[] zipCode = {79023, 79021, 79024};
        String[] phone = {"0683567221", "0683567555", "0683567543"};

        for (int i = 0; i < 3; i++) {
            accountDetail[i].setSurnameEn(surnameEn[i]);
            accountDetail[i].setFirstNameEn(firstNameEn[i]);
            accountDetail[i].setPatronymicEn(patronymicEn[i]);
            accountDetail[i].setSurnameUa(surnameUa[i]);
            accountDetail[i].setFirstNameUa(firstNameUa[i]);
            accountDetail[i].setPatronymicUa(patronymicUa[i]);

            accountDetail[i].setCountryEn(countryEn[i]);
            accountDetail[i].setCountryUa(countryUa[i]);
            accountDetail[i].setCityEn(cityEn[i]);
            accountDetail[i].setCityUa(cityUa[i]);
            accountDetail[i].setStreetEn(streetEn[i]);
            accountDetail[i].setSurnameUa(streetUa[i]);
            accountDetail[i].setBuildingEn(buildingEn[i]);
            accountDetail[i].setBuildingUa(buildingUa[i]);
            accountDetail[i].setFlatEn(flatEn[i]);
            accountDetail[i].setFlatUa(flatUa[i]);
            accountDetail[i].setZipCode(zipCode[i]);
            accountDetail[i].setPhone(phone[i]);
        }

        ShoppingCart[] cart = new ShoppingCart[3];
        for (int i = 0; i < 3; i++) {
            cart[i].setLastUpdate(LocalDateTime.now());
        }

        MyChipher myChipher = new MyChipher();
        for (int i = 0; i < 3; i++) {
            int shoppingCartId = shoppingCartDao.createShoppingCart(cart[i]);
            account[i].setShoppingCartId(shoppingCartId);
            account[i].setCreateTime(LocalDateTime.now());
            String encryptedPassword = myChipher.encrypt(account[i].getPassword());
            account[i].setPassword(encryptedPassword);
        }

        for (int i = 0; i < 3; i++) {
            int accountId = accountDao.createAccount(account[i]);
            accountDetail[i].setAccountId(accountId);
            accountDetailDao.createAccountDetail(accountDetail[i],
                    ImageConverter.convertImageFromFileAsBinaryStream("D:\\Windows\\Pictures\\account.png"));
        }

        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        ProductDetailDao productDetailDao = new ProductDetailDao();
        AccountOrderDao accountOrderDao = new AccountOrderDao();


    }
}
