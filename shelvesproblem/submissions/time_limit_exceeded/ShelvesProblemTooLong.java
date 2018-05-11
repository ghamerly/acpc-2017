import java.util.*;

class ShelvesProblemTooLong
{
   int shelveSize, nbBoxes;
   int[] size, a, b;
   int[] curLengths;
   int bestAmt = 0, curAmt = 0;
   
   void readData()
   {
		Scanner scn = new Scanner(System.in);
      shelveSize = scn.nextInt();
      nbBoxes = scn.nextInt();
      size = new int[nbBoxes+1];
      a = new int[nbBoxes+1];
      b = new int[nbBoxes+1];
      for (int i=1; i<nbBoxes+1; i++)
      {
         size[i] = scn.nextInt();
         a[i] = scn.nextInt();
         b[i] = scn.nextInt();
      }
   }
   
   void calculate(int box)
   {
      // if no more boxes, end recursion
      if (box > nbBoxes)
      {
         if (curAmt > bestAmt)
         {
            bestAmt = curAmt;
         }
         return;
      }
      
      // try box on shelve 1
      if (size[box] <= curLengths[0])
      {
         curAmt += a[box] + 1*b[box];
         curLengths[0] -= size[box];
         calculate(box+1);
         curAmt -= a[box] + 1*b[box];
         curLengths[0] += size[box];
      }
      
      // try box on shelve 2
      if (size[box] <= curLengths[1])
      {
         curAmt += a[box] + 2*b[box];
         curLengths[1] -= size[box];
         calculate(box+1);
         curAmt -= a[box] + 2*b[box];
         curLengths[1] += size[box];
      }
      
      // try box on shelve 3
      if (size[box] <= curLengths[2])
      {
         curAmt += a[box] + 3*b[box];
         curLengths[2] -= size[box];
         calculate(box+1);
         curAmt -= a[box] + 3*b[box];
         curLengths[2] += size[box];
      }
      
      // try box on shelve 4
      if (size[box] <= curLengths[3])
      {
         curAmt += a[box] + 4*b[box];
         curLengths[3] -= size[box];
         calculate(box+1);
         curAmt -= a[box] + 4*b[box];
         curLengths[3] += size[box];
      }
      
      // try not using the box at all
      calculate(box+1);
   } 
    
   void run()
   {
      readData();
      curLengths = new int[4];
      for (int i=0; i<4; i++)
      {
         curLengths[i] = shelveSize;
      }
      calculate(1);
      System.out.println(bestAmt);
   }
   
   public static void main(String[] args)
   {
      ShelvesProblemTooLong pgm = new ShelvesProblemTooLong();
      pgm.run();
   }
}
