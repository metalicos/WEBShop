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

public class OpenOrdersPageCommand implements Command {
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
        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);
        AccountOrderDao accountOrderDao = new AccountOrderDao();

        List<AccountOrder> orders = accountOrderDao.readAllAccountOrdersByAccountId(account.getId());
        session.setAttribute(SessionAttribute.USER_ORDERS, orders);

        return Const.PAGE_ORDERS;
    }
}
