package ru.job4j.generic;

public class Role extends Base {

    private final String position;

    public Role(String id, String position) {
        super(id);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}