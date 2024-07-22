package com.bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.bank.model.Customer;
import com.bank.util.CustomerDAO;
import com.bank.model.Transaction;
import com.bank.util.TransactionDAO;
import com.bank.model.UniqueAccount;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/bank")
	private DataSource dataSource;

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		customerDAO = new CustomerDAO(dataSource);
		transactionDAO = new TransactionDAO(dataSource);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null)
			action = "list";
		try {
			switch (action) {
			case "listCustomer":
				listCustomer(request, response);
				break;
			case "addnewCustomer":
		        addNewCustomer(request,response);
		        break;
			case "insertCustomer":
				insertCustomer(request, response);
				break;
			case "delete":
				deleteCustomer(request, response);
				break;
			case "editCustomer":
				showEditForm(request, response);
				break;
			case "update":
				updateCustomer(request, response);
				break;
			case "listTransactions":
				listTransactions(request, response);
				break;
			case "searchTransactions":
		        searchTransactions(request,response);
		        break;
			default:
				adminHome(request, response);
				break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	
	private void addNewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-add-new-Customers.jsp");
	        dispatcher.forward(request, response);
	  }
	
	private void listTransactions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Transaction> transactionlist = transactionDAO.listTransactions();
		request.setAttribute("theTransactionList", transactionlist);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/transactionFile.jsp");
		requestDispatcher.forward(request, response);

	}

	private void searchTransactions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       String startDate = request.getParameter("startDate");
	          String endDate = request.getParameter("endDate");
	          List<Transaction> transactionlist = transactionDAO.findTransactionsByDateRange(startDate, endDate);
	          System.out.println(transactionlist);
	          request.setAttribute("theTransactionList", transactionlist);
	          RequestDispatcher dispatcher = request.getRequestDispatcher("/transactionFile.jsp");
	          dispatcher.forward(request, response);
	    
	  }
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		int id = Integer.parseInt(request.getParameter("Id"));

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		double balance = Double.parseDouble(request.getParameter("balance"));

		Customer newCustomer = new Customer();
		newCustomer.setFirstName(firstName);
		newCustomer.setLastName(lastName);
		newCustomer.setEmail(email);
		newCustomer.setPassword(password);
		newCustomer.setId(id);

		newCustomer.setBalance(balance);
		customerDAO.updateCustomer(newCustomer);
		response.sendRedirect("AdminController?action=listCustomer");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("Id"));
		Customer customer = customerDAO.getCustomer(id);
		request.setAttribute("theCustomer", customer);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/update-customer.jsp");
		requestDispatcher.forward(request, response);

	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("Id"));

		customerDAO.deleteCustomer(id);
		response.sendRedirect("AdminController?action=listCustomer");

	}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Customer> customerlist = customerDAO.listCustomers();
		request.setAttribute("theCustomerList", customerlist);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customersFile.jsp");
		requestDispatcher.forward(request, response);

	}

	private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String accountNumber = UniqueAccount.accountNumber(dataSource);

		double balance = Double.parseDouble(request.getParameter("balance"));
		Customer newCustomer = new Customer();
		newCustomer.setFirstName(firstName);
		newCustomer.setLastName(lastName);
		newCustomer.setEmail(email);
		newCustomer.setPassword(password);
		newCustomer.setAccountNumber(accountNumber);
		newCustomer.setBalance(balance);
		customerDAO.addCustomer(newCustomer);
		response.sendRedirect("AdminController?action=listCustomer");

	}

	private void adminHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-home.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
