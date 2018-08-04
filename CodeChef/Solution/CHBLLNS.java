import java.io.*;
class Chbllns
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		while(t-->0)
		{
			String line=br.readLine();
			String S[]=line.split(" ");
			long r1=0,g1=0,b1=0;
			long r=Long.parseLong(S[0]);
			long g=Long.parseLong(S[1]);
			long b=Long.parseLong(S[2]);
			long k=Long.parseLong(br.readLine());
			int d=0;
			r1=(k>r)?r:(k-1);
			g1=(k>g)?g:(k-1);
			b1=(k>b)?b:(k-1);
			System.out.println(g1+r1+b1+1);
		}
	}
} 