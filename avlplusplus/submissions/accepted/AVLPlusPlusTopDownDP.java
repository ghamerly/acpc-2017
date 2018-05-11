// Liam Keliher, 2017
//
// Solution for proposed ACPC 2017 problem "AVL++" (avlplusplus)
//
// Top-down DP with memoization in a 2D table.


import java.io.*;

public class AVLPlusPlusTopDownDP{
	private static final int MODULUS = 1_000_000_007;
	private static final int MAX_M = 100;
	private static final int MAX_H = 1_000;
	private static long[][] table = new long[MAX_M + 1][MAX_H + 1];
	//--------------------------------------------------------------------
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder(Q*12);
		for (int q = 0; q < Q; q++){
			String[] tokens = br.readLine().split(" ");
			int m = Integer.parseInt(tokens[0]);
			int h = Integer.parseInt(tokens[1]);
			sb.append(recurse(m, h) + "\n");
		} // for q
		System.out.print(sb);
	} // main(String[])
	//--------------------------------------------------------------------
	private static long recurse(int m, int h){
		if (h == -1){
			return 1;
		} // if

		long currEntry = table[m][h];
		if (currEntry >= 1){
			return currEntry;
		} // if

		long newEntry;
		if (h == 0){
			newEntry = 1;
		} // if
		else{			
			long prev = recurse(m, h-1);

			// Case in which left and right subtrees both have height h-1
			newEntry = (prev*prev) % MODULUS;

			// Case in which one subtree has height h-1 and other is shorter
			int limit = Math.max(-1, h-1-m);
			for (int hOther = h-2; hOther >= limit; hOther--){
				newEntry += (2*prev*recurse(m, hOther)) % MODULUS;
			} // for hOther
			newEntry %= MODULUS;
		} // else

		table[m][h] = newEntry;
		return newEntry;
	} // recurse(int,int)
	//--------------------------------------------------------------------
} // class AVLPlusPlusTopDownDP

