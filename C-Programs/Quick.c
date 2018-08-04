#include<stdio.h>
#include<time.h>

int c=0;
int pivot(int *a,int l,int h)
{
    int n=l;
    int i=l;
    int j=h;
    int p=a[n];
    while(i<j)
    {
        while(a[i]<=p&&i<=h)
        {
            i++;
            c++;
        }
        while(a[j]>p&&j>=l)
        {
            j--;
            c++;
        }
        if(i<j)
        {
            int t=a[i];
            a[i]=a[j];
            a[j]=t;
        }
    }
    int t=a[j];
    a[j]=a[n];
    a[n]=t;
    return j;
}
void sort(int *a,int l,int h)
{
    if(l<h)
    {
        int m=pivot(a,l,h);
        sort(a,l,m-1);
        sort(a,m+1,h);
    }

}

int main(void)
{
    int a[10];
    int i,n=10;
    int f=1,f1;
    FILE *fp=fopen("permute.txt","r");
    clock_t start,stop;
    double t;
    for(i=2;i<=n;i++)
    {
        f=f*i;
    }
    f1=f;
        printf("%d\n",f);
    start=clock();
    while(f--)
    {
        printf("%d\n",f);
        for(i=0;i<n;i++)
        {
            fscanf(fp,"%1d",&a[i]);
        }
        printf("%d\n",f);
        sort(a,0,n-1);

    }
    stop=clock();
    t=((double)(stop-start)/CLOCKS_PER_SEC);
    t=c/f1;
    printf("%f",t);
    fclose(fp);
    return 0;
}

