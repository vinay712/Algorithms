#include<stdio.h>

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
int pivot(edge *ed,int l,int h)
{
    int n=l;
    int i=l;
    int j=h;
    int t;
    int p=(ed+n)->wt;
    while(i<j)
    {
        while((ed+i)->wt<=p&&i<=h)
        {
            i++;
        }
        while((ed+j)->wt>p&&j>=l)
        {
            j--;
        }
        if(i<j)
        {
            t=(ed+i)->s;
            (ed+i)->s=(ed+j)->s;
            (ed+j)->s=t;
            t=(ed+i)->d;
            (ed+i)->d=(ed+j)->d;
            (ed+j)->d=t;
            t=(ed+i)->wt;
            (ed+i)->wt=(ed+j)->wt;
            (ed+j)->wt=t;
        }
    }
    t=(ed+n)->s;
    (ed+n)->s=(ed+j)->s;
    (ed+j)->s=t;
    t=(ed+n)->d;
    (ed+n)->d=(ed+j)->d;
    (ed+j)->d=t;
    t=(ed+n)->wt;
    (ed+n)->wt=(ed+j)->wt;
    (ed+j)->wt=t;
    return j;
}
void sort(edge *a,int l,int h)
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
    FILE *fp=fopen("kruskal.txt","r+");
    int v,e,i,j,k=0;
    int src,dest,wt;
    graph *g=(graph*)malloc(sizeof(graph));
    graph *mst=(graph*)malloc(sizeof(graph));
    edge *ed;
    set *s;
    int *rep;
    fscanf(fp,"%d",&v);
    fscanf(fp,"%d",&e);
    mst->e=v-1;
    mst->v=v;
    ed=(edge*)malloc(sizeof(edge)*(v-1));
    mst->ed=ed;
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
        fscanf(fp,"%d",&wt);
        fgetc(fp);
        src=src-97;
        dest=dest-97;
        (ed+i)->s=src;
        (ed+i)->d=dest;
        (ed+i)->wt=wt;
    }
    sort(ed,0,e-1);
    for(i=0;i<e;i++)
    {
        src=(ed+i)->s;
        dest=(ed+i)->d;
        if(findset(src,dest,rep)==1)
        {
            unionset(src,dest,rep,s);
            ((mst->ed)+k)->s=src;
            ((mst->ed)+k)->d=dest;
            ((mst->ed)+k)->wt=(ed+i)->wt;
            k++;
        }
    }
    for(i=0;i<v-1;i++)
    {
       printf("(%c,%c)->%d\n",(((mst->ed)+i)->s+97),(((mst->ed)+i)->d+97),((mst->ed)+i)->wt);
    }
    fclose(fp);
    return 0;
}
