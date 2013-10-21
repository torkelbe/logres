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

	@Override
	public Solution copySolution() {
		// TODO Auto-generated method stub
		return null;
	}
}
