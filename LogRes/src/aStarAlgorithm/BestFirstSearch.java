package aStarAlgorithm;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch {

	PriorityQueue<Node> CLOSED = new PriorityQueue<Node>();
	PriorityQueue<Node> OPEN = new PriorityQueue<Node>();
	
	boolean unsolved = true;
	AStarAlgorithm problem;
	ArrayList<Node> solution = new ArrayList<Node>();
	
	public void setProblem(AStarAlgorithm problemToSolve, Node rootNode) {
		this.problem = problemToSolve;
		OPEN.add(rootNode);
	}
	
	public void searchSolution() {
		while (unsolved) {				// Search loop
			searchStates();
		}
		System.out.println("Number of moves: " + solution.size());		// Print number of moves in solution
	}
	
	/** Make babies **/
	private void searchStates() {
		Node parentState = OPEN.poll();
		
		if (parentState==null) {
			unsolved = false;
			noSolution();
			return;
		}
		problem.printState(parentState);
		
		CLOSED.add(parentState);
		ArrayList<Node> newStates = problem.createChildStates(parentState);		// Generate children
		
		for (Node child: newStates) {			// Iterate through children
			child.g = parentState.g + 1;		// Set child's g value
			
			if (problem.isGoal(child)) {		// Check if child is a solution
				unsolved = false;
				createSolution(child);
			}
			
			Node copy = problem.DoesStateExist(child);		// Check if node is already found
			if (!(copy==null) && OPEN.contains(copy)) {
//				System.out.println("found in OPEN");
				copy = problem.chooseBest(child, copy);		// If found in OPEN, choose best option
			} else if (!(copy==null) && CLOSED.contains(copy)) {
//				System.out.println("found in CLOSED");
				if (copy == problem.chooseBest(child, copy)) {
					problem.propagatePathImprovements(copy);		// If found in CLOSED (and better), propagate path improvements
				}
			} else {
//				System.out.println("----not found----");
				child.h = problem.h(child);			// Set child's h value
				OPEN.add(child);					// and add to OPEN
			}
		}
	}
	
	private void createSolution(Node state) {
		solution.add(0, state);
		if (!(state.parent == null)) createSolution(state.parent);
	}
	private void noSolution() {
		System.out.println("BestFirstSearch did not find a solution.");
	}

}
