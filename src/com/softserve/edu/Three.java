package MainDirectory;

// 562
public class Three {
    public static void main(String[] args) {
        for(int i=10;i<9999;i++){
            char[] arr=String.valueOf(i).toCharArray();

            int sum=0;
            for (char cha : arr) {
                sum += Math.pow(Double.parseDouble(((Character) cha).toString()),arr.length);
            }
            if (sum==i){
                System.out.println("n="+i);
                for (char g:arr) {
                    System.out.print(" " + g);
                }
             System.out.println();
            }

    }
    }

}
