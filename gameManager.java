/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Le Duy
 * Section: 1pm
 * Date: 3/6/2024
 * Time: 4:27 PM
 *
 * Project: csci205_hw
 * Package: org.vison
 * Class: gameManager
 *
 * Description: The Game Manager who will be responsible
 * for running the mastermind game
 *
 * ****************************************
 */
package org.vison;
import java.util.Scanner;
public class gameManager {
    /** Set up a code maker for the game */
    private final CodeMaker codeMaker;
    /** Set up a code breaker for the game */
    private final CodeBreaker codeBreaker;
    private Scanner scanner;

    /**
     * The game manager to run the game
     */
    public gameManager() {
        codeMaker = new CodeMaker();
        codeBreaker = new CodeBreaker();
        // Set up a scanner to get input for the game
        Scanner scanner = new Scanner(System.in);
    }
    /**
     * Set up the Mastermind Game and ask user for their first guess
     * At the end, ask user if they want to play again
     * @Author Duy Le and Ellyn Ngo
     */
    public void solveGame() {
        // Set up a scanner to get the codebreaker guess
        this.scanner = new Scanner(System.in);

        boolean playAgain = true;
        // Keep track of number of guesses and the player status
        int turn = 0;
        boolean win = false;

        while (playAgain) {
            // Welcome the user and ask for their guesses
            System.out.println("***** WELCOME TO THE MASTERMIND GAME! *****");
            System.out.println("Ready to play against the computer?");
            System.out.println("Guess my code, using numbers between 1 and 6. You have " + CodeBreaker.MAX_TURNS + " guesses.");

            // Interact with the codebreaker and terminate the game when they win or reach the maximum turns
            codeBreaker.solveGame(turn, win, scanner);

            // Ask the user if they want to play again
            System.out.print("Would you like to play again? [y/n]: ");
            String playAgainRep = scanner.nextLine();

            playAgain = isPlayAgain(playAgainRep, playAgain);
        }
    }

    /**
     * Ask if the code breakers want another game
     * @param playAgainRep the input string
     * @param playAgain the previous answer (default = yes)
     * @return the updated answer to the question
     * @Author Ellyn Ngo and Duy Le
     */
    public boolean isPlayAgain(String playAgainRep, boolean playAgain) {
            if (playAgainRep.equalsIgnoreCase("y")) {
                codeMaker.generateSecretCode(); // If the user inputs 'y', generate a new secret code
            } else if (playAgainRep.equalsIgnoreCase("n")) {
                playAgain = false;
                System.out.println("Goodbye!");
                System.out.println("This game is developed by @DuyLe and @EllynNgo.");
            } else {
                System.out.println("Please enter a valid response.");
                System.out.print("Would you like to play again? [y/n]: ");
                playAgainRep = scanner.nextLine();
                playAgain = isPlayAgain(playAgainRep, playAgain);
            }
            return playAgain;
    }
}