package z5_6;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

		//тест конструкторов
	    IsoscelesTriangle[] isoscelesTriangles = new IsoscelesTriangle[] {
	            new IsoscelesTriangle(4, 3.05, "red", "grey"),
                new IsoscelesTriangle(3, 1.43, "green", "orange"),
				new IsoscelesTriangle("1 1 2.44 blue pink", " "),
				new IsoscelesTriangle("2/2/0.86/yellow/purple", "/"),
                new IsoscelesTriangle()
        };
	    System.out.println(" a  |  b  |angle | color1 | color2");
	    System.out.println("----------------------------------");
	    //тест цикла forEach и метода toString()
		for(IsoscelesTriangle i : isoscelesTriangles)
			System.out.println(i.toString());

	    //тест вычисления площади и периметра
		System.out.println("\nPerimeters of 1st and 2nd triangles:");
		System.out.printf("%.2f %.2f\n", isoscelesTriangles[0].perimeter(), isoscelesTriangles[1].perimeter());
		System.out.println("\nAreas of 1st and 2nd triangles:");
		System.out.printf("%.2f %.2f\n", isoscelesTriangles[0].area(), isoscelesTriangles[1].area());

	    //метод compareTo() сравнивает треугольники по их площади
		System.out.println("\nCompare 1st and 2nd triangles:");
		System.out.println(isoscelesTriangles[0].compareTo(isoscelesTriangles[1]));

		//тест сортировки
		Arrays.sort(isoscelesTriangles, IsoscelesTriangle.getComparator(2)); //можно выбрать другое поле от 0 до 4
		System.out.println("\nSorted array (by angle):");
		System.out.println(" a  |  b  |angle | color1 | color2");
		System.out.println("----------------------------------");
		for(IsoscelesTriangle i : isoscelesTriangles)
			System.out.println(i.toString());

		//тест assert
		//new IsoscelesTriangle("assert test"," ");
    }
}