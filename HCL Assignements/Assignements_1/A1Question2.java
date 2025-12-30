/**2. Write a menu driven program in Java to perform insertion, deletion, linear search, binary 
*search, to find maximum value, to count even/ odd and to perform insertion sort operation in 
one dimensional array.*/ 

import java.util.Scanner;

public class A1Question2 
{
    
    public void linear_search(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) 
            {
                System.out.println("Element found at index: " + i);
                return;
            }
        }
        System.out.println("Element not found in the array.");
    }
    public void binary_search(int[] arr, int key) 
    {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) 
        {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) 
            {
                System.out.println("Element found at index: " + mid);
                return;
            }
            if (arr[mid] < key) 
            {
                left = mid + 1;
            } 
            else 
            {
                right = mid - 1;
            }
        }
        System.out.println("Element not found in the array.");
    }
    public void find_maximum(int[] arr) 
    {
        int max = arr[0];
        for (int num : arr) 
        {
            if (num > max) 
            {
                max = num;
            }
        }
        System.out.println("Maximum value in the array: " + max);
    }

    public void count_even_odd(int[] arr) 
    {
        int evenCount = 0;
        int oddCount = 0;
        for (int num : arr) 
        {
            if (num % 2 == 0) 
            {
                evenCount++;
            } 
            else 
            {
                oddCount++;
            }
        }
        System.out.println("Even count: " + evenCount);
        System.out.println("Odd count: " + oddCount);
    }
    public void insertion_sort(int[] arr) 
    {
        for (int i = 1; i < arr.length; i++) 
        {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) 
            {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        System.out.println("Array sorted using Insertion Sort:");
        for (int num : arr) 
        {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public void insertion(int[] arr, int element, int position) 
    {
        int[] newArr = new int[arr.length + 1];
        int j = 0;
        for (int i = 0; i < newArr.length; i++) 
        {
            if (i == position) 
            {
                newArr[i] = element;
            } 
            else 
            {
                newArr[i] = arr[j++];
            }
        }
        System.out.println("Array after insertion:");
        for (int num : newArr) 
        {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public void deletion(int[] arr, int position) 
    {
       
        int[] newArr = new int[arr.length - 1];
        int j = 0;
        for (int i = 0; i < arr.length; i++) 
        {
            if (i != position) 
            {
                newArr[j++] = arr[i];
            }
        }
        System.out.println("Array after deletion:");
        for (int num : newArr) 
        {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        A1Question2 obj = new A1Question2();

        System.out.print("\nEnter size of array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter elements of array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        char cont;
        do{
            System.out.println("\nArray Operations : \n1.Linear Search  2.Binary Search  3.Find Maximum  \n4.Count Even/Odd    5.Insertion Sort  6.Insertion    \n7.Deletion ");
            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();


            switch (choice) {
                case 1 -> {
                    System.out.print("Enter element to search: ");
                    int key = sc.nextInt();
                    obj.linear_search(arr, key);
                }
                case 2 -> {
                    System.out.print("Enter element to search: ");
                    int key = sc.nextInt();
                    obj.binary_search(arr, key);
                }
                case 3 -> obj.find_maximum(arr);
                case 4 -> obj.count_even_odd(arr);
                case 5 -> obj.insertion_sort(arr);
                case 6 -> {
                    System.out.print("Enter element to insert: ");
                    int element = sc.nextInt();
                    System.out.print("Enter position to insert (0 to " + n + "): ");
                    int position = sc.nextInt();
                    obj.insertion(arr, element, position);
                }
                case 7 -> {
                    System.out.print("Enter position to delete (0 to " + (n - 1) + "): ");
                    int position = sc.nextInt();
                    obj.deletion(arr, position);
                }
                case 8 -> System.out.println("Exiting the program. Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
            System.out.print("\nDo you want to continue (Y/N)? ");
            cont = sc.next().charAt(0);

        }while(cont == 'Y' || cont == 'y');

        sc.close();

    }

}