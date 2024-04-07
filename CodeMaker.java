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
 * Class: codeMaker
 *
 * Description:
 *
 * ****************************************
 */
package org.vison;
import java.util.Random;

public class CodeMaker {
    /** The predefined length of the secret code */
    public static final int CODE_LENGTH = 4;
    /** The predefined code range for guesses */
    public static final int CODE_RANGE = 6;

    /** The secret code predefined by the code maker */
    public static int[] secretCode = new int[CODE_LENGTH];

    /** The random secret code generator */
    private static final Random random = new Random();

    /**
     * The mastermind game generator
     */
    public CodeMaker() { generateSecretCode(); }

    /**
     * Generate a random integer array of length CODE_LENGTH
     * @Author Duy Le
     */
    public static void generateSecretCode() {
        for (int i = 0; i < CODE_LENGTH; i++) {
            secretCode[i] = random.nextInt(CODE_RANGE) + 1;
        }
    }
    /**
     * The scoring logic of the game - get the correct position and
     * correct number for each guess round
     * @param guess the codebreaker guess
     * @return a string representing the scoring pegs
     * @Author Duy Le and Ellyn Ngo
     */
    public static String evaluateGuess(int[] guess) {
        // Set the count for scoring pegs
        int correctPosition = 0;
        int correctNumber = 0;

        // Flags to mark matched positions in the guess
        boolean[] matchedCode = new boolean[CODE_LENGTH];
        boolean[] matchedGuess = new boolean[CODE_LENGTH];

        // First pass to get pegs that are correct in both position and number
        correctPosition = getCorrectPosition(guess, matchedCode, matchedGuess, correctPosition);

        /// Second pass to find correct numbers in wrong positions
        correctNumber = getCorrectNumber(guess, matchedGuess, matchedCode, correctNumber);

        return "*".repeat(correctPosition) + "+".repeat(correctNumber) +
                "-".repeat(CODE_LENGTH - correctPosition - correctNumber);
    }

    /**
     * Loop through the guess and get total count of code pegs in correct number
     * @param guess the codebreaker guess
     * @param matchedGuess array representing match guess
     * @param matchedCode array representing matched code
     * @param correctNumber number of code pegs in correct number
     * @return correctNumber the total count of pegs in correct number
     * @Author Duy Le
     */
    public static int getCorrectNumber(int[] guess, boolean[] matchedGuess,
                                       boolean[] matchedCode, int correctNumber) {
        for (int i = 0; i < CODE_LENGTH; i++) {
            // Update each variable if the code breaker guesses the number correctly
            if (!matchedGuess[i]) {
                for (int j = 0; j < CODE_LENGTH; j++) {
                    if (guess[i] == secretCode[j] && !matchedCode[j]) {
                        correctNumber++;
                        matchedCode[j] = true; // Mark this position as matched
                        break; // Stop searching to prevent double-counting
                    }
                }
            }
        }
        return correctNumber;
    }
    /**
     * Loop through the guess and get total count of code pegs in correct position
     * @param guess the codebreaker guess
     * @param matchedGuess array representing match guess
     * @param matchedCode array representing matched code
     * @param correctPosition number of code pegs in correct position
     * @return correctPosition the updated number of code pegs in correct position
     * @Author Duy Le
     */
    public static int getCorrectPosition(int[] guess, boolean[] matchedCode,
                                         boolean[] matchedGuess, int correctPosition) {
        for (int i = 0; i < CODE_LENGTH; i++) {
            // Update each variable if the code breaker guesses the position correctly
            if (guess[i] == secretCode[i]) {
                correctPosition++;
                matchedCode[i] = true;
                matchedGuess[i] = true;
            }
        }
        return correctPosition;
    }

    /**
     * Convert the integer array into a string to show to code breaker
     * at the end of the game if they can't figure out
     * @param array the array of secret code
     * @return string representation of the array
     * @Author Duy Le
     */
    public static String intArrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int value : array) {
            sb.append(value);
        }
        return sb.toString();
    }
}