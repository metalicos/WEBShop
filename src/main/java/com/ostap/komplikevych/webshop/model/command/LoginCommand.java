package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.dao.ShoppingCartDao;
import com.ostap.komplikevych.webshop.entity.*;
import com.ostap.komplikevych.webshop.model.command.cart.ShoppingCartCommand;
import com.ostap.komplikevych.webshop.model.security.MyChipher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginCommand implements Command {
    private static final int EMPTY = 0;

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        Const.logger.trace("Request parameter: email --> " + email);

        String password = request.getParameter("password");

        String errorMessage = null;

        if (Validator.checkIfNullOrEmptyReturnTrue(email, password)) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return Const.PAGE_LOGIN;
        }

        AccountDao accountDao = new AccountDao();
        Account account = accountDao.readAccountByEmail(email);

        Const.logger.trace("Found in DB: account --> " + account);

        MyChipher cipher = new MyChipher();
        if (account == null || !password.equals(cipher.decrypt(account.getPassword()))) {
            errorMessage = "Cannot find account with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return Const.PAGE_LOGIN;
        } else if(AccountStatus.getAccountStatus(account) == AccountStatus.DISABLED){
            errorMessage = "You are banned by administrator.";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            session.invalidate();
            return Const.PAGE_LOGIN;
        } else {

            setupAccountInformation(request, account);

            String language = (String) session.getAttribute(SessionAttribute.LANGUAGE);
            Map<DetailedProduct, Integer> userShoppingCart =
                    (Map<DetailedProduct, Integer>) session.getAttribute("userShoppingCart");
            Const.logger.trace("userShoppingCartWas = " + userShoppingCart);

            userShoppingCart = mergeCarts(userShoppingCart, account.getShoppingCartId(), language);
            Const.logger.trace("userShoppingCartBecome = " + userShoppingCart);

            ShoppingCartCommand.createShoppingCartMapFromDB(request);
        }

        Const.logger.debug("Command finished");
        return "/";
    }


    private Map<DetailedProduct, Integer> mergeCarts(Map<DetailedProduct, Integer> userCart, int shoppingCartId, String lang) {

        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        ShoppingCart shoppingCart = shoppingCartDao.readShoppingCartByShoppingCartId(shoppingCartId);
        Const.logger.info("shoppingCartInDB = " + shoppingCart);
        List<ProductInCart> productsList = shoppingCart.getProducts();
        Const.logger.info("userCart = " + userCart);

        Map<DetailedProduct, Integer> fullShoppingCart;
        if (userCart == null) {
            fullShoppingCart = new HashMap<>();
        } else {
            fullShoppingCart = new HashMap<>(userCart);
        }

        DetailedProduct detailedProduct;
        List<DetailedProduct> unlogedUserList = new ArrayList<>(fullShoppingCart.keySet());
        ProductDao productDao = new ProductDao();
        for (int i = 0; i < fullShoppingCart.size(); i++) {
            ProductInCart product = new ProductInCart();
            detailedProduct = unlogedUserList.get(i);
            product.setProduct(productDao.readProductByProductId(detailedProduct.getId()));
            product.setAmount(fullShoppingCart.get(detailedProduct));

            int productId = detailedProduct.getId();

            Integer amountInCart = fullShoppingCart.get(detailedProduct);
            int amountInDB;
            if (productsList.contains(product)) {
                amountInDB = ShoppingCartDao.readProductInCart(shoppingCartId, productId).getAmount();
                ShoppingCartDao.updateProductInCart(shoppingCartId, productId, (amountInCart + amountInDB));
                Const.logger.trace("product is in DB shopping cart (updating) ");
            } else {
                ShoppingCartDao.createProductInCart(shoppingCartId, productId, amountInCart);
                Const.logger.trace("product is NOT found in DB shopping cart (creating) ");
            }
        }
        return fullShoppingCart;
    }


    private void setupAccountInformation(HttpServletRequest request, Account account) {
        HttpSession session = request.getSession();
        Role accountRole = Role.getRole(account);
        AccountStatus accountStatus = AccountStatus.getAccountStatus(account);
        Const.logger.trace("accountRole --> " + accountRole);
        Const.logger.trace("accountStatus --> " + accountStatus);

        session.setAttribute(SessionAttribute.ACCOUNT, account);
        request.setAttribute(SessionAttribute.ACCOUNT, account);
        Const.logger.trace("Set the session attribute: " + SessionAttribute.ACCOUNT + " --> " + account);


        DetailedAccount detailedAccount = new DetailedAccount(account.getId());
        session.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);
        request.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);


        session.setAttribute(SessionAttribute.ROLE, accountRole);
        request.setAttribute(SessionAttribute.ROLE, accountRole);
        Const.logger.trace("Set the session attribute: " + SessionAttribute.ROLE + " --> " + accountRole);

        session.setAttribute(SessionAttribute.ACCOUNT_STATUS, accountStatus);
        request.setAttribute(SessionAttribute.ACCOUNT_STATUS, accountStatus);
        Const.logger.trace("Set the session attribute: " + SessionAttribute.ACCOUNT_STATUS + " --> " + accountStatus);

        Const.logger.debug("Account " + account + " logged as " + accountRole.name().toLowerCase());
    }


}


