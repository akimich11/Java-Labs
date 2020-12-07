package z5_6;

import java.util.StringTokenizer;

public abstract class Triangle {
    protected final Double a, b, radian_angle;

    public Triangle(Double a, Double b, Double radian_angle) {
        assert (a > 0 && b > 0);
        this.a = a;
        this.b = b;
        assert (radian_angle > 0 && radian_angle < 3.142);
        this.radian_angle = radian_angle;
    }
    public Triangle(String str, String delimiter) {
        StringTokenizer st = new StringTokenizer(str, delimiter);
        assert (st.countTokens() > 2);
        double a = Double.parseDouble(st.nextToken()),
                b = Double.parseDouble(st.nextToken()),
                angle = Double.parseDouble(st.nextToken());
        assert (a > 0 && b > 0);
        this.a = a;
        this.b = b;
        assert (angle > 0 && angle < 3.142);
        this.radian_angle = angle;
    }
    public abstract Double perimeter();
    public abstract Double area();
}