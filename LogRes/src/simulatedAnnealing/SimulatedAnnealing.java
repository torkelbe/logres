package simulatedAnnealing;

import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {

	public static void main(String[] args) {
		
		SimulatedAnnealing searchAlgorithm = new SimulatedAnnealing();
		searchAlgorithm.problem = new EggCartonPuzzle();
		searchAlgorithm.run();
	}

	public final int ITERATION_COUNT_LIMIT = 100;	// Not currently necessary. Temperature regulates iterations.
	public final double INITIAL_TEMPERATURE = 21;	// This has now the effect of regulating number of iterations.
	public final double SCHEDULING_FACTOR = 50;
	// SCEDULING_FACTOR divides the temperature value to get a good spread for the value of p (probability of
	// choosing the best-fitness neighbor) throughout the iterations. Higher SCHEDULING_FACTOR gives a lower
	// overall value of p.
	
	SimulatedAnnealingProblem problem;
	double targetFitness;
	Solution current;
	Random rng = new Random();

	
	public void run() {
		System.out.println("Starting search...");
		Solution solution = SA_search();
		System.out.println("SimulatedAnnealing terminated with fitness = " + current.getFitness() +":");
		System.out.println(solution);
	}
	
	/** 
	 * Simulated Annealing Algorithm
	 * Implemented like the version given in exercise
	 * description, with the addition of checking for
	 * an optimal solution among generated neighbors.
	 */
	public Solution SA_search() {
		current = problem.getInitialState();
		targetFitness = problem.getTargetFitness();
		double T = INITIAL_TEMPERATURE;
		
		// Main search loop:
		for (int i = 0; i < ITERATION_COUNT_LIMIT; i++) {
			
			// Scheduling. End search if temperature reaches 0:
			T = schedule(i);
			if (T <= 0) {
				System.out.println("END: Temperature reached 0");
				return current;
			}
			T = T / SCHEDULING_FACTOR;		// Apply scheduling factor to T to scale temperature appropriately.
			
			// Generating new neighbors and finding the best one:
			ArrayList<Solution> neighbors = problem.generateSuccessors(current);
			Solution next = chooseBestSuccessor(neighbors);
			if (next == null) {		// If no neighbors are legal, return current.
				System.out.println("END: No valid neighbors");
				return current;
			}
			
			// Check if we have found an optimal solution (checking against targetFitness)
			// End search if optimal solution is found:
			if (problem.getFitness(next) >= targetFitness) {
				current = next;
				System.out.println("END: Solution found");
				return current;
			}
			
			// Temperature process:
			double q = (problem.getFitness(next) - problem.getFitness(current)); // problem.getFitness(current);
			double p = Math.exp(-q/T);
//			System.out.println("q = "+q);
//			System.out.println("p = "+p);
			double x = rng.nextDouble();
			if (x > p) {		// With probability p, set current solution to best-fitness neighbor:
				current = next;
			} else {			// With probability 1-p, set current solution to a randomly chosen neighbor:
				int k = rng.nextInt(neighbors.size());
				current = neighbors.get(k);
			}
			System.out.println("Iteration #"+(i+1)+"  (fitness: "+current.getFitness()+")");
			System.out.println(current);
		}
		System.out.println("END: iteration count.");
		return current;
	}
	
	/** 
	 * Evaluates all successors and returns the best-fitness successor
	 */
	public Solution chooseBestSuccessor(ArrayList<Solution> successors) {
		Solution bestSolution = null;
		double bestFitness = -1;
		for (Solution next : successors) {
			double nextFitness = problem.getFitness(next);
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
	private double schedule(int time) {
		return INITIAL_TEMPERATURE - time;
	}
	
}
