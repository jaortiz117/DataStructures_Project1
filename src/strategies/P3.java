package strategies;

import java.util.ArrayList;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

/**
 * 
 * @author Javier A. Ortiz Garc�a 802-16-4820
 * @author Jose A. Velazquez Torres 802-14-8632
 *
 */

public class P3<E> extends AbstractIntersectionFinder<E>{

	public P3(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MySet intersectSets(MySet[] t) {
		ArrayList<E> allElements = new ArrayList<>();
		
		//union of all items
		for(MySet<E> i : t) {
			E[] e = (E[]) i.toArray(new Object[i.size()]);
			for (int j = 0; j < e.length; j++) {
				allElements.add(e[j]);
			}
		}
		

		allElements.sort(null);         
		MySet<E> result = new Set2();  // sets in P3's solution are of type Set2
		E e = allElements.get(0); 
		Integer c = 1;
		for (int i=1; i < allElements.size(); i++) {
		    if (allElements.get(i).equals(e)) 
		       c++;
		    else { 
		       if (c == t.length) 
		          result.add(e);    // m is as in the previous discussion
		       e = allElements.get(i); 
		       c = 1; 
		    } 
		}
		if (c == t.length)
		    result.add(allElements.get(allElements.size()-1));

		return result;
	}

}
