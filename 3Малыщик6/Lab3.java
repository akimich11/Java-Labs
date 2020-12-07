//Лабораторная работа 3. Малыщик Аким. Вариант 6.
import java.util.Random;
import java.util.Scanner;

public class Lab3 {

	public static void main(String[] args) {
		System.out.println("Enter matrix size:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();
        if(n < 1) {
        	System.out.println("Wrong size");
        	System.exit(1);
        }
        int[][] original_matrix = new int[n][n];
        double[][] new_matrix = new double[n][n];
        double[] avg = new double[n];
        Random rand = new Random();

        System.out.println("Original matrix:");
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                original_matrix[i][j] = rand.nextInt(2 * n + 1) - n; //[-n, n]
                avg[i] += original_matrix[i][j];
                System.out.printf("%5d", original_matrix[i][j]);
            }
            avg[i] /= n;
            for(int j = 0; j < n; j++)
                new_matrix[i][j] = original_matrix[i][j] - avg[i];
            System.out.println();
        }

        System.out.println("New matrix:");
        for (double[] i : new_matrix) {
            for(double j : i)
                System.out.printf("%7.2f", j);
            System.out.println();
        }

	}

}
