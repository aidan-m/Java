/* name: Aidan McLaughlin
 * netID: amclaughlin
 * 
 * 
*/
public class GuitarString {
  
  private double SAMPLING_RATE = 44100.0;
  private double CONCERT_A = 440.0;
  private int n = 0;
  private RingBuffer newString;
  
    public         GuitarString(double frequency)  //  creates a guitar string of the specified frequency, using a sampling rate of 44,100
    {
      n = (int) Math.ceil(SAMPLING_RATE / frequency);
      newString = new RingBuffer(n);
      
      // enqueue n 0's
      for (int i = 0; i < n; i++)
      {
        newString.enqueue(0.0);
      }
    }
    public         GuitarString(double[] init)     //  creates a guitar string whose length and initial values are given by the specified array
    {
      n = init.length;
      newString = new RingBuffer(n);
      
      for (int i = 0; i < n; i++)
      {
        newString.enqueue(init[i]);
      }
    }
    public     int length()                        //  returns the number of samples in the ring buffer
    {
      return newString.size();
    }
    public    void pluck()                         //  plucks this guitar string (by replacing the ring buffer with white noise)
    {
      for (int i = 0; i < n; i++)
      {
        newString.dequeue();
        newString.enqueue(StdRandom.uniform(-0.5, 0.5));
      }
    }
    public    void tic()                           //  advances the Karplus-Strong simulation one time step
    {
      
      newString.enqueue((.996 * 0.5 * (newString.dequeue() + newString.peek())));
    }
    public  double sample()                        //  returns the current sample
    {
      return newString.peek();
    }
    
    public static void main(String[] args)         //  tests this class by directly calling both constructors and all instance methods
    {
    double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };  
      GuitarString testString = new GuitarString(samples);
      int m = 25; // 25 tics
      for (int i = 0; i < m; i++) {
          double sample = testString.sample();
          StdOut.printf("%6d %8.4f\n", i, sample);
          testString.tic();
      }
    }
}