package simulatedAnnealing;

public class EggCartonSolution extends Solution {

	public char[][] board;	// <------ board[row][column]
	
	public EggCartonSolution(int size) {
		
		// initialize board:
		board = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = '-';
			}
		}
//		board[2][2] = '0';
	}
	
	public String toString() {
		String print = "";
		for (char[] row : board) {
			print += "[ ";
			for (char i : row) {
				print += i +" ";
			}
			print += "]\n";
		}
		return print;
	}

	public Solution copySolution() {
		EggCartonSolution duplicate = new EggCartonSolution(board.length);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				duplicate.board[i][j] = board[i][j];
			}
		}
		return duplicate;
	}
	
	public float getFitness() {
		return this.fitness;
	}
}
