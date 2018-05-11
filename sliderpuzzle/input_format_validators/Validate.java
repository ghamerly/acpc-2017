import java.util.ArrayList;
import java.util.Scanner;

/**
 * Validator for SliderPuzzle problem.
 * 
 * @author scottbateman
 *
 */
public class Validate {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> checkedIndices = new ArrayList<Integer>();
		
		int num=-1;
		int count = 0;
        int number = -1;

        try {
            number = Integer.parseInt(scan.nextLine());
        } catch (Exception e)

        {
            System.err.println("Problem reading number of cases from first line.");
            System.exit(-1);
        }

        if (number <=0 || number >= 101)
        {
            System.err.println("Number of cases out of bounds, must be between 1 and 100.");
            System.exit(-1);
        }

		//read lines
		for (int i = 0; i < number; i++)
		{

			while (num != 0)
			{
				num = scan.nextInt();

                if (num >= 10 || num < 0)
                {
                    System.err.println("Puzzle digit out of bounds, must be between 0 and 9: "+num);
                    System.exit(-1);
                }
			}
		
			count++;


			//read new line
			scan.nextLine();

            if ( count >= 101)
            {
                System.err.println("Too many cases.");
                System.exit(-1);
            }
            
            num = -1;

		}
        if (scan.hasNext())
        {
            System.err.println("Exata input in the file.");
            System.exit(-1);
        }
        System.exit(42);
	}
}
