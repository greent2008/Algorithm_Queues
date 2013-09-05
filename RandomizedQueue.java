import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] a;
   private int N;
   private int capacity;
   public RandomizedQueue()           // construct an empty randomized queue
   {
       N = 0;
       a = (Item[]) new Object[1];
       capacity = 1;
   }
   public boolean isEmpty()           // is the queue empty?
   {
       return N == 0;
   }
   public int size()                  // return the number of items on the queue
   {
       return N;
   }
   public void enqueue(Item item)     // add the item
   {
       if(item == null)
       {
           throw new java.lang.NullPointerException("item is null");
       }
       if(N == capacity)
       {
           Item[] b = (Item[])new Object[2*capacity];
           int i;
           for(i = 0; i < N; i++)
           {
               b[i] = a[i];
           }
           capacity = 2*capacity;
           b[i] = item;
           a = b;
           N++;
       }
       else
       {
           a[N++] = item; 
       }
   }
   public Item dequeue()              // delete and return a random item
   {
       Item item;
       if(isEmpty())
       {
           throw new NoSuchElementException("RandomizedQueue is empty");
       }
       int index = StdRandom.uniform(N);
       item = a[index];
       if(index != N)
       {
           a[index] = a[N-1];
           a[N-1] = null;
       }
       else
       {
           a[N-1] = null;
       }
       N--;
       if(N > 0 && N == capacity/4)
       {
           Item[] b = (Item[])new Object[capacity/2];
           int i;
           for(i = 0; i < N; i++)
           {
               b[i] = a[i];
           }
           a = b;
           capacity = capacity/2;
       }
       return item;
   }
   public Item sample()               // return (but do not delete) a random item
   {
       if(isEmpty())
       {
           throw new NoSuchElementException("RandomizedQueue is empty");
       }
       int index = StdRandom.uniform(N);
       return a[index];
   }
   public Iterator<Item> iterator()   // return an independent iterator over items in random order
   {
       return new RandomizedQueueIterator();
   }
   private class RandomizedQueueIterator implements Iterator<Item>{
       private int index;
       private Item[] iter;
       public RandomizedQueueIterator()
       {
           iter = (Item[])new Object[N];
           for(index = 0; index < N; index++)
           {
               iter[index] = a[index];
           }
           index = 0;
           StdRandom.shuffle(iter);
       }
       public boolean hasNext()
       {
           return index < N;
       }
       public void remove(){throw new java.lang.UnsupportedOperationException("remove() method in the iterator is called");}
       public Item next()
       {
           if(!hasNext())
           {
               throw new NoSuchElementException("no more items to return");
           }
           int i = index;
           index++;
           return iter[i];
       }
   }
}
