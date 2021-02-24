package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.dao.CategoryDao;
import com.ostap.komplikevych.webshop.entity.Category;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OpenCreateProductWindowCommand implements Command {

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Const.logger.debug("Setup create product page");
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.readAllCategories();
        request.setAttribute("categories",categories);
        Const.logger.debug("Setup finished");
        return Const.PAGE_CREATE_PRODUCT;
    }
}
