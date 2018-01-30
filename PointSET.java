import java.util.ArrayList;
import java.util.Iterator;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
	private SET<Point2D> points;
	   public         PointSET(){
		   points=new SET<Point2D>();
	   }                        // construct an empty set of points 
	   
	   public           boolean isEmpty(){
		   return points.size()==0;
	   }                     // is the set empty? 
	   
	   public               int size(){
		   return points.size();
	   }                         // number of points in the set 
	   
	   public              void insert(Point2D p){
		   if(p==null)throw new java.lang.NullPointerException();
		   points.add(p);
	   }              // add the point to the set (if it is not already in the set)
	   
	   public           boolean contains(Point2D p){
		   if(p==null)throw new java.lang.NullPointerException();
		   return points.contains(p);
	   }            // does the set contain point p? 
	   
	   public              void draw(){
		   Iterator<Point2D> setiter=points.iterator();
		   while(setiter.hasNext()){
			   setiter.next().draw();
		   }
	   }// draw all points to standard draw 
	   
	   public Iterable<Point2D> range(RectHV rect){
		   if(rect==null)throw new java.lang.NullPointerException();
		   ArrayList<Point2D> overlap=new ArrayList<Point2D>();
		   Iterator<Point2D> setiter=points.iterator();
		   while(setiter.hasNext()){
			  Point2D p=setiter.next();
			  if((p.x()<=rect.xmax()&&p.x()>=rect.xmin())&&
					  (p.y()<=rect.ymax()&&p.y()>=rect.ymin())) overlap.add(p);
		   }
		   
		   return overlap;
		      
	   }             // all points that are inside the rectangle 
	   
	   
	   
	   
	   public           Point2D nearest(Point2D p){
		   if(p==null)throw new java.lang.NullPointerException();
		   Point2D nrst=null;
		   double dist;
		   Iterator<Point2D> setiter=points.iterator();
		   
		   if(setiter.hasNext()){nrst=setiter.next();dist=p.distanceTo(nrst);}
		   else{return null;}
		   while(setiter.hasNext()){
			  Point2D i=setiter.next();
			  double current=p.distanceTo(i);
			  if(current<dist){dist=current;nrst=i;}
		   }
		   return nrst;
	   }             // a nearest neighbor in the set to point p; null if the set is empty 

	   public static void main(String[] args) {}                 // unit testing of the methods (optional)
}
