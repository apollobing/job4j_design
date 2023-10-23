package ru.job4j.serialization.json;

public class Camera {
    private final int megapixels;

    public Camera(int megapixels) {
        this.megapixels = megapixels;
    }

    @Override
    public String toString() {
        return "Camera{"
                + "megapixels='" + megapixels + " MP" + '\''
                + '}';
    }

    public int getMegapixels() {
        return megapixels;
    }
}
