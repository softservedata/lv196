package HomeWork.task323;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CoprimeNumbers {
	public static void main(String[] args) {
		CoprimeNumbers copr = new CoprimeNumbers();
		int x = 1000;
		copr.coprimeFinder(x);
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
		System.out.println(arr);
	}
}
