package p1MainClasses;
import java.io.FileNotFoundException;

import dataGenerator.DataReader;
import interfaces.MySet;

/**
 * 
 * @author Javier A. Ortiz García 802-16-4820
 *
 */

public class Part1Main {

	public static void main(String[] args){
		//TODO
//		Read all the input data; in particular, 
//		Read an initial file named parameters.txt, that contains the two integer values for n and m, in that order and one per line. 
//		Read all the F_i_j files expected as per the values for n and m. The program should end with an exception, carrying an appropriate message, in the case that at least one of those files is missing from the input directory. 
//		From that input construct the objects corresponding to sets T0, T1, ... , Tm-1 as described.
//		Apply the selected strategy (or each one of the four strategies) to construct the final intersection set  that results (or sets that result) from the particular input used; and then show results. 

		
		//TODO: check if necessary. perhaps files are generated beforehand
		try {
			FilesGeneratorMain.main(new String[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//read input from args
		int arg = -1;
		
		if (args.length <= 1) {
			if (args.length == 1) 
				arg = Integer.parseInt(args[0]); 
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 1.");
		
		switch(arg) {
		case 1:
			//print: Final Set by P1
			break;
			
		case 2:
			//print: Final Set by P2
			break;
			
		case 3:
			//print: Final Set by P3
			break;
			
		case 4:
			//print: Final Set by P4
			break;

		default:
			//print: Final Set by all P's
			break;
		}
	}

	
	private static MySet<Integer>[] toSetArray() throws FileNotFoundException{
		
		DataReader reader = new DataReader();
		
		Integer[][][] data = (Integer[][][]) reader.readDataFiles();
		
		//TODO: change from Integer array into MySet object
		return null;
	}
	
}
	
	

