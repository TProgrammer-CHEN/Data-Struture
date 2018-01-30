import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class FastCollinearPoints {
	private int n;private LineSegment[] ls;
	
   public FastCollinearPoints(Point[] points){
	   if(points==null){throw new java.lang.NullPointerException();}
	   LineSegment[] lines=new LineSegment[points.length];int ind=0;
	   Arrays.sort(points);
	   
	   for(int i=0;i<points.length-1;i++){
		   if(points[i]==null){throw new java.lang.NullPointerException();}
		   if(points[i].compareTo(points[i+1])==0){throw new java.lang.IllegalArgumentException();}}
	   
	   for(int i=0;i<points.length;i++){// for every point in the points set.
		  Point p=points[i];Point[] aux= new Point[points.length-i-1];
		  
		  for(int j=i+1;j<points.length;j++){//create auxiliary array after the point.
			  aux[j-i-1]=points[j];}
		  
		  Arrays.sort(aux,p.slopeOrder());//sort auxiliary array by their slope to point p
		  Point[] linepoint=new Point[4];linepoint[0]=p;//val for potential points for lines
		  
		  for(int j=0;j<points.length-i-3;j++){//find 3 adjacent equal points
			  linepoint[0]=p;
			  if(p.slopeOrder().compare(aux[j],aux[j+2])==0){// if slope equals, add them to linepoint and sort and add line upon it.
				  for(int k=0;k<3;k++)linepoint[k+1]=aux[j+k];
						  Arrays.sort(linepoint);
						  lines[ind]=new LineSegment(linepoint[0],linepoint[3]);ind++;}
			  //else{Point[] linepoint=new Point[4];linepoint[0]=p;}
			  }}
	   n=ind;
	   ls=new LineSegment[n];
	   for(int i=0;i<n;i++)ls[i]=lines[i];
   }     // finds all line segments containing 4 or more points
   
   public int numberOfSegments(){return ls.length;}        // the number of line segments
   
   public LineSegment[] segments(){
	   final LineSegment[] segment=ls.clone();
	   return segment;}                // the line segments
   
   public static void main(String[] args) {
	   
		    // read the n points from a file
		    In in = new In(args[0]);
		    int n = in.readInt();
		    Point[] points = new Point[n];
		    
		    for (int i = 0; i < n; i++) {
		        int x = in.readInt();
		        int y = in.readInt();
		        points[i] = new Point(x, y);
		    }

		    // draw the points
		    StdDraw.enableDoubleBuffering();
		    StdDraw.setXscale(0, 32768);
		    StdDraw.setYscale(0, 32768);
		    for (Point p : points) {
		        p.draw();
		    }
		    StdDraw.show();

		    // print and draw the line segments
		    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		    for (LineSegment segment : collinear.segments()) {
		        StdOut.println(segment);
		        segment.draw();
		    }
		    StdDraw.show();
	    
	   
   }
}
