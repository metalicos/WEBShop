package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.command.CommonOperations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenHomePageCommand extends Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommonOperations.setupHomePageAndGetAllProducts(request);
        request.setAttribute("categoryLabel", "Наші товари");
        return Const.PAGE_PATH_HOME;
    }

}
