/* name: Aidan McLaughlin
 * netID: amclaughlin
 * 
 * 
*/
public class GuitarHero {
      public static void main(String[] args) {

          // create a double containing the frequency of concert A 
          // will later be used to create all 37 frequencies
          double CONCERT_A = 440.0;
          
          // initialize an array of 37 guitar strings
          GuitarString[] allStrings = new GuitarString[37];
          
          // initialize string array containing all the characters used in our keyboard
          String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
          
          //initialize key used to find the index of the key pressed in the above array
          char key; 
          
          // assign each string in allStrings the appropriate frequency
          for (int i = 0; i < allStrings.length; i++)
          {
            double noteX = CONCERT_A * Math.pow(2, ((i - 24.0)/12.0));
            allStrings[i] = new GuitarString(noteX);
          }
         
          // loop until the user terminates the program
          while (true) {

              // check if the user has typed a key; if so, process it
              if (StdDraw.hasNextKeyTyped()) 
              {
                key = StdDraw.nextKeyTyped();
                
                // if the user pressed a key which has an index in our keyboard array,
                // pluck the associated guitar string
                if (keyboard.indexOf(key) != -1) 
                {
                  allStrings[keyboard.indexOf(key)].pluck();
                }
              }
              
              double sample = 0.0;
              // compute the superposition of samples
              for (int i = 0; i < allStrings.length; i++)
              {
                sample += allStrings[i].sample();
              }
              // play the sample on standard audio
              StdAudio.play(sample);
  
              // advance the simulation of each guitar string by one step 
              for (int i = 0; i < allStrings.length; i++)
              {
                allStrings[i].tic();
              }
          }
       }
  }