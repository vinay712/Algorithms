#include<stdio.h>

int add(int a,int b)
{
    int s,c;
    while(b)
    {
        s=a^b;
        c=a&b;
        a=s;
        b=c<<1;
    }
    return a;
}
int multiply(int a,int b)
{
    int s=0;
    while(b)
    {
        if(b&1)
        {
            s=add(s,a);
        }
        b=b>>1;
        a=a<<1;
    }
    return s;
}
int main(void)
{
    int a,b;
    printf("ENTER 2 NUM: ");
    scanf("%d %d",&a,&b);
    printf("ADD: %d\n",add(a,b));
    printf("MULYIPLY: %d\n",multiply(a,b));
    return 0;
}
