import java.util.Hashtable;
import java.util.Enumeration;

// 4	Write a program to demonstrate all constructors and 15 methods of HashTable.

public class A3Question4 {
    public static void main(String[] args) {
        // Hashtable Constructors
        Hashtable<String, Integer> table1 = new Hashtable<>();
        Hashtable<String, Integer> table2 = new Hashtable<>(10);
        Hashtable<String, Integer> table3 = new Hashtable<>(10, 0.75f);
        Hashtable<String, Integer> table4 = new Hashtable<>(table1);
        
        // 1. put(K key, V value)
        table1.put("One", 1);
        table1.put("Two", 2);
        System.out.println("1. put(): " + table1);
        
        // 2. putIfAbsent(K key, V value)
        table1.putIfAbsent("Three", 3);
        table1.putIfAbsent("One", 10);
        System.out.println("2. putIfAbsent(): " + table1);
        
        // 3. get(Object key)
        System.out.println("3. get(\"One\"): " + table1.get("One"));
        
        // 4. getOrDefault(Object key, V defaultValue)
        System.out.println("4. getOrDefault(\"Five\", 5): " + table1.getOrDefault("Five", 5));
        
        // 5. remove(Object key)
        table1.remove("Three");
        System.out.println("5. remove(\"Three\"): " + table1);
        
        // 6. remove(Object key, Object value)
        table1.put("Two", 2);
        table1.remove("Two", 2);
        System.out.println("6. remove(key, value): " + table1);
        
        // 7. size()
        System.out.println("7. size(): " + table1.size());
        
        // 8. isEmpty()
        System.out.println("8. isEmpty(): " + table1.isEmpty());
        
        // 9. containsKey(Object key)
        System.out.println("9. containsKey(\"One\"): " + table1.containsKey("One"));
        
        // 10. containsValue(Object value)
        System.out.println("10. containsValue(1): " + table1.containsValue(1));
        
        // 11. keys() - Returns Enumeration
        System.out.println("11. keys(): ");
        Enumeration<String> keys = table1.keys();
        while(keys.hasMoreElements()) {
            System.out.println("    " + keys.nextElement());
        }
        
        // 12. elements() - Returns Enumeration
        System.out.println("12. elements(): ");
        Enumeration<Integer> elements = table1.elements();
        while(elements.hasMoreElements()) {
            System.out.println("    " + elements.nextElement());
        }
        
        // 13. keySet()
        System.out.println("13. keySet(): " + table1.keySet());
        
        // 14. putAll(Map m)
        Hashtable<String, Integer> temp = new Hashtable<>();
        temp.put("Four", 4);
        table1.putAll(temp);
        System.out.println("14. putAll(): " + table1);
        
        // 15. clear()
        Hashtable<String, Integer> table5 = new Hashtable<>(table1);
        table5.clear();
        System.out.println("15. clear(): " + table5);
    }
}
