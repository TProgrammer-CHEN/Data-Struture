import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BruteCollinearPoints {
	private LineSegment[] ls;

   public BruteCollinearPoints(Point[] points){
	  if(points==null){throw new java.lang.NullPointerException();}
	  Point s;Point p;Point[] line=new Point[4];
	  int n=0;LineSegment[] tmp=new LineSegment[points.length];
	  
	  
	   for(int i=0;i<points.length-3;i++){
		   Point p0=points[i];if(p0==null) throw new java.lang.NullPointerException();
		   line[0]=p0;
		   for(int j=i+1;j<points.length-2;j++){
			   Point p1=points[j];if(p1==null) throw new java.lang.NullPointerException();
			   
			   if(p0.compareTo(p1)==0){throw new java.lang.IllegalArgumentException();}
			   line[1]=p1;
			   
			   for(int k=j+1;k<points.length-1;k++){
				   Point p2=points[k];
				   if(p2==null) throw new java.lang.NullPointerException();
				   if(p2.compareTo(p0)==0){throw new java.lang.IllegalArgumentException();}
				   line[2]=p2;
				   if(p0.slopeOrder().compare(p1,p2)==0){
					   if(p2.compareTo(p1)==0){throw new java.lang.IllegalArgumentException();}
					   for(int l=k+1;l<points.length;l++){
						   Point p3=points[l];
						   if(p3==null) throw new java.lang.NullPointerException();
						   if(p3.compareTo(p0)==0){throw new java.lang.IllegalArgumentException();}
						   if(p0.slopeOrder().compare(p1,p3)==0){
							   if(p3.compareTo(p1)==0||p3.compareTo(p2)==0){throw new java.lang.IllegalArgumentException();}
							   line[3]=p3;Arrays.sort(line);
							   tmp[n]=new LineSegment(line[0],line[3]);n=n+1;}
					   }}}}}    // finds all line segments containing 4 points
	   
	   ls= new LineSegment[n];
	   for(int i=0;i<n;i++){ls[i]=tmp[i];}
	   
	  }
   
   public int numberOfSegments(){return ls.length;}        // the number of line segments
   public LineSegment[] segments(){
	   final LineSegment[] segment=ls.clone();
	   return segment;}                // the line segments\
   
   public static void main(String[] args) {
	    // read the n points from a file
	    /**In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    
	    
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);}**/
	   int n=15;
	   Point[] points = new Point[n];
	   points[0]=new Point(10000,0);points[1]=new Point(8000,2000);points[2]=new Point(2000,8000);
	   points[3]=new Point(0,10000);points[4]=new Point(20000,0);points[5]=new Point(18000,2000);
	   points[6]=new Point(2000,18000);points[7]=new Point(10000,20000);points[8]=new Point(30000,0);
	   points[9]=new Point(0,30000);points[10]=new Point(20000,10000);points[11]=new Point(13000,0);
	   points[12]=new Point(11000,3000);points[13]=new Point(5000,12000);points[14]=new Point(9000,6000);
	   
	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {p.draw();}
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();}
	    StdDraw.show();   
   }}
