package com.bank.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.sql.DataSource;

public class UniqueAccount {
	private static final Set<String> generatedAccountNumbers = new HashSet<>();
	private static final Random random = new Random();
	
	

	

	public static String accountNumber(DataSource dataSource) {
		String accountNumber;
		try {

			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select accountNumber from customers";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String s=rs.getString("accountNumber");
				generatedAccountNumbers.add(s);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		do {
			accountNumber = generateRandomNumber();
		} while (generatedAccountNumbers.contains(accountNumber));

		generatedAccountNumbers.add(accountNumber);
		return accountNumber;
	}

	private static String generateRandomNumber() {
		StringBuilder accountNumber = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			int digit = random.nextInt(10);
			accountNumber.append(digit);
		}

		return accountNumber.toString();
	}
}
