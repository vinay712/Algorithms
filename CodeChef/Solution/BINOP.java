/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Vinay
 */
import java.io.*;
import java.util.*;
class BINOP {
        public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t=in.nextInt();
		while(t-->0)
		{
			String S=in.readString();
                        char a[]=S.toCharArray();
                        S=in.readString();
                        char b[]=S.toCharArray();
                        int l=a.length;
                        int zs=0,os=0,zd=0,od=0,ans=0;
                        for(int i=0;i<l;i++)
                        {
                            int ca=a[i]-'0';
                            int cb=b[i]-'0';
                            if(ca==0)
                            {
                                if(cb==0)
                                {
                                    zs++;
                                }
                                else if(cb==1)
                                {
                                    zd++;
                                }
                            }
                            else if(ca==1)
                            {
                                if(cb==0)
                                {
                                    od++;
                                }
                                else if(cb==1)
                                {
                                    os++;
                                }
                            }
                        }
                        boolean flag=false;
                        ans=(zd>=od)?od:zd;
                        if(zd>od)
                        {
                            if(os>0 || od>0)
                            {
                                ans+=zd-od;
                                flag=true;
                            }
                        }
                        else
                        {
                            if(zs>0 || zd>0)
                            {
                                ans+=od-zd;
                                flag=true;
                            }
                        }
                        if(flag)
                        {
                            pw.println("Lucky Chef\n"+ans);
                        }
                        else
                        {
                            pw.println("Unlucky Chef");
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