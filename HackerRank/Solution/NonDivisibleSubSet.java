import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the nonDivisibleSubset function below.
     */
    static int nonDivisibleSubset(int k, int[] s) {
        /*
         * Write your code here.
         */
        int remainder[]=new int[k];
        for(int i=0;i<s.length;i++){
            remainder[s[i]%k]++;
        }
        int size=0;
        int i=0;
        if(remainder[i]>0){
            size++;
        }
        for(i=1;i<(k+1)/2;i++){
            size+=Math.max(remainder[i],remainder[k-i]);
        }
        if(k%2==0 && remainder[k/2]>0){
            size++;
        }
        return size;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

        int[] S = new int[n];

        String[] SItems = scanner.nextLine().split(" ");

        for (int SItr = 0; SItr < n; SItr++) {
            int SItem = Integer.parseInt(SItems[SItr].trim());
            S[SItr] = SItem;
        }

        int result = nonDivisibleSubset(k, S);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
