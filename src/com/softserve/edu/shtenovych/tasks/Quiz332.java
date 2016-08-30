package tasks;

import static java.lang.Math.sqrt;

/**
 * @author Petro Shtenovych
 * */
public class Quiz332 {

    /**
     * This class computes four numbers(x, y, z and t) that are
     * the sum of their squares is equal to a given number
     * */
    public static class FourSquare {
        private Integer number;
        private Integer x, y, z, t;

        /**
         * @param number should be more or equal zero
         * @throws IllegalArgumentException if number less than zero
         * */
        public FourSquare(int number) {
            if (number < 0) {
                throw new IllegalArgumentException("The number should be more or equal zero");
            } else {
                this.number = number;
                calculate();
            }
        }

        //calculate squares for x,y,z,t
        private void calculate() {
            int temp1, temp2, t;
            for (int x = (int) sqrt(this.number / 4); x * x <= this.number; x++) {
                temp1 = this.number - x * x;
                for (int y = (int) sqrt(temp1 / 3); y <= x && y * y <= temp1; y++) {
                    temp2 = temp1 - y * y;
                    for (int z = (int) sqrt(temp2 / 2); z <= y && z * z <= temp2; z++) {
                        t = (int) sqrt(temp2 - z * z);
                        assignValues(x, y, z, t);
                        if (checkCondition()) return;
                    }
                }
            }
        }

        private void assignValues(int x, int y, int z, int t) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.t = t;
        }

        private boolean checkCondition() {
            return this.number == getX2() + getY2() + getZ2() + getT2();
        }

        //getters
        public int getNumber() {
            return number;
        }

        public int getX() {
            return x;
        }

        public int getX2() {
            return x * x;
        }

        public int getY() {
            return y;
        }

        public int getY2() {
            return y * y;
        }

        public int getZ() {
            return z;
        }

        public int getZ2() {
            return z * z;
        }

        public int getT() {
            return t;
        }

        public int getT2() {
            return t * t;
        }

        @Override
        public String toString() {
            return "FourSquare{" +
                    "number=" + getNumber() +
                    ", x^2=" + getX2() +
                    ", y^2=" + getY2() +
                    ", z^2=" + getZ2() +
                    ", t^2=" + getT2() +
                    '}';
        }
    }
    //test numbers
    public static class Tester {
        public static void main(String[] args) {
            for (int i = 0; i < 10_000; i++) {
                test(i);
            }
            test(Integer.MAX_VALUE);
        }

        public static void test(int number) {
            Quiz332.FourSquare testInstance = new Quiz332.FourSquare(number);
            boolean successfully = testInstance.getNumber() == (testInstance.getX2() + testInstance.getY2() + testInstance.getZ2() + testInstance.getT2());
            if ( ! successfully) {
                throw new AssertionError("test was failed: " + testInstance);
            }
        }
    }
}