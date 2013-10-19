package aStarAlgorithm;

public abstract class Node implements Comparable<Node> {

	int g;
	int h;
	Node parent;
	Node[] children;
}
