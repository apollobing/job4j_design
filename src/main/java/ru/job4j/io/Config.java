package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .filter(this::correctLine)
                    .forEach(line -> {
                        String[] keyValue = line.split("=", 2);
                        values.put(keyValue[0], keyValue[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean correctLine(String line) {
        if (!line.contains("=")
                || line.startsWith("=")
                || line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException(String.format("\"%s\" the template of the line isn't like \"key=value\"", line));
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}