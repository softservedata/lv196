package HomeWork.task86b;

class NumberSumm {
	
	public static void main(String[] args) {
		int number = 123456;
		NumberSumm nums = new NumberSumm();
		System.out.println(nums.counter(number));
	}
	
	private int counter(int x) {
		int summ = 0;
		char[] arr = (x + "").toCharArray();
		for (char i : arr) {
			summ +=  Character.getNumericValue(i);
		}
		return summ;
	}

}
