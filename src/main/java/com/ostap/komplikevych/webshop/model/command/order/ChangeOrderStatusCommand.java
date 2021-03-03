package com.ostap.komplikevych.webshop.model.command.order;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.dao.AccountOrderDao;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.entity.AccountOrder;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.entity.ProductInOrder;
import com.ostap.komplikevych.webshop.entity.Status;
import com.ostap.komplikevych.webshop.model.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeOrderStatusCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Returns address to go or redirect to place (return Const.REDIRECT+{placeToRedirect};).
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String orderIdString = request.getParameter("orderId");
        String statusIdString = request.getParameter("statusId");
        Const.logger.info("Change Order " + orderIdString + " Status starts...");
        String errorMessage = validate(orderIdString, statusIdString);
        if (errorMessage != null) {
            Const.logger.trace(errorMessage);
            return Const.PAGE_USER_ORDERS;
        }

        int statusId = Integer.parseInt(statusIdString);
        int orderId = Integer.parseInt(orderIdString);
        errorMessage = changeStatus(statusId, orderId);
        if (errorMessage != null) {
            Const.logger.trace(errorMessage);
            return Const.PAGE_USER_ORDERS;
        }
        Const.logger.info("Change Order " + orderIdString + " Status ends...");
        return Const.REDIRECT + "/controller?command=open-user-orders-page";
    }

    /**
     * Methode changes status if status can be changed.
     *
     * @param statusId status ID
     * @return errorMessage if failure operation.
     */
    private String changeStatus(int statusId, int orderId) {
        AccountOrderDao orderDao = new AccountOrderDao();
        AccountOrder order = orderDao.readAccountOrderByAccountOrderId(orderId);
        List<ProductInOrder> products = order.getProducts();
        ProductDao productDao = new ProductDao();
        if (Status.PAID.getId() == statusId && order.getStatusId() == Status.REGISTERED.getId()) {
            Const.logger.info("Status PAID");
            for (ProductInOrder p : products) {
                Product currentProduct = productDao.readProductByProductId(p.getProduct().getId());
                int orderedAmountNow = currentProduct.getOrderedAmount();
                int realAmountNow = currentProduct.getAmount();
                currentProduct.setOrderedAmount(orderedAmountNow - p.getProductAmount());
                currentProduct.setAmount(realAmountNow - p.getProductAmount());
                productDao.updateProduct(currentProduct);
                Const.logger.info("Updating product "+currentProduct.getName());
                order.setStatusId(Status.PAID.getId());
                orderDao.updateAccountOrder(order);
            }
        }
        if (Status.CANCELED.getId() == statusId) {
            Const.logger.info("Status CANCELED");
            for (ProductInOrder p : products) {
                Product currentProduct = productDao.readProductByProductId(p.getProduct().getId());
                int orderedAmountNow = currentProduct.getOrderedAmount();
                currentProduct.setOrderedAmount(orderedAmountNow - p.getProductAmount());
                productDao.updateProduct(currentProduct);
                Const.logger.info("Updating product "+currentProduct.getName());
                order.setStatusId(Status.CANCELED.getId());
                orderDao.updateAccountOrder(order);
            }
        }
        return null;
    }

    /**
     * Checks if input parameters are correct.
     *
     * @param orderIdString  order ID
     * @param statusIdString status ID
     * @return errorMessage if failure operation.
     */
    private String validate(String orderIdString, String statusIdString) {
        if (Validator.checkIfNullOrEmptyReturnTrue(orderIdString)) {
            Const.logger.info("Order was not selected.");
            return "Order was not selected.";
        }
        if (!orderIdString.matches(Validator.UNSIGNED_INTEGER)) {
            Const.logger.info("Order id not number.");
            return "Order id not number.";
        }
        if (Validator.checkIfNullOrEmptyReturnTrue(statusIdString)) {
            Const.logger.info("Status to change was not selected.");
            return "Status to change was not selected.";
        }
        if (!statusIdString.matches(Validator.UNSIGNED_INTEGER)) {
            Const.logger.info("Status id not number.");
            return "Status id not number.";
        }
        return null;
    }

}
