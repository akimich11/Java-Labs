//Малыщик Аким Андреевич. Лабораторная работа 1. Вариант 7: sin x / x
import java.util.Scanner;

public class Lab1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        System.out.println("Enter x");
        double x = in.nextDouble();
        if(x == 0) {
        	System.out.println("Error: wrong x");
        	System.exit(1);
        }
        
        System.out.println("Enter k");
        int k = in.nextInt();
        if(k < 2) {
        	System.out.println("Error: k is too small");
        	System.exit(1);
        }

        double e = 1 / Math.pow( 10, k + 1 );
        double taylor_member = 1, answer = taylor_member;
        int denominator = 2;
        while (Math.abs(taylor_member) > e) {
            taylor_member *= (-1 * x * x) / (denominator * (denominator + 1));
            answer += taylor_member;
            denominator += 2;
        }
        
        String fmt = "%." + k + "f\n";
        
        System.out.print("(Taylor) sin x / x = ");
        System.out.printf(fmt, answer);
        System.out.print("(Math)   sin x / x = ");
        System.out.printf(fmt, (Math.sin(x) / x));

	}
}
