package ua.softserve.training.Lv_196_Java.ivan.rudnytskyi.task182;

/**
 * @author Ivan Rudnytskyi
 * date 2016/08/26 (yyyy/mm/dd)
 *
 * The task (# 182):
 *
 * A natural number is given. Also a sequence of the whole numbers is given - a1, ... an.
 * Find count and sum of the members of the sequence, which divide by 5 and don't divide by 7. 
 *
 *
 */

public class Task182 {

	public static void main(String[] args) {

		//the high limit for the sequence
		long n = 39;

		//the start number of the sequence
		long a1 = -26;

		//the method, which searches for numbers, which a divided by 5 and not divided
		// by 7 - findDivisorsBy5andNotBy7
		findDivisorsBy5andNotBy7(a1, n);
	}
	/**
	 * 
	 * @param a1 - long, the start number of the sequence
	 * @param n - long, the high limit for the sequence
	 * Numbers, which divide by 5 and don't divide by 7, are printed. If no such numbers
	 * are found - the message is printed.
	 * @throws IllegalArgumentException - the numbers must be greater or equal to 0 and 
	 * high limit must be greater than the start number
	 */
	public static void findDivisorsBy5andNotBy7(long a1, long n){

		if (n < 0 || n < a1)
			throw new IllegalArgumentException("check input data");

		//count and sum of the found numbers
		long count = 0;
		long sum = 0;

		for (long i = a1; i <= n; i++){

			if (i % 5 == 0 && i % 7 != 0){

				count++;
				sum += i;

			}

		}	

		//if the numbers are found
		if (count > 0){
			System.out.println("Count of numbers, which divides by 5 and not by 7 "				
					+ "under number " + n + ": " + count);
			System.out.println("Sum of numbers, which divides by 5 and not by 7 "
					+ "under number " + n + ": " + sum);
		} else {		
			//otherwise - print the message				
			System.out.println("There are no numbers, which divide by 5 and not by 7" + 
					" under number " + n);
		}

	}

}
