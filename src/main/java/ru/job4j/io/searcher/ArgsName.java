package ru.job4j.io.searcher;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String param : args) {
            String[] keyValue = param.split("=", 2);
            values.put(keyValue[0].substring(1), keyValue[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String param : args) {
            correctLine(param);
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static void correctLine(String line) {
        if (!line.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format(
                            "Error: This argument '%s' does not start with a '-' character", line
                    )
            );
        }
        if (line.startsWith("-=")) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain a key", line)
            );
        }
        if (line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain a value", line)
            );
        }
        if (!line.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("Error: This argument '%s' does not contain an equal sign", line)
            );
        }

    }
}