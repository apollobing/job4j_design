package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analysis {
    public void unavailable(String source, String target) {
        StringJoiner st = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines()
                    .forEach(st::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strings = st.toString().split(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            st = new StringJoiner(System.lineSeparator());
            String from = "";
            String to = "";
            for (String line : strings) {
                if (from.isEmpty() && (line.startsWith("400") || line.startsWith("500"))) {
                    from = line.split(" ")[1] + ";";
                }
                if (!from.isEmpty() && (!line.startsWith("400") && !line.startsWith("500"))) {
                    to = line.split(" ")[1] + ";";
                }
                if (!from.isEmpty() && !to.isEmpty()) {
                    st.add(from + to);
                    from = "";
                    to = "";
                }
            }
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