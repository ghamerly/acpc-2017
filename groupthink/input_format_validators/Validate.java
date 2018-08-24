// Copyright Liam Keliher, 2017
//
// Input format validator for "Groupthink"


import java.io.*;

public class Validate{
	static final int GOOD_EXIT = 42;
	static final int BAD_EXIT = 13;
	static final int MAX_N = 120;
	static final String POSINT = "^([1-9][0-9]*)$";
	static final String THREE_NONNEGINT = "^(0|[1-9][0-9]*) (0|[1-9][0-9]*) (0|[1-9][0-9]*)$";
	//--------------------------------------------------------------------
	public static void main(String[] args){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			// Check the first line
			String line = br.readLine();
			if (line == null || line.length() == 0){
				System.exit(BAD_EXIT);
			} // if
			if (!line.matches(POSINT)) {
				System.err.println("First line not matched");
				System.exit(BAD_EXIT);
			} // if
			int n = Integer.parseInt(line);
			if (n < 1 || n > MAX_N){
				System.exit(BAD_EXIT);				
			} // if

			// Check each of the next n^2 lines
			boolean[][] used = new boolean[n][n];
			int numUsed = 0;
			for (int index = 1; index <= n*n; index++){
				line = br.readLine();
				if (line == null || line.length() == 0){
					System.exit(BAD_EXIT);
				} // if
				if (!line.matches(THREE_NONNEGINT)) {
					System.err.println("Line " + index + " (out of n^2) not matched");
					System.exit(BAD_EXIT);
				} // if
				String[] tokens = line.split(" ");
				if (tokens.length != 3){   // better safe than sorry
					System.err.println("Line " + index + " (out of n^2) does not contain 3 tokens");
					System.exit(BAD_EXIT);
				} // if
				int i = Integer.parseInt(tokens[0]);
				int j = Integer.parseInt(tokens[1]);
				int k = Integer.parseInt(tokens[2]);
				if (i < 0 || i >= n || j < 0 || j >= n || k < 0 || k >= n){
					System.err.println("One of the tokens on line " + index + " (out of n^2) is out of range");
					System.exit(BAD_EXIT);
				} // if
				if (!used[i][j]){
					numUsed++;
					used[i][j] = true;
				} // if
			} // for index

			if (numUsed != n*n){
				System.err.println("Not all possible n^2 pairs (i,j) were represented in the input");
				System.exit(BAD_EXIT);
			} // if

			// Check for any extra lines after the proper input is finished
			line = br.readLine();
			if (line != null){
				System.exit(BAD_EXIT);					
			} // if
		} // try
		catch(Exception e){
			System.exit(BAD_EXIT);
		} // catch

		System.exit(GOOD_EXIT);

	} // main(String[])
	//--------------------------------------------------------------------
} // class Validate
