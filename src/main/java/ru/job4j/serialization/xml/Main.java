package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        final MobilePhone mobilePhone = new MobilePhone("Pixel 7", true, 128,
                new Camera(48), "GPS", "Fingerprint");
        JAXBContext context = JAXBContext.newInstance(MobilePhone.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(mobilePhone, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            MobilePhone result = (MobilePhone) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}