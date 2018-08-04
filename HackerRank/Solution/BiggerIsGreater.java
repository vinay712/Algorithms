import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
     char[] charArray = w.toCharArray();
		int i = charArray.length - 1;
		TreeSet<Character> ts=new TreeSet<>();
		int index[]=new int[26];
		for (; i >= 0; i--) {
			ts.add(charArray[i]);
			index[charArray[i]-'a']=i;
			SortedSet<Character> ss=ts.tailSet((char)(charArray[i]+1)); 
			if (ss.size()>0) {
				int pos=index[(char)ss.first()-'a'];
				char c = charArray[i];
				charArray[i] = charArray[pos];
				charArray[pos] = c;
				Arrays.sort(charArray, i + 1, charArray.length);
				return new String(charArray);
			}
		}
		return "no answer";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();

            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
