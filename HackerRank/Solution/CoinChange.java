import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	static long getWays(int n, int[] c) {
		Arrays.sort(c);
		long total[][] = new long[n + 1][c.length];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < c.length; j++) {
				if (i - c[j] > 0) {
					total[i][j] += total[i - c[j]][j];
				}
				if (i == c[j]) {
					total[i][j]++;
				}
				if(j>0){
					total[i][j]+=total[i][j-1];
				}
			}
		}/*
		for (int j = 0; j < c.length; j++) {
			for (int i = 1; i <= n; i++) {
				System.out.print(total[i][j]+"\t");
			}
			System.out.println();
		}*/
		return total[n][c.length - 1];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] c = new int[m];
		for (int c_i = 0; c_i < m; c_i++) {
			c[c_i] = in.nextInt();
		}
		// Print the number of ways of making change for 'n' units using coins
		// having the values given by 'c'
		long ways = getWays(n, c);
		System.out.println(ways);
		in.close();
	}
}
