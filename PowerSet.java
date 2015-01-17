import java.util.*;

public class PowerSet {

	public static void main(String[] args) {
		Set<Integer> mySet = new HashSet<Integer>();
		 mySet.add(1);
		 mySet.add(2);
		 mySet.add(3);
		 for (Set<Integer> s : getPowerSet(mySet)) {
		     System.out.println(s);
		 }
	}
	
	public static List<Set<Integer>> getPowerSet(Set<Integer> originalSet) {
		List<Set<Integer>> list = new ArrayList<Set<Integer>>(powerSet(originalSet));
		list.remove(0);
		return list;
	}
	
	public static Set<Set<Integer>> powerSet(Set<Integer> originalSet) {
	    Set<Set<Integer>> sets = new HashSet<Set<Integer>>();
	    if (originalSet.isEmpty()) {
	    	sets.add(new HashSet<Integer>());
	    	return sets;
	    }
	    List<Integer> list = new ArrayList<Integer>(originalSet);
	    Integer head = list.get(0);
	    Set<Integer> rest = new HashSet<Integer>(list.subList(1, list.size())); 
	    for (Set<Integer> set : powerSet(rest)) {
	    	Set<Integer> newSet = new HashSet<Integer>();
	    	newSet.add(head);
	    	newSet.addAll(set);
	    	sets.add(newSet);
	    	sets.add(set);
	    }	
	    return sets;
	}
}
