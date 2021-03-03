package com.ostap.komplikevych.webshop.model.command.account;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountStatus;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeAccountStatusCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Returns address to go or redirect to place (return Const.REDIRECT+{placeToRedirect};).
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String accountId = request.getParameter("accountId");
        String statusId = request.getParameter("statusId");
        String errorMessage;

        errorMessage = validate(accountId, statusId);
        if (errorMessage != null) {
            Const.logger.info(errorMessage);
            return Const.PAGE_USERS;
        }
        int accId = Integer.parseInt(accountId);
        int statId = Integer.parseInt(statusId);
        AccountDao dao = new AccountDao();
        Account account = dao.readAccountByAccountId(accId);
        account.setAccountStatusId(AccountStatus.getAccountStatus(statId).getId());
        dao.updateAccount(account);
        return Const.REDIRECT + "controller?command=open-users-page";
    }

    private String validate(String accountId, String statusId) {
        if (Validator.checkIfNullOrEmptyReturnTrue(accountId, statusId)) {
            Const.logger.trace("Fields are null or empty");
            return "Fields are null or empty";
        }
        return null;
    }
}
