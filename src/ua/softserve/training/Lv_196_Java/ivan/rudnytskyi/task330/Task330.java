package ua.softserve.training.Lv_196_Java.ivan.rudnytskyi.task330;

public class Task330 {

	public static void main(String[] args) {

		//the limit for perfect numbers search
		long number = 50000;

		// the method, which searches for perfect numbers		
		perfectNumbers(number);
		
	}
	/**
	 * in the method perfect number are searched for. If a perfect number is found
	 *  - it is printed. Otherwise, the message is printed - no perfect numbers found.
	 * @param number - long, high limit of the search for perfect numbers
	 * @throws IllegalArgumentException - the number must be greater than 1
	 */
	public static void perfectNumbers (long number){
		
		if (number < 1)
			throw new IllegalArgumentException("The number must be geater then 1");
		
		//if a perfect number is found, the variable is set true
		boolean perfectNumberPresent = false;
		
		//starting from 2 - no need to check number 1. 
		for (long i = 2; i <= number; i++) {
			
			//calling sumDivisors method, which calculates the sum of the divisor of 
			//the given number. If they are equal - the number is perfect and it is 
			//printed
			if (i == sumDivisors(i)){
				
				perfectNumberPresent = true;
				System.out.println("Perfect number under " + number + ": " + i);				
				
			}
			
		}
		//otherwise - if no perfect number is found, print the message
		if (!perfectNumberPresent)
			System.out.println("There are no perfect numbers under " + number);

	}

	/**
	 * in the method a sum of a number's divisor is calculated and returned
	 * @param number - long, the number in question
	 * @return - sum of the number's divisors
	 */
	public static long sumDivisors(long number){

		long sumDivisors = 0;
		//starting from 1 to number / 2 (no need to search for the divisors, which are 
		//greater. If a divisor is found, sum is recalculated
		for (long i = 1; i <= number / 2; i++){

			if (number % i == 0)
				sumDivisors += i;		

		}

		return sumDivisors;

	}

}
