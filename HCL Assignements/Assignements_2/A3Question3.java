import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Interface for Student Management Operations
interface StudentManagementInterface {
    void addStudent(Student student) throws InvalidStudentException;
    void displayAllStudents();
    void removeStudentByRollNo(int rollNo) throws StudentNotFoundException;
    Student searchStudentByRollNo(int rollNo) throws StudentNotFoundException;
}

// Custom Exception Classes
class InvalidStudentException extends Exception {
    public InvalidStudentException(String message) {
        super(message);
    }
}

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }
}

// Student Class
class Student {
    private int rollNo;
    private String name;
    private String email;
    private String phone;
    private double gpa;
    
    // Regex Patterns for validation
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PHONE_PATTERN = "^[0-9]{10}$";
    private static final String NAME_PATTERN = "^[A-Za-z\\s]+$";
    
    public Student(int rollNo, String name, String email, String phone, double gpa) 
            throws InvalidStudentException {
        if (!validateName(name)) {
            throw new InvalidStudentException("Invalid name! Name should contain only letters and spaces.");
        }
        if (!validateEmail(email)) {
            throw new InvalidStudentException("Invalid email format!");
        }
        if (!validatePhone(phone)) {
            throw new InvalidStudentException("Invalid phone number! Must be exactly 10 digits.");
        }
        if (gpa < 0.0 || gpa > 4.0) {
            throw new InvalidStudentException("Invalid GPA! Must be between 0.0 and 4.0.");
        }
        if (rollNo <= 0) {
            throw new InvalidStudentException("Invalid roll number! Must be positive.");
        }
        
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gpa = gpa;
    }
    
    // Getters
    public int getRollNo() {
        return rollNo;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public double getGpa() {
        return gpa;
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
    
    @Override
    public String toString() {
        return "Roll No: " + rollNo + 
               " | Name: " + name + 
               " | Email: " + email + 
               " | Phone: " + phone + 
               " | GPA: " + String.format("%.2f", gpa);
    }
}

// Implementation of StudentManagementInterface
class StudentManagementSystem implements StudentManagementInterface {
    private List<Student> students;
    
    public StudentManagementSystem(String collectionType) {
        switch (collectionType.toLowerCase()) {
            case "arraylist":
                this.students = new ArrayList<>();
                System.out.println("Using ArrayList for student records.");
                break;
            case "vector":
                this.students = new Vector<>();
                System.out.println("Using Vector for student records.");
                break;
            case "linkedlist":
                this.students = new LinkedList<>();
                System.out.println("Using LinkedList for student records.");
                break;
            default:
                this.students = new ArrayList<>();
                System.out.println("Using ArrayList (default) for student records.");
        }
    }
    
    @Override
    public void addStudent(Student student) throws InvalidStudentException {
        try {
            if (student == null) {
                throw new InvalidStudentException("Student object cannot be null.");
            }
            
            // Check if student with same roll number already exists
            for (Student s : students) {
                if (s.getRollNo() == student.getRollNo()) {
                    throw new InvalidStudentException("Student with roll number " + 
                            student.getRollNo() + " already exists.");
                }
            }
            
            students.add(student);
            System.out.println("✓ Student added successfully!");
        } catch (InvalidStudentException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidStudentException("Error adding student: " + e.getMessage());
        }
    }
    
    @Override
    public void displayAllStudents() {
        try {
            if (students.isEmpty()) {
                System.out.println("✗ No students found in the system.");
                return;
            }
            
            System.out.println("\n========== ALL STUDENTS ==========");
            System.out.println("Total Students: " + students.size());
            System.out.println("-".repeat(80));
            
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("-".repeat(80));
        } catch (Exception e) {
            System.out.println("✗ Error displaying students: " + e.getMessage());
        }
    }
    
    @Override
    public void removeStudentByRollNo(int rollNo) throws StudentNotFoundException {
        try {
            Student studentToRemove = null;
            
            for (Student s : students) {
                if (s.getRollNo() == rollNo) {
                    studentToRemove = s;
                    break;
                }
            }
            
            if (studentToRemove == null) {
                throw new StudentNotFoundException("Student with roll number " + 
                        rollNo + " not found!");
            }
            
            students.remove(studentToRemove);
            System.out.println("✓ Student with roll number " + rollNo + " removed successfully!");
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new StudentNotFoundException("Error removing student: " + e.getMessage());
        }
    }
    
    @Override
    public Student searchStudentByRollNo(int rollNo) throws StudentNotFoundException {
        try {
            for (Student s : students) {
                if (s.getRollNo() == rollNo) {
                    return s;
                }
            }
            throw new StudentNotFoundException("Student with roll number " + 
                    rollNo + " not found!");
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new StudentNotFoundException("Error searching student: " + e.getMessage());
        }
    }
    
    public int getStudentCount() {
        return students.size();
    }
}

// Main class
public class A3Question3 {
    static Scanner scanner = new Scanner(System.in);
    static StudentManagementSystem managementSystem;
    
    public static void main(String[] args) {
        try {
            System.out.println("========== STUDENT MANAGEMENT SYSTEM ==========");
            selectCollectionType();
            boolean exit = false;
            
            while (!exit) {
                displayMenu();
                int choice = getValidChoice();
                
                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        managementSystem.displayAllStudents();
                        break;
                    case 3:
                        removeStudent();
                        break;
                    case 4:
                        searchStudent();
                        break;
                    case 5:
                        exit = true;
                        System.out.println("\n=== Thank you for using Student Management System ===");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select between 1-5.");
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
    
    private static void selectCollectionType() {
        try {
            System.out.println("\nSelect Collection Type:");
            System.out.println("1. ArrayList");
            System.out.println("2. Vector");
            System.out.println("3. LinkedList");
            System.out.print("Enter your choice (1-3, default is ArrayList): ");
            
            String choice = scanner.nextLine().trim();
            String collectionType = "arraylist";
            
            if (choice.equals("1")) {
                collectionType = "arraylist";
            } else if (choice.equals("2")) {
                collectionType = "vector";
            } else if (choice.equals("3")) {
                collectionType = "linkedlist";
            }
            
            managementSystem = new StudentManagementSystem(collectionType);
        } catch (Exception e) {
            System.out.println("Error selecting collection type: " + e.getMessage());
            managementSystem = new StudentManagementSystem("arraylist");
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT MENU ==========");
        System.out.println("1. Add Student Record");
        System.out.println("2. Display All Students");
        System.out.println("3. Remove Student by Roll Number");
        System.out.println("4. Search Student by Roll Number");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }
    
    private static int getValidChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number between 1-5.");
            return -1;
        }
    }
    
    private static void addNewStudent() {
        try {
            System.out.print("\nEnter Roll Number: ");
            int rollNo = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter Student Name (letters and spaces only): ");
            String name = scanner.nextLine().trim();
            
            System.out.print("Enter Email ID: ");
            String email = scanner.nextLine().trim();
            
            System.out.print("Enter Phone Number (10 digits): ");
            String phone = scanner.nextLine().trim();
            
            System.out.print("Enter GPA (0.0 - 4.0): ");
            double gpa = Double.parseDouble(scanner.nextLine().trim());
            
            Student student = new Student(rollNo, name, email, phone, gpa);
            managementSystem.addStudent(student);
        } catch (InvalidStudentException e) {
            System.out.println("✗ Invalid Student Data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format! Please enter correct data types.");
        } catch (Exception e) {
            System.out.println("✗ Error adding student: " + e.getMessage());
        }
    }
    
    private static void removeStudent() {
        try {
            System.out.print("\nEnter Roll Number to Remove: ");
            int rollNo = Integer.parseInt(scanner.nextLine().trim());
            managementSystem.removeStudentByRollNo(rollNo);
        } catch (StudentNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid roll number! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void searchStudent() {
        try {
            System.out.print("\nEnter Roll Number to Search: ");
            int rollNo = Integer.parseInt(scanner.nextLine().trim());
            
            Student student = managementSystem.searchStudentByRollNo(rollNo);
            System.out.println("\n========== SEARCH RESULT ==========");
            System.out.println(student);
            System.out.println("✓ Student found successfully!");
        } catch (StudentNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid roll number! Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
