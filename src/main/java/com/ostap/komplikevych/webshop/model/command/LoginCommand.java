package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.dao.ShoppingCartDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.ProductInCart;
import com.ostap.komplikevych.webshop.entity.Role;
import com.ostap.komplikevych.webshop.entity.ShoppingCart;
import com.ostap.komplikevych.webshop.security.MyChipher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        Const.logger.trace("Request parameter: email --> " + email);

        String password = request.getParameter("password");

        String errorMessage = null;
        String forward = Const.PAGE_PATH_LOGIN;

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        }
        AccountDao accountDao = new AccountDao();
        Account account = accountDao.read(email);

        Const.logger.trace("Found in DB: account --> " + account);

        MyChipher chipher = new MyChipher();
        if (account == null || !password.equals(chipher.decrypt(account.getPassword()))) {
            errorMessage = "Cannot find account with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            Const.logger.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role accountRole = Role.getRole(account);
            Const.logger.trace("accountRole --> " + accountRole);
            forward = Const.PAGE_PATH_HOME;
            ShoppingCart cart = (ShoppingCart) session.getAttribute(SessionAttribute.SHOPPING_CART);
            if(cart!=null){
                ShoppingCartDao cartDao = new ShoppingCartDao();
                ShoppingCart accountLoggedShoppingCart = cartDao.read(account.getShoppingCartId());
                List<ProductInCart> productsInLoggedCart = accountLoggedShoppingCart.getProducts();
                productsInLoggedCart.addAll(cart.getProducts());
                accountLoggedShoppingCart.setProducts(productsInLoggedCart);
                cartDao.update(accountLoggedShoppingCart);
            }

            session.setAttribute(SessionAttribute.ACCOUNT, account);
            Const.logger.trace("Set the session attribute: " + SessionAttribute.ACCOUNT + " --> " + account);

            session.setAttribute(SessionAttribute.ROLE, accountRole);
            Const.logger.trace("Set the session attribute: " + SessionAttribute.ROLE + " --> " + accountRole);

            Const.logger.debug("Account " + account + " logged as " + accountRole.name().toLowerCase());

        }
        Const.logger.debug("Command finished");
        return forward;
    }
}