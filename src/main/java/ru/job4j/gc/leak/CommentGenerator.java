package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    private final List<Comment> comments = new ArrayList<>();
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            String pathPhrases = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
            phrases = read(pathPhrases);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        int count = 50;
        for (int i = 0; i < count; i++) {
            String separator = System.lineSeparator();
            StringBuilder comment = new StringBuilder();
            comment.append(phrases.get(random.nextInt(phrases.size()))).append(separator)
                    .append(phrases.get(random.nextInt(phrases.size()))).append(separator)
                    .append(phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(comment.toString(),
                    userGenerator.randomUser()));
            comment.setLength(0);
        }
    }
}
