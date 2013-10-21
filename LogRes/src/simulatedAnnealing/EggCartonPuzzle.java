package simulatedAnnealing;

import java.util.ArrayList;

public class EggCartonPuzzle implements SimulatedAnnealingProblem {

	private final int BOARD_SIZE = 5;
	private final int K = 2;
	private final float TARGET_FITNESS = 1;
	
	@Override
	public float getFitness(Solution solution) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Method for generating neighbor solutions.
	 * It picks the row with the highest egg count and the row
	 * with the lowest egg count, and generates neighbors by
	 * moving different eggs (one in each neighbor) from the
	 * high-count row to the low-count row.
	 */
	public ArrayList<Solution> generateSuccessors(Solution current) {
		ArrayList<Solution> neighbors = new ArrayList<Solution>();
		int[] offenders = pickOffendingRows((EggCartonSolution)current);
		
		for (int j = 0; j < BOARD_SIZE; j++) {
			if (((EggCartonSolution)current).board[offenders[0]][j] == '0') {
				EggCartonSolution newNeighbor = current.
				
			}
		}
		return neighbors;
	}
	
	/**
	 * Inspects an EggCartonSolution and returns two row numbers
	 * [X,Y] where X is the row with the most eggs in it, and
	 * Y is the row with the least eggs in it.
	 * These rows are used for generating new successors.
	 */
	private int[] pickOffendingRows(EggCartonSolution current) {
		int highRowIndex=0, lowRowIndex=1, highCount=0, lowCount=BOARD_SIZE;
		for (int i = 0; i < BOARD_SIZE; i++) {
			int count = countEggsInRow(current.board[i]);
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
		return offendingRows;
	}

	/**
	 * Counts number of eggs in a row of the board.
	 */
	private int countEggsInRow(char[] row) {
		int i = 0;
		for (char c : row) {
			if (c == '0') i++;
		}
		return i;
	}
	

	@Override
	public Solution getInitialState() {
		EggCartonSolution initialState = new EggCartonSolution(BOARD_SIZE);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < K; j++) {
				initialState.board[j][i] = '0';
			}
		}
		return initialState;
	}

	@Override
	public float getTargetFitness() {
		return TARGET_FITNESS;
	}

}
