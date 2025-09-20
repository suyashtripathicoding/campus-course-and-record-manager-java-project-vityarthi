package edu.ccrm.domain;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
public class Enrollment {
private final Student student ;
private final Course course;
private Grade grade;

public  Enrollment(Student student, Course course) {
	this.student = student;
	this.course = course;
}

public Student getStudent() {
	return student;
}

public Course getCourse() {
	return course;
}

public Grade getGrade() {
	return grade;
}

public void setGrade(Grade grade) {
	this.grade = grade;
}

@Override
public String toString() {
	return "Enrollemnt [ Students= " + student.getFullName() + ",Course=" + course.getTitle() + ",Grade=" + (grade != null ? grade.name() : "N/A") +"]";
}

}
