package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.DeliveryDao;
import com.ostap.komplikevych.webshop.entity.Delivery;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChooseDeliveryCommand implements Command {
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
        String deliveryId = request.getParameter("deliveryId");
        if (!Validator.checkIfNullOrEmptyReturnTrue(deliveryId) && deliveryId.matches(Validator.UNSIGNED_INTEGER)) {
            DeliveryDao deliveryDao = new DeliveryDao();
            Delivery delivery = deliveryDao.readDeliveryByDeliveryId(Integer.parseInt(deliveryId));
            session.setAttribute(SessionAttribute.CHOSEN_DELIVERY, delivery);
        }
        return Const.PAGE_MAKE_ORDER;
    }
}
