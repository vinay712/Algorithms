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
class SMARKET {
 public static void main(String[] args)
	{
            InputReader in = new InputReader(System.in);
            PrintWriter pw = new PrintWriter(System.out);
            int t=in.nextInt();
            while(t-->0){
                int n=in.nextInt();
                int q=in.nextInt();
                int a[]=in.nextIntArray(n);
                Node s[]=new Node[n];
                int k=0;
                int temp=a[0],st=0;
                s[k++]=new Node(st);
                for(int i=1;i<n;i++){
                    if(a[i]==temp){
                        s[k-1].add();
                    }
                    else{
                        s[k++]=new Node(i);
                        temp=a[i];                            
                    }
                }
                
                /*for(int i=0;i<n;i++){
                    System.out.println(start[i]+" "+end[i]);
                }*/
                Node[] seg=construct(s, k);
                /*for(int i=0;i<seg.length;i++){
                    System.out.println(seg[i]);
                }*/
                for(int i=0;i<q;i++){
                    int l=in.nextInt()-1;
                    int r=in.nextInt()-1;
                    int c=in.nextInt();
                    if(l==r && c==1){
                        pw.println("1");
                    }
                    else{
                        int ans=0;
                        ans+=compute(seg, 0, k-1, 0, l, r, c);
                        pw.println(ans);
                    }
                }
            }                        
            pw.close();
	} 
        public static int compute(Node seg[],int ss,int se, int si,int qs,int qe,int k){
            //System.out.println(seg[si].ss+" "+seg[si].se+" "+seg[si].max+" "+seg[si].min+" "+k);
            if((seg[si].se-qs+1)<k || (qe-seg[si].ss+1)<k){
                return 0;
            }
            else if(seg[si].ss>=qs && seg[si].se<=qe && k<=seg[si].min){
                //System.out.println("ALL");
                return seg[si].c;
            }
            else if((k>seg[si].max) || (seg[si].ss>qe) || (seg[si].se<qs)){
                //System.out.println("NONE: ");
                return 0;
            }
            else if(ss==se){
                int ts=seg[si].ss;
                int te=seg[si].se;
                ts= (qs>=ts)?qs:ts;
                te= (qe<=te)?qe:te;
                //System.out.println("NODE: ");
                if((te-ts+1)>=k){
                    return seg[si].c;
                }
                else{
                    return 0;
                }
            }
            int m=(ss+se)/2;
            return compute(seg,ss,m,2*si+1,qs,qe,k)+compute(seg,m+1,se,2*si+2,qs,qe,k);
            
        }
        public static Node[] construct(Node n[],int k){
            int x = (int) (Math.ceil(Math.log(k) / Math.log(2))); 
        
            int size = 2 * (1<<x) - 1;
 
            Node seg[]=new Node[size];
            constructUtil(n, seg, 0, k-1, 0);
            return seg;
        }
        public static Node constructUtil(Node n[],Node seg[],int ss,int se,int si){
            if(ss==se){
                seg[si]=n[ss];
                //System.out.println(seg[si]);
                return seg[si];
            }
            int m=(se+ss)/2;
            Node n1=constructUtil(n,  seg, ss, m, 2*si+1);
            Node n2=constructUtil(n,  seg, m+1, se, 2*si+2);
            int max=(n1.max>n2.max)?n1.max:n2.max;
            int min=(n1.min<n2.min)?n1.min:n2.min;
            //System.out.println(min+" "+max);
            seg[si]=new Node(n1.ss,n2.se,n1.c+n2.c,min,max);
            //System.out.println(seg[si]);
            return seg[si];
        }
        static class Node {
            int ss;
            int se;
            int c;
            int min;
            int max;
            public Node(){
                ss=se=-1;
                c=min=0;
                max=0;
            }
            public Node(int ss){
                this.ss=ss;
                this.se=ss;
                c=1;
                min=1;
                max=1;
            }            
            public Node(int ss,int se,int c,int min,int max){
                this.ss=ss;
                this.se=se;
                this.c=c;
                this.min=min;
                this.max=max;
            }
            public void add(){
                this.se++;
                min++;
                max++;
            }
            public String toString(){
                return ss+" "+se+" : "+c+" "+min+" "+max;
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
 
 