package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> listOfFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        List<Path> paths = new ArrayList<>();
        if (listOfFiles.containsKey(fileProperty)) {
            listOfFiles.get(fileProperty).add(file);
        } else {
            paths.add(file);
            listOfFiles.put(fileProperty, paths);
        }
        return super.visitFile(file, attrs);
    }

    public void duplicates() {
        listOfFiles.values().stream()
                .filter(paths -> paths.size() > 1)
                .flatMap(Collection::stream)
                .forEach(path -> System.out.println(path.toAbsolutePath()));
    }
}