package com.techlabs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PreparedStatementTest")
public class PreparedStatementTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
			
			PreparedStatement stmt = con.prepareStatement("insert into student(id, first_name, last_name, email) values(?,?,?,?);");
			
			stmt.setInt(1, 10);
			stmt.setString(2, "Aman");
			stmt.setString(3,"Yadav");
			stmt.setString(4,"aman@gmail.com");
			
			stmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
