import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Permutations {
	static StringBuilder permutations = new StringBuilder("[");
	
	public static String getPermutations(int n) {
		Set<Integer> mySet = new HashSet<Integer>();
		for (int i = 1; i<=n; i++) {
			mySet.add(i);
		}
		
		for (Set<Integer> s : getPowerSet(mySet)) {			
			int[] arr = new int[s.size()];
			int index = 0;
			for (Integer i : s ) {
			  arr[index++] = i;
			}			
		    permute(arr, 0);
		}
		permutations.deleteCharAt(permutations.length()-1);
		permutations.deleteCharAt(permutations.length()-1);
		permutations.append("]");
		
		return permutations.toString();
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
	
	static void permute(int[] a, int k) 
    {
        if (k == a.length) 
        {
        	permutations.append("[");
        	for (int i = 0; i < a.length; i++) 
            {
                if (i < a.length-1) {
                	permutations.append(a[i] + ", ");
                } else {
                	permutations.append(a[i]);
                }
            }
        	permutations.append("], ");
        } 
        else 
        {
            for (int i = k; i < a.length; i++) 
            {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
 
                permute(a, k + 1);
 
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }
}
