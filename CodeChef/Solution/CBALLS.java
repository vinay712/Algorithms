import java.io.*;
class CBalls
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		while(t-->0)
		{
			int n=Integer.parseInt(br.readLine());
			String line=br.readLine();
			String st[]=line.split(" ");
			int a[]=new int[n];
			int m=0,i,j;
			int start=0;
			for(i=0;i<n;i++)
			{
				a[i]=Integer.parseInt(st[i]);
				if(a[i]>m)
				{
					m=a[i];
				}
			}
			int min=Integer.MAX_VALUE;
			int s;
			for(i=2;i<=m;i++)
			{
				if(isPrime(i))
				{
					s=0;
					int d=a[0]%i;
					int temp;
					if(d!=0)
					{
						s+=i-d;
						temp=a[0]+i-d;
					}
					else
					{
						temp=a[0];
					}
					for(j=1;j<n;j++)
					{
						if(a[j]<=temp)
						{
							s+=temp-a[j];
						}
						else
						{
							d=a[j]%i;
							if(d!=0)
							{
								s+=i-d;
								temp=a[j]+i-d;
							}
							else
							{
								temp=a[j];
							}						
						}
						if(s<0)
						{
							break;
						}
					}
					if(s<min && j==n)
					{
						min=s;
					}
				}
			}
			if(m==1)
			{
				min=n;
			}
			System.out.println(min);
		}
	}
	public static boolean isPrime(int n)
	{
		for(int i=2;i*i<=n;i++)
		{
			if(n%i==0)
			{
				return false;
			}
		}
		return true;
	}
}