package com.techlabs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.techlabs.dao.StudentDBUtil;
import com.techlabs.entity.Student;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private StudentDBUtil studentDbUtil;
	
	@Resource(name="jdbc/students")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException{
		super.init();
		studentDbUtil = new StudentDBUtil(dataSource);
	}
	
	public StudentController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command;
		command = request.getParameter("command");
		
		if(command == null) {
			command = "list";
		}
		
		System.out.println(command);
		switch(command) {
			case "add":
				addStudent(request, response);
				break;
			case "delete":
				deleteStudent(request, response);
				break;
			case "load":
				loadStudent(request, response);
				break;
			case "update":
				updateStudent(request, response);
				break;
			case "search":
				System.out.println("In search case");
				searchStudent(request, response);
				break;
			default:
				listStudents(request, response);
		}
		
	}
	
	private void searchStudent(HttpServletRequest request, HttpServletResponse response) {
		String input = request.getParameter("input");
		String type = request.getParameter("type");
		List<Student> studentList = new ArrayList<Student>();
		switch(type){
		case "Id":
			int id = Integer.parseInt(input);
			Student student = studentDbUtil.getStudent(id);
			studentList.add(student);
			break;
		case "fName":
		case "lName":
		case "Email":
		default:
			studentList = studentDbUtil.searchStudents(type,input);
			break;
		}
		request.setAttribute("theStudentList", studentList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("search-student.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Student> studentList = studentDbUtil.getStudents();
		request.setAttribute("theStudentList", studentList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("view-students.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		
		Student student = new Student(firstName, lastName, email);
		
		boolean isAdded = studentDbUtil.addStudent(student);
		System.out.println(isAdded);
		
		response.sendRedirect(request.getContextPath() + "/StudentController");
		
	}
	
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean isDeleted = studentDbUtil.deleteStudent(id);
		System.out.println(isDeleted);
		
		response.sendRedirect(request.getContextPath() + "/StudentController");
	}
	
	
	public void loadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = studentDbUtil.getStudent(id);
		System.out.println(student);
	 
		request.setAttribute("theStudent", student);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("update-student.jsp");
		requestDispatcher.forward(request, response);
		
	}

	public void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		
		Student student = new Student(id, firstName, lastName, email);
		boolean isUpdated = studentDbUtil.updateStudent(student);
		System.out.println(isUpdated);
		response.sendRedirect(request.getContextPath() + "/StudentController");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
