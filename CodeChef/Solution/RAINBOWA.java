
 
import java.util.*;
import java.io.*;
class RAINBOWA {
    public static void main(String[] args)
	{
            try{
            solve();            
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
	} 
        public static void solve() throws Exception{            
            InputReader in = new InputReader(System.in);
            PrintWriter pw = new PrintWriter(System.out);
            int t=in.nextInt();
            while(t-->0){
                int n=in.nextInt();
                int a[]=in.nextIntArray(n);
                pw.println(compute(a,n)?"yes":"no");
            }
            pw.close();
        }
        public static boolean compute(int a[],int n){
            for(int i=0;i<n;i++){
                if(a[i]>7){
                    return false;
                }
            }            
            int f[]=new int[7];
            int c=0;
            int k=-1,i=0;
            for(;i<n && a[i]<7 ;i++){
                if(c!=a[i]){
                    c++;
                    if(c!=a[i]){
                        return false;
                    }
                    k++;
                }
                f[k]++;
            }
            k=0;
            while(i<n && a[i]==7){
                k++;
                i++;
            }
            f[6]=k;
            if(k==0){
                return false;
            }
            k=6;c=7;
            for(;i<n;i++){
                if(c!=a[i]){
                    c--;
                    if(c!=a[i]){
                        return false;
                    }
                    k--;
                }
                f[k]--;
            }
            for(i=0;i<6;i++){
                if(f[i]!=0){
                    return false;                    
                }
            }
            return true;
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