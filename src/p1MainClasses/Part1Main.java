package p1MainClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dataGenerator.DataReader;
import interfaces.MySet;
import mySetImplementations.Set1;
import strategies.P1and2;

/**
 * 
 * @author Javier A. Ortiz García 802-16-4820
 *
 */

public class Part1Main {

	public static void main(String[] args) throws FileNotFoundException{
		//TODO
//		Read all the input data; in particular, 
//		Read an initial file named parameters.txt, that contains the two integer values for n and m, in that order and one per line. 
//		Read all the F_i_j files expected as per the values for n and m. The program should end with an exception, carrying an appropriate message, in the case that at least one of those files is missing from the input directory. 
//		From that input construct the objects corresponding to sets T0, T1, ... , Tm-1 as described.
//		Apply the selected strategy (or each one of the four strategies) to construct the final intersection set  that results (or sets that result) from the particular input used; and then show results. 

		
		//TODO: check if necessary. perhaps files are generated beforehand
		//calls file generator class
		try {
			FilesGeneratorMain.main(new String[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//reading data
		
		DataReader reader = new DataReader();
		Integer[][][] data = (Integer[][][]) reader.readDataFiles();
		
		MySet<Integer>[] t1 = new MySet[data[0].length];//set for P1
		MySet<Integer>[] t2 = new MySet[data[0].length];//set for P2
		
		
		//creates union of all data sets in Set1//TODO: make generic for all strategies
		for (int j = 0; j < data[0].length; j++) {
			t1[j] = new Set1<>();
			for (int i = 0; i < data.length; i++) {
				for (int k= 0; k < data[i][j].length; k++) {
					t1[j].add(data[i][j][k]);
				}
			}
			
		}
		
		//read input from args
		int arg = -1;
		
		if (args.length <= 1) {
			if (args.length == 1) 
				arg = Integer.parseInt(args[0]); 
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 1.");
		
		switch(arg) {//maybe use for loop to make cleaner
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
			System.out.println("Final Set by P1: " + (new P1and2<Integer>("P1").intersectSets(t1)));
			break;
		}
	}

	/**
	 * Converts list of multiple arrays into a single array for input in P3 and P4
	 * @return single list of all data
	 * @throws FileNotFoundException
	 */
	private static MySet<Integer>[] toSetArray() throws FileNotFoundException{
		
		return null;
	}
	
}
	
	

