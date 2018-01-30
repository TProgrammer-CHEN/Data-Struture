
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double mean;
	private double stddev;
	private int num;
	private int t;
	private double[] sample;
	
	public PercolationStats(int n, int trials){
		if(n<=0||trials<=0){throw new java.lang.IllegalArgumentException();}
		t=trials;num = n;
		
		double threshold = 0;double p=0;
		sample = new double[t];
		
		for(int i=0;i<t;i++){
			threshold=0;
			Percolation pc = new Percolation(num);
			
		while(!pc.percolates()){
		int ind=StdRandom.uniform(num*num)+1; 
		int row = ((ind-1)/num)+1;int col =ind-(row-1)*num;
		pc.open(row,col);
		threshold=pc.numberOfOpenSites();
		p=threshold/(num*num);}
		sample[i]=p;
		//sum=sum+p;
		}}// perform trials independent experiments on an n-by-n grid
	
	public double mean(){
		mean= StdStats.mean(sample);
		return mean;}// sample mean of percolation threshold
	
	public double stddev(){
		stddev=StdStats.stddev(sample);
		return stddev;}// sample standard deviation of percolation threshold
	
	public double confidenceLo(){
		double sd = stddev();
		double bar= mean();
		double low= bar-((1.96*sd)/Math.sqrt(t));
		return low;}// low  endpoint of 95% confidence interval
	
	public double confidenceHi(){
		double sd = stddev();
		double bar= mean();
		double up= bar+((1.96*sd)/Math.sqrt(t));
		return up;}// high endpoint of 95% confidence interval
	
	public static void main(String[] args){
		int n = 2//Integer.parseInt (args[0])
				;
		int t = 10000//Integer.parseInt (args[1])
				;
		PercolationStats pcs= new PercolationStats(n,t);
		
		StdOut.println("mean                    = "+pcs.mean());
		StdOut.println("Standard deviation      = "+pcs.stddev());
		StdOut.println("95% confidence interval = ["+pcs.confidenceLo()+","+pcs.confidenceHi()+"]");
		
		}// test client (described below)
}
