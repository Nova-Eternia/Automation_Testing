/**
 * Write a program to perform addition, subtraction, multiplication, transpose, to check square 
 * matrix, to check matrix is diagonal, and to check matrix is identity. 
 */
import java.util.Scanner;

public class A1Question3 {

    static Scanner sc = new Scanner(System.in);

    static int[][] readMatrix(int r, int c) {
        int[][] m = new int[r][c];
        System.out.println("Enter matrix elements:");
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                m[i][j] = sc.nextInt();
        return m;
    }
    static void displayMatrix(int[][] m) {
        for (int[] row : m) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }
    static void addition(int[][] a, int[][] b, int r, int c) {
        int[][] sum = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                sum[i][j] = a[i][j] + b[i][j];

        System.out.println("Addition:");
        displayMatrix(sum);
    }
    static void subtraction(int[][] a, int[][] b, int r, int c) {
        int[][] diff = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                diff[i][j] = a[i][j] - b[i][j];

        System.out.println("Subtraction:");
        displayMatrix(diff);
    }
    static void multiplication(int[][] a, int[][] b, int n) {
        int[][] mul = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    mul[i][j] += a[i][k] * b[k][j];

        System.out.println("Multiplication:");
        displayMatrix(mul);
    }
    static void transpose(int[][] a, int r, int c) {
        System.out.println("Transpose:");
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++)
                System.out.print(a[j][i] + " ");
            System.out.println();
        }
    }

    static boolean isSquare(int r, int c) {
        return r == c;
    }
    static boolean isDiagonal(int[][] a, int n) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && a[i][j] != 0)
                    return false;
        return true;
    }
    static boolean isIdentity(int[][] a, int n) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if ((i == j && a[i][j] != 1) || (i != j && a[i][j] != 0))
                    return false;
        return true;
    }

    public static void main(String[] args) {

        System.out.print("Enter number of rows and columns: ");
        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] A = readMatrix(r, c);
        int[][] B = readMatrix(r, c);

        addition(A, B, r, c);
        subtraction(A, B, r, c);

        if (isSquare(r, c)) {
            multiplication(A, B, r);
            transpose(A, r, c);

            System.out.println("Square Matrix: YES");
            System.out.println("Diagonal Matrix: " + (isDiagonal(A, r) ? "YES" : "NO"));
            System.out.println("Identity Matrix: " + (isIdentity(A, r) ? "YES" : "NO"));
        } else {
            System.out.println("Matrix is NOT square.");
        }
    }
}
