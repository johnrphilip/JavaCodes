package vacuum_again;

import java.util.ArrayList;
import java.util.List;

public class VacuumMain {
    public static void main(String[] args) {
        // 2D array to hold state transitions
        int[][] transitions = {{0, 0, 0}, {1, 2, 3}, {1, 2, 6}, {3, 4, 3}, {3, 4, 8}, {5, 6, 7}, {5, 6, 6}, {7, 8, 7}, {7, 8, 8}};
        // array to hold goal states
        int[] goalStates = {7, 8};

        // initialize frontier and explored variables 
        List<Integer> frontier = new ArrayList<>(), explored = new ArrayList<>();
        
        // initialize the frontier with 1 as directed
        frontier.add(1);
        
        // initialize goalState and iteration for tracking
        int goalState = -1, iteration = 1;
        
        
       
        System.out.println("Breadth-First Search");

        // main BFS algorithm loop until the frontier is empty
        while (!frontier.isEmpty()) {
            System.out.println("Iteration: " + iteration);
            // remove the frontier state and add it to the current state (popping from the queue)
            int currentState = frontier.remove(0);
            
            // mark the current state as explored
            explored.add(currentState);
            
            // tracking the output
            System.out.println("Current node: " + currentState);
            System.out.println("Explored: " + explored);

            // Check for goal state
            for (int goal : goalStates) {
                if (currentState == goal) {
                    goalState = currentState;
                    break;
                }
            }
            if (goalState != -1) break;

            // Explore neighbors based on the provided transition rules
            for (int action = 0; action < 3; ++action) { // Actions are 0, 1, 2 for move left, move right, and clean
                int neighbor = transitions[currentState][action];  // Selecting transition state based on currentState and action
                
                // If the neighbor state is neither in frontier nor explored, add it to frontier
                if (!frontier.contains(neighbor) && !explored.contains(neighbor)) {
                    frontier.add(neighbor);
                }
            }
            
            // Tracking the frontier
            System.out.println("Frontier after update: " + frontier);
            
            iteration++;
        }

        // Output results
        if (goalState != -1) {
            System.out.println("BFS Goal state reached: " + goalState);
        } else {
            System.out.println("BFS Goal state not reached.");
        }
    }
}
