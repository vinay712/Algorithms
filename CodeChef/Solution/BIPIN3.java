import java.io.*;
class Bipin3
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t1=Integer.parseInt(br.readLine());
		final int mod=1000000007;
		while(t1-->0)
		{
			String line=br.readLine();
			String S[]=line.split(" ");
			long n=Long.parseLong(S[0]);
			long k=Long.parseLong(S[1]);
			long ans=k;
			long i;
			k--;
			long d=1;
			int m=1024;
			long t=(n-1)%m;
			n--;
			for(i=1;i<=(n-t)/m;i++)
			{
				d=(d*k)%mod;
			}
			for(i=0;i<m;i++)
			{
				ans=(ans*d)%mod;
			}
			for(i=0;i<t;i++)
			{
				ans=(ans*k)%mod;
			}
			System.out.println(ans);
		}
	}
} 