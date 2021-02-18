package com.ostap.komplikevych.webshop.controller;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.command.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        Locale locale = request.getLocale();
        Const.logger.debug("Current language is ("+locale.getLanguage() + ")");
        String language = (String) request.getAttribute("language");
        if(language==null){
            request.setAttribute("language",locale.getLanguage());
            Const.logger.debug("Language setted as ("+locale.getLanguage() + ")");
        }

        String commandName = request.getParameter("command");
        Const.logger.debug("Request parameter: command --> " + commandName);

        Command command = CommandContainer.get(commandName);
        Const.logger.debug("Obtained command --> " + command);

        String forward = command.execute(request, response);
        Const.logger.debug("Forward address --> " + forward);


        if (forward != null) {
            Const.logger.debug("Controller finished, now go to forward address --> " + forward);
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }
}
