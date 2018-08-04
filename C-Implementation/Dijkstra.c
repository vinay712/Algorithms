#include<stdio.h>

#define max_int 999999

typedef struct edge1
{
    int s;
    int d;
    int wt;
}edge;
typedef struct graph1
{
    int v;
    int e;
    edge* ed;
}graph;
typedef struct node1
{
    int n;
    int wt;
    struct node1 *next;
}node;
typedef struct vertex1
{
    int avail;
    int par;
    int key;
    int n;
}vertex;
typedef struct adjacent1
{
    node* next;
    node* last;
}adjacent;

typedef struct heap1
{
    int *a;
    int size;
}heap;

vertex* ver;
int v;

void initialize(adjacent *ad,heap* h)
{
    int i;
    h->size=v;
    for(i=0;i<v;i++)
    {
        (ad+i)->next=NULL;
        (ad+i)->last=NULL;
        (ver+i)->avail=0;
        (ver+i)->par=-1;
        (ver+i)->n=i;
        (ver+i)->key=max_int;
        h->a[i]=i;
    }
}
void adjacency(adjacent *ad,int s,int d,int wt)
{
    node *n=(node*)malloc(sizeof(node));
    n->n=d;
    n->wt=wt;
    n->next=NULL;
    if((ad+s)->next==NULL)
    {
        (ad+s)->next=n;
    }
    else
    {
        (ad+s)->last->next=n;
    }
    (ad+s)->last=n;
}
void minheapify(heap* h,int i)
{
    int left=2*i+1;
    int right=2*i+2;
    int min=i;
    if((ver+(h->a[min]))->key > (ver+(h->a[left]))->key && left < h->size)
    {
        min=left;
    }
    if((ver+(h->a[min]))->key>(ver+(h->a[right]))->key && right < h->size)
    {
        min=right;
    }
    if(min!=i)
    {
        int t=h->a[min];
        h->a[min]=h->a[i];
        h->a[i]=t;
        minheapify(h,min);
    }
}
vertex* dequeue(heap *h)
{
    if(h->size==0)
    {
        return NULL;
    }
    vertex* ver1=(ver+h->a[0]);
    (ver+h->a[0])->avail=1;
    (h->size)--;
    int t=h->a[0];
    h->a[0]=h->a[h->size];
    h->a[h->size]=t;
    minheapify(h,0);
    return ver1;
}
void insert(heap* h,int i1,int key)
{
    (ver+i1)->key=key;
    int i,par,t;
    for(i=0;i<h->size;i++)
    {
        if(h->a[i]==i1)
        {
            break;
        }
    }
    par=(i-1)/2;
    while(par>=0 && (ver+h->a[i])->key<(ver+h->a[par])->key)
    {
        t=h->a[i];
        h->a[i]=h->a[par];
        h->a[par]=t;
        i=par;
        par=(i-1)/2;
    }
}
void find_path(int d,int s)
{
    while((ver+d)->par!=-1)
    {
        printf("%c-",(d+97));
        d=(ver+d)->par;
    }
    printf("%c\n",(d+97));
}

int main(void)
{
    FILE *fp=fopen("dijkstra.txt","r+");
    int e,i,j,k=0;
    int size=0;
    int src,dest,wt;
    adjacent *ad;
    graph *g=(graph*)malloc(sizeof(graph));
    heap* h=(heap*)malloc(sizeof(heap));
    edge *ed;
    fscanf(fp,"%d",&v);
    fscanf(fp,"%d",&e);
    ed=(edge*)malloc(sizeof(edge)*e);
    g->ed=ed;
    g->e=e;
    g->v=v;
    fgetc(fp);
    ver=(vertex*)malloc(sizeof(vertex)*v);
    ad=(adjacent*)malloc(sizeof(adjacent)*v);
    h->a=(int*)malloc(sizeof(int)*v);
    initialize(ad,h);
    for(i=0;i<e;i++)
    {
        src=fgetc(fp);
        fgetc(fp);
        dest=fgetc(fp);
        fgetc(fp);
        fscanf(fp,"%d",&wt);
        fgetc(fp);
        src=src-97;
        dest=dest-97;
        (ed+i)->s=src;
        (ed+i)->d=dest;
        (ed+i)->wt=wt;
        adjacency(ad,src,dest,wt);
    }
    src=0;
    vertex* v1;
    insert(h,src,0);
    while((v1=dequeue(h))!=NULL)
    {
        node *n=(ad+(v1->n))->next;
        int t1=0;
        while(n!=NULL)
        {
            int t=n->n;
            if((ver+t)->avail==0&& (ver+t)->key>((n->wt)+(ver+v1->n)->key ))
            {
                insert(h,t,n->wt+(ver+v1->n)->key);
                (ver+t)->par=v1->n;
            }
            n=n->next;
        }
    }
    for(i=0;i<v;i++)
    {
        printf("Path from %c to %c (%d): ",(src+97),(i+97),(ver+i)->key);
        find_path(i,src);
    }
    fclose(fp);
    return 0;
}
