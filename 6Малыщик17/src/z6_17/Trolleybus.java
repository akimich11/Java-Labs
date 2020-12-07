package z6_17;

import java.io.Serializable;

public class Trolleybus extends Route implements Serializable {
    private static final long serialVersionUID = 1L;

    public Trolleybus(State state)  { super( Type.TROLLEYBUS, state); }
    public Trolleybus() {}
}
