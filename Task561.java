package homeWork;

import java.util.ArrayList;
import java.util.List;

public class Task561 {

	/**Find all the numbers ranging from 1 to n.
	 * Find all the numbers that are equal to recent numbers of their squares.
	 * */
	
	public static List<Integer> numberToItSquareSimilarity(int number) {
		
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i < number; i++) { 
			//declare a variable digitalsCount (number of digits)
			int digitalsCount = (int) (Math.log10(i) + 1);
			//division of the square on 10^digitalsCount has to be the same as our number is
			if(i == i*i%Math.pow(10, digitalsCount)) { 
				result.add(i);
				result.add(i*i);
				}
			}
		return result;
		}
	}
