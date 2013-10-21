package simulatedAnnealing;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EggCartonSolution hei = new EggCartonSolution(5);
		System.out.println(hei);
		EggCartonPuzzle initState = new EggCartonPuzzle();
		System.out.println(initState.getInitialState());
	}

	public final int ITERATION_COUNT_LIMIT = 120;
	public final float INITIAL_TEMPERATURE = 100;
	public final int SCHEDULING_FACTOR = 100;
	
	SimulatedAnnealingProblem problem;
	float targetFitness;
	Solution current;
	Random rng = new Random();

	
	public void run() {
		Solution solution = SA_search();
		System.out.println(solution);
	}
	
	/** 
	 * Simulated Annealing Algorithm 
	 */
	public Solution SA_search() {
		current = problem.getInitialState();
		targetFitness = problem.getTargetFitness();
		float T = INITIAL_TEMPERATURE;
		
		// Main search loop:
		for (int i = 1; i < ITERATION_COUNT_LIMIT; i++) {
			
			// Scheduling; return if temperature==0:
			T = schedule(i);
			if (T <= 0) return current;
			
			// Generating new neighbors and finding the best one:
			ArrayList<Solution> neighbors = problem.generateSuccessors(current);
			Solution next = chooseBestSuccessor(neighbors);
			if (next == null) return current;	// If no neighbors are legal, return current.
			
			// Check if we have found solution; return if solution found:
			if (problem.getFitness(next) >= targetFitness) {
				current = next;
				return current;
			}
			
			// Temperature process:
			float q = (problem.getFitness(next) - problem.getFitness(current)) / problem.getFitness(current);
			double p = Math.exp(-q/T);
			double x = rng.nextDouble();
			if (x > p) {		// set current solution to best-fitness neighbor:
				current = next;
			} else {			// set current solution to a randomly chosen neighbor:
				int k = rng.nextInt(neighbors.size());
				current = neighbors.get(k);
			}
		}
		
		return current;
	}
	
	/** 
	 * Evaluates all successors and returns
	 * the best-fitness successor
	 */
	public Solution chooseBestSuccessor(ArrayList<Solution> successors) {
		Solution bestSolution = null;
		float bestFitness = 0;
		for (Solution next : successors) {
			float nextFitness = problem.getFitness(next);
			if (nextFitness > bestFitness) {
				bestFitness = nextFitness;
				bestSolution = next;
			}
		}
		return bestSolution;
	}
	
	/**
	 * Scheduler 
	 */
	private float schedule(int time) {
		return INITIAL_TEMPERATURE - time;
	}
	
}
