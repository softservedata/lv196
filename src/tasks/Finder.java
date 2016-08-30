package tasks;

public class Finder {
	
	public static int countNumbers(int[] arr){
		int counter = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] % 3  == 0 && arr[i] % 5 != 0)
				counter++;
		}
		return counter;
	}

	public static void main(String[] args) {

		int number = IOMethods.getInt();
		IOMethods.printCounter(countNumbers(IOMethods.getSequence(number)));
		
	}

}
