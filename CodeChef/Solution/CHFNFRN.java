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
class CHFNFRN {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t=in.nextInt();
		while(t-->0)
		{
			int n=in.nextInt();
                        int m=in.nextInt();
                        int a[][]=new int[n][n];
                        int s1[]=new int[n];
                        int s2[]=new int[n];
                        int p[]=new int[n];
                        int temp=n;
                        for(int i=0;i<m;i++)
                        {
                            int x=in.nextInt();
                            int y=in.nextInt();
                            if(x!=y)
                            {
                                a[x-1][y-1]=1;
                                a[y-1][x-1]=1;
                            }
                        }
                        int s[]=new int[3];
                        boolean flag[]=new boolean[3];
                        s[1]=-1;
                        s[2]=-1;
                        outer:for(int i=0;i<n;i++)
                        {
                            for(int j=0;j<i;j++)
                            {
                                if(a[i][j]==0)
                                {
                                    s[1]++;
                                    s[2]++;
                                    s1[s[1]]=i;
                                    s2[s[2]]=j;
                                    p[i]=1;
                                    p[j]=1;
                                    break outer;
                                }
                            }
                        }
                        if(s[1]==-1 && s[2]==-1)
                        {
                            pw.println("YES");
                            continue;
                        }
                        while(temp-->0 && (s[1]+s[2]+2!=n))
                        {
                            for(int i=0;i<n;i++)
                            {
                                if(p[i]==1)
                                {
                                    continue;
                                }
                                flag[1]=flag[2]=true;
                                for(int j=0;j<=s[1];j++)
                                {
                                    if(a[i][s1[j]]==0)
                                    {
                                        flag[1]=false;
                                    }
                                }
                                for(int j=0;j<=s[2];j++)
                                {
                                    if(a[i][s2[j]]==0)
                                    {
                                        flag[2]=false;
                                    }
                                }
                                if(flag[1] && flag[2])
                                {
                                    if(temp<n/2)
                                    {
                                        s[1]++;
                                        s1[s[1]]=i;
                                        p[i]=1;
                                        temp=n;
                                    }
                                }
                                else if(flag[1])
                                {
                                    s[1]++;
                                    s1[s[1]]=i;
                                    p[i]=1;
                                }
                                else if(flag[2])
                                {
                                    s[2]++;
                                    s2[s[2]]=i;
                                    p[i]=1;
                                }
                            }
                        }
                        if(s[1]+s[2]+2==n)
                        {
                            pw.println("YES");
                        }
                        else
                        {
                            pw.println("NO");
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
 
 