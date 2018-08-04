import java.io.*;
class Tricoin
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t1=Integer.parseInt(br.readLine());
		while(t1-->0)
		{
			long n=Long.parseLong(br.readLine());
			long k=(int)((-1.0+Math.sqrt(1+8*n))/(2.0));
			System.out.println(k);
		}
	}
} 