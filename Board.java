import java.util.ArrayList;
import java.util.Arrays;


public class Board {
		private int num;
		private int[][] goal;
		private int[][] board;
		private int blankrow;private int blankcol;

		public Board(int[][] blocks){
			int n=blocks.length;
			int[][] g= new int[n][n];
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++)
				g[i][j]=i*n+j+1;}
			g[n-1][n-1]=0;
			int[][] b=new int[n][n];
	    	for(int k=0;k<n;k++){
	    		for(int l=0;l<n;l++){
	    			b[k][l]=blocks[k][l];
	    			if(b[k][l]==0){blankrow=k;blankcol=l;}
	    		}}
			this.goal=g;this.num=n;this.board=b;}           // construct a board from an n-by-n array of blocks
	                                           // (where blocks[i][j] = block in row i, column j)
	    
		public int dimension(){return num;}              // board dimension n
	    public int hamming(){
	    	int h=0;
	    	for(int i=0;i<num;i++){
	    		for(int j=0;j<num;j++){
	    		if(board[i][j]!=0&&board[i][j]!=goal[i][j])h=h+1; }}
	    	return h;}    // number of blocks out of place
	    
	    public int manhattan(){
	    	int m=0;
	    	int p;
	    	for(int i=0;i<num;i++){
	    		for(int j=0;j<num;j++){
	    		p=board[i][j];
	    		if(p!=0){
	    		int row=(p-1)/num;int col=p%num-1;if(col==-1) col=num-1;
	    		int a=row-i;int b=col-j;
	    		if(a>0){m=m+a;}
	    		else{m=m-a;}
	    		if(b>0){m=m+b;}
	    		else{m=m-b;}}}}
	    	return m;
	    }// sum of Manhattan distances between blocks and goal
	    
	    public boolean isGoal(){
	    	
	    	return Arrays.deepEquals(goal,board);
	    }                // is this board the goal board?
	    
	    public Board twin(){
	    	int[][] twin=new int[board.length][board[0].length];
	    	for(int i=0;i<num;i++){
	    		for(int j=0;j<num;j++){
	    			twin[i][j]=board[i][j];
	    		}}
	    	if(twin[0][1]==0||twin[1][0]==0){
	    		int tmp = twin[0][0];
	    		twin[0][0]=twin[1][1];
	    		twin[1][1]=tmp;}
	    	else{
	    		int tmp = twin[0][1];
	    		twin[0][1]=twin[1][0];
	    		twin[1][0]=tmp;}
	    	
	    	return new Board(twin);
	    	}                    // a board that is obtained by exchanging any pair of blocks

	    public boolean equals(Object y){
	    	if(!Board.class.isInstance(y))return false;
	    	Board that=(Board) y;
	    	return Arrays.deepEquals(this.board, that.board);
	    }        // does this board equal y?
	    
	    public Iterable<Board> neighbors(){
	    	ArrayList<Board> neigh = new ArrayList<Board>();
	    	int[][] forexc= new int[board.length][board[0].length];
	    	for(int i=0;i<num;i++){
	    		for(int j=0;j<num;j++){
	    			forexc[i][j]=board[i][j];}}
	    	int br=blankrow;int bc=blankcol;
	    	if(br>0){exchangeadd(neigh,forexc,br,bc,br-1,bc);}
	    	if(br<num-1){exchangeadd(neigh,forexc,br,bc,br+1,bc);}
	    	if(bc>0){exchangeadd(neigh,forexc,br,bc,br,bc-1);}
	    	if(bc<num-1){exchangeadd(neigh,forexc,br,bc,br,bc+1);}
	    	return neigh;
	    	
	    }     // all neighboring boards
	    
	    private void exchangeadd(ArrayList<Board> neigh,int[][] forexc,int br,int bc, int x, int y){
	    	int tmp;
	    	tmp=forexc[x][y];
	    	forexc[x][y]=0;
	    	forexc[br][bc]=tmp;
	    	neigh.add(new Board(forexc));
	    	forexc[x][y]=tmp;
	    	forexc[br][bc]=0;
	    }
	    
	    public String toString(){
	    	String output;
	    	output=Integer.toString(num);
	    	for(int i=0;i<num;i++){
	    		output=output+'\n';
	    		for(int j=0;j<num;j++){
	    		output=output+" "+Integer.toString(board[i][j]);
	    		}
	    	}
	    	output=output+"\n";
	    	return output;
	    }               // string representation of this board (in the output format specified below)

	    public static void main(String[] args){} // unit tests (not graded)
	}

