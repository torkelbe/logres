package simulatedAnnealing;

import java.util.ArrayList;

public class EggCartonPuzzle implements SimulatedAnnealingProblem {

	private final int BOARD_SIZE = 10;
	private final int K = 3;
	private final float TARGET_FITNESS = 1;
	
	/**
	 * Returns fitness of solution.
	 * Evaluates the fitness as a value between 0 and 1, where 1
	 * is an optimal solution, and 0 is a minimal solution.
	 * For every egg in each row that exceeds the value in K,
	 * the fitness value is lower.
	 * @param solution
	 */
	public float getFitness(Solution solution) {
		if (solution.fitness != 0) return solution.fitness;
		
		int maxViolations = K*(BOARD_SIZE - K);
		int solutionViolations = 0;
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			int count = countEggsInRow(((EggCartonSolution)solution).board[i]);
			count -= K;
			if (count > 0) solutionViolations += count;
		}
		float fitness = (float)solutionViolations / (float)maxViolations;
		fitness = 1 - fitness;
		solution.fitness = fitness;
		return fitness;
	}

	/**
	 * Method for generating neighbor solutions.
	 * It picks the row with the highest egg count and the row
	 * with the lowest egg count, and generates neighbors by
	 * moving different eggs (one in each neighbor) from the
	 * high-count row to the low-count row.
	 * @param current
	 */
	public ArrayList<Solution> generateSuccessors(Solution current) {
		ArrayList<Solution> neighbors = new ArrayList<Solution>();
		int[] offenders = pickOffendingRows((EggCartonSolution)current);
		
		for (int j = 0; j < BOARD_SIZE; j++) {
			if (((EggCartonSolution)current).board[offenders[0]][j] == '0'
					&& ((EggCartonSolution)current).board[offenders[1]][j] == '-') {
				EggCartonSolution newNeighbor = (EggCartonSolution) current.copySolution();
				swapEgg(newNeighbor, offenders[0], offenders[1], j);
				neighbors.add(newNeighbor);
			} else {
			}
		}
		return neighbors;
	}
	
	/**
	 * Inspects an EggCartonSolution and returns two row numbers
	 * [X,Y] where X is the row with the most eggs in it, and
	 * Y is the row with the least eggs in it.
	 * These rows are used for generating new successors.
	 * @param solution
	 */
	private int[] pickOffendingRows(EggCartonSolution solution) {
		int highRowIndex=0, lowRowIndex=1, highCount=0, lowCount=BOARD_SIZE;
		for (int i = 0; i < BOARD_SIZE; i++) {
			int count = countEggsInRow(solution.board[i]);
			if (count > highCount) {
				highRowIndex = i;
				highCount = count;
			}
			if (count < lowCount) {
				lowRowIndex = i;
				lowCount = count;
			}
		}
		
		int[] offendingRows = {highRowIndex, lowRowIndex};
//		System.out.println("["+offendingRows[0]+", "+offendingRows[1]+"]");
		return offendingRows;
	}

	/**
	 * Counts number of eggs in a row of the board.
	 * @param row
	 */
	private int countEggsInRow(char[] row) {
		int i = 0;
		for (char c : row) {
			if (c == '0') i++;
		}
		return i;
	}
	
	/**
	 * Swaps an egg between rows, in the same column.
	 * Swaps egg from coordinates [row1][column] to [row2][column]
	 * @param solution
	 * @param row1
	 * @param row2
	 * @param column
	 */
	private void swapEgg(EggCartonSolution solution, int row1, int row2, int column) {
//		char temp1 = solution.board[row1][column];
//		char temp2 = solution.board[row2][column];
//		solution.board[row1][column] = temp2;
//		solution.board[row1][column] = temp1;
		solution.board[row1][column] = '-';
		solution.board[row2][column] = '0';
	}

	public Solution getInitialState() {
		EggCartonSolution initialState = new EggCartonSolution(BOARD_SIZE);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < K; j++) {
				initialState.board[j][i] = '0';
			}
		}
		return initialState;
	}

	public float getTargetFitness() {
		return TARGET_FITNESS;
	}

}
