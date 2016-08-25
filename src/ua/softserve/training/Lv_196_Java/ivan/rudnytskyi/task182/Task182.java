package ua.softserve.training.Lv_196_Java.ivan.rudnytskyi.task182;

public class Task182 {

	public static void main(String[] args) {

		//the high limit for the sequence
		long n = 39;

		//the start number of the sequence
		long a1 = 4;

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
		
		if (a1 < 0 || n < 0 || n < a1)
			throw new IllegalArgumentException("check input data");

		//if a perfect number is found, the variable is set true
		boolean divisors5not7Found = false;

		for (long i = a1; i <= n; i++){

			if (i % 5 == 0 && i % 7 != 0){

				divisors5not7Found = true;
				System.out.println("Number, which divides by 5 and not by 7 under number "
						+ n + ": " + i);
			}

		}	
		//otherwise - print the message
		if (!divisors5not7Found)
			System.out.println("There are no numbers, which divide by 5 and not by 7" + 
					" under number " + n);

	}

}
