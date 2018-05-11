// Liam Keliher, 2017
//
// Solution for proposed ACPC 2017 problem "Groupthink"


import java.io.*;

public class Groupthink{
	private static int n;
	private static int[][] table;
	//--------------------------------------------------------------------
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		table = new int[n][n];
		int nSquared = n*n;
		for (int index = 0; index < nSquared; index++){
			String[] tokens = br.readLine().split(" ");
			int i = Integer.parseInt(tokens[0]);
			int j = Integer.parseInt(tokens[1]);
			int k = Integer.parseInt(tokens[2]);
			table[i][j] = k;
		} // for index

		if (!testAssociativity()){
			System.out.println("magma");
			return;
		} // if

		int identity = findIdentity();
		if (identity == -1){
			System.out.println("semigroup");
			return;
		} // if

		if (!testInverses(identity)){			
			System.out.println("monoid");
			return;
		} // if

		System.out.println("group");
	} // main(String[])
	//--------------------------------------------------------------------
	private static boolean testAssociativity(){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				for (int k = 0; k < n; k++){
					if (table[table[i][j]][k] != table[i][table[j][k]]){
						return false;
					} // if
				} // for k
			} // for j
		} // for i
		return true;
	} // testAssociativity()
	//--------------------------------------------------------------------
	private static int findIdentity(){
		for (int i = 0; i < n; i++){
			int count = 0;
			for (int j = 0; j < n; j++){
				if (table[i][j] == j && table[j][i] == j){
					count++;
				} // if
			} // for j
			if (count == n){
				return i;
			} // if
		} // for i
		return -1;
	} // findIdentity()
	//--------------------------------------------------------------------
	// Check that each element has a 2-sided inverse, i.e., for each x
	// there exists z such that xz = zx = id. Actually, checking two-sidedness
	// is overkill, since it can be shown that in a *finite* magma, if xz = id,
	// then zx = id (or vice versa).
	private static boolean testInverses(int id){
		for (int i = 0; i < n; i++){
			boolean foundInverse = false;
			for (int j = 0; j < n; j++){
				if (table[i][j] == id && table[j][i] == id){
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
} // class Groupthink

