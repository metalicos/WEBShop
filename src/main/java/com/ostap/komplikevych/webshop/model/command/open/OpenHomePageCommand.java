package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.command.CommonOperations;
import com.ostap.komplikevych.webshop.model.command.cart.ShoppingCartCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class OpenHomePageCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommonOperations.setupHomePageAndGetAllProducts(request);
        request.setAttribute("categoryLabel", "Наші товари");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);
        if (account==null) {
            Map<DetailedProduct, Integer> userShoppingCart =
                    (Map<DetailedProduct, Integer>) session.getAttribute("userShoppingCart");
            if (userShoppingCart != null) {
                BigDecimal totalProductSum = ShoppingCartCommand.getTotalPriceOfUserShoppingCart(userShoppingCart);
                session.setAttribute("productsInCart", userShoppingCart.size());
                session.setAttribute("totalProductSum", totalProductSum.doubleValue());
            } else {
                session.setAttribute("productsInCart", 0);
                session.setAttribute("totalProductSum", 0);
            }
        }
        return Const.PAGE_HOME;
    }
}
