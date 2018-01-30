import java.util.Arrays;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	private int sum;
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF ufa;
	private final int num;
	
	   public Percolation(int n){
		   if(n<=0){throw new java.lang.IllegalArgumentException();}
		   num = n;
		   uf= new WeightedQuickUnionUF(num*num+2);
		   sum = 0;
		   grid = new boolean[n][n];
		   for(int i=0;i<n;i++){
			   Arrays.fill(grid[i], false);}
		   }                // create n-by-n grid, with all sites blocked
	   
	   public    void open(int row, int col){
		   if(row<1 || col <1 ||row > num || col > num){
			   throw new java.lang.IllegalArgumentException();}
		   
		   if(!isOpen(row,col)){
		   grid[row-1][col-1]=true;
		   sum= sum +1;
		   
		   if(row==1){uf.union(0,indexof(1,col));ufa.union(0,indexof(1,col));}
		   else{if(isOpen(row-1,col)) {
			   uf.union(indexof(row-1,col),indexof(row,col));
			   ufa.union(indexof(row-1,col),indexof(row,col));}}
		   
		   if(row==num){uf.union(num*num+1,indexof(num,col));}
		   else{if(isOpen(row+1,col)) {
			   uf.union(indexof(row+1,col),indexof(row,col));
			   ufa.union(indexof(row+1,col),indexof(row,col));}}
		   //if(row>1){if(isOpen(row-1,col)) {uf.union(index[row-2][col-1],index[row-1][col-1]);}}
		   //if(row<num){if(isOpen(row+1,col)) {uf.union(index[row][col-1],index[row-1][col-1]);}}
		   
		   if(col>1){if(isOpen(row,col-1)){
			   uf.union(indexof(row,col-1),indexof(row,col));
			   ufa.union(indexof(row,col-1),indexof(row,col));}}
		   
		   if(col<num){if(isOpen(row,col+1)){
			   uf.union(indexof(row,col+1),indexof(row,col));
			   ufa.union(indexof(row,col+1),indexof(row,col));}}
		   }}	  // open site (row, col) if it is not open already
	   
	   public boolean isOpen(int row, int col){
		   if(row<1 || col <1 ||row > num || col > num){
			   throw new java.lang.IllegalArgumentException();}
		   return grid[row-1][col-1];}  // is site (row, col) open?
	   
	   public boolean isFull(int row, int col){
		   if(row<1 || col <1 ||row > num || col > num){
			   throw new java.lang.IllegalArgumentException();}
		   return ufa.connected(indexof(row,col), 0);}  // is site (row, col) full?
	   
	   public     int numberOfOpenSites(){return sum;}       // number of open sites
	   
	   public boolean percolates(){return uf.connected(0, num*num+1);}              // does the system percolate?
	   
	   private int indexof(int row,int col){
		   return num*(row-1)+col;}
	   
	   public static void main(String[] args){
		   /**int count=0;
		   In in = new In(args[0]);
		   int n=in.readInt();
		   StdOut.print(n+"\n");
		   Percolation pc=new Percolation(n);
		   
		   for(int i=0;i<231;i++){
		   int row=in.readInt();int col=in.readInt();
		   pc.open(row, col);
		   StdOut.print(row+","+col+pc.isFull(18, 1)+'\n'); 
		   count=count+1;
		   }**/
	   }   // test client (optional)	
}
