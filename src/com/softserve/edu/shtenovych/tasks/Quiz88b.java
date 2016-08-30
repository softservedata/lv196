package tasks;

/**
 * @author Petro Shtenovych
 * */
public class Quiz88b {
    /**
     * Method replaces first and last digit in number and returns result as long value
     * number can be positive or negative
    */
    public static long replaceFirstLastDigits(long number) {
        boolean unsign = (number < 0); //true if value is negative
        if (unsign) {
            number *= (-1); //if value is negative, make its positive
        }
        long count = 1;
        while (number / count >= 10) { //calculates range
            count *= 10;
        }
        long firstDigit = number / count; // get first digit
        long lastDigit = number % 10; // get last digit

        long result = number + (count - 1) * (lastDigit - firstDigit); //replace first and last digit

        return (unsign) ? result * (-1) : result; //if number was negative return negative result
    }

    //test for method
    public static class Tester {
        public static void main(String[] args) {
            test(-7_999_999_999_999_999_998L, -8_999_999_999_999_999_997L);
            test(-33, -33);
            test(0, 0);
            test(123_456, 623_451);
            test(50_000, 5);
            test(7_999_999_999_999_999_998L, 8_999_999_999_999_999_997L);
        }

        private static void test(long number, long assertResult) {
            if( assertResult != replaceFirstLastDigits(number)) {
                throw new AssertionError("test was failed: " + " number=" + number);
            }
        }
    }
}