import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the xorSequence function below.
     */
    static long xorSequence(long l, long r) {
        /*
         * Write your code here.
         */
        return xorLimit(r)^xorLimit(l-1);

    }

    static long xorLimit(long n){
        long c=0;
        if(n<0){
            return c;
        }
        long remainder=n%8;
        	if(remainder<2){
        		c=n;
        	}else if(remainder<4){
        		c=2;
        	}else if(remainder<6){
        		c=n+2;
        	}else{
        		c=0;
        	}
        return c;
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            String[] lr = scanner.nextLine().split(" ");

            long l = Long.parseLong(lr[0].trim());

            long r = Long.parseLong(lr[1].trim());

            long result = xorSequence(l, r);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
