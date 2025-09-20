package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        // Business rule: Check for duplicate student registration number
        Optional<Student> existingStudent = students.stream()
            .filter(s -> s.getRegNo().equals(student.getRegNo()))
            .findFirst();

        if (existingStudent.isPresent()) {
            System.out.println("Error: Student with registration number " + student.getRegNo() + " already exists.");
            return;
        }
        this.students.add(student);
        System.out.println("Student added successfully: " + student.getFullName());
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return a copy to prevent external modification
    }

    public Optional<Student> getStudentByRegNo(String regNo) {
        return students.stream()
            .filter(s -> s.getRegNo().equals(regNo))
            .findFirst();
    }
}
