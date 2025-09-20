package edu.ccrm.domain;

import java.util.List;

public class Transcript {
    private final Student student;
    private final List<Enrollment> enrollments;
    private final double gpa;

    private Transcript(Builder builder) {
        this.student = builder.student;
        this.enrollments = builder.enrollments;
        this.gpa = builder.gpa;
    }

    // Static nested Builder class
    public static class Builder {
        private final Student student;
        private List<Enrollment> enrollments;
        private double gpa;

        public Builder(Student student) {
            this.student = student;
        }

        public Builder enrollments(List<Enrollment> enrollments) {
            this.enrollments = enrollments;
            return this;
        }

        public Builder gpa(double gpa) {
            this.gpa = gpa;
            return this;
        }

        public Transcript build() {
            return new Transcript(this);
        }
    }

    @Override
    public String toString() {
        // Use polymorphism and toString overrides to print the transcript
        StringBuilder sb = new StringBuilder();
        sb.append("--- Student Transcript ---\n");
        sb.append(student.toString()).append("\n");
        sb.append("GPA: ").append(String.format("%.2f", gpa)).append("\n");
        sb.append("Enrolled Courses:\n");
        enrollments.forEach(e -> sb.append("  - ").append(e.toString()).append("\n"));
        return sb.toString();
    }
}