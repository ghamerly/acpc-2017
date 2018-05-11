// Copyright Liam Keliher, 2017
//
// Input format validator for "AVL++" (avlplusplus)


import java.io.*;

public class Validate{
	static final int GOOD_EXIT = 42;
	static final int BAD_EXIT = 13;
	static final int MAX_Q = 100;
	static final int MAX_M = 100;
	static final int MAX_H = 1_000;
	static final String POSINT = "^([1-9][0-9]*)$";
	static final String TWO_NONNEGINT = "^(0|[1-9][0-9]*) (0|[1-9][0-9]*)$";
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
			int Q = Integer.parseInt(line);
			if (Q < 1 || Q > MAX_Q){
				System.err.println("Value of Q (first line) out of range");
				System.exit(BAD_EXIT);				
			} // if

			// Check each of the next Q lines
			for (int q = 0; q < Q; q++){
				line = br.readLine();
				if (line == null || line.length() == 0){
					System.exit(BAD_EXIT);
				} // if
				if (!line.matches(TWO_NONNEGINT)) {
					System.err.println("Line " + (q+1) + " (out of " + Q + " queries) not matched");
					System.exit(BAD_EXIT);
				} // if
				String[] tokens = line.split(" ");
				if (tokens.length != 2){   // better safe than sorry
					System.err.println("Line " + (q+1) + " (out of " + Q + " queries) does not contain 2 tokens");
					System.exit(BAD_EXIT);
				} // if
				int m = Integer.parseInt(tokens[0]);
				int h = Integer.parseInt(tokens[1]);
				if (m < 0 || m > MAX_M || h < 0 || h > MAX_H){
					System.err.println("One of the tokens on line " + (q+1) + " (out of " + Q + " queries) is out of range");
				} // if
			} // for q

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

