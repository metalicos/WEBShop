package com.ostap.komplikevych.webshop.controller;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.localization.Language;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.command.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@MultipartConfig
public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        run(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        run(request, response);
    }

    private void run(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        Const.logger.debug("Controller starts");
        Locale locale = request.getLocale();
        HttpSession session = request.getSession();

        Const.logger.trace("Current locale language is (" + locale.getLanguage() + ")");

        String language = (String) session.getAttribute("language");
        if (language == null) {
            language = Language.getLang(locale.getLanguage()).getName();
            Const.logger.trace("Language saved as (" + language + ")");
            request.setAttribute(SessionAttribute.LANGUAGE,language);
            session.setAttribute(SessionAttribute.LANGUAGE,language);
        }else {
            Const.logger.trace("Language was found in Session = (" + language + ")");
        }

        String query = request.getQueryString();
        Const.logger.error("query = "+query);
        String commandName = request.getParameter("command");
        Const.logger.error("command --> " + commandName);

        Command command = CommandContainer.get(commandName);
        Const.logger.error("Java Class That Run This Command --> " + command);

        String forward = command.execute(request, response);
        Const.logger.error("Forward address to --> " + forward);


        if (forward != null) {
            Const.logger.debug("Controller finished, now go to forward address --> " + forward);
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }
}
