package com.ostap.komplikevych.webshop.model.command.order;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.AccountOrderDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountOrder;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OpenUserOrdersPageCommand implements Command {
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
        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);
        AccountOrderDao accountOrderDao = new AccountOrderDao();

        List<AccountOrder> orders = accountOrderDao.readAllAccountOrders();
        session.setAttribute(SessionAttribute.ALL_USERS_ORDERS, orders);

        return Const.PAGE_USER_ORDERS;
    }
}
