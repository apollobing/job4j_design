package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> messages = new ArrayList<>();
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        boolean botActive = true;
        System.out.println("Chat started");
        messages.add("Chat started" + System.lineSeparator());
        while (!OUT.equals(userInput)) {
            System.out.print("Your message: ");
            userInput = scanner.nextLine();
            messages.add("Your message: " + userInput + System.lineSeparator());
            if (STOP.equals(userInput)) {
                botActive = false;
                messages.add("Bot is inactive" + System.lineSeparator());
                System.out.println("Bot is inactive");
            }
            if (CONTINUE.equals(userInput)) {
                botActive = true;
                messages.add("Bot is active" + System.lineSeparator());
                System.out.println("Bot is active");
                continue;
            }
            if (botActive && !OUT.equals(userInput)) {
                String botAnswer = phrases.get(rand.nextInt(phrases.size()));
                messages.add("Bot answer: " + botAnswer);
                System.out.print("Bot answer: " + botAnswer);
            }
        }
        messages.add("Chat ended");
        saveLog(messages);
        System.out.print("Chat ended!");

    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chatLog.txt", "data/botAnswers.txt");
        cc.run();
    }
}
