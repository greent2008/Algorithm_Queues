import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
   private int N;
   private Node first;
   private Node last;
   private class Node{
       Item value;
       Node pre = null;
       Node next = null;
   }
   /*public Item getfirst()
   {
       if(first == null)
       {
           throw new UnsupportedOperationException("first is null");
       }
       return first.value;
   }*/
   /*public Item getlast()
   {
       if(last == null)
       {
           throw new UnsupportedOperationException("last is null");
       }
       return last.value;
   }*/
   /*public Item getprelast()
   {
       if(prelast == null)
       {
           throw new UnsupportedOperationException("prelast is null");
       }
       return prelast.value;
   }*/
   public Deque()                     // construct an empty deque
   {
       first = last = null;
       N = 0;
   }
   public boolean isEmpty()           // is the deque empty?
   {
       return N == 0;
   }
   public int size()                  // return the number of items on the deque
   {
       return N;
   }
   public void addFirst(Item item)    // insert the item at the front
   {
       if(item == null)
       {
           throw new java.lang.NullPointerException("item is null");
       }
       else
       {
           Node oldfirst = first;
           first = new Node();
           first.value = item;
           if(N == 0)
           {
               last = first;
           }
           else
           {
               oldfirst.pre = first;
               first.next = oldfirst;
           }
           N++;
       }
   }
   public void addLast(Item item)     // insert the item at the end
   {
       if(item == null)
       {
           throw new java.lang.NullPointerException("item is null");
       }
       else
       {
           Node oldlast = last;
           last = new Node();
           last.value = item;
           if(N == 0)
           {
               first = last;
           }
           else
           {
               oldlast.next = last;
               last.pre = oldlast;
           }
           N++;
       }
   }
   public Item removeFirst()          // delete and return the item at the front
   {
       if(N == 0)
       {
           throw new NoSuchElementException("deque is empty");
       }
       else
       {
           Item item = first.value;
           if(N == 1)
           {
               first = last = null;
           }
           else
           {
               first = first.next;
               first.pre = null;
           }
           N--;
           return item;
       }
   }
   public Item removeLast()           // delete and return the item at the end
   {
       if(N == 0)
       {
           throw new NoSuchElementException("deque is empty");
       }
       else
       {
           Item item = last.value;
           if(N == 1)
           {
               last = first = null;
           }
           else
           {
               last = last.pre;
               last.next = null;
           }
           N--;
           return item;
       }
   }
   public Iterator<Item> iterator()   // return an iterator over items in order from front to end
   {
       return new ListIterator();
   }
   private class ListIterator implements Iterator<Item>
   {
       private Node current = first;
       public boolean hasNext()
       {
           return current != null;
       }
       public void remove(){throw new java.lang.UnsupportedOperationException("remove() method in the iterator is called");}
       public Item next()
       {
           if(current == null)
           {
               throw new NoSuchElementException("no more item to return");
           }
           Item item = current.value;
           current = current.next;
           return item;
       }
   }
   /*public static void main(String[] args)
   {
       Thread t1;
       t1 = new Thread(new test());
       t1.start();
   }*/
}
/*class test implements Runnable{
    Deque<String> deque;
    public void run()
    {
        deque = new Deque<String>();
        deque.addFirst("apple");
        deque.addFirst("orange");
        deque.addFirst("mellon");
        deque.removeLast();
        deque.addFirst("banana");
        deque.addFirst("grape");
        Iterator<String> i = deque.iterator();
        while(i.hasNext())
        {
            System.out.print(i.next()+"->");
        }
    }
}*/
