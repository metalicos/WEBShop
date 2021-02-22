package com.ostap.komplikevych.webshop.filter;

import com.ostap.komplikevych.webshop.constant.Const;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoding;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        Const.logger.debug("Encoding Filter starts");

        request.setCharacterEncoding(encoding);

        Const.logger.debug("Encoding Filter finished");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        Const.logger.debug("Filter initialization starts");
        encoding = fConfig.getInitParameter("encoding");
        Const.logger.trace("Encoding from web.xml --> " + encoding);
        Const.logger.debug("Filter initialization finished");
    }

}