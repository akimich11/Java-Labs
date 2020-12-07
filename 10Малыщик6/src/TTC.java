import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ttc")
public class TTC {
    private String long_range;
    private String sighting_range;
    private String clip;
    private String optics;

    public TTC () {}

    public TTC(String long_range, String sighting_range, String clip, String optics) {
        this.long_range = long_range;
        this.sighting_range = sighting_range;
        this.clip = clip;
        this.optics = optics;
    }

    @Override
    public String toString() {
        return "\nTTC [long-range=" + long_range + ", sighting-range=" + sighting_range
                + ", clip=" + clip + ", optics=" + optics +"],\n";
    }
}
