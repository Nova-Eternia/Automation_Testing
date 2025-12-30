
 
/**A class sixth student required to solve basic mathematics problems. For this he/ she needs to 
*perform operations such as addition, subtraction, multiplication, division, remainder, square, 
cube, and absolute. Write a program using methods to perform these basic operations. */

import java.util.Scanner;

public class A1Question1 {

    public void addition(int a, int b) {
        System.out.println("Addition of a and b : " + (a + b));
    }    
    public void subtraction(int a, int b) {
        System.out.println("Subtraction of a and b : " + (a - b));
    }    
    public void multiplication(int a, int b) {
        System.out.println("Multiplication of a and b : " + (a * b));
    }    
    public void division(int a, int b) {
        System.out.println("Division of a and b : " + (a / b));
    }    
    public void remainder(int a, int b) {
        System.out.println("Remainder of a and b : " + (a % b));
    }    
    public void square(int a) {
        System.out.println("Square of a : " + (a * a));
    }    
    public void cube(int a) {
        System.out.println("Cube of a : " + (a * a * a));
    }    
    public void absolute(int a) {
        if (a < 0) {
            a = -a;
        }
        System.out.println("Absolute of a : " + a);
    }    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A1Question1 obj = new A1Question1();

        char cont;

        do {
            System.out.println("\n1.Addition  2.Subtraction  3.Multiplication");
            System.out.println("4.Division  5.Remainder  6.Square");
            System.out.println("7.Cube      8.Absolute   9.Exit");
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();            

            if (choice >= 1 && choice <= 5) {
                System.out.print("Enter two numbers: ");
                int a = sc.nextInt();
                int b = sc.nextInt();

                switch (choice) {
                    case 1 -> obj.addition(a, b);
                    case 2 -> obj.subtraction(a, b);
                    case 3 -> obj.multiplication(a, b);
                    case 4 -> obj.division(a, b);
                    case 5 -> obj.remainder(a, b);
                }
            } 
            else if (choice >= 6 && choice <= 8) {
                System.out.print("Enter a number: ");
                int a = sc.nextInt();

                switch (choice) {
                    case 6 -> obj.square(a);
                    case 7 -> obj.cube(a);
                    case 8 -> obj.absolute(a);
                }
            } 
            else {
                System.out.println("Invalid choice!");
            }
            System.out.print("\nDo you want to continue (Y/N)? ");
            cont = sc.next().charAt(0);
        }while(cont == 'Y' || cont == 'y');
        sc.close();

    }
}
