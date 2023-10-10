package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean alive = true;
            String line = reader.readLine();
            while (line != null) {
                boolean available = line.startsWith("200") || line.startsWith("300");
                String time = line.split(" ")[1];
                if (alive != available) {
                    writer.append(time).append(";").append(available ? System.lineSeparator() : "");
                    alive = !alive;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}