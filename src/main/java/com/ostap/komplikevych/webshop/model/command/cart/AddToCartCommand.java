package com.ostap.komplikevych.webshop.model.command.cart;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class AddToCartCommand extends ShoppingCartCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Map<DetailedProduct, Integer> userShoppingCart =
                (Map<DetailedProduct, Integer>) session.getAttribute("userShoppingCart");

        Const.logger.debug("ADD to CART command");
        Const.logger.debug("User Shopping Cart = " + userShoppingCart);

        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);

        if (account != null) {
            operation(request, account, Operation.ADDITION_LOGGED);
            ShoppingCartCommand.createShoppingCartMapFromDB(request);
            Const.logger.debug("User logged = " + userShoppingCart);
        } else {
            userShoppingCart = operation(request, userShoppingCart, Operation.ADDITION);
            BigDecimal totalProductSum = getTotalPriceOfUserShoppingCart(userShoppingCart);
            session.setAttribute("userShoppingCart", userShoppingCart);
            session.setAttribute("productsInCart", userShoppingCart.size());
            session.setAttribute("totalProductSum", totalProductSum.doubleValue());
        }

        return "/";
    }

}
