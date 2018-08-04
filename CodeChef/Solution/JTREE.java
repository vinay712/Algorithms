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
class JTREE {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
                int n=in.nextInt();
                int m=in.nextInt();
                Node a[]=new Node[n+1];
                Node s[]=new Node[n+1];
                Vertex ve[]=new Vertex[n+1];
                Ticket ti[]=new Ticket[m];
                long cost[]=new long[n+1];
                int hash[]=new int[n+1];
                for(int i=1;i<=n;i++)
                {
                    cost[i]=Long.MAX_VALUE;
                    a[i]=new Node(i);
                    s[i]=a[i];
                }
                for(int i=1;i<n;i++)
                {
                    int x=in.nextInt();
                    int y=in.nextInt();
                    Node n1=new Node(x);
                    s[y].next=n1;
                    s[y]=n1;
                }
                cost[1]=0;
                
                for(int i=1;i<=n;i++)
                {
                    s[i]=a[i];
                }
                
                int rear=1,front=1;
                Vertex v1=new Vertex(1,0,0);
                ve[rear++]=v1;
                int d=0;
                while(rear<=n)
                {
                    int n1=ve[front].v;
                    d=ve[front].l+1;
                    a[n1]=a[n1].next;
                    while(a[n1]!=null)
                    {
                        ve[rear++]=new Vertex(a[n1].d,n1,d);
                        a[n1]=a[n1].next;
                    }
                    front++;
                }    
                for(int i=1;i<=n;i++)
                {
                    hash[ve[i].v]=i;
                    a[i]=s[i];
                }
                for(int i=0;i<m;i++)
                {
                    int v=in.nextInt();
                    int k=in.nextInt();
                    int w=in.nextInt();
                    ti[i]=new Ticket(v, k, w);
                    Node n1=new Node(i);
                    s[v].next=n1;
                    s[v]=n1;
                }                
                int q=in.nextInt();
                int frn[]=new int[q];
                int max=0;
                for(int i=0;i<q;i++)
                {
                    frn[i]=in.nextInt();
                    int tm=hash[frn[i]];
                    if(ve[tm].l>max)
                    {
                        max=ve[tm].l;
                    }
                }
                long costp[]=new long[max+1];
                costp[0]=Long.MAX_VALUE;
                int bp[]=new int[n+1];
                for(int i=2;i<=n && ve[i].l<=max;i++)
                {
                    int v=ve[i].v;
                    Node n1=a[v].next;
                    bp[v]=0;
                    while(n1!=null)
                    {
                        int temp1=ve[i].l;
                        int temp2=ti[n1.d].k;
                        long c=ti[n1.d].w;
                        if(c>=cost[v])
                        {
                            n1=n1.next;
                            continue;
                        }
                        if(temp2>=temp1)
                        {                            
                            if(cost[v]>c)
                            {
                                cost[v]=c;
                                bp[v]=1;
                            }
                        }
                        else
                        {
                            int temp=ve[i].p;
                            long c1=Long.MAX_VALUE;
                            int tm=0;
                            while(temp2>0 && temp>1)
                            {
                                temp2--;
                                int diff=0;
                                if(bp[temp]>1 && (diff=ve[hash[temp]].l-ve[hash[bp[temp]]].l)<=temp2)
                                {
                                        temp=bp[temp];
                                        temp2-=diff;
                                }
                                if(c1>cost[temp])
                                {
                                    c1=cost[temp];
                                    tm=temp;
                                }  
                                temp=ve[hash[temp]].p;                              
                            } 
                            if(c1!=Long.MAX_VALUE && cost[v]>c+c1)
                            {
                                cost[v]=c+c1;
                                bp[v]=tm;
                            }
                        }
                        n1=n1.next;
                    }
                }
                for(int i=0;i<q;i++)
                {
                    pw.println(cost[frn[i]]);
                }
                pw.close();
	}
        static class Node
        {
            int d;
            Node next;
            public Node(int n)
            {
                d=n;
                next=null;
            }
        }
        static class Vertex
        {
            int p,l,v;
            Vertex(int v,int p,int l)
            {
                this.v=v;
                this.p=p;
                this.l=l;
            }
        }
        static class Ticket
        {
            int v,k,w;
            public Ticket(int v,int k,int w) 
            {
                this.v=v;
                this.k=k;
                this.w=w;
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
 