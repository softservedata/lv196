package ua.softserve.training.Lv_196_Java.ivan.rudnytskyi.task330;

import java.util.Set;
import java.util.TreeSet;

public class Task330 {

	public static void main(String[] args) {

		//the limit for perfect numbers search
		long number = 10000;

		//set with the results. TreeSet is used for a natural ordering of the results
		//(just for convenience). The set if filled with the results from the method
		//perfectNumbers
		Set <Long> perfectNumbersSet = perfectNumbers(number);

		//if the set is not empty - print the results
		if (perfectNumbersSet.size() > 0){

			System.out.println("Perfect numbers under " + number + ":");

			perfectNumbersSet.forEach(System.out::println);

		//otherwise - print the message
		} else {
			
			System.out.println("There are no perfect numbers, which are less than " 
					+ number + ":");
			
		}

	}
	/**
	 * in the method a set is filled with the perfect numbers, and the set is returned.
	 *  If there are none - the empty set is returned 
	 * @param number - long, high limit of the search for perfect numbers
	 * @return Set<Long> - set with perfect numbers
	 */
	public static Set<Long> perfectNumbers (long number){

		Set <Long> perfectNumbers = new TreeSet<>();
		
		//starting from 2 - no need to check number 1. 
		for (long i = 2; i <= number; i++) {
			
			//calling sumDivisors method, which calculates the sum of the divisor of 
			//the given number. If they are equal - the number is perfect and it is 
			//added to the set
			if (i == sumDivisors(i))
				perfectNumbers.add(i);

		}

		return perfectNumbers;

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
