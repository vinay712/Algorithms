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
class GIFTCHEF {
    public static void main(String[] args)
	{
            InputReader in = new InputReader(System.in);
            PrintWriter pw = new PrintWriter(System.out);
            int t=in.nextInt();
            final long mod=1000000007;
            while(t-->0){
                char s[]=in.readString().toCharArray();
                char p[]=in.readString().toCharArray();
                int l=p.length;
                int a[]=new int[s.length];
                Temp te=new Temp();
                find(s,p,a,te);
                long comp[]=new long[s.length+1];
                long k[]=new long[s.length+1];
                /*long ans=0;
                for(int i=0;i<s.length;i++){
                    System.out.print(a[i]+"\t");
                }*/
                //System.out.println();
                for(int i=te.end;i>=te.start;i--){
                    if(a[i]==1){
                        //System.out.println(i+"\t"+l+"\t"+s.length);
                        if(i+l>=s.length){
                            comp[i]=1;
                        }
                        else{
                            comp[i]=(k[i+l]+1)%mod;
                        }
                        k[i]=comp[i];
                    }
                    
                    k[i]+=k[i+1];
                    k[i]%=mod;
                }
                
                /*for(int i=te.start;i<=te.end;i++){
                    if(a[i]==1){
                        ans+=comp[i];
                    }
                }                  
                for(int i=0;i<=s.length;i++){
                    System.out.print(k[i]+"\t");
                }    
                System.out.println();
                
                for(int i=0;i<=s.length;i++){
                    System.out.print(comp[i]+"\t");
                }
                System.out.println();*/
                System.out.println(k[te.start]%mod);
            }
            pw.close();
        }
        public static int[] precompute(char a[])
        {
            int a1[]=new int[a.length];
            a1[0]=-1;
            int k=-1;
            for(int i=1;i<a1.length;i++)
            {
                while(k>=0 && a[k+1]!=a[i])
                {
                    k=a1[k];
                }
                if(a[k+1]==a[i])
                {
                    k++;
                }
                a1[i]=k;
            }
            return a1;
        }
        public static void find(char t[],char p[],int a[],Temp te)
        {
            int m=p.length;
            int n=t.length;
            int pi[]=precompute(p);
            int q=-1;
            for(int i=0;i<n;i++)
            {
                while(q>=0 && p[q+1]!=t[i])
                {
                    q=pi[q];
                }
                if(p[q+1]==t[i])
                {
                    q++;
                }
                if(q==m-1)
                {
                    //System.out.println(i-m+1);
                    a[i-m+1]=1;
                    te.count++;
                    te.end=i-m+1;
                    if(!te.flag){
                        te.flag=true;
                        te.start=i-m+1;
                    }
                    q=pi[q];
                }
            }
            
        }
        static class Temp{
            int start;
            int end;
            int count;
            boolean flag;
            Temp(){
                start=end=count=0;
                flag=false;
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