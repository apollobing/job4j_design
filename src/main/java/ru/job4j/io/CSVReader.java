package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        File file = new File(argsName.get("path"));
        try (Scanner scanner = new Scanner(file)) {
            List<Integer> positions = new ArrayList<>();
            List<String> filterHeaders = Arrays.stream(argsName.get("filter").split(",")).toList();
            List<String> data;
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                data = Arrays.stream(scanner.nextLine().split(argsName.get("delimiter"))).toList();
                if (positions.size() == 0) {
                    for (String s : filterHeaders) {
                        if (data.contains(s)) {
                            positions.add(data.indexOf(s));
                        }
                    }
                }
                for (Integer p : positions) {
                    sb.append(positions.indexOf(p) == positions.size() - 1
                            ? data.get(p) + System.lineSeparator()
                            : data.get(p) + argsName.get("delimiter"));
                }
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(sb);
            } else {
                try (PrintWriter pw = new PrintWriter(new FileWriter(argsName.get("out")))) {
                    pw.print(sb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void validation(String inputPath, String out) {
        Path path = Paths.get(inputPath);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist \"%s\"", path.toAbsolutePath())
            );
        }
        if (!path.toFile().isFile()) {
            throw new IllegalArgumentException(
                    String.format("Not file \"%s\"", path.toAbsolutePath())
            );
        }
        if (!inputPath.endsWith(".csv")) {
            throw new IllegalArgumentException(
                    String.format("Not csv file \"%s\"", inputPath)
            );
        }
        if (!out.endsWith(".csv") && !"stdout".equals(out)) {
            throw new IllegalArgumentException(
                    String.format("Not csv file OR not \"%s\"", out)
            );
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("4 arguments not passed to program");
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader reader = new CSVReader();
        reader.validation(argsName.get("path"), argsName.get("out"));
        handle(argsName);
    }
}
