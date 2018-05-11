// Liam Keliher, 2017

// Program used to generate input files for proposed ACPC 2017 problem "Groupthink",
// in particular input files corresponding to groups (Zp*, x), where p is prime
// (and therefore Zp* = {1, 2, 3, ..., p-1}).


import java.util.*;

public class GenerateZpStar{
	private static final int PRIME = 113;
	private static final int SIZE = PRIME - 1;

	private static final boolean RANDOMIZE_LABELS = true;
	private static final boolean RANDOMIZE_OUTPUT_ORDER = true;

	private static int[][] cayleyTable;
	private static Random rand = new Random();
	//--------------------------------------------------------------------
	public static void main(String[] args){
		cayleyTable = new int[SIZE][SIZE];
		fillCayleyTable();

		String[] lines = new String[SIZE*SIZE];
		if (RANDOMIZE_LABELS){
			int[] perm = randomPermutation(SIZE);
			int index = 0;
			for (int i = 0; i < SIZE; i++){
				for (int j = 0; j < SIZE; j++){
					lines[index] = perm[i] + " " + perm[j] + " " + perm[cayleyTable[i][j]];
					index++;
				} // for j
			} // for i
		} // if
		else{
			int index = 0;
			for (int i = 0; i < SIZE; i++){
				for (int j = 0; j < SIZE; j++){
					lines[index] = i + " " + j + " " + cayleyTable[i][j];
					index++;
				} // for j
			} // for i
		} // else

		if (RANDOMIZE_OUTPUT_ORDER){
			for (int index = 0; index < (SIZE*SIZE - 1); index++){
				int other = index + rand.nextInt(SIZE*SIZE - index);
				String temp = lines[index];
				lines[index] = lines[other];
				lines[other] = temp;
			} // for index
		} // if

		System.out.println(SIZE);
		for (int index = 0; index < SIZE*SIZE; index++){
			System.out.println(lines[index]);
		} // for index
	} // main(String[])
	//--------------------------------------------------------------------
	public static void fillCayleyTable(){
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				cayleyTable[i][j] = ((i+1)*(j+1) % PRIME) - 1;
			} // for j
		} // for i
	} // fillCayleyTable()
	//--------------------------------------------------------------------
	private static int[] randomPermutation(int size){
		int[] perm = new int[size];
		for (int i = 0; i < size; i++){
			perm[i] = i;
		} // for i

		for (int i = 0; i < (size-1); i++){
			int j = i + rand.nextInt(size - i);
			int temp = perm[i];
			perm[i] = perm[j];
			perm[j] = temp;
		} // for i

		return perm;
	} // randomPermutation()
	//--------------------------------------------------------------------
} // class GenerateZpStar

