package com.bank.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.bank.model.*;

public class CustomerDAO {
	private DataSource dataSource;

	public CustomerDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void addCustomer(Customer newCustomer) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "insert into customers (firstName,lastName,email,password,accountNumber,balance) values(?,?,?,?,?,?)";
		PreparedStatement prepstmt = conn.prepareStatement(sql);
		prepstmt.setString(1, newCustomer.getFirstName());
		prepstmt.setString(2, newCustomer.getLastName());
		prepstmt.setString(3, newCustomer.getEmail());
		prepstmt.setString(4, newCustomer.getPassword());
		prepstmt.setString(5, newCustomer.getAccountNumber());
		prepstmt.setDouble(6, newCustomer.getBalance());

		prepstmt.executeUpdate();
		conn.close();
	}

	public List<Customer> listCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {

			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from customers";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail(rs.getString(4));
				customer.setAccountNumber(rs.getString(6));
				customer.setPassword(rs.getString(5));
				customer.setBalance(rs.getDouble(7));
				customers.add(customer);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;

	}

	public void deleteCustomer(int id) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "delete from  customers  where id=?";
		PreparedStatement prepstmt = conn.prepareStatement(sql);
		prepstmt.setInt(1, id);
		prepstmt.executeUpdate();
		conn.close();

	}

	public Customer getCustomer(String email) {
		Connection conn;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from  customers  where email = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail(rs.getString(4));
				customer.setAccountNumber(rs.getString(6));
				customer.setPassword(rs.getString(5));
				customer.setBalance(rs.getDouble(7));
				return customer;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	public void updateCustomer(Customer newCustomer) throws SQLException {
		Connection conn = dataSource.getConnection();
		String sql = "update customers set firstName=?,lastName=?,email=?,password=?,balance=? where id=?";
		PreparedStatement prepstmt = conn.prepareStatement(sql);
		prepstmt.setString(1, newCustomer.getFirstName());
		prepstmt.setString(2, newCustomer.getLastName());
		prepstmt.setString(3, newCustomer.getEmail());
		prepstmt.setString(4, newCustomer.getPassword());

		prepstmt.setDouble(5, newCustomer.getBalance());
		prepstmt.setInt(6, newCustomer.getId());

		prepstmt.executeUpdate();
		conn.close();

	}

	

	
	public boolean editCustomer(Customer customer){
	    try {
	    	Connection conn = dataSource.getConnection();
			String sql = "update customers set firstName=?,lastName=?,email=?,password=? where id=?";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, customer.getFirstName());
			prepstmt.setString(2, customer.getLastName());
			prepstmt.setString(3, customer.getEmail());
			prepstmt.setString(4, customer.getPassword());
			prepstmt.setInt(5, customer.getId());

			int rs = prepstmt.executeUpdate();
			if(rs == 1) {
				conn.close();
				return true;
			}
			conn.close();
	    } catch(SQLException e) {
	      e.printStackTrace();
	    }
	    return false;
	  }
	
	
	
	
	
	public String getAccountNumber(String user_name) {
	    String account_number = "";
	    try {
	      Connection con = dataSource.getConnection();
	      String sql = "select * from customers where email = ?";
	      PreparedStatement stmt = con.prepareStatement(sql);
	      
	      stmt.setString(1, user_name);
	      ResultSet rs = stmt.executeQuery();
	      
	      while(rs.next()) {
	        account_number = rs.getString(6);
	        con.close();
	        return account_number;
	      }
	      con.close();
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return null;
	  }

	public void debit(String sender, double amount) {
		try {
		      Connection con = dataSource.getConnection();
		      double balance = getBalanceByAccount(sender) - amount;
		      String sql2 = "update customers set balance = ? where accountNumber = ?";
		      PreparedStatement stmt2 = con.prepareStatement(sql2);
		      stmt2.setDouble(1, balance);
		      stmt2.setString(2, sender);
		      stmt2.executeUpdate();
		      con.close();
		    } catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void credit(String receiver, double amount) {
		try {
		      Connection con = dataSource.getConnection();
		      double balance = getBalanceByAccount(receiver) + amount;
		      String sql2 = "update customers set balance = ? where accountNumber = ?";
		      PreparedStatement stmt2 = con.prepareStatement(sql2);
		      stmt2.setDouble(1, balance);
		      stmt2.setString(2, receiver);
		      stmt2.executeUpdate();
		      con.close();
		    } catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	public Customer getCustomer(int id) {
		Connection conn;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from  customers  where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail(rs.getString(4));
				customer.setAccountNumber(rs.getString(6));
				customer.setPassword(rs.getString(5));
				customer.setBalance(rs.getDouble(7));
				conn.close();
				return customer;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public double getBalanceByAccount(String sender) {
		double balance = 0;
		Connection conn;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from  customers  where accountNumber = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sender);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				balance =  rs.getDouble("balance");
				conn.close();
				return balance;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;
	}
	
	public boolean amountExists(double amount, String sender) throws SQLException {
		Connection con = dataSource.getConnection();
		String sql = "select * from customers where accountNumber = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, sender);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			double balance = rs.getDouble("balance");
			if(balance > amount) {
				con.close();
				return true;
			}
			con.close();
			return false;
		}
		return false;
	}

	public boolean receiverAccountExists(String receiver){
		Connection con;
		try {
			con = dataSource.getConnection();
			String sql = "select * from customers where accountNumber = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, receiver);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int test = rs.getInt(1);
				if(test != 0) {
					con.close();
					return true;
				}
				con.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
