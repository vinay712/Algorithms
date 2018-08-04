import java.io.*;
class Laddu
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		while(t-->0)
		{
			String line=br.readLine();
			String st[]=line.split(" ");
			int n=Integer.parseInt(st[0]);
			int m=0,p;
			if(st[1].equals("INDIAN"))
			{
				m=200;
			}
			else
			{
				m=400;
			}
			int cost=0;
			while(n-->0)
			{
				line=br.readLine();
				st=line.split(" ");
				if(st[0].equals("CONTEST_WON"))
				{
					p=Integer.parseInt(st[1]);
					cost+=300;
					if(p<20)
					{
						cost+=20-p;
					}
				}
				else if(st[0].equals("TOP_CONTRIBUTOR"))
				{
					cost+=300;
				}
				else if(st[0].equals("BUG_FOUND"))
				{
					cost+=Integer.parseInt(st[1]);
				}
				else if(st[0].equals("CONTEST_HOSTED"))
				{
					cost+=50;
				}
			}
			System.out.println((int)(cost/m));
		}
	}
}