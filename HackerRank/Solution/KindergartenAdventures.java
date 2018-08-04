import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the solve function below.
     */
    static int solve(int[] t) {
      
    	int n=t.length;
    	int counter[]=new int[n];
    	for(int i=0;i<n;i++){
    		if(t[i]>0 && t[i]<n){
    			int fail=(i-t[i]+n+1)%n;
    			int pass=(i+1)%n;
    			counter[fail]--;
    			counter[pass]++;
    		}
    	}
    	
    	int max=counter[0];
    	int sol=0;
    	for(int i=1;i<n;i++){
    		counter[i]+=counter[i-1];
    		if(counter[i]>max){
    			max=counter[i];
    			sol=i;
    		}
    	}
    	return sol+1;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int tCount = Integer.parseInt(scanner.nextLine().trim());

        int[] t = new int[tCount];

        String[] tItems = scanner.nextLine().split(" ");

        for (int tItr = 0; tItr < tCount; tItr++) {
            int tItem = Integer.parseInt(tItems[tItr].trim());
            t[tItr] = tItem;
        }

        int id = solve(t);

        bufferedWriter.write(String.valueOf(id));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
