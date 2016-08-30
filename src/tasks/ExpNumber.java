package tasks;

public class ExpNumber {
	
	public static int findMinNumber(int n){
        int result = 1;  
        while (result <= n){
            result = result*2;
        }
        return result;
	}
	
	public static void main(String[] args){
		int number = IOMethods.getInt();
		IOMethods.printNumber(findMinNumber(number));
			
	}

}
