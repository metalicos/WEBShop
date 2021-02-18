package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand extends Command {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        Const.logger.debug("Command " + this.getClass().getName().toUpperCase());

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        Const.logger.error("Set the request attribute: errorMessage --> " + errorMessage);

        return Const.PAGE_PATH_ERROR;
    }

}