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

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDAO customerDbUtil;
	private TransactionDAO transactionDbUtil;

	@Resource(name = "jdbc/bank")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();
		customerDbUtil = new CustomerDAO(dataSource);
		transactionDbUtil = new TransactionDAO(dataSource);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action;
		action = request.getParameter("action");
		if(action == null) action = "home";

		switch (action) {
		case "listPassbook":
			listPassbook(request, response);
			break;
			
		case "searchTransactions":
		      searchTransaction(request, response);
		      break;
			
		case "newTransaction":
			newTransaction(request, response);
			break;
		case "addTransaction":
			try {
				addTransaction(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		

			
		case "editCustomer":
			editCustomer(request, response);
			break;
		case "updateCustomer":
			updateCustomer(request,response);
			break;
		case "notifySuccess":
			notifySuccess(request, response);
			break;
		case  "notifyUpdate":
			notifyUpdate(request, response);
			break;
		

		default:
			customerHome(request, response);
		}
	}
	private void notifySuccess(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("transaction-successful.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	private void notifyUpdate(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile-updated.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void searchTransaction(HttpServletRequest request, HttpServletResponse response) {
	    String startDate = request.getParameter("startDate");
	    String endDate = request.getParameter("endDate");
	    String userName = request.getParameter("username");
	    String acc = customerDbUtil.getAccountNumber(userName);

	    List<Transaction> transactions = transactionDbUtil.getTransactions(startDate, endDate, acc);
	    System.out.println(transactions);
	    request.setAttribute("theTransactionList", transactions);
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view-passbook.jsp");
	    try {
	      requestDispatcher.forward(request, response);
	    } catch (ServletException | IOException e) {
	      e.printStackTrace();
	    }

	  }

	

	

	private void listPassbook(HttpServletRequest request, HttpServletResponse response) {
		String user_name = request.getParameter("user_name");
		String account_number = customerDbUtil.getAccountNumber(user_name);
		List<Transaction> transactions = transactionDbUtil.getTransactionsForCustomer(account_number);
        request.setAttribute("theTransactionList", transactions);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view-passbook.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	private void newTransaction(HttpServletRequest request, HttpServletResponse response) {
		String user_name = request.getParameter("user_name");
		String accountNumber = customerDbUtil.getAccountNumber(user_name);
		request.setAttribute("senderAccountNumber",accountNumber);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new-transaction.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	private void addTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sender = request.getParameter("senderAccountNumber");
		String receiver = request.getParameter("receiverAccountNumber");
		double amount = Integer.parseInt(request.getParameter("amount"));
		boolean receiverExists = customerDbUtil.receiverAccountExists(receiver);
		if(receiverExists == false) {
			try {
				response.sendRedirect("CustomerController?action=notifyFailure&error2=receiver");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!customerDbUtil.amountExists(amount, sender)) {
			try {
				response.sendRedirect("CustomerController?action=notifySuccess&error1=amount");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		double Rbalance = customerDbUtil.getBalanceByAccount(receiver);
		double Sbalance = customerDbUtil.getBalanceByAccount(sender);
		
		customerDbUtil.debit(sender,amount);
		customerDbUtil.credit(receiver, amount);
	
		Transaction transaction = new Transaction();
		transaction.setSenderAccNo(sender);
		transaction.setReceiverAccNo(receiver);
		transaction.setAmount(amount);
		transaction.setRbalance(Rbalance + amount);
		transaction.setSbalance(Sbalance - amount);
		transactionDbUtil.addTransaction(transaction);
        try {
			response.sendRedirect("CustomerController?action=notifySuccess");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	

	

	private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
		int id =  Integer.parseInt(request.getParameter("id"));
		Customer customer = new Customer();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.setId(id);
		boolean updated = customerDbUtil.editCustomer(customer);
		if (updated) {
			try {
				response.sendRedirect("CustomerController?action=notifyUpdate");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("user_name");
		Customer customer = customerDbUtil.getCustomer(email);
		request.setAttribute("theCustomer", customer);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit-profile.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	

	
	

	
	private void customerHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-home.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}