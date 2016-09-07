package homeWork;

public class Task88g {

	/**
	 * Add "1" to the start and the and of the number
	 * */
	
	public static int addOneBothSides(int number) {
		if (number < 0 || number > 99999999) {return 404; } 
		if (number == 0) {return 101;}   
		//declare a variable digitalsCount (number of digits)
		int digitalsCount = (int) (Math.log10(number) + 1);        
		//10 to the power of digitalsCount plus our number will add "1" in front of our number
		int roundedNumber = (int) Math.pow(10, digitalsCount);   
		int result = (roundedNumber + number)*10 + 1; //*10 + 1 - will insert "1" in the end of our number
		return result;
	}
}
