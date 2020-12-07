import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Gun")
public class Guns {

    @XmlElement(name = "gun-object")
    private List<GunObject> gunObjects;

    public Guns() {}

    public Guns(List<GunObject> gunObjects) {
        this.gunObjects = gunObjects;
    }

    public List<GunObject> getGunObjects() {
        return gunObjects;
    }
}
