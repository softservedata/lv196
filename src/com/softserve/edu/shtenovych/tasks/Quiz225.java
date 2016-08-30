package tasks;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Petro Shtenovych
 * */
public class Quiz225 {

    /**This method returns all dividers of given number
     * @param number should be more than zero
     * @throws IllegalArgumentException if number less or equal zero
     */
    public static List<Long> getAllDividers(long number) {
        if (number <= 0) throw new IllegalArgumentException("number should be more than zero");
        List<Long> result = new LinkedList<>();
        result.add(number); //add number, each number is divisible by themselves
        for (long i = number / 2; i > 0 ; i--) {
            if (number % i == 0) { // if the remainder of the division is zero
                result.add(i); //add divider
            }
        }
        return result;
    }
    //test for method
    public static class Tester {
        public static void main(String[] args) {
            test(1, 1);
            test(100, 100, 50, 25, 10, 5, 4, 2, 1);
            test(Integer.MAX_VALUE);
        }
    }

    private static void test(long number, long...dividers) {
        List<Long> list = getAllDividers(number);
        for (long val : dividers) {
            if ( ! list.contains(val)) {
                throw new AssertionError("test was failed, number=" + number);
            }
        }
    }

    private static void test(long number) {
        List<Long> dividers = getAllDividers(number);
        for (long val : dividers) {
            if (number % val != 0) {
                throw new AssertionError("test was failed, number=" + number);
            }
        }
    }
}