package MainDirectory;
// Даны натуральное n, m. Получить сумму m последних цифр числа n. (87)
public class MainClass {
        private Integer n=328094859;
        private int m=2;


        public int sum(){
            String s=n.toString();    //З числа в строку
            char[] arr= s.toCharArray();  //З строки в массив символів
            int summ=0;                   // Ініціалізуемо змінну і прирівнюемо до 0

            for (int i=arr.length-m; i<arr.length; i++){   //беремо з массиву потрібні нам символи
            summ+=Integer.parseInt(((Character)arr[i]).toString());
            }
            return summ;
        }

        public static void main(String[] args) {
            System.out.println(new MainClass().sum());
        }


}
