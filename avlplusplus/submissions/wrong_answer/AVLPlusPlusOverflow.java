// Liam Keliher, 2017
//
// WA (wrong answer) submission for proposed ACPC 2017 problem "AVL++" (avlplusplus)
//
// Identical to AVLPlusPlusBottomUpDP1.java except that int is used instead of long,
// sometimes leading to integer overflow.


import java.io.*;

public class AVLPlusPlusOverflow{
	private static final int MODULUS = 1_000_000_007;
	private static final int MAX_M = 100;
	private static final int MAX_H = 1_000;
	private static int[][] table = new int[MAX_M + 1][MAX_H + 1];
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
			int prev = table[m][h-1];
			table[m][h] = (prev*prev) % MODULUS;
			int limit = Math.min(m+1, h+1);
			for (int i = 2; i <= limit; i++){
				int hOther = h - i;
				if (hOther == -1){
					table[m][h] += (prev << 1) % MODULUS;
				} // if
				else{
					table[m][h] += ((prev*table[m][h-i]) << 1) % MODULUS;
				} // else
			} // for i
			table[m][h] %= MODULUS;
		} // for h
	} // fillOneRow(int)
	//--------------------------------------------------------------------
} // class AVLPlusPlusOverflow
