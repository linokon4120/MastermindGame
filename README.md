# CSCI 205 - Software Engineering and Design
Bucknell University
Lewisburg, PA

## Course Info
Instructor: Joshua Stough

Semester: Spring 2024

## Team Information
>1. Duy Le - Freshman - Computer Engineering
>2. Ellyn Ngo - Sophomore - Business Analytics and Computer Science

## Repository Information:

CSCI 205 Homework and Assignment

## Project Information
In this project, we implement a basic Mastermind Game logic in Java, 
allowing users to interact with the game through the command line interface. 
The game offers two modes: Game mode and Solver mode.

### Game mode
In the Game mode, users can play the typical Mastermind game with a limit of 12 turns. 
After each round, the user will be prompted to play again.

### Solver mode
In the Solver mode, users will again be prompted to choose from different solving algorithms 
to simulate the Mastermind game. The number of games can be specified by the developer and those 
with access to the files. After the simulation, the program outputs basic statistics based 
on the number of turns each run took, including the mean number of turns, the least and most 
number of turns, and the total time it took to run the simulation.

#### Solvers
>- Random Solver: This solver randomly guesses codes until it happens to create a match with the secret code.
>- MiniMax Solver: Utilizes the minimax algorithm to make optimal guesses, solving the Mastermind game in an average of less than 5 moves.
>- NotRandom Solver: This solver systematically guesses codes in the same manner with Random Solver, 
but it will restrict on the possible guesses pool and explore all possible combinations until it finds the correct one.
## How to run it
To run the program, please go ahead and run the 'Main' class.  Follow the prompts to choose between 
Game mode and Solver mode, and select the desired solver if in Solver mode.

Enjoy the game Mastermind and experimenting with different solving strategies!