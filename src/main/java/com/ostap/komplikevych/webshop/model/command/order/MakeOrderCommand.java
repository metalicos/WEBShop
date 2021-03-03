package com.ostap.komplikevych.webshop.model.command.order;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.SessionAttribute;
import com.ostap.komplikevych.webshop.dao.AccountOrderDao;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.dao.ShoppingCartDao;
import com.ostap.komplikevych.webshop.entity.*;
import com.ostap.komplikevych.webshop.model.command.Command;
import com.ostap.komplikevych.webshop.model.command.cart.ShoppingCartCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeOrderCommand implements Command {
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Returns address to go or redirect to place (return Const.REDIRECT+{placeToRedirect};).
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Delivery chosenDelivery = (Delivery) session.getAttribute(SessionAttribute.CHOSEN_DELIVERY);
        Account account = (Account) session.getAttribute(SessionAttribute.ACCOUNT);
        Map<DetailedProduct, Integer> userShoppingCart = ShoppingCartCommand.createShoppingCartMapFromDB(request);
        if (chosenDelivery != null && account != null) {
            AccountOrder accountOrder = new AccountOrder();
            accountOrder.setDeliveryId(chosenDelivery.getId());
            BigDecimal totalProductPrice = ShoppingCartCommand.getTotalPriceOfUserShoppingCart(userShoppingCart);
            accountOrder.setTotalOrderPrice(totalProductPrice.add(chosenDelivery.getDeliveryPrice()));
            List<ProductInOrder> products = new ArrayList<>();
            Product product;
            ProductInOrder productInOrder;
            ProductDao productDao = new ProductDao();
            for (DetailedProduct p : userShoppingCart.keySet()) {
                product = productDao.readProductByProductId(p.getId());
                int amount = userShoppingCart.get(p);
                productInOrder = new ProductInOrder(product, product.getPrice(), amount,
                        LocalDateTime.now(), LocalDateTime.now());
                products.add(productInOrder);
            }
            accountOrder.setAccountId(account.getId());
            accountOrder.setStatusId(Status.REGISTERED.getId());
            AccountOrderDao accountOrderDao = new AccountOrderDao();
            int orderId = accountOrderDao.createAccountOrder(accountOrder);

            for (ProductInOrder p : products) {
                AccountOrderDao.createProductInOrder(orderId, p);
                product = productDao.readProductByProductId(p.getProduct().getId());
                int nowOrdered = product.getOrderedAmount();
                product.setOrderedAmount(nowOrdered + p.getProductAmount());
                productDao.updateProduct(product);
            }

            ShoppingCartDao.deleteAllProductsInCart(account.getShoppingCartId());
            session.setAttribute(SessionAttribute.PRODUCTS_IN_CART, 0);
            session.setAttribute(SessionAttribute.TOTAL_PRODUCT_SUM, 0);
        }
        return Const.REDIRECT + "controller?command=open-orders-page";
    }
}
