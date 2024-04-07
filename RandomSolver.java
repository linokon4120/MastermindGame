/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 3/18/2024
 * Time: 2:36 PM
 *
 * Project: Csci205_hw
 * Package: org.vison
 * Class: RandomSolver
 *
 * Description: Randomly guess codes until
 * it happens to create a match.
 *
 * **************************************
 */
package org.vison;

import java.util.Random;

public class RandomSolver extends SolverCodeBreaker {
    /**
     * The random generator
     */
    private static final Random random = new Random();
    /**
     * The guess array by the solver
     */
    public static int[] guess = new int[CODE_LENGTH];

    /**
     * Generate a random guess
     * @return the guess
     * @Author Ellyn
     */
    public int[] generateGuess() {
        int[] guess = new int[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            guess[i] = random.nextInt(SolverCodeBreaker.CODE_RANGE) + 1;
        }
        return guess;
    }

    /**
     * Convert the integer array into a string to show to code breaker
     * at the end of the game if they can't figure out
     *
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
     * Run the Solver and give the number of guesses taken
     * to solve the code
     * @Author Ellyn
     */
    @Override
    public void solveGame() {
        guessCount = 0;
        boolean win = false;
        while (!win) {
            int[] guess = generateGuess();
            // Evaluate the guess and update guess count

            String result = codeMaker.evaluateGuess(guess);
            win = result.equals("*".repeat(CODE_LENGTH));

            guessCount++;
        }
    }
}