package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.localization.Language;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class SetLanguageCommand implements Command {
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
        String language = request.getParameter("language");
        Const.logger.debug("Language --> " + language);
        if (language == null) {
            language = Language.EN.getName();
        }

        //Locale locale1 = new Locale()
        session.setAttribute("language", language);
        Const.logger.debug("Language added to session");

        String filterQuery = (String) session.getAttribute("saveFilterQuery");
        if (Validator.checkIfNullOrEmptyReturnTrue(filterQuery)) {
            return "/";
        }
        return "/" + filterQuery;
    }
}
