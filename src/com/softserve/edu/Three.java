package MainDirectory;

// 562 Натуральное число из n цифр является числом Армстронга,если сумма его цифр, возведенных в n-ю степень, равна самому числу(как, например, 153 = 1^3+ 5^3+ 3^3). Получить все числа Армстронга,состоящие из двух, трех и четырех цифр.
public class Three {

    public static void main(String[] args) {
        Three three=new Three();
        three.method();
    }
    public void method() {
        for(int i=10;i<9999;i++){
            char[] arr=String.valueOf(i).toCharArray();

            int sum=0;
            for (char cha : arr) {
                sum += Math.pow(method2(cha),arr.length);
            }
            if (sum==i){
                System.out.println("n="+i);
                System.out.println();
            }

        }
    }
    public Double method2(char c){            //метод для перетворення, конвертації char to double
        return Double.parseDouble(((Character) c).toString());
    }

}
