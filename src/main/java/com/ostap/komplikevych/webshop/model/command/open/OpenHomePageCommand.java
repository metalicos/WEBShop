package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.localization.Language;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.sort.Selector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.readAllProductsInShop();
        Const.logger.debug("STEP1 Getting Products from DB");
        for (Product p : products) {
            Const.logger.trace(p);
        }

        List<DetailedProduct> detailedProducts = new ArrayList<>();

        Const.logger.debug("STEP3 reading language");

        String lang = (String) request.getSession().getAttribute("language");
        Language language =  Language.getLang(lang);

        Const.logger.trace(language);
        Const.logger.debug("STEP4 creating detailed products DB");
        for (Product p : products) {
            detailedProducts.add(new DetailedProduct(p.getId(), language));
        }
        for (DetailedProduct p : detailedProducts) {
            Const.logger.trace(p);
        }

        List<String> categoryNames = Selector.selectCategoryNamesFromDetailedProducts(detailedProducts);
        Map<String,Integer> categoryNamesWithProductAmountMap = Selector.selectProductsAmountInCategory(detailedProducts,categoryNames);




        Const.logger.debug("STEP5 setting values to request and session");
        request.getSession().setAttribute("detailedProducts", detailedProducts);
        request.setAttribute("detailedProducts", detailedProducts);

        request.setAttribute("categoryNamesWithProductAmountMap",categoryNamesWithProductAmountMap);
        request.setAttribute("productsFound", detailedProducts.size());
        request.setAttribute("categoryLabel", "Наші товари");

        request.setAttribute("checkedR1", "checked");

        return Const.PAGE_PATH_HOME;
    }

}
