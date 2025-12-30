
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

// Interface for University Management
interface UniversityManagementInterface {
    void addStudent(Student student) throws InvalidStudentException;
    void displayAllStudents();
    void removeStudentById(int studentId) throws StudentNotFoundException;
    Student searchStudentById(int studentId) throws StudentNotFoundException;
    void sortStudentsByMarks();
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

class InvalidCourseException extends Exception {
    public InvalidCourseException(String message) {
        super(message);
    }
}

// Course Class
class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    
    private static final String COURSE_CODE_PATTERN = "^[A-Z]{2,4}[0-9]{3,4}$";
    private static final String COURSE_NAME_PATTERN = "^[A-Za-z\\s&()]+$";
    
    public Course(String courseCode, String courseName, int credits) throws InvalidCourseException {
        if (!validateCourseCode(courseCode)) {
            throw new InvalidCourseException("Invalid course code! Format: ABC123 (letters followed by digits).");
        }
        if (!validateCourseName(courseName)) {
            throw new InvalidCourseException("Invalid course name! Only letters, spaces, & and () are allowed.");
        }
        if (credits <= 0 || credits > 10) {
            throw new InvalidCourseException("Invalid credits! Must be between 1 and 10.");
        }
        
        this.courseCode = courseCode.toUpperCase();
        this.courseName = courseName;
        this.credits = credits;
    }
    
    private static boolean validateCourseCode(String courseCode) {
        return Pattern.matches(COURSE_CODE_PATTERN, courseCode);
    }
    
    private static boolean validateCourseName(String courseName) {
        return Pattern.matches(COURSE_NAME_PATTERN, courseName) && !courseName.trim().isEmpty();
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public int getCredits() {
        return credits;
    }
    
    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + credits + " Credits)";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return courseCode.equals(course.courseCode);
    }
    
    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}

// Student Class
class Student implements Comparable<Student> {
    private int studentId;
    private String name;
    private String email;
    private String phone;
    private List<Course> enrolledCourses;
    private Map<String, Double> courseMarks;
    
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PHONE_PATTERN = "^[0-9]{10}$";
    private static final String NAME_PATTERN = "^[A-Za-z\\s]+$";
    
    public Student(int studentId, String name, String email, String phone) 
            throws InvalidStudentException {
        if (studentId <= 0) {
            throw new InvalidStudentException("Invalid student ID! Must be positive.");
        }
        if (!validateName(name)) {
            throw new InvalidStudentException("Invalid name! Only letters and spaces are allowed.");
        }
        if (!validateEmail(email)) {
            throw new InvalidStudentException("Invalid email format!");
        }
        if (!validatePhone(phone)) {
            throw new InvalidStudentException("Invalid phone number! Must be exactly 10 digits.");
        }
        
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.enrolledCourses = new ArrayList<>();
        this.courseMarks = new HashMap<>();
    }
    
    private static boolean validateName(String name) {
        return Pattern.matches(NAME_PATTERN, name) && !name.trim().isEmpty();
    }
    
    private static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }
    
    private static boolean validatePhone(String phone) {
        return Pattern.matches(PHONE_PATTERN, phone);
    }
    
    public int getStudentId() {
        return studentId;
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
    
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    
    public void enrollCourse(Course course) throws InvalidCourseException {
        if (course == null) {
            throw new InvalidCourseException("Course cannot be null.");
        }
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }
    
    public void addMarks(String courseCode, double marks) throws InvalidStudentException {
        if (marks < 0 || marks > 100) {
            throw new InvalidStudentException("Invalid marks! Must be between 0 and 100.");
        }
        courseMarks.put(courseCode, marks);
    }
    
    public Map<String, Double> getCourseMarks() {
        return courseMarks;
    }
    
    public double getAverageMarks() {
        if (courseMarks.isEmpty()) return 0.0;
        return courseMarks.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
    
    @Override
    public int compareTo(Student other) {
        return Double.compare(other.getAverageMarks(), this.getAverageMarks());
    }
    
    @Override
    public String toString() {
        return "ID: " + studentId + 
               " | Name: " + name + 
               " | Email: " + email + 
               " | Phone: " + phone + 
               " | Avg Marks: " + String.format("%.2f", getAverageMarks());
    }
}

// University Management System Class
class UniversityManagementSystem implements UniversityManagementInterface {
    private List<Student> studentList;
    private Set<Course> courseSet;
    private Map<Integer, Student> studentMap;
    private Map<String, Integer> courseWiseCount;
    
    public UniversityManagementSystem() {
        this.studentList = new ArrayList<>();
        this.courseSet = new HashSet<>();
        this.studentMap = new HashMap<>();
        this.courseWiseCount = new HashMap<>();
    }
    
    @Override
    public void addStudent(Student student) throws InvalidStudentException {
        try {
            if (student == null) {
                throw new InvalidStudentException("Student object cannot be null.");
            }
            if (studentMap.containsKey(student.getStudentId())) {
                throw new InvalidStudentException("Student with ID " + student.getStudentId() + " already exists.");
            }
            
            studentList.add(student);
            studentMap.put(student.getStudentId(), student);
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
            if (studentList.isEmpty()) {
                System.out.println("✗ No students found in the system.");
                return;
            }
            
            System.out.println("\n========== ALL STUDENTS ==========");
            System.out.println("Total Students: " + studentList.size());
            System.out.println("-".repeat(100));
            
            for (Student student : studentList) {
                System.out.println(student);
                if (!student.getEnrolledCourses().isEmpty()) {
                    System.out.println("   Courses: " + student.getEnrolledCourses().stream()
                            .map(Course::getCourseCode).collect(Collectors.joining(", ")));
                }
                if (!student.getCourseMarks().isEmpty()) {
                    System.out.println("   Marks: " + student.getCourseMarks());
                }
            }
            System.out.println("-".repeat(100));
        } catch (Exception e) {
            System.out.println("✗ Error displaying students: " + e.getMessage());
        }
    }
    
    @Override
    public void removeStudentById(int studentId) throws StudentNotFoundException {
        try {
            Student student = studentMap.get(studentId);
            if (student == null) {
                throw new StudentNotFoundException("Student with ID " + studentId + " not found!");
            }
            
            studentList.remove(student);
            studentMap.remove(studentId);
            
            for (Course course : student.getEnrolledCourses()) {
                courseWiseCount.put(course.getCourseCode(), 
                    courseWiseCount.getOrDefault(course.getCourseCode(), 1) - 1);
            }
            
            System.out.println("✓ Student with ID " + studentId + " removed successfully!");
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new StudentNotFoundException("Error removing student: " + e.getMessage());
        }
    }
    
    @Override
    public Student searchStudentById(int studentId) throws StudentNotFoundException {
        try {
            Student student = studentMap.get(studentId);
            if (student == null) {
                throw new StudentNotFoundException("Student with ID " + studentId + " not found!");
            }
            return student;
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new StudentNotFoundException("Error searching student: " + e.getMessage());
        }
    }
    
    @Override
    public void sortStudentsByMarks() {
        try {
            if (studentList.isEmpty()) {
                System.out.println("✗ No students found to sort.");
                return;
            }
            
            List<Student> sortedStudents = new ArrayList<>(studentList);
            Collections.sort(sortedStudents);
            
            System.out.println("\n========== STUDENTS SORTED BY MARKS (Descending) ==========");
            System.out.println("-".repeat(100));
            int rank = 1;
            for (Student student : sortedStudents) {
                System.out.println("Rank " + rank + ": " + student);
                rank++;
            }
            System.out.println("-".repeat(100));
        } catch (Exception e) {
            System.out.println("✗ Error sorting students: " + e.getMessage());
        }
    }
    
    public void enrollStudentInCourse(int studentId, Course course) throws StudentNotFoundException, InvalidCourseException {
        try {
            Student student = searchStudentById(studentId);
            student.enrollCourse(course);
            courseSet.add(course);
            courseWiseCount.put(course.getCourseCode(), 
                courseWiseCount.getOrDefault(course.getCourseCode(), 0) + 1);
            System.out.println("✓ Student enrolled in course successfully!");
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (InvalidCourseException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidCourseException("Error enrolling student: " + e.getMessage());
        }
    }
    
    public void addStudentMarks(int studentId, String courseCode, double marks) 
            throws StudentNotFoundException, InvalidStudentException {
        try {
            Student student = searchStudentById(studentId);
            student.addMarks(courseCode, marks);
            System.out.println("✓ Marks added successfully!");
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (InvalidStudentException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidStudentException("Error adding marks: " + e.getMessage());
        }
    }
    
    public void displayAllCourses() {
        try {
            if (courseSet.isEmpty()) {
                System.out.println("✗ No courses found in the system.");
                return;
            }
            
            System.out.println("\n========== ALL COURSES ==========");
            System.out.println("Total Unique Courses: " + courseSet.size());
            System.out.println("-".repeat(80));
            
            for (Course course : courseSet) {
                System.out.println(course);
            }
            System.out.println("-".repeat(80));
        } catch (Exception e) {
            System.out.println("✗ Error displaying courses: " + e.getMessage());
        }
    }
    
    public void countStudentsCourseWise() {
        try {
            if (courseWiseCount.isEmpty()) {
                System.out.println("✗ No course enrollment data available.");
                return;
            }
            
            System.out.println("\n========== STUDENTS COUNT COURSE-WISE ==========");
            System.out.println("-".repeat(50));
            
            for (Map.Entry<String, Integer> entry : courseWiseCount.entrySet()) {
                System.out.println("Course: " + entry.getKey() + " | Students: " + entry.getValue());
            }
            System.out.println("-".repeat(50));
        } catch (Exception e) {
            System.out.println("✗ Error displaying course count: " + e.getMessage());
        }
    }
    
    public void convertHashMapToTreeMap() {
        try {
            Map<Integer, Student> treeMap = new TreeMap<>(studentMap);
            
            System.out.println("\n========== HASHMAP CONVERTED TO TREEMAP ==========");
            System.out.println("TreeMap (sorted by Student ID):");
            System.out.println("-".repeat(100));
            
            for (Map.Entry<Integer, Student> entry : treeMap.entrySet()) {
                System.out.println("ID: " + entry.getKey() + " | " + entry.getValue());
            }
            System.out.println("-".repeat(100));
        } catch (Exception e) {
            System.out.println("✗ Error converting to TreeMap: " + e.getMessage());
        }
    }
    
    public int getStudentCount() {
        return studentList.size();
    }
}

// Main class
public class A5Question5 {
    static Scanner scanner = new Scanner(System.in);
    static UniversityManagementSystem system;
    
    public static void main(String[] args) {
        try {
            System.out.println("========== UNIVERSITY STUDENT MANAGEMENT SYSTEM ==========");
            system = new UniversityManagementSystem();
            boolean exit = false;
            
            while (!exit) {
                displayMenu();
                int choice = getValidChoice();
                
                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        system.displayAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        system.sortStudentsByMarks();
                        break;
                    case 6:
                        enrollStudentInCourse();
                        break;
                    case 7:
                        addStudentMarks();
                        break;
                    case 8:
                        system.displayAllCourses();
                        break;
                    case 9:
                        system.countStudentsCourseWise();
                        break;
                    case 10:
                        system.convertHashMapToTreeMap();
                        break;
                    case 11:
                        exit = true;
                        System.out.println("\n=== Thank you for using University Management System ===");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select between 1-11.");
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
    
    private static void displayMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Remove Student by ID");
        System.out.println("5. Sort Students by Marks");
        System.out.println("6. Enroll Student in Course");
        System.out.println("7. Add Student Marks");
        System.out.println("8. Display All Courses");
        System.out.println("9. Count Students Course-wise");
        System.out.println("10. Convert HashMap to TreeMap");
        System.out.println("11. Exit");
        System.out.print("Enter your choice (1-11): ");
    }
    
    private static int getValidChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return -1;
        }
    }
    
    private static void addNewStudent() {
        try {
            System.out.print("\nEnter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter Student Name (letters and spaces only): ");
            String name = scanner.nextLine().trim();
            
            System.out.print("Enter Email ID: ");
            String email = scanner.nextLine().trim();
            
            System.out.print("Enter Phone Number (10 digits): ");
            String phone = scanner.nextLine().trim();
            
            Student student = new Student(studentId, name, email, phone);
            system.addStudent(student);
        } catch (InvalidStudentException e) {
            System.out.println("✗ Invalid Student Data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format!");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void searchStudent() {
        try {
            System.out.print("\nEnter Student ID to Search: ");
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            
            Student student = system.searchStudentById(studentId);
            System.out.println("\n========== SEARCH RESULT ==========");
            System.out.println(student);
            System.out.println("✓ Student found!");
        } catch (StudentNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid student ID!");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void removeStudent() {
        try {
            System.out.print("\nEnter Student ID to Remove: ");
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            system.removeStudentById(studentId);
        } catch (StudentNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid student ID!");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void enrollStudentInCourse() {
        try {
            System.out.print("\nEnter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter Course Code (e.g., CS101): ");
            String courseCode = scanner.nextLine().trim().toUpperCase();
            
            System.out.print("Enter Course Name: ");
            String courseName = scanner.nextLine().trim();
            
            System.out.print("Enter Credits (1-10): ");
            int credits = Integer.parseInt(scanner.nextLine().trim());
            
            Course course = new Course(courseCode, courseName, credits);
            system.enrollStudentInCourse(studentId, course);
        } catch (StudentNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (InvalidCourseException e) {
            System.out.println("✗ Invalid Course Data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format!");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    private static void addStudentMarks() {
        try {
            System.out.print("\nEnter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine().trim().toUpperCase();
            
            System.out.print("Enter Marks (0-100): ");
            double marks = Double.parseDouble(scanner.nextLine().trim());
            
            system.addStudentMarks(studentId, courseCode, marks);
        } catch (StudentNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (InvalidStudentException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid input format!");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
