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
public class P1and2<E> extends AbstractIntersectionFinder{

	{
	try {
		DataReader data = new DataReader();
	}
	catch (FileNotFoundException e){
		e.printStackTrace();
		}
	}
	
	
	public P1and2(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MySet intersectSets(MySet[] t) {
		// TODO Auto-generated method stub
		return null;
	}

}
