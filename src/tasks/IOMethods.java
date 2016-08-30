package tasks;

import java.util.Scanner;

public class IOMethods {

	private static Scanner in = new Scanner(System.in);

	public static int getInt() {
		System.out.println("Write the number ");
		String number = in.nextLine();
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			System.out.println("Wrong format, integer please ");
		}
		return Integer.parseInt(number);
	}

	public static int[] getSequence(int num) {
		System.out.println("Write " + num + " numbers with gaps");
		String numbers = in.nextLine();
		String[] line = numbers.split(" ");
		int[] sequence = new int[line.length];
		for (int i = 0; i < line.length; i++) {
			try {
				sequence[i] = Integer.parseInt(line[i]);
			} catch (NumberFormatException e) {
				System.out.println("Wrong format, integer please ");
			}
		}
		return sequence;
	}

	public static void printNumber(int result) {
		System.out.println("min 2^r = " + result);
	}
	
	public static void printCounter(int result){
		System.out.println(result + " numbers");
	}
}
