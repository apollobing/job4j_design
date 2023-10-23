package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "camera")
public class Camera {
    @XmlAttribute
    private int megapixels;

    public Camera() { }

    public Camera(int megapixels) {
        this.megapixels = megapixels;
    }

    @Override
    public String toString() {
        return "Camera{"
                + "megapixels='" + megapixels + " MP" + '\''
                + '}';
    }
}
