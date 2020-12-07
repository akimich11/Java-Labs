package z5_6;

import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

public class IsoscelesTriangle extends Triangle
        implements
        Comparable<IsoscelesTriangle>,
        Iterable<Object>, Iterator<Object> {
    private final String inner_color, border_color;
    private int iterator_idx = 0;

    public void reset() {
        iterator_idx = 0;
    }

    public IsoscelesTriangle() {
        super(5.0,5.0, 3.08);
        inner_color = "black";
        border_color = "white";
    }

    public IsoscelesTriangle(double a, double radian_angle, String inner_color, String border_color) {
        super(a, a, radian_angle);
        this.inner_color = inner_color;
        this.border_color = border_color;
    }
    public IsoscelesTriangle(String str, String delimiter) {
        super(str, delimiter);
        StringTokenizer st = new StringTokenizer(str, delimiter);
        assert (st.countTokens() > 4);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        this.inner_color = st.nextToken();
        this.border_color = st.nextToken();
    }
    @Override
    public Double perimeter() { return a + b + Math.sqrt(a*a + b*b - 2*a*b*Math.cos(radian_angle)); }
    @Override
    public Double area() { return ((a * b * Math.sin(radian_angle)) / 2); }

    public int compareTo( IsoscelesTriangle itr ) {
        if(this.area().compareTo(itr.area()) == 0)
                return this.perimeter().compareTo(itr.perimeter());
        return this.area().compareTo(itr.area());
    }
    public static Comparator<IsoscelesTriangle> getComparator(int sortBy) {
        if (sortBy > 4 || sortBy < 0)
            throw new IndexOutOfBoundsException();
        return (c0, c1) -> {
            String s0 = c0.next(), s1 = c1.next();
            while (c0.iterator_idx != sortBy + 1)
                s0 = c0.next();
            while (c1.iterator_idx != sortBy + 1)
                s1 = c1.next();
            if (sortBy < 3)
                return Double.valueOf(s0).compareTo(Double.valueOf(s1));
            return s0.compareTo(s1);
        };
    }

    public Iterator<Object> iterator() {
        reset();
        return this;
    }
    public boolean hasNext() { return iterator_idx < 4; }
    public String next() {
        switch (iterator_idx)
        {
            case 0:
                iterator_idx++;
                return Double.toString(a);
            case 1:
                iterator_idx++;
                return Double.toString(b);
            case 2:
                iterator_idx++;
                return Double.toString(radian_angle);
            case 3:
                iterator_idx++;
                return inner_color;
            case 4:
                iterator_idx++;
                return border_color;
            default:
                reset();
                return Double.toString(a);
        }
    }

    public String toString() {
        return this.a.toString() +
                " | " + this.b.toString() +
                " | " + this.radian_angle.toString() +
                " | " + this.inner_color +
                " | " + this.border_color;
    }
}