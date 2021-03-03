package com.ostap.komplikevych.webshop.model.command.order;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.DeliveryDao;
import com.ostap.komplikevych.webshop.entity.Delivery;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.localization.Language;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.command.cart.ShoppingCartCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OpenMakeOrderPageCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Returns address to go or redirect to place (return Const.REDIRECT+{placeToRedirect};).
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Map<DetailedProduct, Integer> userShoppingCart = ShoppingCartCommand.createShoppingCartMapFromDB(request);
        String language = (String) session.getAttribute(SessionAttribute.LANGUAGE);
        DetailedProduct availableProduct;
        String errorMessage;
        for (DetailedProduct p : userShoppingCart.keySet()) {
            availableProduct = new DetailedProduct(p.getId(), Language.getLang(language));
            if (availableProduct.getAvailableNumber() < userShoppingCart.get(p)) {
                errorMessage = "Sorry, we only have " + p.getAvailableNumber() + " pcs of " + p.getName();
                Const.logger.info(errorMessage);
                session.setAttribute("errorMessage", errorMessage);
                Const.logger.info(errorMessage);
                return Const.PAGE_SHOPPING_CART;
            }
        }

        DeliveryDao deliveryDao = new DeliveryDao();
        List<Delivery> deliveries = deliveryDao.readAllDeliveries();
        if(session.getAttribute(SessionAttribute.CHOSEN_DELIVERY)==null) {
            Delivery chosenDelivery = deliveries.get(0);
            session.setAttribute(SessionAttribute.CHOSEN_DELIVERY, chosenDelivery);
        }
        session.setAttribute(SessionAttribute.DELIVERIES, deliveries);

        return Const.PAGE_MAKE_ORDER;
    }
}
