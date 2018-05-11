import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.lang.*;

class Validator {
	Pattern word  = Pattern.compile("[01]{1,13}");
	Pattern text = Pattern.compile("[01]{1,3001}");
	Scanner scan = new Scanner(System.in);
	public static void main( String [] args ) throws Exception {
		new Validator().go();
		System.exit(42);
	}
	void go() throws Exception {
		int i,j,k,ts,n;
		try {
			ts = scan.nextInt();
		} catch ( Exception e ) {
			throw new RuntimeException(e.getMessage());
		}
		for ( int t = 0; t < ts; ++t ) {
			try {
				n = scan.nextInt();
			} catch ( Exception e ) {
				throw new RuntimeException(e.getMessage());
			}
			int total = 0;
			for ( i = 0; i < n; ++i ) {
				String w = scan.next();
				Matcher m = word.matcher(w);
				if ( !m.find() || !m.group(0).equals(w) )
					throw new RuntimeException("Problem with the forbidden word");
				total += w.length();
			}
			String T = scan.next();
			Matcher mm = text.matcher(T);
			if ( !mm.find() || !mm.group(0).equals(T) )
				throw new RuntimeException("Problem with the text");
		}
	}
}
