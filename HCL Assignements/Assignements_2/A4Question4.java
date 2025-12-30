import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Interface for Employee Management Operations
interface EmployeeManagementInterface {
    void addEmployee(Integer empId, Employee employee) throws InvalidEmployeeException;
    void displayAllEmployees();
    void removeEmployeeById(Integer empId) throws EmployeeNotFoundException;
    Employee searchEmployeeById(Integer empId) throws EmployeeNotFoundException;
}

// Custom Exception Classes
class InvalidEmployeeException extends Exception {
    public InvalidEmployeeException(String message) {
        super(message);
    }
}

class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

// Employee Class
class Employee {
    private String name;
    private String email;
    private String phone;
    private double salary;
    private String department;
    
    // Regex Patterns for validation
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PHONE_PATTERN = "^[0-9]{10}$";
    private static final String NAME_PATTERN = "^[A-Za-z\\s]+$";
    private static final String DEPARTMENT_PATTERN = "^[A-Za-z\\s]+$";
    
    public Employee(String name, String email, String phone, double salary, String department) 
            throws InvalidEmployeeException {
        if (!validateName(name)) {
            throw new InvalidEmployeeException("Invalid name! Name should contain only letters and spaces.");
        }
        if (!validateEmail(email)) {
            throw new InvalidEmployeeException("Invalid email format!");
        }
        if (!validatePhone(phone)) {
            throw new InvalidEmployeeException("Invalid phone number! Must be exactly 10 digits.");
        }
        if (salary <= 0) {
            throw new InvalidEmployeeException("Invalid salary! Must be positive.");
        }
        if (!validateDepartment(department)) {
            throw new InvalidEmployeeException("Invalid department! Department should contain only letters and spaces.");
        }
        
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String getDepartment() {
        return department;
    }
    
    // Validation methods using REGEX
    private static boolean validateName(String name) {
        return Pattern.matches(NAME_PATTERN, name) && !name.trim().isEmpty();
    }
    
    private static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    private static boolean validatePhone(String phone) {
        return Pattern.matches(PHONE_PATTERN, phone);
    }
    
    private static boolean validateDepartment(String department) {
        return Pattern.matches(DEPARTMENT_PATTERN, department) && !department.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "Name: " + name + 
               " | Email: " + email + 
               " | Phone: " + phone + 
               " | Salary: ₹" + String.format("%.2f", salary) + 
               " | Department: " + department;
    }
}

// Implementation of EmployeeManagementInterface
class EmployeeManagementSystem implements EmployeeManagementInterface {
    private Map<Integer, Employee> employees;
    private String mapType;
    
    public EmployeeManagementSystem(String mapType) {
        this.mapType = mapType;
        switch (mapType.toLowerCase()) {
            case "hashmap":
                this.employees = new HashMap<>();
                System.out.println("✓ Using HashMap for employee records (supports null keys and values).");
                break;
            case "hashtable":
                this.employees = new Hashtable<>();
                System.out.println("✓ Using Hashtable for employee records (does NOT support null keys or values).");
                break;
            case "treemap":
                this.employees = new TreeMap<>();
                System.out.println("✓ Using TreeMap for employee records (does NOT support null keys, but supports null values).");
                break;
            default:
                this.employees = new HashMap<>();
                System.out.println("✓ Using HashMap (default) for employee records.");
                this.mapType = "hashmap";
        }
    }
    
    @Override
    public void addEmployee(Integer empId, Employee employee) throws InvalidEmployeeException {
        try {
            if (empId == null && !mapType.equalsIgnoreCase("hashmap")) {
                throw new InvalidEmployeeException("Employee ID cannot be null with " + mapType + ".");
            }
            
            if (employee == null && !mapType.equalsIgnoreCase("hashmap")) {
                throw new InvalidEmployeeException("Employee object cannot be null with " + mapType + ".");
            }
            
            if (empId != null && empId <= 0) {
                throw new InvalidEmployeeException("Employee ID must be a positive number.");
            }
            
            if (empId != null && employees.containsKey(empId)) {
                throw new InvalidEmployeeException("Employee with ID " + empId + " already exists.");
            }
            
            employees.put(empId, employee);
            System.out.println("✓ Employee added successfully!");
        } catch (InvalidEmployeeException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidEmployeeException("Error adding employee: " + e.getMessage());
        }
    }
    
    @Override
    public void displayAllEmployees() {
        try {
            if (employees.isEmpty()) {
                System.out.println("✗ No employees found in the system.");
                return;
            }
            
            System.out.println("\n========== ALL EMPLOYEES ==========");
            System.out.println("Total Employees: " + employees.size());
            System.out.println("-".repeat(90));
            
            for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
                Integer empId = entry.getKey();
                Employee employee = entry.getValue();
                
                String empIdStr = empId == null ? "NULL" : empId.toString();
                String empStr = employee == null ? "NULL" : employee.toString();
                
                System.out.println("Emp ID: " + empIdStr + " | " + empStr);
            }
            System.out.println("-".repeat(90));
        } catch (Exception e) {
            System.out.println("✗ Error displaying employees: " + e.getMessage());
        }
    }
    
    @Override
    public void removeEmployeeById(Integer empId) throws EmployeeNotFoundException {
        try {
            if (empId == null) {
                throw new EmployeeNotFoundException("Employee ID cannot be null.");
            }
            
            if (!employees.containsKey(empId)) {
                throw new EmployeeNotFoundException("Employee with ID " + empId + " not found!");
            }
            
            employees.remove(empId);
            System.out.println("✓ Employee with ID " + empId + " removed successfully!");
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Error removing employee: " + e.getMessage());
        }
    }
    
    @Override
    public Employee searchEmployeeById(Integer empId) throws EmployeeNotFoundException {
        try {
            if (empId == null) {
                throw new EmployeeNotFoundException("Employee ID cannot be null for search.");
            }
            
            if (!employees.containsKey(empId)) {
                throw new EmployeeNotFoundException("Employee with ID " + empId + " not found!");
            }
            
            return employees.get(empId);
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Error searching employee: " + e.getMessage());
        }
    }
    
    public int getEmployeeCount() {
        return employees.size();
    }
    
    public String getMapType() {
        return mapType;
    }
}

// Main class
public class A4Question4 {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeManagementSystem managementSystem;
    
    public static void main(String[] args) {
        try {
            System.out.println("========== EMPLOYEE MANAGEMENT SYSTEM ==========");
            selectMapType();
            boolean exit = false;
            
            while (!exit) {
                displayMenu();
                int choice = getValidChoice();
                
                switch (choice) {
                    case 1:
                        addNewEmployee();
                        break;
                    case 2:
                        managementSystem.displayAllEmployees();
                        break;
                    case 3:
                        searchEmployee();
                        break;
                    case 4:
                        removeEmployee();
                        break;
                    case 5:
                        demonstrateNullHandling();
                        break;
                    case 6:
                        exit = true;
                        System.out.println("\n=== Thank you for using Employee Management System ===");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select between 1-6.");
                }
                
                if (!exit) {
                    System.out.print("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    private static void selectMapType() {
        try {
            System.out.println("\nSelect Map Type:");
            System.out.println("1. HashMap (allows null keys and null values)");
            System.out.println("2. Hashtable (does NOT allow null keys or null values)");
            System.out.println("3. TreeMap (does NOT allow null keys, but allows null values)");
            System.out.print("Enter your choice (1-3, default is HashMap): ");
            
            String choice = scanner.nextLine().trim();
            String mapType = "hashmap";
            
            if (choice.equals("1")) {
                mapType = "hashmap";
            } else if (choice.equals("2")) {
                mapType = "hashtable";
            } else if (choice.equals("3")) {
                mapType = "treemap";
            }
            
            managementSystem = new EmployeeManagementSystem(mapType);
        } catch (Exception e) {
            System.out.println("Error selecting map type: " + e.getMessage());
            managementSystem = new EmployeeManagementSystem("hashmap");
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n========== EMPLOYEE MANAGEMENT MENU ==========");
        System.out.println("1. Add Employee Record");
        System.out.println("2. Display All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Remove Employee by ID");
        System.out.println("5. Demonstrate Null Key/Value Handling");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }
    
    private static int getValidChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number between 1-6.");
            return -1;
        }
    }
    
    private static void addNewEmployee() {
        try {
            System.out.print("\nEnter Employee ID: ");
            Integer empId = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter Employee Name (letters and spaces only): ");
            String name = scanner.nextLine().trim();
            
            System.out.print("Enter Email ID: ");
            String email = scanner.nextLine().trim();
            
            System.out.print("Enter Phone Number (10 digits): ");
            String phone = scanner.nextLine().trim();
            
            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Enter Department: ");
            String department = scanner.nextLine().trim();
            
            Employee employee = new Employee(name, email, phone, salary, department);
            managementSystem.addEmployee(empId, employee);
        } catch (InvalidEmployeeException e) {
            System.out.println("✗ Invalid Employee Data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format! Please enter correct data types.");
        } catch (Exception e) {
            System.out.println("✗ Error adding employee: " + e.getMessage());
        }
    }
    
    private static void searchEmployee() {
        try {
            System.out.print("\nEnter Employee ID to Search: ");
            Integer empId = Integer.parseInt(scanner.nextLine().trim());
            
            Employee employee = managementSystem.searchEmployeeById(empId);
            System.out.println("\n========== SEARCH RESULT ==========");
            System.out.println("Emp ID: " + empId + " | " + employee);
            System.out.println("✓ Employee found successfully!");
        } catch (EmployeeNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid employee ID! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void removeEmployee() {
        try {
            System.out.print("\nEnter Employee ID to Remove: ");
            Integer empId = Integer.parseInt(scanner.nextLine().trim());
            managementSystem.removeEmployeeById(empId);
        } catch (EmployeeNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid employee ID! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateNullHandling() {
        String mapType = managementSystem.getMapType();
        System.out.println("\n========== NULL KEY/VALUE HANDLING DEMO ==========");
        System.out.println("Current Map Type: " + mapType.toUpperCase());
        System.out.println("-".repeat(50));
        
        if (mapType.equalsIgnoreCase("hashmap")) {
            System.out.println("\nHashMap allows null keys and null values.");
            System.out.println("\nAttempting to add null key with null value...");
            try {
                managementSystem.addEmployee(null, null);
                System.out.println("✓ Successfully added entry with null key and null value!");
                managementSystem.displayAllEmployees();
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        } else if (mapType.equalsIgnoreCase("hashtable")) {
            System.out.println("\nHashtable does NOT allow null keys or null values.");
            System.out.println("\nAttempting to add null key...");
            try {
                managementSystem.addEmployee(null, new Employee("Test", "test@example.com", "1234567890", 50000, "IT"));
                System.out.println("✓ Successfully added!");
            } catch (Exception e) {
                System.out.println("✗ Error (as expected): " + e.getMessage());
            }
            
            System.out.println("\nAttempting to add null value...");
            try {
                managementSystem.addEmployee(999, null);
                System.out.println("✓ Successfully added!");
            } catch (Exception e) {
                System.out.println("✗ Error (as expected): " + e.getMessage());
            }
        } else if (mapType.equalsIgnoreCase("treemap")) {
            System.out.println("\nTreeMap does NOT allow null keys, but allows null values.");
            System.out.println("\nAttempting to add null key...");
            try {
                managementSystem.addEmployee(null, new Employee("Test", "test@example.com", "1234567890", 50000, "IT"));
                System.out.println("✓ Successfully added!");
            } catch (Exception e) {
                System.out.println("✗ Error (as expected): " + e.getMessage());
            }
            
            System.out.println("\nAttempting to add null value...");
            try {
                managementSystem.addEmployee(999, null);
                System.out.println("✓ Successfully added null value!");
                managementSystem.displayAllEmployees();
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }
}
