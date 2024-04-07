/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Joshua Stough
 * Lab Section: 60 10AM
 *
 * Team member 1: Ellyn Ngo
 * Team member 2: Duy Le
 * Date: 03/05/2024
 *
 * Lab / Assignment: Homework 1
 *
 * Description: The Mastermind game main program to
 * run the game logic
 *
 * *****************************************/

package org.vison;

import org.vison.gameManager;
import org.vison.SolverCodeBreaker;

import java.util.Scanner;

public class Main {

    /**
     * The main program to run the game logic
     * @param args the command line arguments
     * @Author Duy Le and Ellyn Ngo
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to play in User mode (1) or Solver mode (2)?: " );
        if (scanner.hasNextInt()) {

            int gameType = scanner.nextInt();
            // If the use choose to run in User mode
            if (gameType == 1) {
                gameManager gameManager = new gameManager();
                gameManager.solveGame();
            }

            // If the user choose to run in Solver mode
            else if (gameType == 2) {
                int solverType = 0;
                System.out.print("Which solver do you want to use?: (1 - Random, 2 - MiniMax, 3 - NotRandom): ");

                if (scanner.hasNextInt()) {
                    solverType = scanner.nextInt();
                }
                SolverCodeBreaker.simulateGame(solverType);
            }
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}