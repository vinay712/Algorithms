import java.io.*;
class Color
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		while(t-->0)
		{
			int n=Integer.parseInt(br.readLine());
			String S=br.readLine();
			int r=0,g=0,b=0;
			for(int i=0;i<n;i++)
			{
				char c=S.charAt(i);
				if(c=='R')
				{
					r++;
				}
				else if(c=='G')
				{
					g++;
				}
				else
				{
					b++;
				}
			}
			int max=(r>b)?((r>g)?r:g):(b>g)?b:g;
			System.out.println(n-max);
		}
	}
} 