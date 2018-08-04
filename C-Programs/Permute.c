#include<stdio.h>

int n;
void generate(FILE* fp,int *a,int n,int j)
{
    int t;
    if(j==n)
    {
        for(t=0;t<n;t++)
        {
            fprintf(fp,"%d",a[t]);
        }
        fprintf(fp,"\n");
        return;
    }
    int i;
    for(i=j;i<n;i++)
    {
        t=a[i];
        a[i]=a[j];
        a[j]=t;
        generate(fp,a,n,j+1);
        t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}

int main(void)
{
    FILE *fp;
    fp=fopen("permute.txt","w+");
    int a[]={0,1,2,3,4,5,6,7,8,9};
    n=10;
    generate(fp,a,n,0);
    fclose(fp);
    return 0;
}
