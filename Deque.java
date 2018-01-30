import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	   private int n;
	   private Item[] list;
	   private int first;
	   private int prior;
	   private int last;
	   private int latter;
	   
	
	public Deque(){
		list = (Item[]) new Object[4];
        n = 0;
        first = 0;
        prior = 0;
        last = 0;
        latter = 0;
	   }                           // construct an empty deque
	
	public boolean isEmpty(){return n==0;}                 // is the deque empty?
	   
	public int size(){return n;}                        // return the number of items on the deque
	   
	public void addFirst(Item item){
		 if (item == null) throw new NullPointerException();
		 if (n == list.length){resize(2*list.length);}
		 if(isEmpty()){prior=0;first=0;last=0;latter=1;}
		 list[prior--]=item;
		 first = prior+1;
		 if(prior==-1){prior=list.length-1;}
		 n++;}				// add the item to the front
	   
	public void addLast(Item item){
		if (item == null) throw new NullPointerException();
		if (n == list.length){resize(2*list.length);}
		if(isEmpty()){prior=list.length-1;first=0;last=0;latter=0;}
		list[latter++]=item;
		 last=latter-1;
		 if(latter==list.length){latter=0;}
		 n++;}				// add the item to the end
	   
	public Item removeFirst(){
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item tmp = list[first];
		list[first++] = null;
		n--;
		prior=first-1;
		if(first==list.length){first=0;}
		if (n > 0 && n == list.length/4) resize(list.length/2); 
		return tmp;}                // remove and return the item from the front
	   
	public Item removeLast(){
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
		Item tmp = list[last--];
		n--;
		latter=last+1;
		if(last==-1){last=list.length-1;}
		if (n > 0 && n == list.length/4) resize(list.length/2); 
		return tmp;}                 // remove and return the item from the end
	
	private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = list[(first + i) % list.length];
        }
        list = temp;
        first = 0;prior=list.length-1;
        last  = n-1;latter=n;}
	   
	public Iterator<Item> iterator(){
		return new DequeIterator();
	}         // return an iterator over items in order from front to end
	
	private class DequeIterator implements Iterator<Item>{
		private int i=0;
		//public DequeIterator() {i = 0;}
		public boolean hasNext(){
			return n>i;}
		public Item next(){
			if (!hasNext()) throw new NoSuchElementException();
			Item tmp= list[(i+first)%list.length];
			i++;
			return tmp;
			}
		
		public void remove() {throw new UnsupportedOperationException();}
		}
	
	public static void main(String[] args){
		Deque<String> Hanzi= new Deque();
		Hanzi.addLast("d");System.out.println(Hanzi.last);
		Hanzi.addFirst("a");System.out.println(Hanzi.last);
		Hanzi.addFirst("e");System.out.println(Hanzi.last);
		Hanzi.addLast("b");System.out.println(Hanzi.last);
		Hanzi.addLast("c");System.out.println(Hanzi.last);
		Iterator<String> it=Hanzi.iterator();
		while(it.hasNext()){
			System.out.println(it.next());}
		//while(!Hanzi.isEmpty()){System.out.println(Hanzi.removeLast()+Hanzi.removeFirst());}
		}   // unit testing (optional)
	}
