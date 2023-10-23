package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "phone")
@XmlAccessorType(XmlAccessType.FIELD)
public class MobilePhone {
    @XmlAttribute
    private String model;

    @XmlAttribute
    private boolean support5g;

    @XmlAttribute
    private int memory;
    private Camera camera;

    @XmlElementWrapper
    @XmlElement(name = "feature")
    private String[] features;

    public MobilePhone() { }

    public MobilePhone(String model, boolean support5g, int memory, Camera camera, String... features) {
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

    public static void main(String[] args) throws JAXBException {

        final MobilePhone mobilePhone = new MobilePhone("Samsung Galaxy S23", true, 256,
                new Camera(50), "Compass", "Wireless DeX");

        JAXBContext context = JAXBContext.newInstance(MobilePhone.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(mobilePhone, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}