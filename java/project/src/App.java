import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private String studentID;
    private int[] marks = new int[3];
    private int totalMark;

    public Student(String name, String studentID, int[] marks) {
        this.name = name;
        this.studentID = studentID;
        this.marks = marks;
        calculateTotalMark();
    }

    public String getName() 
    {
        return name;
    }

    public String getStudentID() 
    {
        return studentID;
    }

    public int getTotalMark() 
    {
        return totalMark;
    }

    private void calculateTotalMark() 
    {
        for (int mark : marks) 
        {
            totalMark += mark;
        }
    }

    @Override
    public String toString() 
    {
        return "Name: " + name + ", Student ID: " + studentID + ", Marks: " +
                marks[0] + ", " + marks[1] + ", " + marks[2] + ", Total Mark: " + totalMark;
    }
}

public class App 
{
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        String fileName;

        System.out.print("Enter the name of the text file: ");
        fileName = scanner.nextLine();

        readStudentDataFromFile(fileName);

        while (true) 
        {
            System.out.println("\nMenu:");
            System.out.println("1. List all students and their total marks");
            System.out.println("2. List students with total marks below a threshold");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-2): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) 
            {
                case 1:
                    listAllStudents();
                    break;
                case 2:
                    System.out.print("Enter the threshold: ");
                    int threshold = scanner.nextInt();
                    listStudentsBelowThreshold(threshold);
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // F1 Function
    private static void readStudentDataFromFile(String fileName) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                // Check for comment lines and ignore them
                if (line.startsWith("//")) 
                {
                    continue;
                }

                // Split the line into parts: Name, Student ID, Assignment Marks
                String[] parts = line.split(",");
                if (parts.length == 5) 
                {
                    String name = parts[0].trim();
                    String studentID = parts[1].trim();
                    int[] marks = {
                            Integer.parseInt(parts[2].trim()),
                            Integer.parseInt(parts[3].trim()),
                            Integer.parseInt(parts[4].trim())
                    };
                    Student student = new Student(name, studentID, marks);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
            System.exit(1);
        }
    }
    // F2 Function
    private static void listAllStudents() 
    {
        System.out.println("List of all students and their total marks:");
        for (Student student : students) 
        {
            System.out.println(student);
        }
    }

    // F3 Function
    private static void listStudentsBelowThreshold(int threshold) 
    {
        System.out.println("List of students with total marks below " + threshold + ":");
    
        List<Student> studentsBelowThreshold = new ArrayList<>();
    
        for (Student student : students) 
        {
            if (student.getTotalMark() < threshold) 
            {
                studentsBelowThreshold.add(student);
            }
        }
    
        for (Student student : studentsBelowThreshold) 
        {
            System.out.println(student.getName() + ", " + student.getStudentID() + ", " + student.getTotalMark());
        }
    }
    
}
