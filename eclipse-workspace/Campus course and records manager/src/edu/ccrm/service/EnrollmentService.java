package edu.ccrm.service;

import edu.ccrm.domain.Enrollment ;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Grade;
import edu.ccrm.service.DuplicateEnrollmentException;
import edu.ccrm.service.MaxCreditLimitExceededException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
   private final List<Enrollment> enrollments;
   
   public EnrollmentService() {
	   this.enrollments = new  ArrayList<>();
	   
   }
   
   public void enrollStudent(Student student, Course course)throws DuplicateEnrollmentException , MaxCreditLimitExceededException{
	   for(Enrollment enrollment : enrollments) {
		   if(enrollment.getStudent().equals(student) && enrollment.getCourse().equals(course)) {
			   throw new DuplicateEnrollmentException("Student is already enrolled in this course");
		   }
	   }
	   
	   int currentCredits = 0;
	   for(Enrollment enrollment : enrollments) {
		   if(enrollment.getStudent().equals(student) && enrollment.getCourse().getSemester().equals(course.getSemester())) {
			   currentCredits += enrollment.getCourse().getCredits();
		   }
		   
	   }
		   if(currentCredits + course.getCredits()>18) {
			   throw new  MaxCreditLimitExceededException("Maximum credit limit for the  semester exceeded");
		   }
	
	   
	   Enrollment newEnrollment = new Enrollment(student, course) ;
	   enrollments.add(newEnrollment);
   }

   // New method to record a grade for an existing enrollment
   public void recordGrade(Student student, Course course, Grade grade) {
       for (Enrollment enrollment : enrollments) {
           if (enrollment.getStudent().equals(student) && enrollment.getCourse().equals(course)) {
               enrollment.setGrade(grade);
               System.out.println("Recorded grade " + grade.name() + " for " + student.getFullName() + " in " + course.getTitle());
               return;
           }
       }
       System.out.println("Enrollment not found to record grade.");
   }

   // New method to compute a student's GPA
   public double computeGPA(Student student) {
       double totalPoints = 0;
       int totalCredits = 0;
       for (Enrollment enrollment : enrollments) {
           if (enrollment.getStudent().equals(student) && enrollment.getGrade() != null) {
               totalPoints += enrollment.getGrade().getGradePoints() * enrollment.getCourse().getCredits();
               totalCredits += enrollment.getCourse().getCredits();
           }
       }
       return totalCredits > 0 ? totalPoints / totalCredits : 0;
   }
}
