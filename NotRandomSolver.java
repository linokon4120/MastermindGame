/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo and Duy Le
 * Date: 3/15/2024
 * Time: 3:17 PM
 *
 * Project: csci205_hw
 * Package: org.vison
 * Class: NotRandomSolver
 *
 * Description: Randomly guess codes from the pool of
 * all possible guesses until it happens to create a match.
 *
 * **************************************
 */
package org.vison;
import org.vison.CodeMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The RandomSolver class constructor
 * @Author Ellyn Ngo
 */
public class NotRandomSolver extends SolverCodeBreaker {

    /** The random generator */
    private static final Random random = new Random();

    /** Keep track of the remaining guesses that we haven't used */
    private static List<String> allPossibleGuesses = new ArrayList<>();


    /**
     * The RandomSolver class constructor
     */
    public NotRandomSolver() {
        generateAllPossibleGuesses("", 0);
    }


    /**
     * The method to generate all possible guesses given
     * the code length
     * @param prefix the string prefix for the guess
     * @param length code's length
     * @Author Ellyn
     */
    public void generateAllPossibleGuesses(String prefix, int length) {
        if (length == CODE_LENGTH) {
            allPossibleGuesses.add(prefix);
            return;
        }

        // The code generation started from 1 to 6
        for (int i = 1; i <= CODE_RANGE; i++) {
            generateAllPossibleGuesses(prefix + i, length + 1 );
        }
    }

    /**
     * If called, generate a 'random' guess that is pulled from
     * the remaining guess pool
     * @return the integer array of code generated
     * @Author Ellyn
     */
    public static int[] generateGuess() {
        if (allPossibleGuesses.isEmpty()) {
            throw new IllegalStateException("No remaining guesses");
        }
        return stringToArray(allPossibleGuesses.get(random.nextInt(allPossibleGuesses.size())));
    }

    /**
     * After the code get used, remove it from the remaining
     * available guesses pool
     * @param guess the guess that got pulled out
     * @Author Ellyn
     */
    public static void removeFromRemaining(String guess) {
            allPossibleGuesses.remove(guess);
    }

    /**
     * Run the Solver and give the number of guesses taken
     * to solve the code
     * @Author Ellyn
     */
    @Override
    public void solveGame() {
        guessCount = 0;
        boolean win = false;
        while (!win) {
            // Generate a guess and remove it from the possible guesses
            int[] guess = generateGuess();
            removeFromRemaining(intArrayToString(guess));

            // Evaluate the guess and update guess count
            String result = CodeMaker.evaluateGuess(guess);
            win = result.equals("*".repeat(CODE_LENGTH));
            guessCount++;
        }
        if (win) {
            allPossibleGuesses.clear();
        }
    }

    /**
     * Convert String into array
     * @param guess the guess string
     * @return the integer array of guess
     * @Author Duy Le
     */
    public static int[] stringToArray(String guess) {

        int[] result_array = new int[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            result_array[i] = guess.charAt(i) - '0';
        }
        return result_array;
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

    /**
     * Getter method for all possible guesses
     * @return the guesses pool
     * @Author Ellyn
     */
    public static List<String> getAllPossibleGuesses() {
        return allPossibleGuesses;
    }
}
