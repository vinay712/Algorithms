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
class RESCALC {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t=in.nextInt();
		while(t-->0)
		{
			int n=in.nextInt();
                        int max=0,p=-1;
                        for(int i=0;i<n;i++)
                        {
                            int c=in.nextInt();
                            int s=c;
                            int d=0;
                            int type[]=new int[6];
                            for(int j=0;j<c;j++)
                            {
                                type[in.nextInt()-1]++;
                            }
                            Arrays.sort(type);
                            s+=type[0]*4;
                            d=type[0];
                            if(type[1]>d)
                            {
                                s+=(type[1]-d)*2;
                                d=type[1];
                            }
                            if(type[2]>d)
                            {
                                s+=(type[2]-d);
                            }
                            if(s==max)
                            {
                                p=-1;
                            }
                            else if(s>max)
                            {
                                max=s;
                                p=i;
                            }
                        }
                        switch (p) {
                            case -1:
                                pw.println("tie");
                                break;
                            case 0:
                                pw.println("chef");
                                break;
                            default:
                                pw.println(p+1);
                                break;
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
 