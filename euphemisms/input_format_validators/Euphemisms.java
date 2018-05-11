import java.util.Scanner;


public class Euphemisms {

	private static int getInt(String line){
		return Integer.parseInt(line.trim());
	}

	private static boolean checkWord(String binaryWord){
		for(int i=0; i<binaryWord.length(); i++){
			if(binaryWord.charAt(i)!='0' && binaryWord.charAt(i)!='1'){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		 Scanner scn = new Scanner(System.in);
		 int ts = getInt(scn.nextLine());
		 if(ts>100|| ts < 1){
			 System.err.println("The number of Tests should be in [1, 100]");
			 System.exit(1);
		 }
		 int count=0;
		 while(scn.hasNext()){
			 String cases = scn.nextLine();
			 if(cases.trim().equals("")) continue;
			 count++;

			 int words = getInt(cases);
			 if(words>30 || words<1){
				 System.err.println("The number of words should be in [1, 30]");
				 System.exit(1);
			 }
			 for(int i=0; i< words; i++){
				 String word = scn.nextLine().trim();
				 int iWord = word.length();
				 if(iWord>13 || iWord<1){
					 System.err.println("Each word should be in [1, 13] for "+word);
					 System.exit(1);
				 }
				 if(!checkWord(word)){
					 System.err.println("Digit in "+word+" Should be either 0 or 1");
					 System.exit(1);
				 }
			 }
			 String T = scn.nextLine().trim();
			 if(T.length()>3000 || T.length()<1){
				 System.err.println("T's value should be in [1, 3000]");
				 System.exit(1);
			 }
			 if(!checkWord(T)){
				 System.err.println("Digit in "+T+" Should be either 0 or 1");
				 System.exit(1);
			 }
		 }

		 if(count!=ts){
			 System.err.println("Find "+count+" tests, but except "+ts);
			 System.exit(1);
		 }
		 System.exit(42);
	}

}
