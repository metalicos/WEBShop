package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.dao.AccountDetailDao;
import com.ostap.komplikevych.webshop.dao.MyChipher;
import com.ostap.komplikevych.webshop.dao.ShoppingCartDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountDetail;
import com.ostap.komplikevych.webshop.entity.ShoppingCart;

import java.time.LocalDateTime;

public class CreateAccount {
    public static void createAccount(Account account,AccountDetail accountDetail){
        AccountDetailDao dao = new AccountDetailDao();
        AccountDao accountDao = new AccountDao();
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();

        ShoppingCart cart = new ShoppingCart();
        cart.setLastUpdate(LocalDateTime.now());

        int shoppingCartId = shoppingCartDao.create(cart);
        account.setShoppingCartId(shoppingCartId);
        account.setCreateTime(LocalDateTime.now());

        MyChipher myChipher = new MyChipher();
        String encryptedPassword = myChipher.encrypt(account.getPassword());
        account.setPassword(encryptedPassword);

        int accountId = accountDao.create(account);

        accountDetail.setAccountId(accountId);
        dao.create(accountDetail);
    }
}
