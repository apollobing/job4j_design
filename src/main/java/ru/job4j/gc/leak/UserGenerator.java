package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {
    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        StringBuilder userData = new StringBuilder();
        int newUsers = 1000;
        for (int i = 0; i < newUsers; i++) {
            String separator = " ";
            userData.append(surnames.get(random.nextInt(surnames.size()))).append(separator)
                            .append(names.get(random.nextInt(names.size()))).append(separator)
                            .append(patrons.get(random.nextInt(patrons.size())));
            users.add(new User(userData.toString()));
            userData.setLength(0);
        }
    }

    private void readAll() {
        try {
            String pathNames = "src/main/java/ru/job4j/gc/leak/files/names.txt";
            names = read(pathNames);
            String pathSurnames = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
            surnames = read(pathSurnames);
            String pathPatrons = "src/main/java/ru/job4j/gc/leak/files/patr.txt";
            patrons = read(pathPatrons);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}
