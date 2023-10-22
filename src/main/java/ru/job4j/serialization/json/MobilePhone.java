package ru.job4j.serialization.json;

import java.util.Arrays;

public class MobilePhone {
    private final String model;
    private final boolean support5g;
    private final int memory;
    private final Camera camera;
    private final String[] features;

    public MobilePhone(String model, boolean support5g, int memory, Camera camera, String[] features) {
        this.model = model;
        this.support5g = support5g;
        this.memory = memory;
        this.camera = camera;
        this.features = features;
    }

    @Override
    public String toString() {
        return "MobilePhone{"
                + "model=" + model
                + ", support5g=" + support5g
                + ", memory=" + memory + "GB"
                + ", camera=" + camera
                + ", features=" + Arrays.toString(features)
                + '}';
    }
}