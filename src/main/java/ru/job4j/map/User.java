package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Bob", 2, new GregorianCalendar(1970, Calendar.APRIL, 15));
        User user2 = new User("Bob", 2, new GregorianCalendar(1970, Calendar.APRIL, 15));

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);

        int hashCodeUser1 = user1.hashCode();
        int hashUser1 = hashCodeUser1 ^ (hashCodeUser1 >>> 16);
        int bucketUser1 = hashUser1 & 15;

        int hashCodeUser2 = user2.hashCode();
        int hashUser2 = hashCodeUser2 ^ (hashCodeUser2 >>> 16);
        int bucketUser2 = hashUser2 & 15;

        System.out.printf("user1 - hashcode: %s, hash: %s, bucket: %s",
                hashCodeUser1, hashUser1, bucketUser1);
        System.out.println();
        System.out.printf("user2 - hashcode: %s, hash: %s, bucket: %s",
                hashCodeUser2, hashUser2, bucketUser2);
    }
}
