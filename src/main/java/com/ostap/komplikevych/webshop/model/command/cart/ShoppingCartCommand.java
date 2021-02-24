package com.ostap.komplikevych.webshop.model.command.cart;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.ShoppingCartDao;
import com.ostap.komplikevych.webshop.entity.*;
import com.ostap.komplikevych.webshop.localization.Language;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartCommand {
    public static BigDecimal getTotalPriceOfUserShoppingCart(Map<DetailedProduct, Integer> userShoppingCart) {
        List<DetailedProduct> detailedProducts = new ArrayList<>(userShoppingCart.keySet());
        DetailedProduct product;
        BigDecimal totalProductSum = new BigDecimal(0);
        for (int i = 0; i < detailedProducts.size(); i++) {
            product = detailedProducts.get(i);
            Const.logger.trace(product);
            BigDecimal amount = BigDecimal.valueOf(userShoppingCart.get(product));
            Const.logger.trace(amount);
            BigDecimal semiSum = product.getPrice().multiply(amount);
            totalProductSum = totalProductSum.add(semiSum);
        }
        return totalProductSum;
    }

    public Map<DetailedProduct, Integer> operation(HttpServletRequest request,
                                                   Map<DetailedProduct, Integer> userShoppingCart,
                                                   Operation operation) {
        HttpSession session = request.getSession();
        String productId = request.getParameter("productId");
        String language = (String) session.getAttribute("language");


        if (productId != null && productId.matches("[0-9]")) {
            DetailedProduct detailedProduct =
                    new DetailedProduct(Integer.parseInt(productId), Language.getLang(language));

            Const.logger.error(detailedProduct);
            if (userShoppingCart == null) {
                userShoppingCart = new HashMap<>();
            }

            Integer amountProduct = userShoppingCart.get(detailedProduct);

            if (operation == Operation.ADDITION) {
                add(userShoppingCart, amountProduct, detailedProduct);
            }
            if (operation == Operation.SUBTRACTION) {
                sub(userShoppingCart, amountProduct, detailedProduct);
            }

        } else {
            Const.logger.error("Id of product = null OR didn`t match [0-9]");
        }

        return userShoppingCart;
    }


    public void operation(HttpServletRequest request, Account account, Operation operation) {

        String productId = request.getParameter("productId");

        if (productId != null && account != null && productId.matches("[0-9]")) {
            int cartId = account.getShoppingCartId();
            int pId = Integer.parseInt(productId);
            ProductInCart productInCart = ShoppingCartDao.readProductInCart(cartId, pId);
            Const.logger.trace(productInCart);

            int amount = 0;
            if (productInCart != null) {
                amount = productInCart.getAmount();
            }
            if (operation == Operation.ADDITION_LOGGED) {
                addLogged(amount, cartId, pId);
            }
            if (operation == Operation.SUBTRACTION_LOGGED) {
                subLogged(amount, cartId, pId);
            }

        }
    }

    private void add(Map<DetailedProduct, Integer> cart,
                     Integer amount, DetailedProduct product) {
        if (amount == null) {
            cart.put(product, 1);
        } else {
            cart.put(product, ++amount);
        }
    }

    private void sub(Map<DetailedProduct, Integer> cart,
                     Integer amount, DetailedProduct product) {
        if (amount != null) {
            cart.put(product, --amount);
            if (amount <= 0) {
                cart.remove(product);
            }
        }
    }

    private void addLogged(int amount, int cartId, int productId) {
        if (amount >= 1) {
            Const.logger.trace(amount + " amount is >= 1");
            ShoppingCartDao.updateProductInCart(cartId, productId, ++amount);
        } else {
            Const.logger.trace(amount + " amount is < 1");
            ShoppingCartDao.createProductInCart(cartId, productId, 1);
        }
    }

    private void subLogged(int amount, int cartId, int productId) {
        if (amount > 1) {
            Const.logger.trace(amount + " amount is > 1");
            ShoppingCartDao.updateProductInCart(cartId, productId, --amount);
        } else {
            Const.logger.trace(amount + " amount is <= 1");
            ShoppingCartDao.deleteProductInCart(cartId, productId);
        }
    }

    public static Map<DetailedProduct, Integer> createShoppingCartMapFromDB(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<DetailedProduct, Integer> userShoppingCart = null;
        Role accountRole = (Role) session.getAttribute(SessionAttribute.ROLE);
        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);
        String lang = (String) session.getAttribute(SessionAttribute.LANGUAGE);
        if (accountRole != null && account != null) {
            ShoppingCartDao cartDao = new ShoppingCartDao();
            ShoppingCart cart = cartDao.readShoppingCartByShoppingCartId(account.getShoppingCartId());
            List<ProductInCart> products = cart.getProducts();
            userShoppingCart = new HashMap<>();
            DetailedProduct detailedProduct;
            for (ProductInCart productInCart : products) {
                detailedProduct = new DetailedProduct(productInCart.getProduct().getId(),
                        Language.getLang(lang));
                userShoppingCart.put(detailedProduct, productInCart.getAmount());
            }

            BigDecimal totalProductSum = getTotalPriceOfUserShoppingCart(userShoppingCart);
            session.setAttribute("productsInCart", userShoppingCart.size());
            session.setAttribute("totalProductSum", totalProductSum.doubleValue());

            session.setAttribute("userShoppingCart", userShoppingCart);
            request.setAttribute("userShoppingCart", userShoppingCart);
            Const.logger.debug("UserShoppingCart=" + userShoppingCart);
        }
        return userShoppingCart;
    }

    enum Operation {
        ADDITION, SUBTRACTION, ADDITION_LOGGED, SUBTRACTION_LOGGED;
    }
}
