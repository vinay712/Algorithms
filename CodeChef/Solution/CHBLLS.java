import java.io.*;
class Chballs
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int ans=0;
		System.out.println("1\n2 1 2\n2 3 4");
		int c=Integer.parseInt(br.readLine());
		if(c==0)
		{
			ans=5;
		}
		else if(c>0)
		{
			System.out.println("1\n1 1\n1 2");
			c=Integer.parseInt(br.readLine());
			if(c>0)
			{
				ans=1;
			}
			else if(c<0)
			{
				ans=2;
			}
		}
		else if(c<0)
		{
			System.out.println("1\n1 3\n1 4");
			c=Integer.parseInt(br.readLine());
			if(c>0)
			{
				ans=3;
			}
			else if(c<0)
			{
				ans=4;
			}
		}
		System.out.println("2\n"+ans);
	}
} 