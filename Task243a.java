package homeWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task243a {
	
	/**
	 * Number has to represented as sum of squares of two other numbers
	 * */

	public static List<List<Integer>> sunSqr(int number) {
		// our number can't consist of the sum of the squares of numbers greater than its the root
		int numberSqr = (int) Math.sqrt(number); 
		//collect all the numbers and their squares in the map
		Map<Integer, Integer> sqrs = new HashMap<>();
		for(int i = 0; i <= numberSqr; i++) {
		sqrs.put(i * i, i);
		}
		//use loop to find if the is any coincidence between (number - i * i) and our keys in map
		List<List<Integer>> resutls = new ArrayList<>();
		for(int i = numberSqr; i >= numberSqr / 2; i--) {
		int diff = number - i * i;   //theoretically our other square 
		if(sqrs.containsKey(diff) && sqrs.get(diff) <= i) {
		resutls.add(Arrays.asList(i, sqrs.get(diff))); //fill in our results
		}
		}

		if (resutls.isEmpty()) {    //if the number is not decomposed in the sum of squares that will turn 404
		resutls.add(Arrays.asList(404));
		} 

		return resutls;
}
}
