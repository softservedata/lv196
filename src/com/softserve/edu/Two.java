package MainDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class Two {

//243 б)
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Integer n=Integer.parseInt(br.readLine());
        for (int i=1;i<=n;i++)
        {
            double x2=i^2;
            double y2=n-x2;
            if( ( (round(sqrt(y2))-(sqrt(y2)))==0)&&(round(sqrt(x2))-(sqrt(x2)))==0&&(x2>=y2)&&(x2>0)&&(y2>0))
            {
                System.out.println("x="+sqrt(x2)+" "+"y="+sqrt(y2));
            }
        }
    }
}
