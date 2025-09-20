package edu.ccrm.io;

import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Instructor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportExportService {

    public List<Student> importStudents(String filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try (Stream<String> lines = Files.lines(path)) {
                students = lines.map(line -> {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        return new Student(parts[0], parts[1], parts[2], parts[0]); // Using id as regNo for simplicity
                    }
                    return null;
                }).filter(s -> s != null)
                .collect(Collectors.toList());
            }
        }
        return students;
    }

    public void exportStudents(List<Student> students, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = students.stream()
            .map(s -> String.join(",", s.getId(), s.getFullName(), s.getEmail(), s.getRegNo()))
            .collect(Collectors.toList());
        Files.write(path, lines);
    }

    public void exportCourses(List<Course> courses, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = courses.stream()
            .map(c -> String.join(",", c.getCode(), c.getTitle(), String.valueOf(c.getCredits()), c.getDepartment()))
            .collect(Collectors.toList());
        Files.write(path, lines);
    }
}