package com.example.sbertaste.repository;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Repository
public class FileSystemRepository {
    public String saveImage(byte[] content, String imageName, String location) throws Exception {

        URI uri = URI.create(Objects.requireNonNull(getClass().getClassLoader().getResource(location)) + imageName);
        Path newFile = Paths.get(uri);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);

        return newFile.getFileName().toString();
    }

    public FileSystemResource findInFileSystem(String file) {
        try {
            URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource(file)).toURI();

            return new FileSystemResource(Paths.get(uri).toString());
        } catch (Exception ex) {
            throw new RuntimeException(String.format("File with location %s is not found", file));
        }
    }
}
