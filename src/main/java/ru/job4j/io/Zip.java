package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validation(String location, String fileExtension, String archive) {
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
        if (!archive.endsWith(".zip")) {
            throw new IllegalArgumentException(
                    String.format("Not zip archive \"%s\"", archive)
            );
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        if (args.length != 3) {
            throw new IllegalArgumentException("3 arguments not passed to program");
        }
        ArgsName parseArgs = ArgsName.of(args);
        zip.validation(parseArgs.get("d"), parseArgs.get("e"), parseArgs.get("o"));
        zip.packFiles(
                Search.search(Paths.get(parseArgs.get("d")),
                        p -> !p.toFile().getName().endsWith(parseArgs.get("e"))),
                new File(parseArgs.get("o")));
    }
}