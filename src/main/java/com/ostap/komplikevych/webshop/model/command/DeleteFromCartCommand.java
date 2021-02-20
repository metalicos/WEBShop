package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.localization.Language;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteFromCartCommand extends Command {

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
        List<DetailedProduct> userShoppingCart = (List<DetailedProduct>) session.getAttribute("userShoppingCart");

        Const.logger.debug("Id DELETE from CART command");
        Const.logger.debug("Printing User Shopping Cart");
        Const.logger.debug(userShoppingCart);

        if (userShoppingCart == null) {
            userShoppingCart = new ArrayList<>();
        }

        String id = request.getParameter("productId");
        String language = (String) session.getAttribute("language");

        if(id!=null && id.matches("[0-9]")) {
            int productId = Integer.parseInt(id);
            DetailedProduct detailedProduct = new DetailedProduct(productId, Language.getLang(language));
            userShoppingCart.remove(detailedProduct);
        }else{
            Const.logger.error("Id of product = null OR didn`t match [0-9]");
        }
        session.setAttribute("userShoppingCart",userShoppingCart);
        session.setAttribute("productsInCart",userShoppingCart.size());

        return "/controller?command=open-cart-page";
    }
}
