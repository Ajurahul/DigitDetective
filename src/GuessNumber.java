import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
		public static void main(String[] args) {
			// Create instances of Random and Scanner
	        Random random = new Random();
	        Scanner scanner = new Scanner(System.in);
	        
	        // Define the range and maximum number of attempts
	        final int MIN = 1;
	        final int MAX = 100;
	        final int MAX_ATTEMPTS = 10;
	        
	        // Generate a random number
	        int numberToGuess = random.nextInt(MAX - MIN + 1) + MIN;
	        
	        System.out.println("Welcome to Guess the Number!");
	        System.out.println("I have selected a number between " + MIN + " and " + MAX + ".");
	        System.out.println("Can you guess it? You have " + MAX_ATTEMPTS + " attempts.");
	        
	        int attempts = 0;
	        boolean hasGuessedCorrectly = false;
	        
	        // Start the guessing game
	        while (attempts < MAX_ATTEMPTS) {
	            System.out.print("Enter your guess: ");
	            int userGuess = scanner.nextInt();
	            
	            attempts++;
	            
	            if (userGuess < MIN || userGuess > MAX) {
	                System.out.println("Your guess is out of range. Please guess a number between " + MIN + " and " + MAX + ".");
	            } else if (userGuess < numberToGuess) {
	                System.out.println("Too low! Try again.");
	            } else if (userGuess > numberToGuess) {
	                System.out.println("Too high! Try again.");
	            } else {
	                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
	                hasGuessedCorrectly = true;
	                break;
	            }
	        }
	        
	        if (!hasGuessedCorrectly) {
	            System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + ".");
	        }
	        
	        // Close the scanner
	        scanner.close();
		}
}
