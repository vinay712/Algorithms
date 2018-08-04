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
class CLIQUED {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
                int t=in.nextInt();
                while(t-->0){
                    int n=in.nextInt();
                    int k=in.nextInt();
                    int x=in.nextInt();
                    int m=in.nextInt();
                    int s=in.nextInt()-1;
                    PriorityQueue<Node> que=new PriorityQueue<>();
                    long d[]=new long[n];
                    Vertex v[]=new Vertex[n];
                    boolean flag[]=new boolean[n];
                    for(int i=0;i<n;i++){
                        v[i]=new Vertex();
                        d[i]=Long.MAX_VALUE;
                        flag[i]=false;
                    }
                    for(int i=0;i<m;i++){
                        int s1=in.nextInt()-1;
                        int d1=in.nextInt()-1;
                        int w=in.nextInt();
                        v[s1].a.add(new Node(d1,w));
                        v[d1].a.add(new Node(s1,w));
                    }
                    d[s]=0;
                    que.add(new Node(s,d[s]));
                    Node n1;
                    boolean temp=false;
                    while((n1=que.poll())!=null){
                        int v1=n1.n;
                        flag[v1]=true;
                        Iterator<Node> it=v[v1].a.iterator();
                        while(it.hasNext()){
                            Node n2=it.next();
                            if(!flag[n2.n] && (d[n2.n]>(n2.w+d[n1.n]) || d[n2.n]==Long.MAX_VALUE)){
                                d[n2.n]=n2.w+d[n1.n];
                                que.add(new Node(n2.n,d[n2.n]));
                            }
                        }
                        if(!temp && v1<k){
                            temp=true;
                            for(int i=0;i<k;i++){
                                if(!flag[i] && (d[i]>x+d[v1] || d[i]==Long.MAX_VALUE)){
                                    d[i]=x+d[v1];
                                    que.add(new Node(i,d[i]));
                                }
                            }
                            
                        }
                    }
                    for(int i=0;i<n;i++){
                        System.out.print(d[i]+" ");
                    }
                    System.out.println();
                }                        
                pw.close();
	} 
        static class Node implements Comparable<Node>{
            int n;
            long w;
            public Node(){
                w=n=0;
            }
            public Node(int n,long w){
                this.n=n;
                this.w=w;
            }
            public int compareTo(Node n){
                long d=w-n.w;
                if(d<0){
                    return -1;
                }
                return 1;
            }
        }
        static class Vertex{
            ArrayList<Node> a;
            int n;
            public Vertex(){
                a=new ArrayList<>();
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