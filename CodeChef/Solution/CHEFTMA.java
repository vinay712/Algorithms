import java.io.*;
class Cheftma
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		while(t-->0)
		{
			String st[]=(br.readLine()).split(" ");
			int n=Integer.parseInt(st[0]);
			int m=Integer.parseInt(st[1]);
			int k=Integer.parseInt(st[2]);
			String pt[]=(br.readLine()).split(" ");
			String ct[]=(br.readLine()).split(" ");
			String wb[]=(br.readLine()).split(" ");
			String bb[]=(br.readLine()).split(" ");
			int a[]=new int[n];
			int i,j;
			for(i=0;i<n;i++)
			{
				a[i]=Integer.parseInt(pt[i])-Integer.parseInt(ct[i]);
			}
			int b[]=new int[100001];
			for(i=0;i<m;i++)
			{
				j=Integer.parseInt(wb[i]);
				b[j]++;
			}
			for(i=0;i<k;i++)
			{
				j=Integer.parseInt(bb[i]);
				b[j]++;
			}
			int ans=0;
			for(i=0;i<n;i++)
			{
				for(j=a[i];j>0;j--)
				{
					if(b[j]>0)
					{
						ans+=a[i]-j;
						b[j]--;
						break;
					}
				}
				if(j==0)
				{
					ans+=a[i];
				}
			}
			System.out.println(ans);
		}
	}
} 