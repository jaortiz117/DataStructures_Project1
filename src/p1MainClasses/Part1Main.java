package p1MainClasses;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dataGenerator.DataReader;
import interfaces.IntersectionFinder;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import strategies.P1and2;
import strategies.P3;
import strategies.P4;


/**
 * 
 * @author Javier A. Ortiz García 802-16-4820
 *
 */

public class Part1Main {
	
	private static final int P1 = 1;

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
		
		//read input from args
		int arg = -1;
		
		if (args.length <= 1) {
			if (args.length == 1) 
				arg = Integer.parseInt(args[0]); 
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 1.");
		
		MySet<Integer>[] dataSet = toSetArray(arg);
		
		ArrayList<IntersectionFinder<Integer>> strategies = new ArrayList<IntersectionFinder<Integer>>();
		strategies.add(new P1and2<Integer>("P1"));
		strategies.add(new P1and2<Integer>("P2"));
		strategies.add(new P3<Integer>("P3"));
		strategies.add(new P4<Integer>("P4"));
		
		//print output
		if(arg != -1) {
			System.out.println("Final Set by "+ strategies.get(arg-1).getName() + ": "
					+ strategies.get(arg-1).intersectSets(dataSet));
		}
		else {
			int i = 1;
			for(IntersectionFinder<Integer> p : strategies) {
				dataSet = toSetArray(i);
				System.out.println("Final Set by "+ p.getName() + ": "
						+ p.intersectSets(dataSet));
				i++;
			}	
		}
	}

	/**
	 * Converts list of multiple arrays into a single array for input in P3 and P4
	 * @return single list of all data
	 * @throws FileNotFoundException
	 */
	private static MySet<Integer>[] toSetArray(int arg) throws FileNotFoundException{
		//reading data
		DataReader reader = new DataReader();
		Integer[][][] data = (Integer[][][]) reader.readDataFiles();

		MySet<Integer>[] t = new MySet[data[0].length];

		//creates union of all data sets in Set t
		for (int j = 0; j < data[0].length; j++) {
			if(arg == P1)
				t[j] = new Set1<>();
			else
				t[j] = new Set2<>();
				
			for (int i = 0; i < data.length; i++) {
				for (int k= 0; k < data[i][j].length; k++) {
					t[j].add(data[i][j][k]);
				}
			}
		}
		
		return t;
	}
	
}
	
	

