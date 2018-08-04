#include<stdio.h>

#define max_int 999999

typedef struct edge1
{
    int s;
    int d;
}edge;
typedef struct edgelist1
{
    int s;
    int d;
    struct edgelist1 *next;
}edgelist;
typedef struct graph1
{
    int v;
    int e;
    edge* ed;
}graph;
typedef struct node1
{
    int n;
    struct node1 *next;
}node;
typedef struct vertex1
{
    int color;
    int par;
    int n;
}vertex;
typedef struct adjacent1
{
    node* next;
}adjacent;
typedef struct queue1
{
    node* front;
    node* rear;
}queue;

vertex* ver;
int v;
adjacent *ad;
edgelist* tedge;

void enqueue(queue *q,int d)
{
    node *n=(node*)malloc(sizeof(node));
    n->n=d;
    n->next=NULL;
    if(q->front==NULL)
    {
        q->front=n;
    }
    else
    {
        q->rear->next=n;
    }
    q->rear=n;
}
int dequeue(queue *q)
{
    if(q->front==NULL)
    {
        return -1;
    }
    int n=q->front->n;
    q->front=q->front->next;
    return n;
}
edgelist *addedge(edgelist *el,int s,int d)
{
    edgelist *n=(edgelist*)malloc(sizeof(edgelist));
    n->s=s;
    n->d=d;
    n->next=NULL;
    edgelist *st=el;
    if(el==NULL)
    {
        return n;
    }
    while(st->next!=NULL)
    {
        st=st->next;
    }
    st->next=n;
    return el;
}
void bfs(int s,queue *q)
{
    enqueue(q,s);
    (ver+s)->color=1;
    while(q->front!=NULL)
    {
        int v1=dequeue(q);
        node *n=(ad+v1)->next;
        while(n!=NULL)
        {
            if((ver+n->n)->color==0)
            {
                (ver+n->n)->par=v1;
                (ver+n->n)->color=1;
                tedge=addedge(tedge,v1,n->n);
                enqueue(q,n->n);
            }
            n=n->next;
        }
        (ver+v1)->color=2;
    }
}
void initialize(queue *q)
{
    int i;
    q->front=NULL;
    q->rear=NULL;
    tedge=NULL;
    for(i=0;i<v;i++)
    {
        (ad+i)->next=NULL;
        (ver+i)->color=0;
        (ver+i)->par=-1;
        (ver+i)->n=i;
    }
}
void adjacency(int s,int d)
{
    node *n=(node*)malloc(sizeof(node));
    n->n=d;
    n->next=(ad+s)->next;
    (ad+s)->next=n;
}
int main(void)
{
    FILE *fp=fopen("bfs.txt","r+");
    int e,i,j;
    int src,dest;
    graph *g=(graph*)malloc(sizeof(graph));
    edge *ed;
    fscanf(fp,"%d",&v);
    fscanf(fp,"%d",&e);
    ed=(edge*)malloc(sizeof(edge)*e);
    ad=(adjacent*)malloc(sizeof(adjacent)*v);
    ver=(vertex*)malloc(sizeof(vertex)*v);
    queue *q=(queue*)malloc(sizeof(queue));
    g->ed=ed;
    g->e=e;
    g->v=v;
    fgetc(fp);
    initialize(q);
    for(i=0;i<e;i++)
    {
        src=fgetc(fp);
        fgetc(fp);
        dest=fgetc(fp);
        fgetc(fp);
        src=src-97;
        dest=dest-97;
        (ed+i)->s=src;
        (ed+i)->d=dest;
        adjacency(src,dest);
    }
    for(i=0;i<v;i++)
    {
        if((ver+i)->color==0)
        {
            bfs(i,q);
        }
    }
    edgelist *n=tedge;
    while(n!=NULL)
    {
        printf("(%c,%c)\n",(n->s+97),(n->d+97));
        n=n->next;
    }

    fclose(fp);
    return 0;
}
