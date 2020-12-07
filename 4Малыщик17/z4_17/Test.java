package z4_17;

public class Test {

    public static void main(String[] args) {
	    BooleanVector v1 = new BooleanVector();
	    BooleanVector v2 = new BooleanVector(3);
	    BooleanVector v3 = new BooleanVector(3, new boolean[] {true, true, false});
        System.out.print("v1: ");
	    v1.print();
        System.out.print("v2: ");
        v2.print();
        System.out.print("v3: ");
        v3.print();
        v2.conjunction_with(v3); //результат конъюнкции сохраняется в v2
        System.out.print("v2 && v3: ");
        v2.print();
        v3.disjunction_with(v2);
        System.out.print("v3 || v2: ");
        v3.print();
        v1.negation();
        System.out.print("Negation of v1: ");
        v1.print();
        System.out.println("Number of zeroes in v1: " + v1.count_zeroes());
        System.out.println("Number of ones in v3: " + v3.count_ones());
    }
}
