#include<stdio.h>
int s=0;
void min_heapify(int *a,int i)
{
    int left=2*i+1;
    int right=2*i+2;
    int min=i;
    if(a[left]<a[min] && left<s)
    {
        min=left;
    }
    if(a[right]<=a[min] && right<s)
    {
        min=right;
    }
    if(min!=i)
    {
        int t=a[min];
        a[min]=a[i];
        a[i]=t;
        min_heapify(a,min);
    }
}
void enqueue(int *a,int n)
{
   a[s]=n;
   s++;
   int t=s-1;
   while(t>0&&a[(t-1)/2]>a[t])
   {
       int f=a[(t-1)/2];
       a[(t-1)/2]=a[t];
       a[t]=f;
       t=(t-1)/2;
   }
}
void sort(int *a)
{
    int i=0;
    int n=s;
    for(i=0;i<n;i++)
    {
        int t=a[0];
        a[0]=a[s-1];
        a[s-1]=t;
        s--;
        min_heapify(a,0);
    }
    for(i=0;i<n/2;i++)
    {
        int t=a[i];
        a[i]=a[n-1-i];
        a[n-1-i]=t;
    }
    printf("Sorted Array\n");
    for(i=0;i<n;i++)
    {
        printf("%d  ",a[i]);
    }
    printf("\n");
    s=n;
}
void Dequeue(int *a)
{
    if(s==0)
    {
        printf("No Element to Dequeue\n");
        return;
    }
    int t=a[0];
    a[0]=a[s-1];
    a[s-1]=t;
    s--;
    min_heapify(a,0);
    printf("The Element Deleted: %d\n",a[s]);
}
int main(void)
{
    int a[100];
    int c=0;
    int n,i;
    do{
        printf("Press 1 to Enter\nPress 2 to Dequeue\nPress 3 to Sort\nPress 4 to Display\nPress 5 to quit\n");
        printf("Enter Your Choice: ");
        scanf("%d",&c);
        switch(c)
        {
        case 1:printf("Enter Number: ");
            scanf("%d",&n);
            enqueue(a,n);
            break;
        case 2:Dequeue(a);
            break;
        case 3:sort(a);
            c=5;
            break;
        case 4:for(i=0;i<s;i++)
            {
                printf("%d   ",a[i]);
            }
            printf("\n");
            break;
        case 5:break;
        default:printf("Invalid Input\n");
            break;
        }
    }while(c!=5);
    return 0;
}
