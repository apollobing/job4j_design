package ru.job4j.io.searcher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SearchTest {

    @Test
    void getName(@TempDir Path tempDir) throws IOException {
        File file1 = tempDir.resolve("even.txt").toFile();
        try (PrintWriter out = new PrintWriter(file1)) {
            out.println("Java");
        }
        File file2 = tempDir.resolve("even2.txt").toFile();
        try (PrintWriter out = new PrintWriter(file2)) {
            out.println("Idea");
        }
        File target = tempDir.resolve("files.txt").toFile();

        String[] input = {"-d=" + tempDir, "-n=even", "-t=name", "-o=" + target.getAbsolutePath()};

        Search.run(input);

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString()).contains("even.txt").contains("even2.txt");
    }

    @Test
    void getMask(@TempDir Path tempDir) throws IOException {
        File file1 = tempDir.resolve("log.txt").toFile();
        try (PrintWriter out = new PrintWriter(file1)) {
            out.println("Some");
        }
        File file2 = tempDir.resolve("another.txt").toFile();
        try (PrintWriter out = new PrintWriter(file2)) {
            out.println("Word");
        }
        File target = tempDir.resolve("files.txt").toFile();

        String[] input = {"-d=" + tempDir, "-n=*.?x?", "-t=mask", "-o=" + target.getAbsolutePath()};

        Search.run(input);

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString()).contains("log.txt").contains("another.txt");
    }

    @Test
    void getRegex(@TempDir Path tempDir) throws IOException {
        File file1 = tempDir.resolve("log.txt").toFile();
        try (PrintWriter out = new PrintWriter(file1)) {
            out.println("Maven");
        }
        File file2 = tempDir.resolve("top.css").toFile();
        try (PrintWriter out = new PrintWriter(file2)) {
            out.println("Hibernate");
        }
        File target = tempDir.resolve("files.txt").toFile();

        String[] input = {"-d=" + tempDir, "-n=^\\w{3}\\.\\w+$", "-t=regex", "-o=" + target.getAbsolutePath()};

        Search.run(input);

        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString()).contains("log.txt").contains("top.css");
    }
}