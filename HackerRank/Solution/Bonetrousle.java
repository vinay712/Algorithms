import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the bonetrousle function below.
     */
    static long[] bonetrousle(long n, long k, int b) {
        /*
         * Write your code here.
         */
        long min=(b+1)*b/2;
        long max= (2*k-b+1)*b/2;
        if(n<min || n>max){
            return null;
        } else{
            
        long a[]=new long[b];
            int r=(int)((n-min)%b);
            long q=(n-min)/b;
            for(int i=0;i<b;i++){
                a[i]+=q+i+1;
                if(i>=b-r){
                    a[i]++;
                }
            }
            
        return a;
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nkb = scanner.nextLine().split(" ");

            long n = Long.parseLong(nkb[0].trim());

            long k = Long.parseLong(nkb[1].trim());

            int b = Integer.parseInt(nkb[2].trim());

            long[] result = bonetrousle(n, k, b);

            if(result == null){
                bufferedWriter.write("-1");
            }else{
            for (int resultItr = 0; resultItr < result.length; resultItr++) {
                bufferedWriter.write(String.valueOf(result[resultItr]));

                if (resultItr != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
