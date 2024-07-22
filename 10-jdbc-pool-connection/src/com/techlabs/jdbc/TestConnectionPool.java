package com.techlabs.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestConnectionPool")
public class TestConnectionPool extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/students")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(Connection con = dataSource.getConnection();
				Statement stmt = con.createStatement();){
			
			String query = "select * from student";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				System.out.println(rs.getString("first_name"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}