import java.util.Scanner;

public class StudentGradeCalculator {

    // Method to calculate the grade based on the average percentage
    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take the number of subjects as input
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        // Array to store marks of each subject
        double[] marks = new double[numSubjects];
        double totalMarks = 0;

        // Input marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + ": ");
            marks[i] = scanner.nextDouble();
            totalMarks += marks[i];
        }

        // Calculate the average percentage
        double averagePercentage = (totalMarks / (numSubjects * 100)) * 100;

        // Calculate the grade based on the average percentage
        String grade = calculateGrade(averagePercentage);

        // Display the results
        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + totalMarks + " / " + (numSubjects * 100));
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
    }
}
