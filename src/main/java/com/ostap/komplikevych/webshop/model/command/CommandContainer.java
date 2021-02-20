package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.model.command.open.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("open-login-page", new OpenLoginPageCommand());
        commands.put("open-register-page", new OpenRegistrationPageCommand());
        commands.put("open-orders-page", new OpenOrdersPageCommand());
        commands.put("open-profile-page", new OpenProfilePageCommand());
        commands.put("open-cart-page", new OpenShoppingCartPageCommand());
        commands.put("open-home-page",new OpenHomePageCommand());
        commands.put("set-language", new SetLanguageCommand());
        commands.put("sort-products",new SortCommand());
        commands.put("add-to-cart",new AddToCartCommand());
        commands.put("delete-from-cart",new DeleteFromCartCommand());
        commands.put("select-by-selector",new SelectBySelectorCommand());

        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("register", new RegisterCommand());


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