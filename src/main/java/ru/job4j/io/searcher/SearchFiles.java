package ru.job4j.io.searcher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> condition;
    private String output;

    public SearchFiles(Predicate<Path> condition, String output) {
        this.condition = condition;
        this.output = output;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(output, Charset.forName("WINDOWS-1251"), true))) {
                pw.println(file.toAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CONTINUE;
    }

}