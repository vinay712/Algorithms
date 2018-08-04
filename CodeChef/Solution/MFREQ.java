/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Vinay
 */
import java.util.*;
import java.io.*;
class MFREQ {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
                int n=in.nextInt();
                int m=in.nextInt();
                int a[]=in.nextIntArray(n);
                int freq[]=new int[n];
                int last[]=new int[n];
                int first[]=new int[n];
                int p=-1;
                int fi=-1,la=-1;
                for(int i=0;i<n;i++){
                    if(a[i]==p){
                        freq[i]=freq[i-1]+1;
                        first[i]=fi;
                        la=i;
                    }
                    else{
                        for(int j=fi;fi!=-1 && j<i;j++){
                            last[j]=la;
                        }
                        freq[i]=1;
                        first[i]=i;
                        fi=i;
                        la=i;
                        p=a[i];
                        
                    }
                }
                for(int j=fi;j<n;j++){
                    last[j]=la;
                }
                /*for(int i=0;i<n;i++){
                    System.out.print(freq[i]+" ");
                }
                System.out.println();
                for(int i=0;i<n;i++){
                    System.out.print(first[i]+" ");
                }
                System.out.println();
                for(int i=0;i<n;i++){
                    System.out.print(last[i]+" ");
                }
                System.out.println();*/
                for(int i=0;i<m;i++){
                    int l=in.nextInt();
                    l--;
                    int r=in.nextInt();
                    r--;
                    int k=in.nextInt();
                    k--;
                    int mid=(r+l)/2;
                    int ans=-1;
                    int maxfirst=first[mid]>l?first[mid]:l;
                    int minlast=last[mid]<r?last[mid]:r;
                    if((minlast-maxfirst)>=k){
                        ans=a[mid];
                    }
                    
                    System.out.println(ans);
                }
                pw.close();
	} 
       	static class InputReader
	{
 
		private InputStream stream;
		private byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;
 
		public InputReader(InputStream stream)
		{
			this.stream = stream;
		}
 
		public int snext()
		{
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars)
			{
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n)
		{
			int a[] = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}
 
                public long[] nextLongArray(int n)
		{
			long a[] = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}
 
		public String readString()
		{
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c)
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		public interface SpaceCharFilter
		{
			public boolean isSpaceChar(int ch);
		}
	}
}
 