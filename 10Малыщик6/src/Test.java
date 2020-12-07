import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.*;

public class Test {

    public static void marshall(Guns guns, String filename, String extension) {
        try {
            Marshaller jaxbMarshaller = JAXBContext.newInstance(Guns.class).createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            if (!extension.equals("."))
            	jaxbMarshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, filename + extension);
            jaxbMarshaller.marshal(guns, new File(filename + ".xml"));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Guns unmarshall(String filename) {
        try {
            Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(Guns.class).createUnmarshaller();
            return (Guns)jaxbUnmarshaller.unmarshal(new File(filename + ".xml"));
        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        GunObject obj1 = new GunObject("AK-47","Russia", "Two-handed", "plastic",
                new TTC("long", "500 m", "Yes", "No"));
        GunObject obj2 = new GunObject("M4A1-S","USA", "Two-handed", "steel",
                new TTC("long", "500 m", "No", "No"));
        GunObject obj3 = new GunObject("Glock-18","Germany", "One-handed", "metal",
                new TTC("short", "250 m", "Yes", "No"));
        GunObject obj4 = new GunObject("Walter","UK", "One-handed", "steel",
                new TTC("short", "300 m", "No", "No"));
        GunObject obj5 = new GunObject("M16","USA", "Two-handed", "carbon",
                new TTC("medium", "1500 m", "No", "Yes"));

        ArrayList<GunObject> gunObjects = new ArrayList<>();
        gunObjects.add(obj1);
        gunObjects.add(obj2);
        gunObjects.add(obj3);
        gunObjects.add(obj4);
        gunObjects.add(obj5);
        Guns guns  = new Guns(gunObjects);

        String filename = "guns", extension = "";
        if (args.length != 0 && (args[0].equals("xsd") || args[0].equals("dtd")))
        	extension = args[0];
            
        System.out.print("Marshalling......");
        marshall(guns, filename, "." + extension);
        System.out.println("Done.\n");

        System.out.println("UnMarshalling : ");
        Guns unmarshalled_guns = unmarshall(filename);
        if(unmarshalled_guns != null)
            System.out.println(unmarshalled_guns.getGunObjects());
    }
}
