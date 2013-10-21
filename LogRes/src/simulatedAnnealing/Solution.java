package simulatedAnnealing;

public abstract class Solution {

	public float fitness;
	
	/**
	 * Returns a copy of itself
	 */
	public abstract Solution copySolution();
	public abstract float getFitness();
	
}
