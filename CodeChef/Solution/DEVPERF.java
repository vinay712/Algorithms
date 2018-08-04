import java.io.*;
class Devperf
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		while(t-->0)
		{
			String line=br.readLine();
			String st[]=line.split(" ");
			int r=Integer.parseInt(st[0]);
			int c=Integer.parseInt(st[1]);
			int top=r,bottom=-1,left=c,right=-1;
			for(int i=0;i<r;i++)
			{
				line=br.readLine();
				for(int j=0;j<c;j++)
				{
					char ch=line.charAt(j);
					if(ch=='*')
					{
						if(j<left)
						{
							left=j;
						}
						if(j>right)
						{
							right=j;
						}
						if(i<top)
						{
							top=i;
						}
						if(i>bottom)
						{
							bottom=i;
						}
					}
				}
			}
			int cm=(right-left+1)/2;
			int rm=(bottom-top+1)/2;
			int ans=0;
			if(bottom!=-1 && right!=-1)
			{
				ans=(cm>rm?cm:rm)+1;
			}
			System.out.println(ans);
		}
	}
} 