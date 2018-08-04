/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author Vinay
 */
import java.awt.Point;
import java.util.*;
import java.io.*;
class RNDGRID {
 public static void main(String[] args)
	{
            InputReader in = new InputReader(System.in);
            PrintWriter pw = new PrintWriter(System.out);
            int t=in.nextInt();
            while(t-->0){
                int l=in.nextInt();
                int n=in.nextInt();
                char s[]=in.nextCharArray(l);
                char c[][]=new char[n][];
                for(int i=0;i<n;i++){
                    c[i]=in.nextCharArray(n);
                }
                int minx=0,maxx=0;
                int miny=0,maxy=0;
                int dx[]=new int[l];
                int dy[]=new int[l];
                int index[]=new int[l+1];
                int score[]=new int[l+1];
                int k=0;
                int ans=0;
                for(int i=0;i<s.length;i++ ){
                    if(s[i]=='L'){
                        dx[i]=-1;
                        dy[i]=0;
                    }
                    else if(s[i]=='R'){
                        dx[i]=1;
                        dy[i]=0;
                    }
                    else if(s[i]=='U'){
                        dx[i]=0;
                        dy[i]=-1;
                    }
                    else if(s[i]=='D'){
                        dx[i]=0;
                        dy[i]=1;
                    }
                    if(i>0){
                        dx[i]+=dx[i-1];
                        dy[i]+=dy[i-1];
                    }
                    maxx=dx[i]>maxx?dx[i]:maxx;
                    minx=dx[i]<minx?dx[i]:minx;
                    maxy=dy[i]>maxy?dy[i]:maxy;
                    miny=dy[i]<miny?dy[i]:miny;
                }
                //System.out.println(minx+" "+maxx);
                //System.out.println(miny+" "+miny);
                //String S="";
                //S+=s[0];
                boolean temp[][]=new boolean[maxy-miny+1][maxx-minx+1];
                int x=-minx;
                int y=-miny;
                temp[y][x]=true;
                for(int i=0;i<l;i++){
                    score[i]=i;
                    x=dx[i]-minx;
                    y=dy[i]-miny;
                    if(!temp[y][x]){
                        index[k++]=i;
                        //S+=s[i];
                        temp[y][x]=true;
                    }
                }
                score[l]=l;
                index[k++]=l;
                //System.out.println(S);
                /*for(int i=0;i<k-1;i++){
                    int j=index[i];
                    System.out.println(dx[j]+" "+dy[j]+" "+score[j]);
                }*/
                
                //int fscore[]=new int[l+1];
                ArrayList<Point> a1=new ArrayList<>();
                ArrayList<Point> a2=new ArrayList<>();
                int fin[][]=new int[n][n];
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        if(c[i][j]=='.'){
                            //fin[i][j]=-1;
                            //System.out.println(i+" "+j);
                            a1.add(new Point(i,j));                            
                        }
                        else{
                            a2.add(new Point(i,j));
                        }
                    }
                }
                if(a2.size()!=0 && a1.size()<(2*a2.size())){
                //if(false){
                    Iterator<Point> it=a1.iterator();
                    while(it.hasNext()){
                        Point p1=it.next();
                        int i=p1.x;
                        int j=p1.y;
                        for(int p=0;p<k-1;p++){
                            int d=index[p];
                            int sc=index[p+1];
                            x=j+dx[d];
                            y=i+dy[d];
                            //System.out.println(sc+" "+x+" "+y);
                            if(x>=0 && x<n && y>=0 && y<n && c[y][x]=='.'){
                                fin[i][j]=sc;
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
                else{
                    for(int i=0;i<n;i++){
                        for(int j=0;j<n;j++){
                            fin[i][j]=l;
                        }
                    }
                    for(int i=0;i<k-1;i++){
                        int j=index[i];
                        //int x1=n;
                        for(int p=0;p<n;p++){
                            x=n-dx[j];
                            if(x<n && x>=0 && c[p][x]=='.'){
                                fin[p][x]=Math.min(fin[p][x], score[j]);
                            }
                            x=-1-dx[j];
                            if(x<n && x>=0 && c[p][x]=='.'){
                                fin[p][x]=Math.min(fin[p][x], score[j]);
                            }
                            x=n-dy[j];
                            if(x<n && x>=0 && c[x][p]=='.'){
                                fin[x][p]=Math.min(fin[x][p], score[j]);
                            }
                            x=-1-dy[j];
                            if(x<n && x>=0 && c[x][p]=='.'){
                                fin[x][p]=Math.min(fin[x][p], score[j]);
                            }  
                        }
                    }
                    Iterator<Point> it=a2.iterator();
                    while(it.hasNext()){
                        Point p1=it.next();
                        int i=p1.x;
                        int j=p1.y;
                        for(int p=0;p<k-1;p++){
                            int d=index[p];
                            int sc=score[d];
                            x=j-dx[d];
                            y=i-dy[d];
                            //System.out.println(sc+" "+x+" "+y);
                            if(x>=0 && x<n && y>=0 && y<n && c[y][x]=='.'){
                                fin[y][x]=Math.min(fin[y][x], sc);
                            }
                        }
                    }
                }
                /*ArrayList<Point> a1=new ArrayList<>();
                ArrayList<Point> a2=new ArrayList<>();
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        if(c[i][j]=='.'){
                            a1.add(new Point(j,i));
                        }
                    }
                }
                int i,j;
                int sc[][]=new int[n][n];
                for(i=0;i<k-1 && a1.size()!=0;i++){
                    Iterator<Point> it=a1.iterator();
                    j=index[i];
                    while(it.hasNext()){
                        Point p=it.next();
                        x=p.x+dx[j];
                        y=p.y+dy[j];
                        //System.out.println("Move: ("+p.x+","+p.y+") -> ("+x+","+y+")");
                        if(x>=0 && x<n && y>=0 && y<n && c[y][x]=='.'){
                            sc[p.y][p.x]=score[index[i+1]];
                            a2.add(p);
                        }
                    }
                    int diff=a1.size()-a2.size();
                    //System.out.println(a1.size()+" "+a2.size()+" "+diff+" "+score[j]);
                    if(diff%2!=0){
                        ans^=score[j];
                    }
                    a1.clear();
                    ArrayList<Point> tem=a1;
                    a1=a2;
                    a2=tem; 
                }
                if(a1.size()%2!=0){
                    ans^=score[index[i]];
                    //System.out.println(score[index[i]]);
                }
                for(i=0;i<n;i++){
                    for(j=0;j<n;j++){
                        System.out.print(sc[i][j]+"\t");
                    }
                    System.out.println();
                }*/
                //System.out.println(a1.size()+" "+score[index[i]]);
                
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        //System.out.print(fin[i][j]+"\t");
                        if(c[i][j]=='.'){
                            ans^=fin[i][j];
                        }
                    }
                    //System.out.println();
                }
                pw.println(ans);
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
                public int nextChar(){
                    int c = snext();
                    while (isSpaceChar(c))
                            c = snext();
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
                public char[] nextCharArray(int n){
                    char c[]=new char[n];
                    for(int i=0;i<n;i++){
                        c[i]=(char)nextChar();
                    }
                    return c;
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
 