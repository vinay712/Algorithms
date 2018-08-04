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
class IPCTRAIN {
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
                int n=in.nextInt();
                int d=in.nextInt();
                Trainer tr[]=new Trainer[n];
                for(int i=0;i<n;i++){
                    tr[i]=new Trainer(in.nextInt(), in.nextInt(), in.nextInt());
                }
                pw.println(compute(tr,d)); 
            }
            pw.close();
        }
        public static long compute(Trainer t[],int d){
            long ans=0;
            Arrays.sort(t);
            int a[]=new int[d];
            int c=0;
            long sum=0;
            PriorityQueue<Trainer> queue=new PriorityQueue<>(new Comparator<Trainer>() {
                public int compare(Trainer o1, Trainer o2) {
                    return o2.s-o1.s;
                }
            });
            for(int i=0;i<d;i++){
                while(c<t.length && t[c].d==i){
                    queue.add(t[c]);
                    sum+=(long)(t[c].s)*(long)(t[c].t);
                    //System.out.println(i+"\t"+t[c]);
                    c++;
                }
                Trainer n=queue.peek();
                //System.out.println(n);
                if(n!=null){
                    n.t--;
                    if(n.t==0){
                        queue.poll();
                    }
                    a[i]=n.s;
                }
                ans+=(long)a[i];
            }
            
            return sum-ans;           
        }
        static class Trainer implements Comparable<Trainer>{
            int d,t,s;
            public Trainer(int d,int t,int s){
                this.d=d-1;
                this.t=t;
                this.s=s;
            }
            public int compareTo(Trainer t){
                return this.d-t.d;
            }
            public String toString(){
                return d+" "+t+" "+s;
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
 