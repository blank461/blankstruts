package lf.blank.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lf.blank.framework.Action;
import lf.blank.service.UserService;

public class LoginAction implements Action {
	UserService userService = new UserService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("name") == null) {
			return "login";
		}

		if (userService.login(request.getParameter("name"), request.getParameter("password")) == null) {
			return "error";
		} else {
			return "success";
		}
	}

}
