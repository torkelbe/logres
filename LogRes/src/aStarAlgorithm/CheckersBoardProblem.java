package aStarAlgorithm;

import java.util.ArrayList;

public class CheckersBoardProblem implements AStarAlgorithm {
	
	BestFirstSearch searchAlgorithm;
	
//	private Checkers startNode; 
//  private int[] goal; 
    private int endH; 
	
	public CheckersBoardProblem(BestFirstSearch searcher, Checkers startNode, int[] goal){ 
		searchAlgorithm = searcher;
		
//        this.startNode=startNode;
//        this.goal=goal; 
        
        Checkers endGoal= new Checkers(goal, null); 
        endH= h(endGoal); 
    }
	
	/**MAIN**/
	public static void main(String[] args) {
		
		int[] initialSetup = {1,1,1,0,-1,-1,-1};
		int[] goalState = {-1,-1,-1,0,1,1,1};
		
		BestFirstSearch searcher = new BestFirstSearch();
		Checkers rootNode = new Checkers(initialSetup, null);
		CheckersBoardProblem task = new CheckersBoardProblem(searcher, rootNode, goalState);
		
		searcher.setProblem(task, rootNode);
		searcher.searchSolution();
	}

	@Override
	public Node chooseBest(Node state1, Node state2) {
        Checkers first = (Checkers) state1; 
        Checkers second = (Checkers) state2; 
        if(first.getG()>second.getG()) 
            return second; 
  
        else
            return first;
	}

	@Override
	public Node DoesStateExist(Node state) {
		
		int[] newBoard = ((Checkers)state).getBoard();
		
		for (Node old: searchAlgorithm.OPEN) {
			if (newBoard == ((Checkers)old).getBoard()) {
				return old;
			}
		}
		for (Node old: searchAlgorithm.CLOSED) {
			if (newBoard == ((Checkers)old).getBoard()) {
				return old;
			}
		}
		
		return null;
	}

	@Override
	public ArrayList<Node> createChildStates(Node state) {
		Checkers board= (Checkers) state; 
        ArrayList<Node> result = new ArrayList<Node>(); 
        int i; 
        for(i=0; i<board.getBoard().length; i++){ 
            if(board.getBoard()[i]==0){ 
                break; 
            } 
        }    
        
        if(i==1) { 
            result.add(moveLeft(board, i)); 
        } else if (i>1) { 
            result.add(hopLeft(board, i)); 
            result.add(moveLeft(board, i)); 
        }
        
        if(i==board.getBoard().length-2) { 
            result.add(moveRight(board, i)); 
        } else if (i<board.getBoard().length-2) {
        	result.add(moveRight(board, i));
        	result.add(hopRight(board, i));
        }
        return result; 
	}
	
	private Node hopRight(Checkers c, int index) { 
        int[] nextState = new int[c.getBoard().length]; 
        System.arraycopy(c.getBoard(), 0, nextState, 0, c.getBoard().length); 
        int temp = nextState[index+2]; 
        nextState[index+2]=0; 
        nextState[index]=temp; 
        Checkers newChilde = new Checkers(nextState, c); 
        newChilde.setH(endH-h(newChilde)); 
        return newChilde;} 
  
    private Node moveRight(Checkers c, int index) { 
        int[] nextState = new int[c.getBoard().length]; 
        System.arraycopy(c.getBoard(), 0, nextState, 0, c.getBoard().length); 
        int temp = nextState[index+1]; 
        nextState[index+1]=0; 
        nextState[index]=temp; 
        Checkers newChilde = new Checkers(nextState, c); 
        newChilde.setH(endH-h(newChilde)); 
        return newChilde; 
    } 
    
    private Checkers hopLeft(Checkers c, int index){ 
        int[] nextState = new int[c.getBoard().length]; 
        System.arraycopy(c.getBoard(), 0, nextState, 0, c.getBoard().length); 
        int temp = nextState[index-2]; 
        nextState[index-2]=0; 
        nextState[index]=temp; 
        Checkers newChilde = new Checkers(nextState, c); 
        newChilde.setH(endH-h(newChilde)); 
        return newChilde; 
    } 
  
    private Checkers moveLeft(Checkers c, int index){ 
        int[] nextState = new int[c.getBoard().length]; 
        System.arraycopy(c.getBoard(), 0, nextState, 0, c.getBoard().length); 
        int temp = nextState[index-1]; 
        nextState[index-1]=0; 
        nextState[index]=temp; 
        Checkers newChilde = new Checkers(nextState, c); 
        newChilde.setH(endH-h(newChilde)); 
        return newChilde; 
    } 

	@Override
	public boolean isGoal(Node state) {
		return ((Checkers) state).getH()==0; 
	}

	@Override
	public int h(Node state) {

        Checkers checkerBoard= (Checkers) state; 
        int[] board = checkerBoard.getBoard(); 
  
        int redCounter=0; 
        int blackCounter=0; 
        for(int i=0; i<board.length;i++){ 
            if(board[i]==1){ 
                redCounter+=i; 
            } 
            else if(board[i]==-1){ 
                blackCounter+=i;         
            } 
        } 
        return (blackCounter-redCounter); 
	}

	@Override
	public void propagatePathImprovements(Node state) {
		for (Node child: state.children) {
			child.g = state.g + 1;
			propagatePathImprovements(child);
		}
	}

	@Override
	public void printState(Node state) {
		System.out.println(state);
	}

}
