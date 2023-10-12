package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, String> listOfFiles = new HashMap<>();
    private Set<String> listOfDuplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (listOfFiles.containsKey(fileProperty)) {
            listOfDuplicates.add(listOfFiles.get(fileProperty));
            listOfDuplicates.add(file.toAbsolutePath().toString());
        } else {
            listOfFiles.put(fileProperty, file.toAbsolutePath().toString());
        }
        return super.visitFile(file, attrs);
    }

    public void duplicates() {
        listOfDuplicates.forEach(System.out::println);
    }
}