package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String getModel() {
        return model;
    }

    public boolean isSupport5g() {
        return support5g;
    }

    public int getMemory() {
        return memory;
    }

    public Camera getCamera() {
        return camera;
    }

    public String[] getFeatures() {
        return features;
    }

    public static void main(String[] args) {
        JSONObject jsonCamera = new JSONObject("{\"megapixels\":48}");

        List<String> list = new ArrayList<>();
        list.add("GPS");
        list.add("Fingerprint");
        JSONArray jsonFeatures = new JSONArray(list);

        final MobilePhone mobilePhone = new MobilePhone("Pixel 7", true, 128,
                new Camera(48), new String[] {"GPS", "Fingerprint"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", mobilePhone.getModel());
        jsonObject.put("support5g", mobilePhone.isSupport5g());
        jsonObject.put("memory", mobilePhone.getMemory());
        jsonObject.put("camera", jsonCamera);
        jsonObject.put("features", jsonFeatures);
        System.out.println(jsonObject);

        System.out.println(new JSONObject(mobilePhone));
    }
}