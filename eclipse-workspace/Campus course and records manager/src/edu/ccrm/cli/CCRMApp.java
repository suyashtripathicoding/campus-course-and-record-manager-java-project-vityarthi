package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.DuplicateEnrollmentException;
import edu.ccrm.service.MaxCreditLimitExceededException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class CCRMApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final ImportExportService ioService = new ImportExportService();
    private static final BackupService backupService = new BackupService();

    public static void main(String[] args) {
        AppConfig config = AppConfig.getInstance();
        System.out.println("Welcome to the Campus Course & Records Manager!");
        System.out.println("Data folder path: " + config.getDataFolderPath());

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    manageStudents();
                    break;
                case "2":
                    manageCourses();
                    break;
                case "3":
                    manageEnrollmentAndGrades();
                    break;
                case "4":
                    manageFileOperations();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Enrollment & Grading");
        System.out.println("4. File Operations");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void manageStudents() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add a student");
        System.out.println("2. List all students");
        System.out.println("3. Go back");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            addStudent();
        } else if ("2".equals(choice)) {
            listStudents();
        }
    }
    
    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine();
        Student student = new Student(id, fullName, email, regNo);
        studentService.addStudent(student);
    }
    
    private static void listStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void manageCourses() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add a course");
        System.out.println("2. List all courses");
        System.out.println("3. Search courses by department");
        System.out.println("4. Go back");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            addCourse();
        } else if ("2".equals(choice)) {
            listAllCourses();
        } else if ("3".equals(choice)) {
            searchCoursesByDepartment();
        }
    }

    private static void addCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        Course course = new Course(code, title, credits, department);
        courseService.addCourse(course);
    }

    private static void listAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    private static void searchCoursesByDepartment() {
        System.out.print("Enter department name to search: ");
        String department = scanner.nextLine();
        List<Course> results = courseService.searchCoursesByDepartment(department);
        if (results.isEmpty()) {
            System.out.println("No courses found for department: " + department);
        } else {
            System.out.println("\n--- Courses in " + department + " ---");
            results.forEach(System.out::println);
        }
    }

    private static void manageEnrollmentAndGrades() {
        System.out.println("\n--- Enrollment & Grades ---");
        System.out.println("1. Enroll a student in a course");
        System.out.println("2. Record a grade");
        System.out.println("3. Compute and print a student's GPA");
        System.out.println("4. Go back");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                enrollStudent();
                break;
            case "2":
                recordGrade();
                break;
            case "3":
                computeGPA();
                break;
            case "4":
                // Go back
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void enrollStudent() {
        System.out.print("Enter student registration number: ");
        String studentRegNo = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();

        Optional<Student> studentOpt = studentService.getStudentByRegNo(studentRegNo);
        Optional<Course> courseOpt = courseService.getCourseByCode(courseCode);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            try {
                enrollmentService.enrollStudent(studentOpt.get(), courseOpt.get());
            } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    private static void recordGrade() {
        System.out.print("Enter student registration number: ");
        String studentRegNo = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter grade (e.g., S, A, B, etc.): ");
        String gradeString = scanner.nextLine().toUpperCase();

        Optional<Student> studentOpt = studentService.getStudentByRegNo(studentRegNo);
        Optional<Course> courseOpt = courseService.getCourseByCode(courseCode);

        if (studentOpt.isPresent() && courseOpt.isPresent()) {
            try {
                Grade grade = Grade.valueOf(gradeString);
                enrollmentService.recordGrade(studentOpt.get(), courseOpt.get(), grade);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid grade entered. Please use a valid grade (S, A, B, etc.).");
            }
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    private static void computeGPA() {
        System.out.print("Enter student registration number: ");
        String studentRegNo = scanner.nextLine();

        Optional<Student> studentOpt = studentService.getStudentByRegNo(studentRegNo);

        if (studentOpt.isPresent()) {
            double gpa = enrollmentService.computeGPA(studentOpt.get());
            System.out.println("GPA for " + studentOpt.get().getFullName() + " is: " + String.format("%.2f", gpa));
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void manageFileOperations() {
        System.out.println("\n--- File Operations ---");
        System.out.println("1. Import students");
        System.out.println("2. Export students");
        System.out.println("3. Create backup");
        System.out.println("4. Go back");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                importStudents();
                break;
            case "2":
                exportStudents();
                break;
            case "3":
                createBackup();
                break;
            case "4":
                // Go back
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void importStudents() {
        System.out.print("Enter file path to import students from (e.g., data/students.csv): ");
        String filePath = scanner.nextLine();
        try {
            List<Student> importedStudents = ioService.importStudents(filePath);
            importedStudents.forEach(studentService::addStudent);
            System.out.println("Successfully imported " + importedStudents.size() + " students.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void exportStudents() {
        System.out.print("Enter file path to export students to (e.g., data/students_export.csv): ");
        String filePath = scanner.nextLine();
        try {
            ioService.exportStudents(studentService.getAllStudents(), filePath);
            System.out.println("Successfully exported students to " + filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void createBackup() {
        String sourceDir = "data";
        String destDir = "backups";
        try {
            backupService.createBackup(sourceDir, destDir);
            System.out.println("Backup created successfully.");
            System.out.println("Listing backup directory contents recursively:");
            backupService.listFilesByDepth(Paths.get(destDir), 5);
        } catch (IOException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }
}