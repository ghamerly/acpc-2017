// Liam Keliher, 2017
//
// Program used to generate random input files for proposed ACPC 2017 problem "Groupthink"


import java.util.*;

public class GenerateRandom{
	private static final int MAGMA = 0;
	private static final int SEMIGROUP = 1;
	private static final int MONOID = 2;
	private static final int GROUP = 3;

	private static final int SIZE = 120;
	private static final int TARGET = MAGMA;

	private static final boolean RANDOMIZE_OUTPUT_ORDER = true;

	private static int[][] cayleyTable = new int[SIZE][SIZE];
	private static Random rand = new Random();
	//--------------------------------------------------------------------
	public static void main(String[] args){
		int count = 0;
		int result = -1;
		while (result != TARGET){
			fillTable();
			result = 0;
			if (testAssociativity()){
				result++;
				int identity = findIdentity();
				if (identity >= 0){
					result++;
					if (testInverses(identity)){
						result++;
					} // if
				} // if
			} // if
			count++;
			if (count % 1_000_000 == 0){
				System.out.println(count);
			} // if
		} // while


		String[] lines = new String[SIZE*SIZE];
		int index = 0;
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				lines[index] = i + " " + j + " " + cayleyTable[i][j];
				index++;
			} // for j
		} // for i

		if (RANDOMIZE_OUTPUT_ORDER){
			for (index = 0; index < (SIZE*SIZE - 1); index++){
				int other = index + rand.nextInt(SIZE*SIZE - index);
				String temp = lines[index];
				lines[index] = lines[other];
				lines[other] = temp;
			} // for index
		} // if

		System.out.println(SIZE);
		for (index = 0; index < SIZE*SIZE; index++){
			System.out.println(lines[index]);
		} // for index
	} // main(String[])
	//--------------------------------------------------------------------
	public static void fillTable(){
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				cayleyTable[i][j] = rand.nextInt(SIZE);
			} // for j
		} // for i
	} // fillTable()
	//--------------------------------------------------------------------
	private static boolean testAssociativity(){
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				for (int k = 0; k < SIZE; k++){
					if (cayleyTable[cayleyTable[i][j]][k] != cayleyTable[i][cayleyTable[j][k]]){
						return false;
					} // if
				} // for k
			} // for j
		} // for i
		return true;
	} // testAssociativity()
	//--------------------------------------------------------------------
	private static int findIdentity(){
		for (int i = 0; i < SIZE; i++){
			int count = 0;
			for (int j = 0; j < SIZE; j++){
				if (cayleyTable[i][j] == j && cayleyTable[j][i] == j){
					count++;
				} // if
			} // for j
			if (count == SIZE){
				return i;
			} // if
		} // for i
		return -1;
	} // findIdentity()
	//--------------------------------------------------------------------
	private static boolean testInverses(int id){
		for (int i = 0; i < SIZE; i++){
			boolean foundInverse = false;
			for (int j = 0; j < SIZE; j++){
				if (cayleyTable[i][j] == id){
					foundInverse = true;
					break;
				} // if
			} // for j
			if (!foundInverse){
				return false;
			} // if
		} // for i
		return true;
	} // testInverses()
	//--------------------------------------------------------------------
} // class GenerateRandom

