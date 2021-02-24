package com.ostap.komplikevych.webshop.model.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public interface Command extends Serializable {
    /**
     * Execution method for command.
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;
}