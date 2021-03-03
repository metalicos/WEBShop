package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.model.command.account.*;
import com.ostap.komplikevych.webshop.model.command.cart.AddToCartCommand;
import com.ostap.komplikevych.webshop.model.command.cart.DeleteFromCartCommand;
import com.ostap.komplikevych.webshop.model.command.cart.OpenShoppingCartPageCommand;
import com.ostap.komplikevych.webshop.model.command.open.*;
import com.ostap.komplikevych.webshop.model.command.order.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("noCommand", new NoCommand());

        commands.put("open-login-page", new OpenLoginPageCommand());
        commands.put("open-register-page", new OpenRegistrationPageCommand());
        commands.put("open-orders-page", new OpenOrdersPageCommand());
        commands.put("open-profile-page", new OpenProfilePageCommand());
        commands.put("open-cart-page", new OpenShoppingCartPageCommand());
        commands.put("open-home-page", new OpenHomePageCommand());
        commands.put("open-product-info", new OpenProductInfoCommand());
        commands.put("open-create-product", new OpenCreateProductWindowCommand());
        commands.put("open-create-category", new OpenCreateCategoryCommand());
        commands.put("open-users-page", new OpenUsersPageCommand());
        commands.put("open-user-orders-page", new OpenUserOrdersPageCommand());
        commands.put("change-account-photo", new ChangeAccountPhotoCommand());
        commands.put("choose-delivery", new ChooseDeliveryCommand());
        commands.put("change-order-status", new ChangeOrderStatusCommand());
        commands.put("change-account-status",new ChangeAccountStatusCommand());
        commands.put("change-account-role",new ChangeAccountRoleCommand());

        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("register", new RegisterCommand());
        commands.put("make-order", new MakeOrderCommand());
        commands.put("open-make-order-page", new OpenMakeOrderPageCommand());

        commands.put("create-product", new CreateProductCommand());
        commands.put("create-category", new CreateCategoryCommand());
        commands.put("change-account-details", new ChangeAccountDetailsCommand());
        commands.put("change-account-password", new ChangeAccountPasswordCommand());

        commands.put("set-language", new SetLanguageCommand());
        commands.put("sort-products", new SortCommand());
        commands.put("add-to-cart", new AddToCartCommand());
        commands.put("delete-from-cart", new DeleteFromCartCommand());
        commands.put("select-by-selector", new SelectBySelectorCommand());

        Const.logger.debug("Command container (size=" + commands.size() + ") was successfully initialized");
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            Const.logger.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}