// Liam Keliher, 2017
//
// Solution for proposed ACPC 2017 problem "AVL++" (avlplusplus)
//
// Straightforward bottom-up DP.  Builds a 2D table of the maximum
// possible size, and then performs a single lookup for each query.


import java.io.*;

public class AVLPlusPlusBottomUpDP1{
	private static final int MODULUS = 1_000_000_007;
	private static final int MAX_M = 100;
	private static final int MAX_H = 1_000;
	private static long[][] table = new long[MAX_M + 1][MAX_H + 1];
	//--------------------------------------------------------------------
	public static void main(String[] args) throws IOException{
		fillTable();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder(Q*12);
		for (int q = 0; q < Q; q++){
			String[] tokens = br.readLine().split(" ");
			int m = Integer.parseInt(tokens[0]);
			int h = Integer.parseInt(tokens[1]);
			sb.append(table[m][h] + "\n");
		} // for q
		System.out.print(sb);
	} // main(String[])
	//--------------------------------------------------------------------
	private static void fillTable(){		
		for (int m = 0; m <= MAX_M; m++){
			fillOneRow(m);
		} // for m
	} // fillTable()
	//--------------------------------------------------------------------
	private static void fillOneRow(int m){
		table[m][0] = 1;
		for (int h = 1; h <= MAX_H; h++){
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
} // class AVLPlusPlusBottomUpDP1

