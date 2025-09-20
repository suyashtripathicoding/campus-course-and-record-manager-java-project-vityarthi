package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Semester;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService {
    private final List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        // Business rule: Check for duplicate course code
        Optional<Course> existingCourse = courses.stream()
            .filter(c -> c.getCode().equals(course.getCode()))
            .findFirst();

        if (existingCourse.isPresent()) {
            System.out.println("Error: Course with code " + course.getCode() + " already exists.");
            return;
        }
        this.courses.add(course);
        System.out.println("Course added successfully: " + course.getTitle());
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }
    
    // Search & Filter (by instructor, department, semester) using the Stream API
    public List<Course> searchCoursesByInstructor(Instructor instructor) {
        return courses.stream()
            .filter(c -> c.getInstructor() != null && c.getInstructor().equals(instructor))
            .collect(Collectors.toList());
    }

    public List<Course> searchCoursesByDepartment(String department) {
        return courses.stream()
            .filter(c -> c.getDepartment().equalsIgnoreCase(department))
            .collect(Collectors.toList());
    }

    public List<Course> searchCoursesBySemester(Semester semester) {
        return courses.stream()
            .filter(c -> c.getSemester() != null && c.getSemester().equals(semester))
            .collect(Collectors.toList());
    }
    public Optional<Course> getCourseByCode(String code) {
        return courses.stream()
            .filter(c -> c.getCode().equals(code))
            .findFirst();
    }
}
