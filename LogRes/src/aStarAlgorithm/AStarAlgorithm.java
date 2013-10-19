package aStarAlgorithm;

import java.util.ArrayList;

public interface AStarAlgorithm {

	public Node chooseBest(Node state1, Node state2);
	public Node DoesStateExist(Node state);
	public ArrayList<Node> createChildStates(Node state);
	public boolean isGoal(Node state);
	public int h(Node state);
	public void printState(Node state);
	public void propagatePathImprovements(Node state);
	
}
