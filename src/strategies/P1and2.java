package strategies;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;

import java.io.FileNotFoundException;

import dataGenerator.DataReader;
import setIntersectionFinders.AbstractIntersectionFinder;

/** 
 * 
 * @author Javier A. Ortiz García 802-16-4820
 *
 */
public class P1and2<E> extends AbstractIntersectionFinder<E>{

//	{
//	try {
//		DataReader data = new DataReader();
//	}
//	catch (FileNotFoundException e){
//		e.printStackTrace();
//		}
//	}
//	
	
	public P1and2(String name) {
		super(name);
	}

	/**
	 * Finds the intersection of sets
	 * 
	 * @param t Set t is union of data from input files
	 * @return intersection of all sets t
	 */
	public MySet<E> intersectSets(MySet<E>[] t) {
		// TODO Auto-generated method stub
		
		//make generic for types Set1 and 2
		
		MySet<E> result = null;
		try {
			if(this.getName().equals("P1"))
				result = (Set1<E>) t[0].clone();
			else
				result = (Set2<E>) t[0].clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(E item : t[0]) {
			for(int j = 0; j<t.length; j++) {
				if(!(t[j].contains(item))) {
					result.remove(item);
				}
			}
		}
		
		
		return result;
	}

}
