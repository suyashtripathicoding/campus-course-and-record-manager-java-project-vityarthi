package edu.ccrm.io;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {

    public void createBackup(String sourceDir, String destDir) throws IOException {
        Path sourcePath = Paths.get(sourceDir);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = Paths.get(destDir, "backup_" + timestamp);
        Files.createDirectories(backupDir);
        Files.walk(sourcePath)
            .forEach(source -> {
                Path destination = backupDir.resolve(sourcePath.relativize(source));
                try {
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.err.println("Failed to copy " + source + ": " + e.getMessage());
                }
            });
        System.out.println("Backup created at: " + backupDir);
    }
    
    // Recursive utility to list files by depth
    public void listFilesByDepth(Path dir, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(dir, depth)) {
            stream.forEach(System.out::println);
        }
    }
}