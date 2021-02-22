package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.localization.Language;

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
        Map<DetailedProduct, Integer> userShoopingCart =
                (Map<DetailedProduct, Integer>) session.getAttribute("userShoppingCart");

        Const.logger.debug("Id DELETE from CART command");
        Const.logger.debug("Printing User Shopping Cart");
        Const.logger.debug(userShoopingCart);

        String id = request.getParameter("productId");
        String language = (String) session.getAttribute("language");



        if (id != null && id.matches("[0-9]")) {
            int productId = Integer.parseInt(id);
            DetailedProduct detailedProduct = new DetailedProduct(productId, Language.getLang(language));
            Const.logger.error(detailedProduct);

            if (userShoopingCart == null) {
                userShoopingCart = new HashMap<>();
            }

            Integer amountProduct = userShoopingCart.get(detailedProduct);

            if (amountProduct != null) {
                userShoopingCart.put(detailedProduct, --amountProduct);
                if(amountProduct<=0){
                    userShoopingCart.remove(detailedProduct);
                }
            }
        } else {
            Const.logger.error("Id of product = null OR didn`t match [0-9]");
        }

        List<DetailedProduct> detailedProducts = userShoopingCart.keySet().stream().collect(Collectors.toList());
        DetailedProduct product;
        BigDecimal totalProductSum = new BigDecimal(0);
        for (int i = 0; i < detailedProducts.size(); i++) {
            product = detailedProducts.get(i);
            Const.logger.trace(product);

            BigDecimal amount = BigDecimal.valueOf(userShoopingCart.get(product));
            Const.logger.trace(amount);

            BigDecimal semiSum = product.getPrice().multiply(amount);
            totalProductSum = totalProductSum.add(semiSum);
        }

        session.setAttribute("userShoppingCart", userShoopingCart);
        session.setAttribute("productsInCart", userShoopingCart.size());
        session.setAttribute("totalProductSum",totalProductSum.doubleValue());


        return "controller?command=open-cart-page";
    }
}
