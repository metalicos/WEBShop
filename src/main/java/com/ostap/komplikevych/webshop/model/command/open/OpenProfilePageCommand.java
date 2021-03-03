package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.AccountDetailDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountDetail;
import com.ostap.komplikevych.webshop.entity.DetailedAccount;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

public class OpenProfilePageCommand implements Command {
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
        if (account != null) {
            DetailedAccount detailedAccount = new DetailedAccount(account.getId());
            Const.logger.trace(detailedAccount);
            session.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);
            request.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);
            Const.logger.info("Update finished redirect to page Profile ");
        }
        return Const.PAGE_PROFILE;
    }
}
