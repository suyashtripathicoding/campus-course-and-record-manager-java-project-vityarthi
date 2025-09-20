package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
	private String regNo;
	private List<Course> enrolledCourses;
	public Student(String id , String fullName, String email, String regNo) {
		super(id, fullName, email);
		this.regNo = regNo;
		this.enrolledCourses = new ArrayList<>();
		
	}
	@Override
	public String getRole() {
		return "Student";
	}
	public String getRegNo() {
		return regNo;
		
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public List<Course> getEnrolledCourses(){
		return enrolledCourses ;
	}
	public void enrolledInCourse(Course course) {
		if(course!= null) {
			this.enrolledCourses.add(course);
		}
	}
	
	@Override
	public String toString() {
		return "Student [ID= " +getId()+",Name" +getFullName() +",RegNo=" + regNo + "]";
	}
}
