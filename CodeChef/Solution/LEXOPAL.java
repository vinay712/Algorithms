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
class LEXOPAL {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t=in.nextInt();
		while(t-->0)
		{
			char c[]=in.readString().toCharArray();
                        int l=c.length;
                        String S="";
                        char ans[]=new char[l];
                        boolean flag=false;
                        for(int i=0;i<l/2;i++)
                        {
                            ans[i]=c[i];
                            ans[l-1-i]=c[l-1-i];
                            if(c[i]!=c[l-1-i])
                            {
                                if(c[i]=='.' && c[l-1-i]!='.' )
                                {
                                    ans[i]=c[l-1-i];
                                }
                                else if(c[l-1-i]=='.' && c[i]!='.')
                                {
                                    ans[l-1-i]=c[i];
                                }
                                else
                                {
                                    flag=true;
                                }
                            }
                            else
                            {
                                if(c[i]=='.')
                                {
                                    ans[i]=ans[l-1-i]='a';
                                }
                            }
                        }
                        if(flag)
                        {
                            pw.println("-1");
                        }
                        else
                        {
                            if(l%2==1)
                            {
                                if(c[l/2]=='.')
                                {
                                    ans[l/2]='a';
                                }
                                else
                                {
                                    ans[l/2]=c[l/2];
                                }
                            }
                            for(int i=0;i<l;i++)
                            {
                                S+=ans[i];
                            }
                            pw.println(S);
                        }
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
 