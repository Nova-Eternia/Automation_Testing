

// 3	Write a program to demonstrate all constructors and 15 methods of HashMap.


import java.util.HashMap;
import java.util.Map;
public class A3Question3 {
    public static void main(String[] args) {
        // HashMap Constructors
        HashMap<String, Integer> map1 = new HashMap<>(); // Empty constructor
        HashMap<String, Integer> map2 = new HashMap<>(10); // With capacity
        HashMap<String, Integer> map3 = new HashMap<>(10, 0.75f); // With capacity and load factor
        HashMap<String, Integer> map4 = new HashMap<>(map1); // From another map
        
        System.out.println("=== HashMap Constructors ===");
        System.out.println("map1 (Empty): " + map1);
        System.out.println("map2 (Capacity 10): " + map2);
        System.out.println("map3 (Capacity 10, Load 0.75): " + map3);
        System.out.println("map4 (From map): " + map4);
        
        System.out.println("\n=== HashMap Methods ===");
        
        // 1. put(K key, V value)
        map1.put("One", 1);
        map1.put("Two", 2);
        System.out.println("1. put(): " + map1);
        
        // 2. putIfAbsent(K key, V value)
        map1.putIfAbsent("Three", 3);
        map1.putIfAbsent("One", 10);
        System.out.println("2. putIfAbsent(): " + map1);
        
        // 3. get(Object key)
        System.out.println("3. get(\"One\"): " + map1.get("One"));
        
        // 4. getOrDefault(Object key, V defaultValue)
        System.out.println("4. getOrDefault(\"Five\", 5): " + map1.getOrDefault("Five", 5));
        
        // 5. remove(Object key)
        map1.remove("Three");
        System.out.println("5. remove(\"Three\"): " + map1);
        
        // 6. remove(Object key, Object value)
        map1.put("Two", 2);
        map1.remove("Two", 2);
        System.out.println("6. remove(key, value): " + map1);
        
        // 7. size()
        System.out.println("7. size(): " + map1.size());
        
        // 8. isEmpty()
        System.out.println("8. isEmpty(): " + map1.isEmpty());
        
        // 9. containsKey(Object key)
        System.out.println("9. containsKey(\"One\"): " + map1.containsKey("One"));
        
        // 10. containsValue(Object value)
        System.out.println("10. containsValue(1): " + map1.containsValue(1));
        
        // 11. keySet()
        System.out.println("11. keySet(): " + map1.keySet());
        
        // 12. values()
        System.out.println("12. values(): " + map1.values());
        
        // 13. entrySet()
        System.out.println("13. entrySet(): " + map1.entrySet());
        
        // 14. putAll(Map m)
        HashMap<String, Integer> temp = new HashMap<>();
        temp.put("Four", 4);
        map1.putAll(temp);
        System.out.println("14. putAll(): " + map1);
        
        // 15. clear()
        HashMap<String, Integer> map5 = new HashMap<>(map1);
        map5.clear();
        System.out.println("15. clear(): " + map5);
    }
}
