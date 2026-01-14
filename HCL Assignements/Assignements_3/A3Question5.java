// Write a program to demonstrate all constructors and 15 methods of TreeMap.

import java.util.Stack;

public class A3Question5 {
    public static void main(String[] args) {
        // Stack Constructors
        Stack<Integer> stack1 = new Stack<>(); // Empty constructor
        Stack<Integer> stack2 = new Stack<>();
        stack2.push(10);
        stack2.push(20); // Stack with elements
        
        System.out.println("=== Stack Constructors ===");
        System.out.println("stack1 (Empty): " + stack1);
        System.out.println("stack2 (With elements): " + stack2);
        
        System.out.println("\n=== Stack Methods ===");
        
        // 1. push(E item)
        stack1.push(1);
        stack1.push(2);
        System.out.println("1. push(): " + stack1);
        
        // 2. pop()
        System.out.println("2. pop(): " + stack1.pop());
        System.out.println("   After pop: " + stack1);
        
        // 3. peek()
        System.out.println("3. peek(): " + stack1.peek());
        
        // 4. isEmpty()
        System.out.println("4. isEmpty(): " + stack1.isEmpty());
        
        // 5. search(Object o)
        stack1.push(3);
        stack1.push(4);
        System.out.println("5. search(3): " + stack1.search(3));
    }
}
