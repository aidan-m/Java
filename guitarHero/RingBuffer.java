/*
* name: Aidan McLaughlin
* netID; amclaughlin
* 
*/
public class RingBuffer {
  
    private int first = 0; // stores index of least recently inserted item
    private int last = 0;  // stores index one beyond most recently inserted item
    private int size = 0;  // stores number of items currently in the ring buffer
    private double[] ringBuffer; //= new double[capacity];
    
    public         RingBuffer(int capacity)  //  creates an empty ring buffer with the specified capacity
    {
      ringBuffer = new double[capacity];
    }
    
    public     int capacity()                //  returns the capacity of this ring buffer
    {
      return ringBuffer.length;
    }
    
    public     int size()                    //  returns the number of items currently in this ring buffer
    {
      /*
      if (first < last)
      {
        size = last - first;
      }
      else if (first > last)
      {
        size = (capacity() - first + last + 1);
      }
      else if (first == last)
      {
        size = 0;
      }*/
      return size;
    }
    
    public boolean isEmpty()                 //  is this ring buffer empty (size equals zero)?
    {
      if (size() == 0)
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    
    public boolean isFull()                  //  is this ring buffer full (size equals capacity)?
    {
      if (size() == capacity())
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    
    public    void enqueue(double x)         //  adds item x to the end of this ring buffer
    {
      if (isFull())
      {
        throw new RuntimeException("Enqueue called but the ring buffer is full!");
      }
      else if (last == capacity())
      {
        last = 0;
      }
      size++;
      ringBuffer[last] = x;
      last++;
    }
    
    public  double dequeue()                 //  deletes and returns the item at the front of this ring buffer
    {
      if (isEmpty())
      {
        throw new RuntimeException("Dequeue called but the ring buffer is empty!");
      }
      else if (first == capacity())
      {
        first = 0;
      }
      size = size - 1;
      first++;
      return ringBuffer[first-1];
    }
    
    public  double peek()                    //  returns the item at the front of this ring buffer
    {
      if (isEmpty())
      {
        throw new RuntimeException("Peek called but the ring buffer is empty!");
      }
      else if (first == capacity())
      {
        return ringBuffer[0];
      }
      return ringBuffer[first];
    }

    public static void main(String[] args)   //  tests this class by directly calling all instance method
    {
      int n = Integer.parseInt(args[0]);
      RingBuffer buffer = new RingBuffer(n);  
      for (int i = 1; i <= n; i++) {
          buffer.enqueue(i);
          //StdOut.println(i);
      }
      double t = buffer.dequeue();
      buffer.enqueue(t);
      StdOut.println("Size after wrap-around is " + buffer.size());  
      while (buffer.size() >= 2) {
          double x = buffer.dequeue();
          double y = buffer.dequeue();
          //StdOut.println(x + " " + y);
          buffer.enqueue(x + y);

      }
      StdOut.println(buffer.peek());
    }
}