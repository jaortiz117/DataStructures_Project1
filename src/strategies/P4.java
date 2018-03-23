package strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

/**
 * 
 * @author Javier A. Ortiz García 802-16-4820 section 070
 * @author Jose A. Velazquez Torres 802-14-8632 section 070
 *
 */
public class P4<E> extends AbstractIntersectionFinder<E>{

	public P4(String name) {
		super(name);
	}

	/**
	 * Finds intersection of sets according to the strategy of P4
	 * @param t Union of sets
	 * @return returns intersection of all sets t
	 */
	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
		
		//create union of all items in t
		ArrayList<E> allElements = new ArrayList<>();
		
		for(MySet<E> i : t) {
			E[] e = (E[]) i.toArray(new Object[i.size()]);
			for (int j = 0; j < e.length; j++) {
				allElements.add(e[j]);
			}
		}
		
		//P4 as specified
		HashMap<E, Integer> map = new HashMap<>();
		
		for(E e : allElements) {
			Integer c = map.getOrDefault(e, 0);
			map.put(e, c+1);
		}
		
		MySet<E> result = new Set2<>();
		
		for(Map.Entry<E, Integer> entry : map.entrySet()) {
			if(entry.getValue() == t.length)
				result.add(entry.getKey());
		}
		
		return result;
	}

}
