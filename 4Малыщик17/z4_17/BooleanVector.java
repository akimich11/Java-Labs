package z4_17;

public class BooleanVector {
    boolean[] vector;
    int n;
    public BooleanVector() {
        this.n = 5;
        this.vector = new boolean[n];
    }
    public BooleanVector(int n) {
        this.n = n;
        this.vector = new boolean[n];
    }
    public BooleanVector(int n, boolean[] vector) {
        this.n = n;
        this.vector = new boolean[n];
        System.arraycopy(vector, 0, this.vector, 0, n);
    }

    public void disjunction_with(BooleanVector v) {
        assert (this.n == v.n);
        for(int i = 0; i < this.n; i++)
            this.vector[i] = this.vector[i] || v.vector[i];
    }
    public void conjunction_with(BooleanVector v) {
        assert (this.n == v.n);
        for(int i = 0; i < this.n; i++)
            this.vector[i] = this.vector[i] && v.vector[i];
    }
    public void negation() {
        for(int j = 0; j < this.n; j++)
            this.vector[j] = !this.vector[j];
    }

    public int count_zeroes() {
        int counter = 0;
        for(boolean j : this.vector)
            if(!j)
                counter++;
        return counter;
    }
    public int count_ones() {
        int counter = 0;
        for(boolean j : this.vector)
            if(j)
                counter++;
        return counter;
    }

    public void print() {
        for(boolean j : this.vector) {
            if(!j)
                System.out.print("0 ");
            else
                System.out.print("1 ");
        }
        System.out.println();
    }
}