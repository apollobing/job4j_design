package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public static void create(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName
                    + "(id serial primary key, "
                    + "name VARCHAR(255), "
                    + "email VARCHAR(255))";
            statement.execute(sql);
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(str -> {
                String[] nameEmail = str.split(";", 2);
                if (nameEmail[0].isEmpty() || nameEmail[1].isEmpty()) {
                    throw new IllegalArgumentException("Your string must contain \"name;email;\"");
                }
                users.add(new User(nameEmail[0], nameEmail[1].replace(";", "")));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            create(cnt, "users");
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "data/dump.txt");
        db.save(db.load());
        System.out.println("Done!");
    }
}