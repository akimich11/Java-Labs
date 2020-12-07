import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "gun-object")

public class GunObject {

    private String model;
    private String handy;
    private String origin;
    private String material;

    @XmlElement(name = "ttc")
    private TTC characteristics;

    public GunObject() {}

    public GunObject(String model, String origin, String handy, String material, TTC characteristics) {
        super();
        this.model = model;
        this.origin = origin;
        this.handy = handy;
        this.characteristics = characteristics;
        this.material = material;
    }


    @Override
    public String toString() {
        return "\nGun [model=" + model + ", handy=" + handy
                + ", origin=" + origin + ", material=" + material + ", " +
                characteristics.toString() + "]";
    }
}
