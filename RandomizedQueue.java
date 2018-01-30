import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] list;       // queue elements
    private int n;          // number of elements on queue
	   
    
    public RandomizedQueue(){
        list = (Item[]) new Object[4];
        n = 0;
        }                 // construct an empty randomized queue
	   
    public boolean isEmpty(){
    	return n==0;
    }                 // is the queue empty?
	   
    public int size(){
    	return n;
    }                        // return the number of items on the queue
    
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = list[i];
        }
        list = temp;
    }
    
    public void enqueue(Item item){
    	if (item == null) throw new NullPointerException();
    	if (n == list.length) resize(2*list.length);   // double size of array if necessary
        list[n++] = item;                 
        int index = StdRandom.uniform(n);
        movelast(list,index,n);
    }           // add the item
	   
    public Item dequeue(){
    	if(isEmpty()){throw new java.util.NoSuchElementException();}
    	Item result=list[n-1];
    	list[n-1]=null;
    	n--;
    	if (n > 0 && n == list.length/4) resize(list.length/2);
    	return result;
    }                    // remove and return a random item
	   
    public Item sample(){
    	if(isEmpty()){throw new java.util.NoSuchElementException();}
    	int index = StdRandom.uniform(n);
    	return list[index];	
    }                     // return (but do not remove) a random item
	   
    public Iterator<Item> iterator(){
    	return new RandomQueueIterator();
    }         // return an independent iterator over items in random order
	
    private class RandomQueueIterator implements Iterator<Item>{
    	private Item next;
    	private int remain=n;
    	private Item[] seque = copy(list);
    	
    	public boolean hasNext(){
			return remain>0;}
    	
    	public Item next(){
    		if (!hasNext()) throw new NoSuchElementException();
    		int index=StdRandom.uniform(remain);
    		movelast(seque,index,remain);
    		next=seque[remain-1];
    		remain--;
    		return next;
    	}
    	public void remove() {throw new UnsupportedOperationException();}
    }
    
    private void movelast(Item[] seq,int i,int n){
    	Item selected;
    	selected = seq[i];
    	seq[i]=seq[n-1];
    	seq[n-1]=selected;
    }
    
    private Item[] copy(Item[] a){
    	int n=a.length;
    	Item[] b = (Item[]) new Object[n];
    	for(int i =0;i<n;i++){b[i]=a[i];}
    	return b;
    }
    
    public static void main(String[] args){
    	RandomizedQueue<String> rq = new RandomizedQueue<String>();
    	rq.enqueue("a");
    	rq.enqueue("b");
    	rq.enqueue("c");
    	rq.enqueue("d");


    }   // unit testing (optional)
	}
