/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Le Duy
 * Section: 1pm
 * Date: 3/18/2024
 * Time: 1:30 PM
 *
 * Project: csci205_hw
 * Package: org.vison
 * Class: MinimaxSolver
 *
 * Description: The MiniMaxSolver to the Mastermind
 * game, invented by Donald Knuth
 *
 * ****************************************
 */
package org.vison;

import org.vison.CodeMaker;

import java.util.ArrayList;
import java.util.List;

public class MinimaxSolver extends SolverCodeBreaker {

    /**
     * The guess array by the solver
     */
    public List<String> allPossibleGuesses = new ArrayList<>();

    /** Set the current guess for the first guess */
    public String currentGuess = "1122"; // Starting guess as per Knuth's suggestion

    /**
     * Generate the guesses pool when run the constructor
     */
    public MinimaxSolver() {
        generateAllPossibleGuesses("", 0);
    }

    /**
     * The method to generate all possible guesses given
     * the code length
     * @param prefix the string prefix for the guess
     * @param length code's length
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
     * Simulates evaluating a guess against the secret code.
     * This method should be replaced with the actual evaluation logic in the real game.
     *
     * @param guess The guess string.
     * The algorithm of evaluateGuess is inspired by Mastermind-Five-Guess-Algorithm in C++
     *              found at ........
     * Citation Link: ..........
     * @return Feedback string.
     */
    public String evaluateGuess(String guess) {
        // This method should provide feedback in the form "*+-" based on the real game's evaluation.
        // For simulation purposes, let's assume a random feedback.
        // Replace this with the actual feedback mechanism.

        int correctPosition = 0;
        int correctNumber = 0;
        // make 2 arrays to check the frequency of the guess that meet again.
        int[] secretCode = new int[CODE_RANGE + 4];
        int[] guessCode = new int[CODE_RANGE + 4];

        // First pass to find number that are correct in both color and position
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess.charAt(i) == currentGuess.charAt(i)) {
                correctPosition++;
            } else {
                // For number not in the correct position, tally their occurrences
                secretCode[Character.getNumericValue(currentGuess.charAt(i))]++;
                guessCode[Character.getNumericValue(guess.charAt(i))]++;
            }
        }

        // Second pass to find number that are in sequence but in the wrong position
        for (int i = 0; i < CODE_RANGE; i++) {
            correctNumber += Math.min(secretCode[i], guessCode[i]);
        }

        // Construct feedback string
        String response = "*".repeat(correctPosition) + "+".repeat(correctNumber);
        response += "-".repeat(CODE_LENGTH - correctPosition - correctNumber);

        return response;
    }

    /**
     * the main algorithm to solve the problem
     * Simulates the feedback between two guesses.
     */
    @Override
    public void solveGame() {
        boolean win = false;
        guessCount = 0;
        List<String> possibleGuesses = new ArrayList<>(allPossibleGuesses);
        while (!win) {
            guessCount++;
            String response = CodeMaker.evaluateGuess(stringToArray(currentGuess));

            if (response.equals("****")) {
                System.out.println("Code finished in " + guessCount + " guesses! The code was: " + currentGuess);
                win = true;
                break;
            } else {
                System.out.println("Guess " + guessCount + ": " + currentGuess + " --> Feedback: " + response);
                // Update possible guesses based on feedback
                List<String> nextPossibleGuesses = new ArrayList<>();
                for (String possibleGuess : possibleGuesses) {
                    if (evaluateGuess(possibleGuess).equals(response)) {
                        nextPossibleGuesses.add(possibleGuess);
                    }
                }
                possibleGuesses = new ArrayList<>(nextPossibleGuesses);

                // Apply Minimax to select the next guess
                //currentGuess = possibleGuesses.get(random.nextInt(possibleGuesses.size()));
                if (!possibleGuesses.isEmpty()) {
                    currentGuess = possibleGuesses.get(0);
                }
            }
        }
    }

    /**
     * Convert String into array
     * @param guess the guess string
     * @return the integer array of guess
     * @Author Duy Le
     */
    private int[] stringToArray(String guess) {

        int[] result_array = new int[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            result_array[i] = guess.charAt(i) - '0';
        }
        return result_array;
    }
}


