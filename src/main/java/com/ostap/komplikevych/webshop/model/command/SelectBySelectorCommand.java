package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.model.sort.Selector;
import com.ostap.komplikevych.webshop.model.sort.SelectorType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SelectBySelectorCommand extends Command {
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
        //command=select-by-selector&selector=category&value=all
        List<DetailedProduct> detailedProducts = CommonOperations.setupHomePageAndGetAllProducts(request);
        SelectorType selector = SelectorType.getSelectorType(request.getParameter("selector"));
        String value = request.getParameter("value");
        String fromPrice = request.getParameter("from-price");
        String toPrice = request.getParameter("to-price");
        String saveFilterQuery = "";

        if (selector == SelectorType.CATEGORY) {
            if (!Validator.checkIfNullOrEmptyReturnTrue(value)) {
                detailedProducts = Selector.selectDetailedProductsByCategoryName(detailedProducts, value);
                request.setAttribute("categoryLabel", value);
                saveFilterQuery = "controller?"+request.getQueryString();
            }
        } else if (selector == SelectorType.COLOR) {
            if (!Validator.checkIfNullOrEmptyReturnTrue(value)) {
                detailedProducts = Selector.selectDetailedProductsByColor(detailedProducts, value);
                request.setAttribute("categoryLabel", "Фільтровані за кольором: "+value);
                saveFilterQuery = "controller?"+request.getQueryString();
            }
        } else if (selector == SelectorType.SIZE) {
            if (!Validator.checkIfNullOrEmptyReturnTrue(value)) {
                detailedProducts = Selector.selectDetailedProductsThatContainStringValue(detailedProducts, value);
                request.setAttribute("categoryLabel", "Фільтровані за розміром: "+value);
                saveFilterQuery = "controller?"+request.getQueryString();
            }
        } else if (selector == SelectorType.PRICE) {
            if (!Validator.checkIfNullOrEmptyReturnTrue(fromPrice, toPrice)
                    && fromPrice.matches("[0-9]") && toPrice.matches("[0-9]")) {

               detailedProducts = Selector.selectDetailedProductsThatMatchPrice(detailedProducts,
                        Integer.parseInt(fromPrice), Integer.parseInt(toPrice));
                request.setAttribute("categoryLabel", "Фільтровані за ціною від "+fromPrice+" до "+toPrice);
                saveFilterQuery = "controller?"+request.getQueryString();
            }
        } else if (selector == SelectorType.SEARCH_BAR) {
            if (value != null) {
                detailedProducts = Selector.selectDetailedProductsThatContainStringValue(detailedProducts, value);
                request.setAttribute("categoryLabel", "Пошук за: "+value);
                saveFilterQuery = "controller?"+request.getQueryString();
            }
        }
        request.setAttribute("detailedProducts", detailedProducts);
        session.setAttribute("saveFilterQuery",saveFilterQuery);
        return Const.PAGE_HOME;
    }
}
