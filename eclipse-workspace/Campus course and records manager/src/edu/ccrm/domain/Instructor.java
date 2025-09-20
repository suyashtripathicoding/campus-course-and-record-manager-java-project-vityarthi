package edu.ccrm.domain;

public class Instructor extends Person{
	private String department;
	private double salary;
	//constructor for the class
	public Instructor(String id, String fullName, String email, String department, double salary) {
		super(id, fullName, email);
		this.department = department;
		this.salary = salary;
	}
	//Implementation of the abstract method
	@Override
	public String getRole() {
		return "Instructor";
		
	}
	//getters and setters specific to instructor
	public String getDepartment() {
		return department;
		
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public double getSalary() {
		return salary ;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Instructor [ ID= " +getId() +",Name=" +getFullName() + ", Department=" + department + "]";
	}
	
}