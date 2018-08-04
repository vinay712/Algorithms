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
class FENWITER {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
                int t=in.nextInt();
                while(t-->0)
                {
                    
                    try{
                    String l1=in.readString();
                    String l2=in.readString();
                    String l3=in.readString();
                    int n=in.nextInt();
                    int ans=1;
                    int d=0;
                    int d1=0;
                    boolean temp;
                    boolean flag=false;
                    for(int i=l3.length()-1;i>=0;i--)
                    {
                        char c=l3.charAt(i);
                        if(c=='0')
                        {
                            flag=true;
                        }
                        else if(flag && c=='1')
                        {
                            ans++;
                        }
                    }
                    temp=flag;
                    for(int i=l2.length()-1;i>=0;i--)
                    {
                        char c=l2.charAt(i);
                        if(c=='0')
                        {
                            flag=true;
                        }
                        else if(flag && c=='1')
                        {
                            d++;
                        }
                        if(c=='1')
                        {
                            d1++;
                        }
                    }
                    if(temp)
                    {
                        ans+=d1*n;                        
                    }
                    else if(flag)
                    {
                        ans+=d+d1*(n-1);
                    }
                    for(int i=l1.length()-1;i>=0;i--)
                    {
                        char c=l1.charAt(i);
                        if(c=='0')
                        {
                            flag=true;
                        }
                        else if(flag && c=='1')
                        {
                            ans++;
                        }
                    }
                    pw.println(ans);
                    }
                    catch(Exception e){}
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