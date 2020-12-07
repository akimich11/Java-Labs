package z6_17;

import java.io.Serializable;

public class Tram extends Route implements Serializable {
    private static final long serialVersionUID = 1L;

    public Tram(State state)  { super( Type.TRAM, state); }
    public Tram() { }
}
