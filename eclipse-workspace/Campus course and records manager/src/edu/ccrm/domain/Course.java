package edu.ccrm.domain;

import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;

public class Course {
    private String code;
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;
    private String department;

    // The constructor you have already implemented
    public Course(String code, String title, int credits, String department) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.department = department;
    }

    // All getters and setters you have already implemented
    // Make sure you have the following to avoid errors
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }
    
    public Semester getSemester() {
        return semester;
    }
    
    public String getDepartment() {
        return department;
    }
    
    // Setters
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    
    // ... all other getters and setters
    
    @Override
    public String toString() {
        return "Course [Code=" + code + ", Title=" + title + ", Credits=" + credits + ", Dept=" + department + "]";
    }
}