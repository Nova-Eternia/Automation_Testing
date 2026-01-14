// 1	Write a program to demonstrate all constructors and 15 methods of ArrayList.

import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;


public class A3Question1 {
    public static void main(String[] args) {
        // ArrayList Constructors
        ArrayList<String> list1 = new ArrayList<>(); // Empty constructor
        ArrayList<String> list2 = new ArrayList<>(10); // With capacity
        ArrayList<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C")); // From collection
        
        System.out.println("=== ArrayList Constructors ===");
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        
        System.out.println("\n=== ArrayList Methods ===");
        
        // 1. add(E e) - Add element
        list1.add("X");
        list1.add("Y");
        System.out.println("1. add(): " + list1);
        
        // 2. add(int index, E e) - Add at index
        list1.add(1, "Z");
        System.out.println("2. add(index, element): " + list1);
        
        // 3. remove(int index) - Remove by index
        list1.remove(0);
        System.out.println("3. remove(index): " + list1);
        
        // 4. remove(Object o) - Remove by value
        list1.remove("Z");
        System.out.println("4. remove(object): " + list1);
        
        // 5. get(int index) - Get element
        System.out.println("5. get(0): " + list1.get(0));
        
        // 6. set(int index, E e) - Replace element
        list1.set(0, "M");
        System.out.println("6. set(index, element): " + list1);
        
        // 7. size() - Get size
        System.out.println("7. size(): " + list1.size());
        
        // 8. isEmpty() - Check if empty
        System.out.println("8. isEmpty(): " + list1.isEmpty());
        
        // 9. contains(Object o) - Check if contains
        System.out.println("9. contains(\"M\"): " + list1.contains("M"));
        
        // 10. indexOf(Object o) - Get index of element
        System.out.println("10. indexOf(\"M\"): " + list1.indexOf("M"));
        
        // 11. lastIndexOf(Object o) - Get last index
        list1.add("M");
        System.out.println("11. lastIndexOf(\"M\"): " + list1.lastIndexOf("M"));
        
        // 12. clear() - Remove all elements
        ArrayList<String> temp = new ArrayList<>(list1);
        temp.clear();
        System.out.println("12. clear(): " + temp);
        
        // 13. addAll(Collection c) - Add all from collection
        list1.addAll(list3);
        System.out.println("13. addAll(): " + list1);
        
        // 14. removeAll(Collection c) - Remove all from collection
        list1.removeAll(list3);
        System.out.println("14. removeAll(): " + list1);
        
        // 15. toArray() - Convert to array
        Object[] arr = list1.toArray();
        System.out.println("15. toArray(): " + Arrays.toString(arr));
    }
}
