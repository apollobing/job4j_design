package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder value = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new FileReader(cachingDir + FileSystems.getDefault().getSeparator() + key))
        ) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(value::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value.toString();
    }
}
