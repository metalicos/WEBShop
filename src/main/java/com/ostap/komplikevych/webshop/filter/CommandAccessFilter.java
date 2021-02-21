package com.ostap.komplikevych.webshop.filter;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.constant.Validator;
import com.ostap.komplikevych.webshop.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CommandAccessFilter implements Filter {
    private final Map<Role, List<String>> logged = new HashMap<>();
    private List<String> unlogged = new ArrayList<String>();
    private List<String> outOfControl = new ArrayList<String>();

    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Const.logger.debug("Filter starts");

        if (accessAllowed(request)) {
            Const.logger.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher(Const.PAGE_ERROR_PERMITION_DENIED)
                    .forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String commandName = request.getParameter("command");

        if (Validator.checkIfNullOrEmptyReturnTrue(commandName)) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession();

        Role accountRole = (Role) session.getAttribute("accountRole");
        if (accountRole == null) {
            return unlogged.contains(commandName);
        }

        return logged.get(accountRole).contains(commandName);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        Const.logger.debug("Filter initialization starts");
        logged.put(Role.ADMIN, asList(filterConfig.getInitParameter("logged-admin")));
        logged.put(Role.USER, asList(filterConfig.getInitParameter("logged-user")));
        unlogged = asList(filterConfig.getInitParameter("unlogged"));
        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        Const.logger.trace("Logged --> " + logged);
        Const.logger.trace("Unlogged --> " + unlogged);
        Const.logger.trace("Out of controll --> " + outOfControl);
        Const.logger.debug("Filter initialization finished");
    }


    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

}