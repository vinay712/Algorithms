#include <stdio.h>

#define max_int 999999

int** m;
int** s;
int *p;
int n;
int *o;
int *c;
void split(int i,int j)
{
    if(i==j)
    {
        return;
    }
    int d=s[i][j];
    if(c[i]>0)
    {
        c[i]--;
    }
    else
    {
        o[i]++;
    }
    if(o[j]>0)
    {
        o[j]--;
    }
    else
    {
        c[j]++;
    }
    if(c[d+1]>0)
    {
        c[d+1]--;
    }
    else
    {
        o[d+1]++;
    }
    if(o[d]>0)
    {
        o[d]--;
    }
    else
    {
        c[d]++;
    }
    split(i,d);
    split(d+1,j);
}
void compute()
{
    int l,g,i,j,k;
    for(i=1;i<=n;i++)
    {
        m[i][i]=0;
    }
    for(l=2;l<=n;l++)
    {
        for(i=1;i<=n-l+1;i++)
        {
            j=i+l-1;
            m[i][j]=max_int;
            for(k=i;k<j;k++)
            {
                g=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
                if(m[i][j]>g)
                {
                    m[i][j]=g;
                    s[i][j]=k;
                }
            }
        }
    }
}
int main(void)
{
    int i,j;
    scanf("%d",&n);
    p=(int*)malloc(sizeof(int)*(n+1));
    o=(int*)malloc(sizeof(int)*(n+1));
    c=(int*)malloc(sizeof(int)*(n+1));
    m=(int**)malloc(sizeof(int*)*(n+1));
    s=(int**)malloc(sizeof(int*)*(n+1));
    for(i=0;i<=n;i++)
    {
        scanf("%d",&p[i]);
        m[i]=(int*)malloc(sizeof(int)*(n+1));
        s[i]=(int*)malloc(sizeof(int)*(n+1));
        o[i]=0;
        c[i]=0;
    }

    compute();
    printf("COST\n");
    for(i=1;i<=n;i++)
    {
        for(j=1;j<=n;j++)
        {
            if(i>j)
            {
                printf("\t");
            }
            else
            {
                printf("%d\t",m[i][j]);
            }
        }
        printf("\n");
    }
    printf("SPLIT MATRIX\n");
    for(i=1;i<=n;i++)
    {
        for(j=1;j<=n;j++)
        {
            if(i>=j)
            {
                printf("\t");
            }
            else
            {
                printf("%d\t",s[i][j]);
            }
        }
        printf("\n");
    }
    split(1,n);
    for(i=1;i<=n;i++)
    {
        for(j=0;j<o[i];j++)
        {
            printf("(");
        }
        printf(" m%d ",i);
        for(j=0;j<c[i];j++)
        {
            printf(")");
        }
        if(i<n)
        {
            printf("*");
        }
    }
    printf("\n");
    return 0;
}
