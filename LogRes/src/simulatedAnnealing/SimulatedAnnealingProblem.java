package simulatedAnnealing;

import java.util.ArrayList;

public interface SimulatedAnnealingProblem {

	/**
	 * Returns the fitness of node.
	 * Evaluates this if necessary.
	 */
	public float getFitness(Solution node);
	
	/**
	 * Generates new neighbors
	 * to the given node.
	 */
	public ArrayList<Solution> generateSuccessors(Solution current);
	
	public Solution getInitialState();
	public float getTargetFitness();
}
