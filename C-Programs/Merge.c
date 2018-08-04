#include<stdio.h>
#include<time.h>

#define max_int 9999

int s=0;
void merge(int *a,int l,int h,int m)
{
    int n=(l+h)/2;
    int n1=m-l+2;
    int n2=h-m+1;
    int b[n1];
    int c[n2];
    int i=l;
    int j=h;
    int t;
    for(t=0;t<n1-1;t++)
    {
        b[t]=a[l+t];
    }
    b[t]=max_int;
    for(t=0;t<n2-1;t++)
    {
        c[t]=a[m+t+1];
    }
    c[t]=max_int;
    t=l;
    i=j=0;
    while(t<=h)
    {
        while(b[i]<c[j])
        {
            a[t]=b[i];
            s++;
            i++;
            t++;
        }
        while(c[j]<b[i])
        {
            a[t]=c[j];
            s++;
            j++;
            t++;
        }
    }
}
void sort(int *a,int l,int h)
{
    if(l<h)
    {
        int m=(l+h)/2;
        sort(a,l,m);
        sort(a,m+1,h);
        merge(a,l,h,m);
    }

}

int main(void)
{
    int a[]={9,8,7,6,5,4,3,2,1,0};
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
    printf("%f\n",t);
    t=s/f1;
    printf("%f\n",t);
    fclose(fp);

    return 0;
}


