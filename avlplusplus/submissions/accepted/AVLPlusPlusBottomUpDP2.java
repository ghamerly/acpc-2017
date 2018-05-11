// Liam Keliher, 2017
//
// Solution for proposed ACPC 2017 problem "AVL++" (avlplusplus)
//
// Straightforward bottom-up DP.  This is a slightly refined version
// of AVLPlusPlusBottomUpDP1.java.  The refinement is that for each value
// of m, we only generate as much of the corresponding row of the DP table
// as necessary. For some input files, this will result in faster execution,
// but clearly there are input files for which there will be no gain.


import java.io.*;

public class AVLPlusPlusBottomUpDP2{
	private static final int MODULUS = 1_000_000_007;
	private static final int MAX_M = 100;

	private static int hMax[] = new int[MAX_M + 1];
	private static long[][] table;
	static{
		for (int m = 0; m <= MAX_M; m++){
			hMax[m] = -1;
		} // for m
	} // static initializer
	//--------------------------------------------------------------------
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		int[][] queries = new int[Q][2];
		for (int q = 0; q < Q; q++){
			String[] tokens = br.readLine().split(" ");
			int m = Integer.parseInt(tokens[0]);
			int h = Integer.parseInt(tokens[1]);
			queries[q][0] = m;
			queries[q][1] = h;
			if (h > hMax[m]){
				hMax[m] = h;
			} // if
		} // for q

		// Fill DP table
		table = new long[MAX_M + 1][];
		for (int m = 0; m <= MAX_M; m++){
			if (hMax[m] >= 0){
				table[m] = new long[hMax[m] + 1];
				fillOneRow(m);
			} // if
		} // for m

		StringBuilder sb = new StringBuilder(Q*12);
		for (int q = 0; q < Q; q++){
			int m = queries[q][0];
			int h = queries[q][1];
			sb.append(table[m][h] + "\n");
		} // for q
		System.out.print(sb);
	} // main(String[])
	//--------------------------------------------------------------------
	private static void fillOneRow(int m){
		int hMax = table[m].length - 1;
		table[m][0] = 1;
		for (int h = 1; h <= hMax; h++){
			long prev = table[m][h-1];
			long newEntry = (prev * prev) % MODULUS;
			int limit = Math.min(m+1, h+1);
			for (int i = 2; i <= limit; i++){
				int hOther = h - i;
				if (hOther == -1){
					newEntry += (prev << 1) % MODULUS;
				} // if
				else{
					newEntry += ((prev * table[m][h-i]) << 1) % MODULUS;
				} // else
			} // for i
			newEntry %= MODULUS;
			table[m][h] = newEntry;
		} // for h
	} // fillOneRow(int)
	//--------------------------------------------------------------------
} // class AVLPlusPlusBottomUpDP2

