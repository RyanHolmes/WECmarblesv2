import java.util.ArrayList;
import java.util.*;

/* CAN MODIFY THIS CLASS 
 * except, do not change the name of the class, or generateOutput
 * 
 */


public class OutputGenerator {
	public static ArrayList<String> results = new ArrayList<String>();
	public static void generateOutput(int numColours, StringBuilder outputStream) {
		//outputStream.append("<html>");
		//outputStream.append(numColours + " was inputted.");
		//outputStream.append("</html>");
		ArrayList<item> mySet = new ArrayList<item>();
		for(int i = 0; i< numColours; i++){
			//if we're doing the first thing.
			mySet.add(new item(i));
			//if we're doing the second thing.
		}
		allSets(new ArrayList<item>(),mySet);
		System.out.println(results);
		outputStream.append(results);
	}
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
public static void allSets(ArrayList<item> originalSet, ArrayList<item> leftOvers){
	results.add(originalSet.toString());
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
