package HomeWork.task322;

import java.util.ArrayList;
import java.util.List;

public class DivisionCounter {

	public static void main(String[] args) {
		DivisionCounter div = new DivisionCounter();
		div.maxDivision();
	}

	public void maxDivision() {
		int maxCount = 0;
		List<Integer> arr = new ArrayList<>();
		for (int i = 1; i <= 10000; i++) {
			int count = 0;
			for (int j = 1; j <= i; j++) {
				if (i % j == 0) {
					count++;
				}
			}
			if (count > maxCount) {
				arr.clear();
				arr.add(i);
				maxCount = count;
			}
			else if (count == maxCount) {
				arr.add(i);
			}
		}
		System.out.println(arr);
	}
}
