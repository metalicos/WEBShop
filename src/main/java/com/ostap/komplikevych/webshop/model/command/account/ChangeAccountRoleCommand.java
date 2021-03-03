package com.ostap.komplikevych.webshop.model.command.account;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.Role;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeAccountRoleCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Returns address to go or redirect to place (return Const.REDIRECT+{placeToRedirect};).
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Const.logger.trace("Account role change starts");
        String roleIdStr = request.getParameter("roleId");
        String accountIdStr = request.getParameter("accountId");
        String errorMessage;

        errorMessage = validate(roleIdStr, accountIdStr);
        if (errorMessage != null) {
            Const.logger.info(errorMessage);
            return Const.PAGE_USERS;
        }

        int accId = Integer.parseInt(accountIdStr);
        int roleId = Integer.parseInt(roleIdStr);
        AccountDao dao = new AccountDao();
        Account account = dao.readAccountByAccountId(accId);
        account.setRoleId(Role.getRole(roleId).getId());
        dao.updateAccount(account);
        Const.logger.trace("Account role change ends");
        return Const.REDIRECT + "controller?command=open-users-page";
    }

    private String validate(String roleIdStr, String accountIdStr) {
        if (Validator.checkIfNullOrEmptyReturnTrue(roleIdStr, accountIdStr)) {
            Const.logger.trace("Fields are null or empty");
            return "Fields are null or empty";
        }
        return null;
    }
}
