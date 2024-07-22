package com.bank.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import com.bank.model.*;

public class TransactionDAO {
	private DataSource dataSource;

	public TransactionDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public List<Transaction> listTransactions() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from transactions";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(rs.getInt(1));
				transaction.setSenderAccNo(rs.getString(2));
				transaction.setReceiverAccNo(rs.getString(3));
				transaction.setType(rs.getString(4));
				transaction.setAmount(rs.getDouble(5));
				transaction.setDate(rs.getDate(6));

				transactions.add(transaction);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;

	}

	
	
	public List<Transaction> getTransactionsForCustomer(String accountNumber){
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions = getDebitTransactionsForCustomer(accountNumber);
		List<Transaction> transactions2 = new ArrayList<Transaction>();
		transactions2 = getCreditTransactionsForCustomer(accountNumber);
		transactions.addAll(transactions2);
		Collections.sort(transactions, (t1, t2) -> Integer.compare(t1.getId(), t2.getId()));
		return transactions;
	}
	public List<Transaction> getDebitTransactionsForCustomer(String sender) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {

			Connection con = dataSource.getConnection();

			String sql = "select * from transactions t where senderAccNo = ?";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, sender);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String senderAccount_number = rs.getString("senderAccNo");
				String recAccount_number = rs.getString("receiverAccNo");
				Date date=rs.getDate("date");
				int amount = rs.getInt("amount");
				String type= "debit";
				double Rbalance = rs.getDouble("Rbalance");
				double Sbalance = rs.getDouble("Sbalance");
				Transaction transaction =new Transaction();		
				transaction.setAmount(amount);
				transaction.setType(type);
				transaction.setDate(date);
				transaction.setId(id);
				transaction.setSenderAccNo(senderAccount_number);
				transaction.setReceiverAccNo(recAccount_number);
				transaction.setSbalance(Sbalance);
				transaction.setRbalance(Rbalance);
				transactions.add(transaction);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	public List<Transaction> getCreditTransactionsForCustomer(String receiver) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			
			Connection con = dataSource.getConnection();

			String sql = "select * from transactions t where receiverAccNo = ?";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, receiver);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String senderAccount_number = rs.getString("senderAccNo");
				String recAccount_number = rs.getString("receiverAccNo");
				Date date=rs.getDate("date");
				int amount = rs.getInt("amount");
				String type= "credit";
				double Rbalance = rs.getDouble("Rbalance");
				double Sbalance = rs.getDouble("Sbalance");
				Transaction transaction =new Transaction();		
				transaction.setAmount(amount);
				transaction.setType(type);
				transaction.setDate(date);
				transaction.setId(id);
				transaction.setSenderAccNo(senderAccount_number);
				transaction.setReceiverAccNo(recAccount_number);
				transaction.setSbalance(Sbalance);
				transaction.setRbalance(Rbalance);
				transactions.add(transaction);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
	
	public boolean addTransaction(Transaction transaction) {
		try {
				Connection con = dataSource.getConnection();
				String sql = "insert into transactions (senderAccNo,receiverAccNo,amount, Sbalance,Rbalance) values(?,?,?,?,?);";
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, transaction.getSenderAccNo());
				stmt.setString(2, transaction.getReceiverAccNo());
				stmt.setDouble(3, transaction.getAmount());
				stmt.setDouble(4, transaction.getSbalance());
				stmt.setDouble(5, transaction.getRbalance());
				int rs = stmt.executeUpdate();
				if (rs == 1) {
					con.close();
					return true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Transaction> findTransactionsByDateRange(String startDate, String endDate) {
	     List<Transaction> transactions = new ArrayList<Transaction>();
	          String sql = "SELECT * FROM transactions WHERE date BETWEEN ? AND ?";
	          

	          try (Connection conn =dataSource.getConnection();
	               PreparedStatement ps = conn.prepareStatement(sql)) {

	              ps.setString(1, startDate);
	              ps.setString(2, endDate);
	              ResultSet rs = ps.executeQuery();

	              while (rs.next()) {
	                  Transaction transaction = new Transaction();
	                  transaction.setSenderAccNo(rs.getString(2));
	                  transaction.setReceiverAccNo(rs.getString(3));
	                  transaction.setType("Transfer");
	                  transaction.setId(rs.getInt("id"));
	                  transaction.setDate(rs.getDate("date"));
	                  transaction.setAmount(rs.getDouble("amount"));
	                  System.out.println(transaction);
	                  transactions.add(transaction);
	              }
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }
	          
	          return transactions;

	    
	  }
	
	public List<Transaction> getTransactions(String startDate, String endDate, String account) {
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    
	    String sql = "SELECT * FROM transactions WHERE date BETWEEN ? AND ? AND (senderAccNo = ? OR receiverAccNo = ?)";

	        

	        try (Connection conn =dataSource.getConnection();
	             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

	           preparedStatement.setString(1, startDate);
	             preparedStatement.setString(2, endDate);
	             preparedStatement.setString(3, account);
	             preparedStatement.setString(4, account);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	                Transaction transaction = new Transaction();
	                String a=rs.getString(2);
	                if(a.equals(account)) {
	                  transaction.setSenderAccNo(a);
	                  transaction.setType("debit");
	                   transaction.setReceiverAccNo(rs.getString(3));
	                }
	                else {
	                  transaction.setSenderAccNo(rs.getString(3));
	                  transaction.setType("credit");
	                   transaction.setReceiverAccNo(a);
	                }
	                
	                transaction.setId(rs.getInt("id"));
	                transaction.setDate(rs.getDate("date"));
	                transaction.setAmount(rs.getDouble("amount"));
	                transaction.setRbalance(rs.getDouble(7));
	                transaction.setSbalance(rs.getDouble(8));
	                transactions.add(transaction);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return transactions;
	    
	  }

}
