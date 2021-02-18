package com.ostap.komplikevych.webshop.model.command;

import com.ostap.komplikevych.webshop.constant.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogoutCommand extends Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		Const.logger.debug("Command " + this.getClass().getName().toUpperCase());
		
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();

		Const.logger.debug("Command finished");
		return Const.PAGE_PATH_LOGIN;
	}

}