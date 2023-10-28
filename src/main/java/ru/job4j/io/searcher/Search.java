package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {

    public static void run(String[] params) throws IOException {
        if (params.length < 4) {
            throw new IllegalArgumentException(
                    String.format("""
                                                        
                            You use "%d" argument(s), but should 4:
                            First is directory path;
                            Second is file name or mask;
                            Third is search type which should be "name" or "mask";
                            Fourth is path to output txt file.""", params.length)
            );
        }
        ArgsName parseArgs = ArgsName.of(params);
        String dir = parseArgs.get("d");
        String template = parseArgs.get("n");
        String type = parseArgs.get("t");
        String output = parseArgs.get("o");
        validation(dir, template, type, output);
        Path start = Paths.get(dir);
        if ("mask".equals(type)) {
            Pattern pattern = Pattern.compile(template);
            search(start, p -> pattern.matcher(p.toFile().getName()).matches(), output);
        } else {
            search(start, p -> p.toFile().getName().contains(template), output);
        }
    }

    public static void search(Path root, Predicate<Path> condition, String output) throws IOException {
        SearchFiles searcher = new SearchFiles(condition, output);
        Files.walkFileTree(root, searcher);
    }

    private static void validation(String dir, String template, String type, String output) {
        Path path = Paths.get(dir);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist \"%s\"", path.toAbsolutePath())
            );
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory \"%s\"", path.toAbsolutePath())
            );
        }
        if (template.length() < 3) {
            throw new IllegalArgumentException(
                    String.format("Template \"%s\" contains less than 3 symbols", template)
            );
        }
        if (!"name".equals(type) && !"mask".equals(type)) {
            throw new IllegalArgumentException(
                    String.format("\"%s\" not \"name\" or \"mask\"", type)
            );
        }
        if (!output.endsWith(".txt")) {
            throw new IllegalArgumentException(
                    String.format("Not txt file \"%s\"", output)
            );
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please use 4 arguments: ");
        String[] params = scanner.nextLine().split(" ");
        scanner.close();
        run(params);
    }
}