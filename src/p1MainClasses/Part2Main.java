package p1MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dataGenerator.DataGenerator;
import interfaces.IntersectionFinder;
import interfaces.MySet;
import mySetImplementations.AbstractMySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import strategies.P1and2;
import strategies.P3;
import strategies.P4;

/**
 * 
 * @author Javier A. Ortiz 802-16-4820
 *
 */

public class Part2Main {
	
	public static Integer[][][] dataset;
	
	/**
	 * Collection of strategies to be used when testing
	 * @author MoCkY1998
	 *
	 * @param <T>
	 */
	private static class StrategiesTimeCollection {
		
		private IntersectionFinder<Integer> strategy;
		private ArrayList<SimpleEntry<Integer, Float>> experiments;
		private int sum;
		
		public StrategiesTimeCollection(IntersectionFinder<Integer> strategy) {
			this.strategy = strategy;
			this.sum = 0;
			this.experiments = new ArrayList<SimpleEntry<Integer, Float>>();
		}
		
		/**
		 * Resets current sum to 0
		 */
		public void resetSum() {
			this.sum = 0;
		}
		
		/**
		 * Run the strategy using the data in p
		 * @param p data set
		 */
		public void runTrial(Object[][][] p){ 
			MySet<Integer>[] t = new MySet[p[0].length];
			
			for (int j=0; j<m; j++) {
			    t[j] = new Set2<>(); //empty set of the particular type that the strategy uses as per the description given...
			    
			    if(this.strategy.getName().equals("P1")) {
			    	t[j] = new Set1<>();
			    }
			    
			    for (int i=0; i<n; i++) {
			        for (int k = 0; k < dataset[i][j].length; k++)
			            t[j].add((Integer) dataset[i][j][k]);  // add to set t[j] the element dataset[i][j][k]
			    }
			}

			this.strategy.intersectSets(t);
		}
		
		/**
		 * Accumulate the estimated time (add it) to sum of times that the current strategy has 
		 * so far accumulated  on the previous trials for data sets of the current size.
		 * @param increase 
		 */
		public void incSum(int increase) {
			sum += increase;	
		}
		
		/**
		 * get current sum value
		 * @return sum
		 */
		public int getSum() {
			return sum;
		}
		
		public String toString() {
			return ""+this.strategy.getName()+", sum: "+ this.getSum();
		}

		public void add(SimpleEntry<Integer, Float> sizeTime) {
			experiments.add(sizeTime);
		}

		public String getStrategyName() {
			return strategy.getName();
		}

		public int size() {
			// TODO Auto-generated method stub
			return experiments.size();
		}
		
		public SimpleEntry<Integer, Float> get(int index){
			return experiments.get(index);
		}
	}
	
	private static int n;
	private static int m;
	private static int iSize;
	private static int fSize;
	private static int iStep;
	private static int rep;
	private static ArrayList<StrategiesTimeCollection> resultsPerStrategy = new ArrayList<StrategiesTimeCollection>();


	/**
	 * Main method for part 2
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		if (args.length <= 6) {
			n = 10; 
			m = 50; 
			iSize = 1000; 
			fSize = 50000;
			iStep = 1000;
			rep = 200;
			if (args.length >= 1) 
				n = Integer.parseInt(args[0]); 
			if (args.length >= 2) 
				m = Integer.parseInt(args[1]); 
			if (args.length >= 3) 
				iSize = Integer.parseInt(args[2]);
			if (args.length >= 4) 
				fSize = Integer.parseInt(args[3]);
			if (args.length >= 5) 
				iStep = Integer.parseInt(args[4]);
			if (args.length == 6) 
				rep = Integer.parseInt(args[5]);
		} 
		else 
			System.out.println("Invalid number of parameters. Must be <= 6.");
		
		//init resultsPerStrategy ArrayList
		resultsPerStrategy.add(new StrategiesTimeCollection(new P1and2<Integer>("P1")));
		resultsPerStrategy.add(new StrategiesTimeCollection(new P1and2<Integer>("P2")));
		resultsPerStrategy.add(new StrategiesTimeCollection(new P3<Integer>("P3")));
		resultsPerStrategy.add(new StrategiesTimeCollection(new P4<Integer>("P4")));


		for (int size=iSize; size<=fSize; size+=iStep) { 
			// For each strategy, reset the corresponding internal variable that will be used to store the sum 
			// of times that the particular strategy exhibits for the current size size.
			for (StrategiesTimeCollection strategy : resultsPerStrategy) 
				strategy.resetSum();    // set to 0 an internal instance variable used to accumulate sum of times

			// Run all trials for the current size. 
			for (int r = 0; r<rep; r++) {
				// The following will be the common dataset to be used in the current trial by all the strategies being
				// tested. Here, that data set is generated by a method that gets as input (parameter values): n, m, size. 
				// Where n and m are the number of companies and number of crime events, respective. The generated
				// must satisfy: size  =  i=1nj=1m(dataset[i][j].length)
				dataset = (Integer[][][]) generateData(n, m, size);  // data set for this trial of given size

				// Apply each one of the strategies being tested using the previous dataset (of size size) as input; 
				// and, for each, estimate the time that the execution takes.
				for (StrategiesTimeCollection strategy : resultsPerStrategy) {	
					long startTime = System.nanoTime();  // Measure system’s clock time before.
					strategy.runTrial(dataset);          // Run the strategy using the data in dataset.        
					long endTime = System.nanoTime();    // Measure system’s clock time after.

					int estimatedTime = (int) (endTime-startTime);   // The estimated time.
					// Accumulate the estimated time (add it) to sum of times that the current strategy has 
					// so far accumulated  on the previous trials for datasets of the current size. 
					strategy.incSum(estimatedTime);    				
				}
			}
			// For each strategy, compute the average time for the current size.	
			for (StrategiesTimeCollection strategy : resultsPerStrategy) {
				strategy.add( new AbstractMap.SimpleEntry<Integer, Float>
				(size, (strategy.getSum()/((float) rep)))
						); 
			}
		}
		
		saveResults();
		System.out.println("Success!!!");
	}
	
	/**
	 * Create data for use when experimenting time it takes to run tests
	 * @param n
	 * @param m
	 * @param size
	 * @return array of all data
	 */
	private static Object[][][] generateData(int n, int m, int size) {
		DataGenerator dg = new DataGenerator(n, m, size);
		Object[][][] setsLists = dg.generateData();
		return setsLists;  
	}
	
	/**
	 * Saves the results from main calculation into text file
	 * @throws FileNotFoundException
	 */
	public static void saveResults() throws FileNotFoundException { 

		PrintStream out = new PrintStream(new File("experimentalResults", "allResults.txt"));
		out.print("Size");
		for (StrategiesTimeCollection trc : resultsPerStrategy) 
			out.print("\t" + trc.getStrategyName()); 
		out.println();

		int numberOfExperiments = resultsPerStrategy.get(0).size(); 
		for (int i=0; i<numberOfExperiments; i++) {
			out.print(resultsPerStrategy.get(0).get(i).getKey());
			for (StrategiesTimeCollection trc : resultsPerStrategy)
				out.print("\t" + trc.get(i).getValue());
			out.println(); 
		}

		out.close();

	}

}
