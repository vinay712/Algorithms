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
class DEVMAC {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		int t=in.nextInt();
                final int limit=1000001;
                int s[]=new int[limit];
                s[1]=1;
                for(int i=2;i<limit;i+=2)
                {
                    s[i]=2;
                }
                for(int i=3;i<limit;i+=2)
                {
                    if(s[i]==0)
                    {
                        s[i]=i;
                        for(int j=i;(long)(i)*(long)(j)<limit;j+=2)
                        {
                            
                            if(s[i*j]==0)
                            {
                                s[i*j]=i;
                            }
                        }
                    }
                }
		while(t-->0)
		{
			int n=in.nextInt();
                        int m=in.nextInt();
                        int a[]=in.nextIntArray(n);
                        int a1[]=new int[n];
                        for(int i=0;i<n;i++)
                        {
                            a1[i]=s[a[i]];
                            a[i]/=s[a[i]];
                        }
                        SegmentTree st=new SegmentTree(a1, n,s,a);
                        //st.display();
                        for(int i=0;i<m;i++)
                        {
                            int op=in.nextInt();
                            int l=in.nextInt();
                            int r=in.nextInt();
                            switch(op)
                            {
                                case 1:System.out.print(st.getMax(l-1, r-1)+" ");
                                        break;
                                case 0: 
                                            //System.out.println(l1+" "+r1);
                                            st.updateValue(l-1, r-1);
                                        //st.display();
                                        
                                        break;
                            }
                            
                        }
                        System.out.println();
     		}
                pw.close();
	} 
        static class SegmentTree 
        {
            int st[];
            int a1[];
            int n;
            int s[];
            int a[];
            SegmentTree(int arr[], int n,int s[],int a[])
            {
                int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
                int max_size = 2 * (int) Math.pow(2, x) - 1;
                st = new int[max_size]; 
                a1=arr;
                this.s=s;
                this.a=a;
                this.n=n;
                constructSTUtil(0, n - 1, 0);
            }
            int constructSTUtil(int ss, int se, int si)
            {
                if (ss == se) 
                {
                    st[si] = a1[ss];
                    return a1[ss];
                }
                int mid =(ss+se)/2;
                int max1=constructSTUtil(ss, mid, si * 2 + 1);
                int max2=constructSTUtil(mid + 1, se, si * 2 + 2);
                st[si] =max1>max2?max1:max2;
                return st[si];
            }
            int getMax(int qs, int qe)
            {
                return getMaxUtil(0, n - 1, qs, qe, 0);
            }
            int getMaxUtil(int ss, int se, int qs, int qe, int si)
            {
                if(st[si]==1)
                {
                    return 1;
                }
                if (qs <= ss && qe >= se)
                {
                    return st[si];
                }
                if (se < qs || ss > qe)
                {
                    return 0;
                }
                int mid = (ss+se)/2;
                int max1=getMaxUtil(ss, mid, qs, qe, 2 * si + 1);
                int max2=getMaxUtil(mid + 1, se, qs, qe, 2 * si + 2);
                return max1>max2?max1:max2;
            }
            void updateValue(int qs,int qe)
            {
                updateValueUtil(qs,qe,0,n-1,0);
            }
            int updateValueUtil(int qs, int qe,int ss,int se,int si)
            {
                if(st[si]==1)
                {
                    return 1;
                }
                if(se < qs || ss > qe)
                {
                    return st[si];
                }
                if(ss==se)
                {
                    a1[ss]=s[a[ss]];
                    a[ss]/=s[a[ss]];
                    st[si]=a1[ss];
                    return a1[ss];
                }
                int mid=(ss+se)/2;
                int max1=updateValueUtil(qs,qe,ss, mid, si * 2 + 1);
                int max2=updateValueUtil(qs,qe,mid + 1, se, si * 2 + 2);
                st[si] =max1>max2?max1:max2;
                return st[si];
            }
            void display()
            {
                for(int i=0;i<n;i++)
                {
                    System.out.print(a1[i]+"\t");
                }
                System.out.println();
                for(int i=0;i<st.length;i++)
                {
                    System.out.print(st[i]+"\t");
                }
                System.out.println();
            }
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