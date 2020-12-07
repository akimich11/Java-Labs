package z6_17;

import java.io.Serializable;
import java.util.Date;

public class Bus extends Route implements Serializable {
    private static final long serialVersionUID = 1L;

    public Bus( State state ) {
        super( Type.BUS, state);
    }
    public Bus() { }
}
