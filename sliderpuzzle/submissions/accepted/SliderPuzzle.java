import java.util.ArrayList;
import java.util.Scanner;

/**
 * SliderPuzzle - solves a puzzle where each notch on a slider
 * dictates how many spaces to the left or right, a marker
 * on a slider can be moved. The goal is to move from the 
 * first notch on the slider (leftmost) to the right notch
 * on the slider (rightmost). 
 * 
 * @author scottbateman
 *
 */
public class SliderPuzzle {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Integer> checkedIndices = new ArrayList<Integer>();
		
		int num=-1;
		int count = 0;

        int number = Integer.parseInt(scan.nextLine());

		//read lines
		for (int i = 0; i < number; i++)
		{

			while (num != 0)
			{
				num = scan.nextInt();
				numbers.add(num);
			}
		
			count++;

			if (solvable(0,numbers, checkedIndices))
				System.out.println("Puzzle "+count+" is solvable.");
			else
				System.out.println("Puzzle "+count+" is not solvable.");

			//read new line
			scan.nextLine();

			//reset number reader, numbers, and checked list
			num = -1;
			numbers.clear();
			checkedIndices.clear();
		}
	}
	
	/**
	 * From the given start position recursively determine
	 * whether or not the board is solvable within a given
	 * set of moves.
	 * 
	 * @param start the starting position for the marker
	 * @param numbers the list of numbers on the board
	 * @param checkedIndices a list of indices already checked
	 * @return boolean - whether the board is solvable 
	 */
	public static boolean solvable(int start, ArrayList<Integer> numbers, ArrayList<Integer> checkedIndices){
		
		//if out of bounds that this is not a solveable path
		if (start < 0 || start >= numbers.size())
			return false;
		
		//if this index has already been checked don't keep going
		if (checkedIndices.contains(start))
			return false;

		//add index to checked list
		checkedIndices.add(start);

		//get start
		int num = numbers.get(start);
		
		//if on the last spot
		if (num == 0)
			return true;
		
		//recurse - moving both right (first since it is the direction of the last spot) and left
		else
			return (solvable(start + num, numbers, checkedIndices) || solvable(start - num, numbers, checkedIndices));
				
	}
}
