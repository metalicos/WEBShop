package com.ostap.komplikevych.webshop.model.command.open;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.localization.Language;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OpenProductInfoCommand extends Command {

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Const.logger.debug("Command Show Product Info Starts");
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("language");
        Const.logger.trace("Language="+language);
        String productId = request.getParameter("productId");
        Const.logger.trace("ProductID="+productId);

        if(!Validator.checkIfNullOrEmptyReturnTrue(language,productId)){
            Const.logger.trace("ProductID not nul and not empty");
            if(productId.matches("[0-9]")){
                Const.logger.trace("ProductID is Numeric");
                DetailedProduct detailedProduct =
                        new DetailedProduct(Integer.parseInt(productId), Language.getLang(language));
                Const.logger.trace("DetailedProduct to show = "+detailedProduct);
                Const.logger.trace("Put product to session and request");
                session.setAttribute(SessionAttribute.PRODUCT_TO_SHOW,detailedProduct);
                request.setAttribute(SessionAttribute.PRODUCT_TO_SHOW,detailedProduct);
                Const.logger.debug("Command Show Product Info End");
                return Const.PAGE_PRODUCT_INFO;
            }
        }
        Const.logger.debug("Command Show Product Info End");
        return Const.PAGE_HOME;
    }
}
