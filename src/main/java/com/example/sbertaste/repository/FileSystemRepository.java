package com.example.sbertaste.repository;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Repository
public class FileSystemRepository {
    String RESOURCES_DIR = Objects.requireNonNull(FileSystemRepository.class.getResource("/")).getPath();

    public String saveImage(byte[] content, String imageName) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + imageName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath().toString();
    }

    public FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception ex) {
            throw new RuntimeException(String.format("File with location %s is not found", location));
        }
    }
}
