/* ***************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Ellyn Ngo
 * Section: 02
 * Date: 3/21/2024
 * Time: 10:59 AM
 *
 * Project: csci205_hw
 * Package: org.vison
 * Class: Solver
 *
 * Description: The Solver mode of Code Breaker
 * Also the parent class for the three solvers
 *
 * **************************************
 */
package org.vison;

import org.vison.CodeBreaker;
import org.vison.CodeMaker;

public abstract class SolverCodeBreaker extends CodeBreaker {
    /** Set up a code maker for the game */
    protected static final CodeMaker codeMaker = new CodeMaker();
    /** The predefined length of the secret code */
    protected static final int CODE_LENGTH = codeMaker.CODE_LENGTH;
    /** The predefined code range for guesses */
    protected static final int CODE_RANGE = codeMaker.CODE_RANGE;
    /** The number of games to be played */
    private static final int numGames = 1000;
    /** The average turns that the solver takes to solve */
    private static int avgTurns;
    /** The min turn that the solver takes to solve */
    private static int minTurns = Integer.MAX_VALUE;
    /** The max turn that the solver takes to solve */
    private static int maxTurns = 0;
    /** The total time that the solver takes to solve */
    private static double runTime;
    /** The number of turns to be kept track of */
    protected int guessCount;

    /**
     * The solveGame abstract
     */
    public abstract void solveGame();

    /**
     * Simulate the game 1000 times
     * @param solverType based on use input
     */
    public static void simulateGame(int solverType) {
        int totalTurn = 0;

        long startTimeRec = System.nanoTime();

        String solverName = "";

        for (int i = 0; i< numGames; i++) {

            SolverCodeBreaker solver;
            if (solverType == 1) {solver = new RandomSolver(); solverName = "Random Solver";}

            else if (solverType == 2) {
                solver = new MinimaxSolver();
                ((MinimaxSolver) solver).generateAllPossibleGuesses("",0);
                solverName = "MiniMax Solver";
            }
            else { solver = new NotRandomSolver(); solverName = "Not Random Solver";}

            solver.solveGame();

            totalTurn += solver.guessCount;
            if (solver.guessCount < minTurns) {
                minTurns = solver.guessCount;
            }
            if (solver.guessCount > maxTurns) {
                maxTurns = solver.guessCount;
            }
        }
        avgTurns = totalTurn/numGames;
        runTime = (System.nanoTime() - startTimeRec) / 1000000000.0;

        System.out.println("Number of games solved by " + solverName + " : " + numGames);
        System.out.println("Average: " + avgTurns + " turns");
        System.out.println("Shortest: " + minTurns + " turns");
        System.out.println("Longest: " + maxTurns + " turns");
        System.out.println("TOTAL TIME: " + runTime + " sec");

        System.out.println("Goodbye!");

    }

}
//Check the Minimax not using new SecretCode