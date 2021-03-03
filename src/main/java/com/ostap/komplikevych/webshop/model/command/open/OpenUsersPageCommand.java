package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.DetailedAccount;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenUsersPageCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Returns address to go or redirect to place (return Const.REDIRECT+{placeToRedirect};).
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AccountDao accountDao = new AccountDao();
        List<Account> accounts = accountDao.readAllAccounts();
        List<DetailedAccount> detailedAccounts = new ArrayList<>();
        for (Account a:accounts){
            detailedAccounts.add(new DetailedAccount(a.getId()));
        }
        request.getSession().setAttribute(SessionAttribute.ACCOUNTS,detailedAccounts);
        return Const.PAGE_USERS;
    }
}
