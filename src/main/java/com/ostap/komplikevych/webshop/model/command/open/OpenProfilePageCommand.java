package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.entity.Account;
import com.ostap.komplikevych.webshop.entity.DetailedAccount;
import com.ostap.komplikevych.webshop.localization.Language;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.command.CommonOperations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class OpenProfilePageCommand extends Command {
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
        if(account!=null){
            DetailedAccount detailedAccount = new DetailedAccount(account.getId());
            session.setAttribute(SessionAttribute.DETAILED_ACCOUNT,detailedAccount);
            request.setAttribute(SessionAttribute.DETAILED_ACCOUNT,detailedAccount);
        }
        return Const.PAGE_PATH_PROFILE;
    }
}
