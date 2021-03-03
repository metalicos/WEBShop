package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.CategoryDao;
import com.ostap.komplikevych.webshop.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCategoryCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Const.logger.debug("Create Category Command Starts");
        String categoryNameUa = request.getParameter("сNameUa");
        String categoryNameEn = request.getParameter("сNameEn");
        String categoryAboutUa = request.getParameter("cAboutUa");
        String categoryAboutEn = request.getParameter("cAboutEn");
        String errorMessage;

        if (Validator.checkIfNullOrEmptyReturnTrue(categoryNameEn, categoryNameUa, categoryAboutEn, categoryAboutUa)) {
            errorMessage = "Input mistakes, input fields are null or empty";
            Const.logger.trace("Request Attributes Are Null Or Empty");
            request.setAttribute("failErrorMessage", errorMessage);
            Const.logger.debug("Create Category Command Ends");
            return Const.PAGE_CREATE_CATEGORY;
        }

        if (Validator.checkIfContainsRestrictedWords(categoryNameEn, categoryNameUa, categoryAboutEn, categoryAboutUa)) {
            errorMessage = "Warning! You are using restricted words, so be careful or you will be banned by the main administrator.";
            request.setAttribute("failErrorMessage", errorMessage);
            Const.logger.debug("Create Category Command Ends");
            return Const.PAGE_CREATE_CATEGORY;
        }

        Const.logger.trace("Request Attributes Not Null And Not Empty");
        Const.logger.trace("categoryNameUa=" + categoryNameUa);
        Const.logger.trace("categoryNameEn=" + categoryNameEn);
        Const.logger.trace("categoryAboutUa=" + categoryAboutUa);
        Const.logger.trace("categoryAboutEn=" + categoryAboutEn);
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category();
        category.setNameUa(categoryNameUa);
        category.setNameEn(categoryNameEn);
        category.setDescriptionUa(categoryAboutUa);
        category.setDescriptionEn(categoryAboutEn);
        Const.logger.trace("Request Attributes are inputed into Entity Category");
        int categoryId = categoryDao.createCategory(category);
        Const.logger.trace("Category ID = " + categoryId);
        if (categoryId == -1) {
            errorMessage = "Category creation failed, please check all field and try again";
            Const.logger.error(errorMessage);
            request.setAttribute("failErrorMessage", "Category creation failed, please check all fields and try again.");
            Const.logger.debug("Create Category Command Ends");
            return Const.PAGE_CREATE_CATEGORY;
        }

        Const.logger.debug("Create Category Command Ends");
        return Const.REDIRECT + "controller?command=open-home-page";
    }
}