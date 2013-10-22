package simulatedAnnealing;

public abstract class Solution {

	public double fitness;
	
	/**
	 * Returns a copy of itself
	 */
	public abstract Solution copySolution();
	public abstract double getFitness();
	
}
