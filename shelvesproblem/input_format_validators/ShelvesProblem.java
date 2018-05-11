import java.util.Scanner;

public class ShelvesProblem {

	private static int getInt(String line){
		return Integer.parseInt(line.trim());
	}
	public static void main(String[] args) {
	      Scanner scn = new Scanner(System.in);
	      int shelveSize = getInt(scn.nextLine());

	      int nbBoxes = getInt(scn.nextLine());

	      if(shelveSize>=20 ||shelveSize<=0 || !(nbBoxes<100 && nbBoxes>0 )){
		  System.err.println("The shelves should be less than 20, and the boxes should be in (0, 100) ");
		  System.exit(1);
	      }
	      int count=0;
	      while(scn.hasNext()){

		  String line = scn.nextLine().trim();
		  if(line.equals("")) continue;
		  String[] box = line.split(" ");
		  count++;
		  if(box.length!=3){
			  System.err.println("Each box should have 3 ints, "+line);
			  System.exit(1);
		  }
		  int size = getInt(box[0]);
		  if(!(size>0 && size<20)){
			  System.err.println("The size should be in (0, 20), but sees "+ line);
			  System.exit(1);
		  }
		  int a = getInt(box[1]);
		  int b = getInt(box[2]);
		  for(int i=1; i<=4; i++){
			  if(a+b*i<0){
				  System.err.println("Evaluation a+b*i should be >0. but sees "+(a+b*i)+" for "+line);
				  System.exit(1);
			  }
		  }
	      }
	      if(count!=nbBoxes){
		  System.err.println("Find "+count+" boxes, but expect "+nbBoxes+" boxes");
		  System.exit(1);
	      }
	      System.exit(42);
	}

}
