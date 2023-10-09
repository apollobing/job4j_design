package ru.job4j.io;

import java.io.*;

public class Analysis {
    boolean alive = true;

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            final StringBuilder st = new StringBuilder();
            reader.lines()
                    .forEach(line -> {
                        boolean available = line.startsWith("200") || line.startsWith("300");
                        String time = line.split(" ")[1];
                        if (alive && !available) {
                            st.append(time).append(";");
                            alive = false;
                        }
                        if (!alive && available) {
                            st.append(time).append(";").append(System.lineSeparator());
                            alive = true;
                        }
                    });
            writer.write(st.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}