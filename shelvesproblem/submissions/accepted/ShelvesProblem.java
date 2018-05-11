import java.util.*;

class ShelvesProblem
{
   int shelveSize, nbBoxes;
   int[] size, a, b;
   int[][][][][] opt;
   
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
   
   void calculate()
   {
      int newOpt;
      opt = new int[nbBoxes+1][shelveSize+1][shelveSize+1][shelveSize+1][shelveSize+1];
      for (int j=0; j<shelveSize+1; j++)
         for (int k=0; k<shelveSize+1; k++)
            for (int l=0; l<shelveSize+1; l++)
               for (int m=0; m<shelveSize+1; m++)
                  opt[0][j][k][l][m] = 0;
      
      for (int i=1; i<nbBoxes+1; i++)
         for (int j=0; j<shelveSize+1; j++)
            for (int k=0; k<shelveSize+1; k++)
               for (int l=0; l<shelveSize+1; l++)
                  for (int m=0; m<shelveSize+1; m++)
                  {
                     opt[i][j][k][l][m] = opt[i-1][j][k][l][m];
                     if (j-size[i]>=0)
                     {
                        newOpt = (a[i]+1*b[i]) + opt[i-1][j-size[i]][k][l][m];
                        if (newOpt > opt[i][j][k][l][m])
                           opt[i][j][k][l][m] = newOpt;
                     }
                     if (k-size[i]>=0)
                     {
                        newOpt = (a[i]+2*b[i]) + opt[i-1][j][k-size[i]][l][m];
                        if (newOpt > opt[i][j][k][l][m])
                           opt[i][j][k][l][m] = newOpt;
                     }
                     if (l-size[i]>=0)
                     {
                        newOpt = (a[i]+3*b[i]) + opt[i-1][j][k][l-size[i]][m];
                        if (newOpt > opt[i][j][k][l][m])
                           opt[i][j][k][l][m] = newOpt;
                     }
                     if (m-size[i]>=0)
                     {
                        newOpt = (a[i]+4*b[i]) + opt[i-1][j][k][l][m-size[i]];
                        if (newOpt > opt[i][j][k][l][m])
                           opt[i][j][k][l][m] = newOpt;
                     }
                }
      System.out.println(opt[nbBoxes][shelveSize][shelveSize][shelveSize][shelveSize]);
   } 
   
   void traceBack()
   {
      int i=nbBoxes, j=shelveSize, k=shelveSize, l=shelveSize, m=shelveSize;
      while (i>0)
      {
         if (opt[i][j][k][l][m] == opt[i-1][j][k][l][m])
         {
            System.out.println("Do not take item " + size[i] +
                           "/" + a[i] + "/" + b[i]);
         }
         else if ((j-size[i]>=0) &&
                     (opt[i][j][k][l][m] == (a[i]+1*b[i]) + opt[i-1][j-size[i]][k][l][m]))
         {                     
            System.out.println("Put item " + size[i] +
                           "/" + a[i] + "/" + b[i] + " in row 1");
            j -= size[i];
         }
         else if ((k-size[i]>=0) &&
                     (opt[i][j][k][l][m] == (a[i]+2*b[i]) + opt[i-1][j][k-size[i]][l][m]))
         {                     
            System.out.println("Put item " + size[i] +
                           "/" + a[i] + "/" + b[i] + " in row 2");
            k -= size[i];
         }
         else if ((l-size[i]>=0) &&
                     (opt[i][j][k][l][m] == (a[i]+3*b[i]) + opt[i-1][j][k][l-size[i]][m]))
         {                     
            System.out.println("Put item " + size[i] +
                           "/" + a[i] + "/" + b[i] + " in row 3");
            l -= size[i];
         }
         else if ((m-size[i]>=0) &&
                     (opt[i][j][k][l][m] == (a[i]+4*b[i]) + opt[i-1][j][k][l][m-size[i]]))
         {                     
            System.out.println("Put item " + size[i] +
                           "/" + a[i] + "/" + b[i] + " in row 4");
            m -= size[i];
         }
         else
            System.out.println("ERROR");
         i--;
      }
   }
 
   void run()
   {
      readData();
      calculate();
      //traceBack();
   }
   
   public static void main(String[] args)
   {
      ShelvesProblem pgm = new ShelvesProblem();
      pgm.run();
   }
}
