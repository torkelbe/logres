package aStarAlgorithm;

import java.util.PriorityQueue; 

public class Checkers extends Node { 
    private int[] board;// 1 = R, -1=B. 
    public PriorityQueue<Node> children; 
    public Checkers parent;  
    private int g;// kostnaden fra å gå en node til den neste. 
    private int h;//heuristic, den sier hvor bra denne noden er i forhold til sluttresultatet. 
  
  
    public Checkers(int[] currentState, Checkers parent){ 
        this.board = currentState; 
        this.parent=parent; 
          
        if(parent==null) 
            g=0; 
        else
        this.g=parent.g+1; 
          
        children=new PriorityQueue<Node>(); 
    } 
  
    public int getH(){ 
        return h; 
    } 
    public void setH(int h){ 
        this.h=h; 
    } 
    public int getF(){ 
        return g+h; 
  
    } 
    
    public int getG(){ 
        return g; 
    } 
  
	public int[] getBoard(){ 
	    return board; 
	} 
  
    @Override
    public int compareTo(Node o) { 
        Checkers c= (Checkers) o; 
        if(c.getF()>getF()) 
            return 1; 
        else if(getF()<c.getF()) 
            return-1; 
        else
            return 0; 
    } 
    
    public String toString() {
    	String out = " ";
    	for (int i: board) {
    		if (i==1){
    			out += "R ";
    		} else if (i==-1){
    			out += "B ";
    		} else {
    			out += "_ ";
    		}
    	}
    	return out;
    }
  
  
} 