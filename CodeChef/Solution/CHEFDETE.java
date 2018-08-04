import java.io.*;
class Chefdete
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n =Integer.parseInt(br.readLine());
		String R[]=(br.readLine()).split(" ");
		int a[]=new int[n+1];
		for(int i=0;i<n;i++)
		{
			a[Integer.parseInt(R[i])]++;
		}
		for(int i=1;i<=n;i++)
		{
			if(a[i]==0)
			{
				System.out.print(i+" ");
			}
		}
		System.out.println();
	}
} 