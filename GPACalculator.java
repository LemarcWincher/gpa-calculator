// TIP: To run the code, press the Run button or use the keyboard shortcut (usually Shift+F10).

/**
 * GPA Calculator Application.
 *
 * @author Lemarc Wincher
 * @version 1.0
 * @since 2025-08-02
 */


import java.util.Scanner; //imports Scanner

public class GPACalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   //Creates Scanner for user input via keyboard
        System.out.println("Welcome to the GPA calculator!"); //Begins introduction of app
        System.out.println();
        System.out.println("This program is designed to calculate your GPA based upon courses taken, credit hours attempted, and grades received.");
        System.out.print("Please press enter to continue...");
        System.out.println();
        scanner.nextLine(); //Pauses to allow the user to read



        double totalGradePoints = 0.0; //creates variable for grade points
        int totalCreditHours = 0; //creates variable for total credit hours

        int numberOfClasses; //creates variable for number of classes

        //START CLASSES VALIDATION
        while (true) { // Validate number of classes input
            System.out.print("How many classes would you like to enter today? ");
            if (scanner.hasNextInt()) { //if input is an integer
                numberOfClasses = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline

                if (numberOfClasses > 0) { //if classes is less than zero...
                    break; // valid input, exit loop
                } else {
                    System.out.println("The number of classes must be greater than zero. Please try again.");
                }
            } else { //if input is anything other than a number in general...
                System.out.println("That's not a valid number. Please enter a positive integer.");
                scanner.nextLine(); // discard invalid input
            }
        }
            //CLASSES VALIDATION END

        for (int i = 1; i <= numberOfClasses; i++) { // Start collecting class data and assigns number of classes to variable
            System.out.println("\n--- Class " + i + " ---");

            System.out.print("Enter the name of class #" + i + " (or press Enter to skip): ");
            String className = scanner.nextLine();

            if (className.trim().isEmpty()) { //if class name is empty, call it class number in the order inputted
                className = "Class " + i;
            }

            String courseLetterGrade;
            double gradePoints;

            do {
                System.out.print("Enter your letter grade for " + className + ": ");
                courseLetterGrade = scanner.nextLine();
                gradePoints = convertGradeToPoints(courseLetterGrade);

                if (gradePoints == -1) { //if grade points is returning invalid
                    System.out.println("Invalid grade entered. Please try again.");
                }
            } while (gradePoints == -1); //while grade points returns invalid

            // START CREDIT HOURS VALIDATION
            int courseCreditHour;
            while (true) { // Validate credit hours input
                System.out.print("Please enter the credit hours for " + className + ": ");
                if (scanner.hasNextInt()) { // if input is an integer assign it
                    courseCreditHour = scanner.nextInt();
                    scanner.nextLine(); // consume leftover newline
                    if (courseCreditHour > 0) { // if credit hour is less than zero, break
                        break; // valid input, exit loop
                    } else {
                        System.out.println("Credit hours must be greater than zero. Please try again.");
                    }
                } else {
                    System.out.println("That's not a valid number. Please enter a positive integer.");
                    scanner.nextLine(); // discard invalid input
                }
            }
            // END CREDIT HOURS VALIDATION

            double classGradePoints = gradePoints * courseCreditHour; //formula for grade points in current class on screen
            totalGradePoints += classGradePoints; //adds class grade points to running total grade points
            totalCreditHours += courseCreditHour; //adds credit hours from class to running total credit hours

        }



        // START DIVISION BY ZERO VALIDATION
        if (totalCreditHours == 0) { //if credit hours is zero
            System.out.println("\nNo credit hours entered. Cannot calculate GPA.");
        } else {
            double finalGPA = totalGradePoints / totalCreditHours; // calculates GPA
            System.out.printf("\nYour final GPA is: %.2f\n", finalGPA); //states grade points earned
        }
        // END DIVISION BY ZERO VALIDATION
        scanner.close(); //closes scanner, no more user input needed

    }
    
        //NEW METHOD BEGINS
        public static double convertGradeToPoints (String courseLetterGrade) { //creates method to convert grades to points outside of main but within the class
            switch (courseLetterGrade.toUpperCase()) {
                case "A":  return 4.0;
                case "A-": return 3.7;
                case "B+": return 3.3;

                case "B":  return 3.0;
                case "B-": return 2.7;
                case "C+": return 2.3;
                case "C":  return 2.0;
                case "C-": return 1.7;
                case "D+": return 1.3;
                case "D":  return 1.0;
                case "F":  return 0.0;
                default:   return -1; // assigns anything invalid to return -1

            }
        }

    }
