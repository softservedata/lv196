package HomeWork.task323;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CoprimeNumbers {
	public static void main(String[] args) {
		CoprimeNumbers copr = new CoprimeNumbers();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please, print number:");
		try {
			int x = sc.nextInt();
			copr.coprimeFinder(x);
		} catch (InputMismatchException e) {
			System.out.println("You must print number, please try again");
		}
		sc.close();
	}

	public void coprimeFinder(int x) {
		Set<Integer> set = new TreeSet<>();
		List<Integer> arr = new ArrayList<>();
		boolean origin;

		for (int i = x - 1; i > 1; i--) {
			if (x % i == 0)
				set.add(i);
		}
		for (int i = x - 1; i > 1; i--) {
			origin = true;
			for (int j = i; j > 1; j--) {
				if (i % j == 0) {
					if (set.contains(j)) {
						origin = false;
						break;
					}
				}
				if (j == 2 && origin)
					arr.add(i);
			}
		}
		System.out.println("Your number has " + arr.size() + " coprime numbers");
		System.out.println(arr);
	}
}
