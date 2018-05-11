// Liam Keliher, 2017

// Program used to generate input files for proposed ACPC 2017 problem "Groupthink",
// in particular input files corresponding to symmetric groups, i.e., groups whose
// elements are all permutations of some (smaller) set.
//
// Here SIZE refers to the size of the set whose permutations are being generated,
// not to the SIZE of the resulting group (which is SIZE factorial).


import java.util.*;

public class GenerateSymmetricGroup{
	private static final int SIZE = 5;

	private static final boolean RANDOMIZE_OUTPUT_ORDER = true;

	private static int[][] perms;
	private static int numPerms;
	private static int[][] cayleyTable;
	//--------------------------------------------------------------------
	public static void main(String[] args){
		int sizeFactorial = 1;
		for (int i = 2; i <= SIZE; i++){
			sizeFactorial *= i;
		} // for i

		perms = new int[sizeFactorial][SIZE];
		cayleyTable = new int[sizeFactorial][sizeFactorial];

		int[] perm = new int[SIZE];
		for (int i = 0; i < SIZE; i++){
			perm[i] = i;
		} // for i

		recurse(perm, 0);
		fillCayleyTable();

		for (int index = 0; index < numPerms; index++){
			System.out.println(Arrays.toString(perms[index]));
		} // for index
		System.out.println();

		String[] lines = new String[numPerms*numPerms];
		int index = 0;
		for (int i = 0; i < numPerms; i++){
			for (int j = 0; j < numPerms; j++){
				lines[index] = i + " " + j + " " + cayleyTable[i][j];
				index++;
			} // for j
		} // for i

		if (RANDOMIZE_OUTPUT_ORDER){
			Random rand = new Random();
			for (index = 0; index < (numPerms*numPerms - 1); index++){
				int other = index + rand.nextInt(numPerms*numPerms - index);
				String temp = lines[index];
				lines[index] = lines[other];
				lines[other] = temp;
			} // for index
		} // if

		System.out.println(numPerms);
		for (index = 0; index < numPerms*numPerms; index++){
			System.out.println(lines[index]);
		} // for index
	} // main(String[])
	//--------------------------------------------------------------------
	private static void recurse(int[] currPerm, int currIndex){
		if (currIndex == SIZE){
			for (int i = 0; i < SIZE; i++){
				perms[numPerms][i] = currPerm[i];
			} // for i
			numPerms++;
		} // if
		else{
			for (int i = currIndex; i < SIZE; i++){
				int temp = currPerm[currIndex];
				currPerm[currIndex] = currPerm[i];
				currPerm[i] = temp;
				recurse(currPerm, currIndex + 1);
				temp = currPerm[currIndex];
				currPerm[currIndex] = currPerm[i];
				currPerm[i] = temp;
			} // for i
		} // else
	} // recurse(int[],int)
	//--------------------------------------------------------------------
	public static void fillCayleyTable(){
		for (int i = 0; i < numPerms; i++){
			for (int j = 0; j < numPerms; j++){
				int[] p = new int[SIZE];
				for (int z = 0; z < SIZE; z++){
					p[z] = perms[j][perms[i][z]];
				} // for z

				// Find p in the list of permutations
				for (int index = 0; index < numPerms; index++){
					int count = 0;
					for (int z = 0; z < SIZE; z++){
						if (p[z] == perms[index][z]){
							count++;
						} // if
					} // for z
					if (count == SIZE){
						cayleyTable[i][j] = index;
						break;
					} // if
				} // for index
			} // for j
		} // for i
	} // fillCayleyTable()
	//--------------------------------------------------------------------
} // class GenerateSymmetricGroup

