package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.localization.Language;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetLanguageCommand extends Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String language = request.getParameter("language");
        Const.logger.debug("Language --> "+language);
        if (language == null) {
            language = Language.EN.getName();
        }
        request.setAttribute("language",language);
        Const.logger.debug("Language added to request attribute");

        request.getSession().setAttribute("language",language);
        Const.logger.debug("Language added to session");

        return Const.PAGE_PATH_HOME;
    }
}
