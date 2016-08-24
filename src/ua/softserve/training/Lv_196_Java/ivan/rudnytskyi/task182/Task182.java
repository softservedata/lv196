package ua.softserve.training.Lv_196_Java.ivan.rudnytskyi.task182;

import java.util.Set;
import java.util.TreeSet;

public class Task182 {

	public static void main(String[] args) {
		
		//the high limit for the sequence
		long n = 39;
		
		//the start number of the sequence
		long a1 = 4;
		
		//set with the results. TreeSet is used for a natural ordering of the results
		//(just for convenience). The set if filled with the results from the method
		//findDivisorsBy5andNotBy7
		Set <Long> divisors5Not7 = findDivisorsBy5andNotBy7(a1, n);
		
		//if the set is not empty - print the results
		if (divisors5Not7.size() > 0){

			System.out.println("Numbers, which divide by 5 and not by 7 under number "
					+ n + ":");

			divisors5Not7.forEach(System.out::println);

		//otherwise - print the message
		} else {
			
			System.out.println("There are no Numbers, which divide by 5 and not by 7" + 
					" under number " + n + ":");
			
		}


	}
	/**
	 * 
	 * @param a1 - long, the start number of the sequence
	 * @param n - long, the high limit for the sequence
	 * @return - Set <Long> with the numbers, which divide by 5 and don't divide by 7
	 */
	public static Set <Long> findDivisorsBy5andNotBy7(long a1, long n){
		
		Set <Long> divisors5Not7 = new TreeSet<>();
		
		for (long i = a1; i <= n; i++){
			
			if (i % 5 == 0 && i % 7 != 0)
				divisors5Not7.add(i);
			
		}
		
		return divisors5Not7;		
		
	}

}


