package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.localization.Language;
import com.ostap.komplikevych.webshop.model.sort.Selector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonOperations {
    /**
     * Recieve request and make operations with DB to get filters,product amount and products as well.
     * If you start this methode you will totally setup Home page. To get filtered products you should.
     * returned List<DetailedProduct> put into selector, and then put into HttpServletRequest request
     * so write request.setAttribute("detailedProducts", detailedProducts);
     * @param request
     * @return
     */
    public static List<DetailedProduct> setupHomePageAndGetAllProducts(HttpServletRequest request){



        HttpSession session = request.getSession();
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.readAllProductsInShop();

        Const.logger.debug("Setting Up Home Page");
        //products.forEach(Const.logger::trace);
        List<DetailedProduct> detailedProducts = new ArrayList<>();


        String language = (String) session.getAttribute("language");
        Const.logger.trace("Language="+language);


        for (Product p : products) {
            detailedProducts.add(new DetailedProduct(p.getId(), Language.getLang(language)));
        }
        //detailedProducts.forEach(Const.logger::trace);


        Map<String,Integer> categoryNamesWithProductAmountMap =
                Selector.selectProductsAmountWithCategoryNames(detailedProducts);
        Map<String,Integer> colorNamesWithProductAmountMap =
                Selector.selectProductsAmountWithColorNames(detailedProducts);
        Map<String,Integer> sizeNamesWithProductAmountMap =
                Selector.selectProductsAmountWithSizeNames(detailedProducts);

        session.setAttribute("language", language);
        request.setAttribute("language", language);

        session.setAttribute("detailedProducts", detailedProducts);
        request.setAttribute("detailedProducts", detailedProducts);

        request.setAttribute("categoryNamesWithProductAmountMap",categoryNamesWithProductAmountMap);
        session.setAttribute("categoryNamesWithProductAmountMap", categoryNamesWithProductAmountMap);
        request.setAttribute("colorNamesWithProductAmountMap",colorNamesWithProductAmountMap);
        session.setAttribute("colorNamesWithProductAmountMap", colorNamesWithProductAmountMap);
        request.setAttribute("sizeNamesWithProductAmountMap",sizeNamesWithProductAmountMap);
        session.setAttribute("sizeNamesWithProductAmountMap", sizeNamesWithProductAmountMap);

        request.setAttribute("productsFound", detailedProducts.size());
        request.setAttribute("checkedR1", "checked");
        Const.logger.debug("Setting Up Finished");
        return detailedProducts;
    }
}
