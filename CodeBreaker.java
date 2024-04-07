/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Le Duy
 * Section: 1pm
 * Date: 3/6/2024
 * Time: 4:28 PM
 *
 * Project: csci205_hw
 * Package: org.vison
 * Class: codeBreaker
 *
 * Description: The logic of the code breaker's
 * interaction with the game
 *
 * ****************************************
 */
package org.vison;
import java.util.Scanner;
public class CodeBreaker {
    /** The maximum turns allowed for the code breaker */
    static final int MAX_TURNS = 12;

    /**
     * The interaction when the code breaker plays the game
     * @param turn the number of turn taken so far
     * @param win status of the game
     * @param scanner scan for code breaker's input
     * @Author Duy Le and Ellyn Ngo
     */
    public void solveGame(int turn, boolean win, Scanner scanner) {
        while (turn < MAX_TURNS && !win) {
            System.out.print("Guess " + (turn + 1) + ": ");
            int[] guess = new int[CodeMaker.CODE_LENGTH];
            String input = scanner.nextLine();

            // Checking if the input is valid (4 digits long and only have numbers 1-6)
            if (checkedValidInput(input)) continue;

            // Get the numeric value to the array
            getNumericGuess(guess, input);

            // Report this turn's result in scoring pegs
            String result = CodeMaker.evaluateGuess(guess);
            win = printResult(turn, win, result, input);
            turn++;
        }

        if (!win) {
            System.out.println("You've run out of guesses. The code was: " + CodeMaker.intArrayToString(CodeMaker.secretCode));
        }
    }

    /**
     * Print out the result of this turn and update the 'winning' status
     * @param turn the current turn of the game
     * @param win the winning status
     * @param result the result after comparing with the secret code
     * @param input the code breaker's string input
     * @return the updated winning status
     * @Author Duy Le and Ellyn Ngo
     */
    public static boolean printResult(int turn, boolean win, String result, String input) {
        // If the result after comparing is all "*", the code breaker won!
        if (result.equals("*".repeat(CodeMaker.CODE_LENGTH))) {
            System.out.println(input + " --> " + result +
                    " YOU WON! You guessed the code in " + (turn + 1) + " moves!");
            win = true;
        }
        // Otherwise, they need to try again
        else {
            System.out.println(input + " --> " + result +
                    " Try again. " + (MAX_TURNS - turn - 1) + " guesses left.");
        }
        return win;
    }

    /**
     * Change the code breaker's string input into an
     * integer array to compare with secret code
     * @param guess the int array to turn the input into
     * @param input the string input
     * @Author Duy Le
     */
    public static void getNumericGuess(int[] guess, String input) {
        for (int i = 0; i < CodeMaker.CODE_LENGTH; i++) {
            guess[i] = Character.getNumericValue(input.charAt(i));
        }
    }

    /**
     * Validate the input if they meet the game's requirement
     * @param input the code breaker's input
     * @return boolean stating if the input is valid
     * @Author Duy Le and Ellyn Ngo
     */
    public static boolean checkedValidInput(String input) {
        // Check if the input is exactly 4 digits long
        if (input.length() != CodeMaker.CODE_LENGTH) {
            System.out.println("The input value should have 4 digits only. " +
                    "Please try again using digits between 1 and 6.");
            return true;
        }
        // Check if the input only have numbers 1-6
        else if (!input.matches("[1-6]+")) {
            System.out.println("The input value should be between 1 and 6. " +
                    "Please try again.");
            return true;
        }
        return false;
    }
}