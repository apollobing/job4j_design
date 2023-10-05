package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info hasDifference = new Info(0, 0, 0);
        Map<Integer, String> prevMap = new HashMap<>();
        for (User u : previous) {
            prevMap.put(u.getId(), u.getName());
        }
        Map<Integer, String> currMap = new HashMap<>();
        for (User u : current) {
            currMap.put(u.getId(), u.getName());
        }
        for (User u : current) {
            if (prevMap.get(u.getId()) == null) {
                hasDifference.setAdded();
            }
            if (prevMap.get(u.getId()) != null
                    && !Objects.equals(prevMap.get(u.getId()), u.getName())) {
                hasDifference.setChanged();
            }
        }
        for (User u : previous) {
            if (currMap.get(u.getId()) == null) {
                hasDifference.setDeleted();
            }
        }
        return hasDifference;
    }
}