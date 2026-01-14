// 2	Write a program to demonstrate 15 methods of Vector, including all constructors.

import java.lang.*;
import java.util.Vector;
import java.util.Arrays;        
public class A3Question2 {
    public static void main(String[] args) {
        // Vector Constructors
        Vector<String> v1 = new Vector<>(); // Empty constructor
        Vector<String> v2 = new Vector<>(5); // With capacity
        Vector<String> v3 = new Vector<>(5, 3); // With capacity and increment
        Vector<String> v4 = new Vector<>(Arrays.asList("A", "B", "C")); // From collection
        
        System.out.println("=== Vector Constructors ===");
        System.out.println("v1 (Empty): " + v1);
        System.out.println("v2 (Capacity 5): " + v2);
        System.out.println("v3 (Capacity 5, Increment 3): " + v3);
        System.out.println("v4 (From List): " + v4);
        
        System.out.println("\n=== Vector Methods ===");
        
        // 1. add(E e)
        v1.add("X");
        v1.add("Y");
        System.out.println("1. add(): " + v1);
        
        // 2. add(int index, E e)
        v1.add(1, "Z");
        System.out.println("2. add(index, element): " + v1);
        
        // 3. addElement(E obj)
        v1.addElement("W");
        System.out.println("3. addElement(): " + v1);
        
        // 4. remove(int index)
        v1.remove(0);
        System.out.println("4. remove(index): " + v1);
        
        // 5. removeElement(Object obj)
        v1.removeElement("Z");
        System.out.println("5. removeElement(): " + v1);
        
        // 6. get(int index)
        System.out.println("6. get(0): " + v1.get(0));
        
        // 7. elementAt(int index)
        System.out.println("7. elementAt(0): " + v1.elementAt(0));
        
        // 8. set(int index, E e)
        v1.set(0, "M");
        System.out.println("8. set(index, element): " + v1);
        
        // 9. size()
        System.out.println("9. size(): " + v1.size());
        
        // 10. isEmpty()
        System.out.println("10. isEmpty(): " + v1.isEmpty());
        
        // 11. contains(Object o)
        System.out.println("11. contains(\"M\"): " + v1.contains("M"));
        
        // 12. indexOf(Object o)
        System.out.println("12. indexOf(\"M\"): " + v1.indexOf("M"));
        
        // 13. lastIndexOf(Object o)
        v1.add("M");
        System.out.println("13. lastIndexOf(\"M\"): " + v1.lastIndexOf("M"));
        
        // 14. removeAllElements()
        Vector<String> temp = new Vector<>(v1);
        temp.removeAllElements();
        System.out.println("14. removeAllElements(): " + temp);
        
        // 15. capacity()
        System.out.println("15. capacity(): " + v1.capacity());
    }
}
