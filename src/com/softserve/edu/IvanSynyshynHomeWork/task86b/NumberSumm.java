package HomeWork.task86b;

import java.util.InputMismatchException;
import java.util.Scanner;

class NumberSumm {
	
	public static void main(String[] args) {
		NumberSumm nums = new NumberSumm();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please, print number:");
		try {
			int number = sc.nextInt();
			System.out.println("Sum of the digits of your number is: " + nums.counter(number));
		}
		catch (InputMismatchException e) {
			System.out.println("You must print number, please try again");
		}
		sc.close();
	}
	
	private int counter(int x) {
		x = Math.abs(x);
		int summ = 0;
		char[] arr = (x + "").toCharArray();
		for (char i : arr) {
			summ +=  Character.getNumericValue(i);
		}
		return summ;
	}

}
