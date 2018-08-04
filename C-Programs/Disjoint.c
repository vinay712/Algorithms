#include<stdio.h>

typedef struct edge1
{
    int s;
    int d;
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
    struct node1 *next;
}node;
typedef struct set1
{
    int m;
    int rep;
    node *next;
    node *last;
}set;
void makeset(set *s,int *rep,int v)
{
    int i;
    for(i=0;i<v;i++)
    {
        (s+i)->m=1;
        (s+i)->rep=i;
        node* n=(node*)malloc(sizeof(node));
        n->n=i;
        n->next=NULL;
        (s+i)->next=n;
        (s+i)->last=n;
        rep[i]=i;
    }
}
int findset(int u,int v,int *rep)
{
    if(rep[u]==rep[v])
    {
        return 0;
    }
    return 1;
}
void unionset(int u,int v,int *rep,set *s)
{
    u=rep[u];
    v=rep[v];
    set *smax=((s+u)->m>=(s+v)->m)?(s+u):(s+v);
    set *smin=((s+u)->m<(s+v)->m)?(s+u):(s+v);
    node *n=smin->next;
    while(n!=NULL)
    {
        rep[n->n]=smax->rep;
        n=n->next;
    }
    smax->last->next=smin->next;
    smax->last=smin->last;
    smax->m=smax->m+smin->m;
    smin->m=0;
    smin->next=NULL;
    smin->last=NULL;
}
int main(void)
{
    FILE *fp=fopen("disjoint.txt","r+");
    int v,e,i,j;
    int src,dest;
    graph *g=(graph*)malloc(sizeof(graph));
    edge *ed;
    set *s;
    int *rep;
    fscanf(fp,"%d",&v);
    fscanf(fp,"%d",&e);
    ed=(edge*)malloc(sizeof(edge)*e);
    g->ed=ed;
    g->e=e;
    g->v=v;
    fgetc(fp);
    s=(set*)malloc(sizeof(set)*v);
    rep=(int*)malloc(sizeof(int)*v);
    makeset(s,rep,v);
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
    }
    for(i=0;i<e;i++)
    {
        src=(ed+i)->s;
        dest=(ed+i)->d;
        if(findset(src,dest,rep)==1)
        {
            unionset(src,dest,rep,s);
        }
    }
    for(i=0;i<v;i++)
    {
        if((s+i)->m!=0)
        {
            printf("set: ");
            node *n=(s+i)->next;
            while(n!=NULL)
            {
                printf("%c, ",((n->n)+97));
                n=n->next;
            }
            printf("\n");
        }
    }
    fclose(fp);
    return 0;
}
