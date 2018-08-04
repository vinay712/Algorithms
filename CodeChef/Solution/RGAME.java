import java.io.*;
class Rupsa
{
	public static void main(String args[])
	{
		try{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int test=Integer.parseInt(br.readLine());
		final int mod=1000000007;
		while(test-->0)
		{
			int n=Integer.parseInt(br.readLine());
			String line=br.readLine();
			String St[]=line .split(" ");
			long a=Long.parseLong(St[0]);
			long b=Long.parseLong(St[1]);
			long t=2;
			long s=t*((a*b)%mod)%mod;
			long p=t*((a+b)%mod)%mod;;
			for(int i=2;i<=n;i++)
			{
				long c=Long.parseLong(St[i]);
				t=(t*2)%mod;
				s=((c*p)%mod+(s*2)%mod)%mod;
				p=(p+(c*t)%mod)%mod;
			}
			System.out.println(s);
		}
		}
		catch(Exception e){}
	}
} 
} 