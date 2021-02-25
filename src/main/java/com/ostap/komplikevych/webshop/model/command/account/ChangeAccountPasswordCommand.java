package com.ostap.komplikevych.webshop.model.command.account;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountDao;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.AccountDetail;
import com.ostap.komplikevych.webshop.entity.DetailedAccount;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.security.MyChipher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeAccountPasswordCommand implements Command {

    /**
     * Executes replacement of password.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Const.logger.info("Start change account details command");
        AccountDetail newDetails = new AccountDetail();
        HttpSession session = request.getSession();

        String oldPass = request.getParameter("old-password");
        String newPass = request.getParameter("new-password");
        String checkNewPass = request.getParameter("check-new-password");


        Const.logger.trace("newDetails = " + newDetails);

        String errorMessage;

        if (Validator.checkIfNullOrEmptyReturnTrue(oldPass, newPass, checkNewPass)) {
            errorMessage = "Password is empty";
            Const.logger.trace("errorMessage = " + errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            return Const.PAGE_PROFILE;
        }

        if (!Validator.checkIfMatchValidator(Validator.PASSWORD, newPass)) {
            errorMessage = "Password mismatch validator (Must be at least 1 character Uppercase, 1 character Lowercase, 1 digit (0-9), and min length 8)";
            Const.logger.trace("errorMessage = " + errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            return Const.PAGE_PROFILE;
        }

        if (!newPass.equals(checkNewPass)) {
            errorMessage = "New passwords are different";
            Const.logger.trace("errorMessage = " + errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            return Const.PAGE_PROFILE;
        }

        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);

        if (account != null) {
            AccountDao accountDao = new AccountDao();
            MyChipher chipher = new MyChipher();
            account.setPassword(chipher.encrypt(newPass));
            accountDao.updateAccount(account);

            DetailedAccount detailedAccount = new DetailedAccount(account.getId());
            Const.logger.trace(detailedAccount);
            session.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);
            request.setAttribute(SessionAttribute.DETAILED_ACCOUNT, detailedAccount);
            Const.logger.info("Update finished redirect to page Profile ");
        }

        return Const.REDIRECT + "controller?command=open-profile-page";
    }
}
