package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final MobilePhone mobilePhone = new MobilePhone("Pixel 7", true, 128,
                new Camera(48), new String[] {"GPS", "Fingerprint"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(mobilePhone));
        System.out.println(mobilePhone);

        final String mobilePhoneJson =
                "{"
                        + "\"model\":\"Nexus 5\","
                        + "\"support5g\":false,"
                        + "\"memory\":32,"
                        + "\"camera\":"
                        + "{"
                        + "\"megapixels\":8"
                        + "},"
                        + "\"features\":"
                        + "[\"Qi\",\"Accelerometer\"]"
                        + "}";
        final MobilePhone mobilePhoneMod = gson.fromJson(mobilePhoneJson, MobilePhone.class);
        System.out.println(mobilePhoneMod);
    }
}