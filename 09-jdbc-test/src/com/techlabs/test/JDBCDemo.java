package com.techlabs.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JDBCDemo")
public class JDBCDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
			
			Statement stmt = con.createStatement();
			
			String insertQuery = "insert into student(id, first_name, last_name, email)" 
					+ "values(7, 'Abhishek', 'Pandey', 'abhi@gmail.com')";
			
			stmt.executeUpdate(insertQuery);
			
			String insertQuery1 = "insert into student(id, first_name, last_name, email)" 
									+ "values(1, 'Abhishek', 'Pandey', 'abhi@gmail.com')";
			String insertQuery2 = "insert into student(id, first_name, last_name, email)" 
					+ "values(2, 'manvendra', 'kumar', 'manvendra@gmail.com')";
			String insertQuery3 = "insert into student(id, first_name, last_name, email)" 
					+ "values(3, 'pradeep', 'Patil', 'pradeep@gmail.com')";
			String insertQuery4 = "insert into student(id, first_name, last_name, email)" 
					+ "values(4, 'pratik', 'y', 'pratik@gmail.com')";
			String insertQuery5 = "insert into student(id, first_name, last_name, email)" 
					+ "values(5, 'varish', 'v', 'varish@gmail.com')";
			String insertQuery6 = "insert into student(id, first_name, last_name, email)" 
					+ "values(6, 'agrah', 'j', 'agrah@gmail.com')";
			
			stmt.executeUpdate(insertQuery1);
			stmt.executeUpdate(insertQuery2);
			stmt.executeUpdate(insertQuery3);
			stmt.executeUpdate(insertQuery4);
			stmt.executeUpdate(insertQuery5);
			stmt.executeUpdate(insertQuery6);
			
			String updateQuery = "update student SET id = '7' where id = '10'";
			stmt.executeUpdate(updateQuery);
			
			String deleteQuery = "delete from student where id = '7'";
			stmt.executeUpdate(deleteQuery);
			
			
			String selectQuery = "select * from student";
			ResultSet rs = stmt.executeQuery(selectQuery);
			while(rs.next()) {
				String firstName = rs.getString("first_name");
				System.out.println(firstName);
				System.out.println("____________________");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
