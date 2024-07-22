package com.bank.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.bank.model.UserLogin;
import com.bank.util.UserDbUtil;

@WebServlet("/UserLoginController")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDbUtil userDbUtil;
	
	@Resource(name="jdbc/bank")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException{
		super.init();
		userDbUtil = new UserDbUtil(dataSource);
	}
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null) action="verifyUser";
		switch(action) {
		case "verifyUser":
			verifyUser(request, response);
			break;
		}
		
	}


	private void verifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user_type = request.getParameter("user_type");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		
		UserLogin user = new UserLogin(user_name, password);
		boolean isUser = userDbUtil.verifyUser(user);
		if(user_type.equals("admin") && user_name.equals("admin") && password.equals("admin") ) {
			HttpSession session = request.getSession();
            session.setAttribute("username", user_name);
            session.setAttribute("user_type", "admin");
            response.sendRedirect("AdminController");
		}
		else if(user_type.equals("customer") && isUser) {
			HttpSession session = request.getSession();
            session.setAttribute("user_name", user_name);
            session.setAttribute("user_type", "user");
            response.sendRedirect("CustomerController");
		}
		else {
			response.sendRedirect("Controller?error=true");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
