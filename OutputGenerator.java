import java.util.ArrayList;
import java.util.*;

/* CAN MODIFY THIS CLASS 
 * except, do not change the name of the class, or generateOutput
 * 
 */


public class OutputGenerator {
	public static ArrayList<ArrayList<item>> results;
	public static void generateOutput(int numColours, StringBuilder outputStream) {
		results = new ArrayList<ArrayList<item>>();
		outputStream.append("<html><head>");
		outputStream.append("<style>.Circle1 {background: #f00;width: 25px;height: 25px;border-radius: 50%;}.Circle2 {background: rgb(0,0,0);width: 25px;height: 25px;border-radius: 50%;}.Circle3 {background: rgb(0,0,204);width: 25px;height: 25px;border-radius: 50%;}.Circle4 {background: rgb(255,255,0);width: 25px;height: 25px;border-radius: 50%;}.Circle5 {background: rgb(0,153,0);width: 25px;height: 25px;border-radius: 50%;}.Circle6 {background: rgb(153,0,153);width: 25px;height: 25px;border-radius: 50%;}.Circle7 {background: rgb(255,128,0);width: 25px;height: 25px;border-radius: 50%;}.Circle0 {background: rgb(102,51,0);width: 25px;height: 25px;border-radius: 50%;}</style></head>");
		outputStream.append("</head><body>");
		ArrayList<item> mySet = new ArrayList<item>();
		for(int i = 0; i< numColours; i++){
			//if we're doing the first thing.
			mySet.add(new item(i));
			//if we're doing the second thing.
			for(int j=1; j<=i; j++){
				mySet.add(new item(i,j-1));
			}
		}
		//so here, the recursive function is called.
		allSets(new ArrayList<item>(),mySet);
		// DEBUGGING:
		System.out.println(results);
		// Sorting
		Collections.sort(results, new Comparator<ArrayList<item>> () {
			// This sorts the list completely.
			@Override
			public int compare(ArrayList<item> a, ArrayList<item> b) {
				if(a.size() > b.size()){
					return 1;
				} else 
					return 0;
				}
			});
		// for loop that creates circles. NOTE: This does not work.
		for(int i=0; i<results.size(); i++){
			for(int j=0; j<results.get(i).size();j++){
				outputStream.append("<td class='Circle"+results.get(i).get(j)+"'/>"+results.get(i).get(j)+"<td>");
			}
			outputStream.append("</body><br/>");
		}
		outputStream.append("</html>");
		//outputStream.append(results);
	}
	
	//private items.
	public static class item{
		public int color;
		public int secondColor;
		public item(int solid){
			color = solid;
			secondColor = -1;
		}
		public item(int solid, int half){
			color = solid;
			secondColor = half;
		}
		public boolean isDouble(){
			if(secondColor == -1){
				return false;
			}
			return true;
		}
		public boolean colorMatch(item newItem){
			if(secondColor == -1 && newItem.secondColor == -1){
				if(color == newItem.color){
					return true;
				} else {
					return false;
				}
			} else {
				if(color == newItem.color || color == newItem.secondColor || secondColor == newItem.color || secondColor == newItem.secondColor){
					return true;
				} else {
					return false;
				}
			}
		}
		public String toString(){
			if(secondColor == -1){
				return Integer.toString(color);
			}
			return Integer.toString(color) + ":" + Integer.toString(secondColor);
			
		}
	}
	//this is the recrusive function that does the analy
public static void allSets(ArrayList<item> originalSet, ArrayList<item> leftOvers){
	results.add(originalSet);
	if(leftOvers.isEmpty()){
		return;
	}
	for(int i =0; i< leftOvers.size(); i++){
		ArrayList<item> sendSet = (ArrayList<item>) originalSet.clone();
		ArrayList<item> sendLeft = (ArrayList<item>) leftOvers.clone();
		sendSet.add(leftOvers.get(i));
		item currentItem = leftOvers.get(i);
		for(int j=sendLeft.size()-1; j >= 0; j--){
			if( j == i || currentItem.colorMatch(sendLeft.get(j))){
				sendLeft.remove(j);
			}
		}
		//System.out.println(send);
		allSets(sendSet,sendLeft);
	}
}
}
