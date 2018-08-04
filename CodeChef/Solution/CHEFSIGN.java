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
class CHEFSIGN {
    public static void main(String[] args)
	{
            solve();            
	} 
        public static void solve(){
            InputReader in = new InputReader(System.in);
            PrintWriter pw = new PrintWriter(System.out);
            //System.out.println(gcd(22,5));
            int t=in.nextInt();
            while(t-->0){
                pw.println(compute(in.readString()));         
                
            }
            
            pw.close();
        }
        public static int compute(String S){
            int counter=1;
            int greater=0;
            int max=counter;
            int l=S.length();
            for(int i=0;i<l;i++){
                char c=S.charAt(i);
                if(c=='<'){
                    if(greater!=0){
                        counter=1;
                        if(greater>=max){
                            max=greater+1;
                        }
                    }
                    counter++;
                    greater=0;
                }
                else if(c=='>'){
                    greater++;
                }                
                if(counter>max){
                    max=counter;
                }
            }
            if(greater!=0){
                if(greater>=max){
                    max=greater+1;
                }
            }
            return max;
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
                public int nextChar(){
                    int c = snext();
                    while (isSpaceChar(c))
                            c = snext();
                    return c;
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
                public char[] nextCharArray(int n){
                    char c[]=new char[n];
                    for(int i=0;i<n;i++){
                        c[i]=(char)nextChar();
                    }
                    return c;
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
                public String readLine(){
                    int c = snext();
                    while (isSpaceChar(c))
                            c = snext();
                    StringBuilder res = new StringBuilder();
                    do {
                            res.appendCodePoint(c);
                            c = snext();
                    } while (c!='\n');
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
 
 