/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Vinay
 */
//999999900000 1000000000000
import java.util.*;
import java.io.*;
class CHEFDIV {
 public static void main(String[] args)
	{
		InputReader in = new InputReader(System.in);
		PrintWriter pw = new PrintWriter(System.out);
                long a=in.nextLong();
                long b=in.nextLong();
                //int m1=(int)(Math.sqrt(b));
                //long a1[]=new long[m1+1];
                
                int t=0;
                int m1 = (int)(Math.sqrt(b))+1;
                int limit = (int)(Math.sqrt(m1))+1;
                long a1[]=new long[m1+1];
                t=simpleSieve(limit, a1); 
                //System.out.println(a1);
                long low  = limit;
                long high1 = 2*limit;
                int l=t;
                while (low < m1)
                {
                    boolean mark[]=new boolean[limit+1];
                    //System.out.println(a1.size());
                    for (int i = 0; i < l; i++)
                    {
                        long loLim = (low/a1[i]);
                        loLim*= a1[i];
                        if (loLim < low)
                            loLim += a1[i];
 
                        for (long j=loLim; j<high1; j+=a1[i]){
                            mark[(int)(j-low)] = true;
                        }
                    }
                    for (long i = low; i<high1; i++)
                        if (!mark[(int)(i - low)]){
                            a1[t]=i;
                            t++;
                        }
                    low  = low + limit;
                    high1 = high1 + limit;
                    if (high1 >= m1){
                        high1 = m1;
                    }
                }  
                if(a==1){
                    a++;
                }
                
                int n=(int)(b-a+1);
                boolean flag[]=new boolean[n];
                long ans=0;
                long temp=0;
                l=t-1;
                //System.out.println(a1[l]);
                long a2=a;
                Factors f[]=new Factors[n];
                for(int i=0;i<t;i++){
                    long loLim=a2/a1[i];
                    loLim*=a1[i];
                    if(loLim<a2){
                        loLim+=a1[i];
                    }
                    for (long j=loLim; j<=b; j+=a1[i]){
                        //System.out.println(j);
                        
                        int k=(int)(j-a);
                        if(j!=a1[i]){
                            if(!flag[k]){
                                //System.out.println(j);
                                flag[k] = true;
                                temp++;
                            }
                        }
 
                        if(f[k]==null){
                            f[k]=new Factors();
                        }
                        f[k].add(a1[i]);
                        //temp++;
                    }                    
                }
                temp=(b-a+1-temp);
                //System.out.println(temp);
                ans+=temp*2;
                //System.out.println(ans);
                //long qw=ans;
                temp=0;
                for(long i=b;i>=a;i--){
                    int j=(int)(i-a);
                    temp=1;
                    if(flag[j]){
                        //System.out.println(i);
                        flag[j]=false;
                        PriorityQueue<Node> q=new PriorityQueue<>();
                        Node n1=null;
                        long m=i;
                        int c=1;    
                        //System.out.print(m+"-> ");
                        c=1;
                        for(int k=0;k<f[j].n;k++){
                            long k1=f[j].a.get(k);
                            n1=new Node(k1);
                            c=1;
                            while(m%k1==0){
                                c++;
                                m/=k1;
                            }
                            //System.out.println("ADDING : "+a1[k]+" "+c);
                            //System.out.print("( "+k1+" , "+c+" ), ");
                            n1.add(c);
                            temp*=c;
                            q.add(n1);                                
 
                        }
                        c=2;
                        if(m>1){
                            n1=new Node(m);
                            n1.add(c);
                            //System.out.println("ADDING : "+m+" "+c);
                            temp*=c;
                            q.add(n1);
                        }
                        //System.out.println(m);
                        //System.out.println("Computing: "+i);
                        c=1;
                        m=i;
                        //ans+=temp;
                        while((n1=q.poll())!=null){
                            //System.out.println("POPING : "+n1.n+" "+n1.d);                            
                            ans+=c*(temp);
                            //System.out.println(i+" "+c+" "+ans);
                            temp/=n1.d;
                            long j1=((m/n1.n)-a);
                            //System.out.println("ALSO CALCULTING: "+(m/n1.n));
                            if(j1>=0){
                                
                                //System.out.println(i+" "+(j1+a));
                                //System.out.println(m+" "+n1.n+" "+j1);
                                j=(int)j1;
                                if(flag[j]){
                                    c++;
                                    //System.out.println((j1+a)+" ");
                                    flag[j]=false;
                                }
                            }
                            m/=n1.n;
                            if(n1.d!=1){
                                n1.add(n1.d-1);
                                temp*=n1.d;
                                q.add(n1);
                            }
                            if(temp==1){
                                //ans+=1;
                                break;
                            }
                        }
                        //System.out.println(" ANS "+(ans-qw));
                        //qw=ans;
                    }
                }
                //System.out.println(r);
                pw.println(ans);
                pw.close();            
        } 
 
        static class Node implements Comparable<Node>{
            long n;
            int d;
            public Node(){
                n=d=0;
            }
            public Node(long n){
                this.n=n;
            }
            public void add(int d){
                this.d=d;
            }
            public int compareTo(Node n){
                return n.d-d;
            }
        }
        static class Factors{
            int n;
            ArrayList<Long> a;
            public Factors(){
                n=0;
                a=new ArrayList<>();
            }
            public void add(Long a){
                this.a.add(a);
                n++;
            }
        }
        public static int simpleSieve(int limit, long prime[]){
            boolean mark[]=new boolean[limit+1]; 
            int t=0;
            for (int p=2; p*p<limit; p++){
                if (!mark[p]){
                    for (int i=p*2; i<limit; i+=p){
                        mark[i] = true;
                    }
                }
            }
            for(int p=2;p<limit;p++){
                if(!mark[p]){
                    prime[t]=p;
                    t++;
                }
            }
            return t;
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
 