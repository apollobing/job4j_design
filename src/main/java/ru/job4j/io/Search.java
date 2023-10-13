package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    String.format("You use \"%d\" argument, \nbut should 2, "
                            + "first is directory path, second is file extension", args.length)
                    );
        }
        validation(args[0], args[1]);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validation(String location, String fileExtension) {
        Path path = Paths.get(location);
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
        if (!fileExtension.startsWith(".")) {
            throw new IllegalArgumentException(
                    String.format("Not file extension \"%s\"", fileExtension)
            );
        }
    }
}