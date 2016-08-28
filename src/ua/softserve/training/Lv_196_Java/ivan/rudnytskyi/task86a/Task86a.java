package ua.softserve.training.Lv_196_Java.ivan.rudnytskyi.task86a;

/**
 * 
* @author Ivan Rudnytskyi
 * date 2016/08/26 (yyyy/mm/dd)
 *
 * The task (#86a):
 * 
 * A natural number is given. How many digits does it contain?
 *
 *
 */

public class Task86a {

	public static void main(String[] args) {
		
		//the natural number
		long number = 95684;

		//processing and printing the result of the calculation
		System.out.println("Count of digits in the natural number " + 
				number + " is "	+ digitCount(number));

	}	

	/**
	 * the method calculates length (number of digits in a natural number)
	 * @param number - long, the number, typed by a user 
	 * @return - int, number of digits in the entered number
	 * @throws IllegalArgumentException if the number is not natural (natural number is
	 * a non-negative whole number)
	 */

	public static int digitCount(long number){

		//checking if the number is natural
		if (number < 0)
			throw new IllegalArgumentException("The number must be greater or equal to 0");

		//if the number is 0 - return 1
		if (number == 0)
			return 1;

		int count = 0;

		//counting the number's quantity of digits by dividing it by 10 repeatedly
		//when it is 0 - stop counting
		while (number > 0) {
			number /= 10;
			count++;
		}

		//returning the result
		return count;
	}

}
