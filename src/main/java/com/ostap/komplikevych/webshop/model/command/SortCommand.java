package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.model.sort.SortType;
import com.ostap.komplikevych.webshop.model.sort.Sorter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SortCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String sortType = request.getParameter("sort");
        List<DetailedProduct> detailedProducts =
                (List<DetailedProduct>) request.getSession().getAttribute("detailedProducts");

        if (detailedProducts != null) {
            if (SortType.NAME_A_Z.getType().equals(sortType)) {
                Sorter.setSortDetailedProductByNameAZ(detailedProducts);
                request.setAttribute("checkedR1", "checked");
                request.setAttribute("checkedR2", "");
                request.setAttribute("checkedR3", "");
                request.setAttribute("checkedR4", "");
                request.setAttribute("checkedR5", "");
            } else if (SortType.NAME_Z_A.getType().equals(sortType)) {
                Sorter.setSortDetailedProductByNameZA(detailedProducts);
                request.setAttribute("checkedR1", "");
                request.setAttribute("checkedR2", "checked");
                request.setAttribute("checkedR3", "");
                request.setAttribute("checkedR4", "");
                request.setAttribute("checkedR5", "");
            } else if (SortType.PRICE_UP.getType().equals(sortType)) {
                Sorter.setSortDetailedProductByPriceFromCheapToExpensive(detailedProducts);
                request.setAttribute("checkedR1", "");
                request.setAttribute("checkedR2", "");
                request.setAttribute("checkedR3", "checked");
                request.setAttribute("checkedR4", "");
                request.setAttribute("checkedR5", "");
            } else if (SortType.PRICE_DOWN.getType().equals(sortType)) {
                Sorter.setSortDetailedProductByPriceFromExpensiveToCheap(detailedProducts);
                request.setAttribute("checkedR1", "");
                request.setAttribute("checkedR2", "");
                request.setAttribute("checkedR3", "");
                request.setAttribute("checkedR4", "checked");
                request.setAttribute("checkedR5", "");
            } else if (SortType.DATE_NEW.getType().equals(sortType)) {
                Sorter.sortDetailedProductByCreateDateNew(detailedProducts);
                Sorter.setSortDetailedProductByPriceFromExpensiveToCheap(detailedProducts);
                request.setAttribute("checkedR1", "");
                request.setAttribute("checkedR2", "");
                request.setAttribute("checkedR3", "");
                request.setAttribute("checkedR4", "");
                request.setAttribute("checkedR5", "checked");
            } else {
                Sorter.sortDetailedProductByCreateDateOld(detailedProducts);
                request.setAttribute("checkedR1", "");
                request.setAttribute("checkedR2", "");
                request.setAttribute("checkedR3", "");
                request.setAttribute("checkedR4", "");
                request.setAttribute("checkedR5", "checked");
            }
        }

        return Const.PAGE_HOME;
    }
}
