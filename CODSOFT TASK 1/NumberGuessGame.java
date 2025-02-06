import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRoundsWon = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        // Loop for multiple rounds
        while (true) {
            int numberToGuess = random.nextInt(100) + 1;  // Random number between 1 and 100
            int maxAttempts = 10;  // Set maximum attempts
            int attemptsLeft = maxAttempts;
            boolean isCorrect = false;

            System.out.println("\nA new round has started!");
            System.out.println("Guess a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number correctly.");

            // Game loop for the current round
            while (attemptsLeft > 0) {
                System.out.println("Attempts left: " + attemptsLeft);
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    isCorrect = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            // Display round result
            if (isCorrect) {
                System.out.println("Congratulations! You guessed the number correctly.");
                totalRoundsWon++;
            } else {
                System.out.println("Sorry! You've run out of attempts. The correct number was: " + numberToGuess);
            }

            // Ask user if they want to play another round
            System.out.print("\nWould you like to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (!playAgain.equals("yes")) {
                break;
            }
        }

        // Display final score
        System.out.println("\nGame Over!");
        System.out.println("You won " + totalRoundsWon + " rounds.");
        scanner.close();
    }
}
