import java.io.*;
class Seakam
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		final int mod=1000000007;
		while(t-->0)
		{
			int m=0,n=0;
			String st[];
			st=(br.readLine()).split(" ");
			n=Integer.parseInt(st[0]);
			m=Integer.parseInt(st[1]);
			long fact=1;
			int i,j;
			int v1[]=new int[m];
			int v2[]=new int[m];
			int a[]=new int[n+1];
			int num=1;
			for(i=0;i<m;i++)
			{
				st=(br.readLine()).split(" ");
				v1[i]=Integer.parseInt(st[0]);
				v2[i]=Integer.parseInt(st[1]);
				if(v1[i]==v2[i])
				{
					i--;
					m--;
					continue;	
				}
				if(a[v1[i]]==0)
				{
					a[v1[i]]=num;
					num++;
				}
				if(a[v2[i]]==0)
				{
					a[v2[i]]=num;
					num++;
				}
			}
			int deg[]=new int[num];
			int rep[]=new int[num];
			for(i=2;i<n-m;i++)
			{
				fact=(fact*i)%mod;
			}
			long f[]=new long[m+1];
			long p[]=new long[m+2];
			int sel[]=new int[m];
			p[0]=1;
			for(i=n-m;i<=n;i++)
			{
				if(i!=0)
				{
					fact=(fact*i)%mod;
				}
				if((n-i)%2==0)
				{
					f[n-i]=fact;
				}
				else
				{
					f[n-i]=(-fact);
				}
				p[i-n+m+1]=(p[i-n+m]*2)%mod;
			}
			long ans=f[0];
			int num1=0;
			int disjoint=0,d=0,n1,n2,flag;
			while(num1<m)
			{
				i=0;
				flag=0;
				while(i<m && sel[i]==1)
				{
					sel[i]=0;
					num1--;
					i++;
				}
				sel[i]=1;
				d=1;
				int dis=0;
				for(j=1;j<num;j++)
				{
					deg[j]=0;
					rep[j]=0;
				}
				for(j=0;j<m;j++)
				{
					//System.out.print(sel[m-j-1]);
					if(sel[j]==1)
					{
						n1=a[v1[j]];
						n2=a[v2[j]];
						deg[n1]++;
						deg[n2]++;
						if(deg[n1]>2 || deg[n2]>2)
						{
							flag=1;
							break;
						}
						if(rep[n1]==0 && rep[n2]==0)
						{
							rep[n1]=d;
							rep[n2]=d;
							dis++;
							d++;
						}
						else if(rep[n1]==0)
						{
							rep[n1]=rep[n2];
						}
						else if(rep[n2]==0)
						{
							rep[n2]=rep[n1];
						}
						else
						{
							if(rep[n2]==rep[n1])
							{
								flag=1;
								break;
							}
							else
							{
								int temp=rep[n2];
								for(int k=0;k<num;k++)
								{
									if(rep[k]==temp)
									{
										rep[k]=rep[n1];
									}
								}
								dis--;
							}
						}
					}
				}						
				num1++;
				disjoint=dis;
				//System.out.println("  "+num1+"  "+disjoint+"  "+flag);
				if(flag==0 && disjoint!=0)
				{
					ans=(ans+(f[num1]*p[disjoint])%mod+mod)%mod;
				}
			}
			System.out.println(ans);
		}
	}
} 