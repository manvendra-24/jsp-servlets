package com.techlabs.model;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	public static List<Student> getStudents(){
		List<Student> students = new ArrayList<>();
		students.add(new Student(1,"Manvendra","k","manvendra@monocept.com"));
		students.add(new Student(2,"pradeep","p","pradeep@monocept.com"));
		students.add(new Student(3,"prateek","y","prateek@monocept.com"));
		students.add(new Student(4,"varish","v","varish@monocept.com"));
		
		return students;
	}
}
