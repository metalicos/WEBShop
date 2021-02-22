package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.dao.ShoppingCartDao;
import com.ostap.komplikevych.webshop.entity.*;
import com.ostap.komplikevych.webshop.localization.Language;
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
import java.util.stream.Collectors;

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        Const.logger.trace("Request parameter: email --> " + email);

        String password = request.getParameter("password");

        String errorMessage = null;
        String forward = Const.PAGE_LOGIN;

        if (Validator.checkIfNullOrEmptyReturnTrue(email, password)) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        AccountDao accountDao = new AccountDao();
        Account account = accountDao.readAccountByEmail(email);

        Const.logger.trace("Found in DB: account --> " + account);

        MyChipher chipher = new MyChipher();
        if (account == null || !password.equals(chipher.decrypt(account.getPassword()))) {
            errorMessage = "Cannot find account with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role accountRole = Role.getRole(account);
            AccountStatus accountStatus = AccountStatus.getAccountStatus(account);
            Const.logger.trace("accountRole --> " + accountRole);
            Const.logger.trace("accountStatus --> " + accountStatus);
            ShoppingCart cart = (ShoppingCart) session.getAttribute(SessionAttribute.SHOPPING_CART);
            if (cart != null) {
                ShoppingCartDao cartDao = new ShoppingCartDao();
                ShoppingCart accountLoggedShoppingCart =
                        cartDao.readShoppingCartByShoppingCartId(account.getShoppingCartId());
                List<ProductInCart> productsInLoggedCart = accountLoggedShoppingCart.getProducts();
                productsInLoggedCart.addAll(cart.getProducts());
                accountLoggedShoppingCart.setProducts(productsInLoggedCart);
                cartDao.updateShoppingCart(accountLoggedShoppingCart);
            }

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

            String language = (String) session.getAttribute(SessionAttribute.LANGUAGE);

//            Map<DetailedProduct, Integer> userShoopingCart =
//                    (Map<DetailedProduct, Integer>) session.getAttribute("userShoppingCart");
//
//            userShoopingCart = mergeCarts(userShoopingCart,account.getShoppingCartId(),language);
//
//            List<DetailedProduct> detailedProducts = new ArrayList<>(userShoopingCart.keySet());
//            DetailedProduct product;
//            BigDecimal totalProductSum = new BigDecimal(0);
//            for (int i = 0; i < detailedProducts.size(); i++) {
//                product = detailedProducts.get(i);
//                Const.logger.trace(product);
//                BigDecimal amount = BigDecimal.valueOf(userShoopingCart.get(product));
//                Const.logger.trace(amount);
//                BigDecimal semiSum = product.getPrice().multiply(amount);
//                totalProductSum = totalProductSum.add(semiSum);
//            }
//
//            session.setAttribute("userShoppingCart", userShoopingCart);
//            session.setAttribute("productsInCart", userShoopingCart.size());
//            session.setAttribute("totalProductSum",totalProductSum.doubleValue());

        }
        Const.logger.debug("Command finished");
        return "/";
    }

    private  Map<DetailedProduct, Integer> mergeCarts(Map<DetailedProduct, Integer> userCart ,int shoppingCartId,String lang){

        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        ShoppingCart shoppingCart = shoppingCartDao.readShoppingCartByShoppingCartId(shoppingCartId);
        List<ProductInCart> productsList = shoppingCart.getProducts();

        Map<DetailedProduct, Integer> userShoopingCart = new HashMap<>();

        for (int i = 0; i < productsList.size(); i++) {
            userShoopingCart.put(new DetailedProduct(productsList.get(i).getProduct().getId(),
                    Language.getLang(lang)),productsList.get(i).getAmount());
        }

        userShoopingCart.putAll(userCart);
        return  userShoopingCart;
    }
}