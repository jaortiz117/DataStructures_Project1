package strategies;

import interfaces.MySet;

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

	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
		// TODO Auto-generated method stub
		
		//make generic for types MySet1 and 2
		
		MySet<E> result = t[0];
		
		for(E item : result) {
			for(int j = 0; j<t.length; j++) {
				if(!(t[j].contains(item))) {
					result.remove(item);
				}
			}
		}
		
		
		return result;
	}

}
