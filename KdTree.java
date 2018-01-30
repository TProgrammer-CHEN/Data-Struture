
import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

	   private node root;
	   private ArrayList<Point2D> draw=new ArrayList<Point2D>();
	   
	   private class node {
		   private Point2D self;
		   private node left,right;
		   boolean direct;
		   int size;
		   double x,y;
		   
		   public node(Point2D p){
			   x=p.x();y=p.y();
			   size=1;self=p;
			   left=null;right=null;}	   
	   }
	
	   public         KdTree(){}         // construct an empty set of points 
	   
	   public boolean isEmpty(){return size()==0;}// is the set empty? 
	   
	   public int size(){return size(root);}                         // number of points in the set 
	   
	   private int size(node nd){
		   if(nd==null) return 0;
		   return nd.size;
	   }
	   
	   public void insert(Point2D p){
		   if (p == null) throw new IllegalArgumentException();
		   draw.add(p);
		   root=this.insert(root, p,true);
	   }              // add the point to the set (if it is not already in the set)
	   
	   private node insert(node x,Point2D p,boolean d){
		   if (x == null){node branch=new node(p);branch.direct=d;return branch;}
		   if(x.x==p.x()&&x.y==p.y()){return x;}
		   boolean axis=x.direct;
		   
		   if(axis){
			   if(x.x>p.x()){x.left=insert(x.left,p,!axis);}
			   else{x.right=insert(x.right,p,!axis);}}
		   
		   else{
			   if(x.y>p.y()){x.left=insert(x.left,p,!axis);}
			   else{x.right=insert(x.right,p,!axis);}}
		   
		   x.size=1+size(x.left)+size(x.right);
		   return x;}
	   
	   public  boolean contains(Point2D p){
		   if (p == null) throw new IllegalArgumentException();
		   return seek(root,p);}// does the set contain point p? 
	   
	   private boolean seek(node x, Point2D p){
		   if (x == null) return false;
		   if(x.x==p.x()&&x.y==p.y()) return true;
		   boolean axis=x.direct;
		   if(axis){
			   if(x.x>p.x()){return seek(x.left,p);}
			   else{return seek(x.right,p);}}
		   
		   else{
			   if(x.y>p.y()){return seek(x.left,p);}
			   else{return seek(x.right,p);}}
	   }
	   
	   public              void draw(){
		   Iterator<Point2D> iter= draw.iterator();
		   while(iter.hasNext()){
			   iter.next().draw();
		   }
	   }                        // draw all points to standard draw 
	   
	   public Iterable<Point2D> range(RectHV rect){
		   ArrayList<Point2D> overlap=new ArrayList<Point2D>();
		   cover(rect,overlap,root);
		   return overlap;
	   }             // all points that are inside the rectangle 
	   
	   private void cover(RectHV rect,ArrayList<Point2D> array,node n){
		   if(n==null) return;
		   boolean d = n.direct;
		   if(d){//compare by x
			   if(n.x>=rect.xmin()&&n.x<=rect.xmax()){//with in range of x, search both subtrees
				   if(n.y<=rect.ymax()&&n.y>=rect.ymin())array.add(new Point2D(n.x,n.y));
				   cover(rect,array,n.left);
				   cover(rect,array,n.right);}
			   
			   else{//out of range of x
				   if(n.x>rect.xmax()) cover(rect,array,n.left);
				   else cover(rect,array,n.right);
			   } 
		   }
		   else{//compare by y
			   if(n.y>=rect.ymin()&&n.y<=rect.ymax()){//with in range of y, search both subtrees
				   if(n.x<=rect.xmax()&&n.x>=rect.xmin())array.add(new Point2D(n.x,n.y));
				   cover(rect,array,n.left);
				   cover(rect,array,n.right);}
				   
			   else{//out of range of y
				   if(n.y>rect.ymax()) cover(rect,array,n.left);
				   else cover(rect,array,n.right);   
				   }
		   }
		   
	   
	   }
	   
	   public    Point2D nearest(Point2D p){
		   Point2D nrst;
		   nrst=explore(p,root,root.self);
		   return nrst;
	   }             // a nearest neighbor in the set to point p; null if the set is empty 
	   
	   private Point2D explore(Point2D p,node nd,Point2D nrst) {
		   if(nd==null){return nrst;}
		   if(p.distanceTo(nd.self)<p.distanceTo(nrst)){nrst=nd.self;}
		   double ptol;
		   if(nd.direct){
			   ptol=p.x()-nd.x;
			   if(ptol<0){
				   nrst=explore(p,nd.left,nrst);ptol=-ptol;
				   if(ptol<p.distanceTo(nrst)) nrst=explore(p,nd.right,nrst);}
			   else{
				   nrst=explore(p,nd.right,nrst);
				   if(ptol<p.distanceTo(nrst)) nrst=explore(p,nd.left,nrst);}
		   }
		   else{
			   ptol=p.y()-nd.y;
			   if(ptol<0){
				   nrst=explore(p,nd.left,nrst);ptol=-ptol;
				   if(ptol<p.distanceTo(nrst)) nrst=explore(p,nd.right,nrst);}
			   else{
				   nrst=explore(p,nd.right,nrst);
				   if(ptol<p.distanceTo(nrst)) nrst=explore(p,nd.left,nrst);}
		   }
		   
		   
		   return nrst;
		   }

	   public static void main(String[] args) {;}                 // unit testing of the methods (optional)
}
