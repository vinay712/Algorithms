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

vertex* ver;
int v;
adjacent *ad;
edgelist* tedge;
edgelist* bedge;
edgelist* cedge;
edgelist* fedge;


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
int isparent(int s,int d)
{
    if((ver+d)->par==s)
    {
        return 1;
    }
    else if((ver+d)->par==-1)
    {
        return 0;
    }
    else
    {
       return isparent(s,(ver+d)->par);
    }
}
void dfs(int s)
{
    (ver+s)->color=1;
    node *n=(ad+s)->next;
    while(n!=NULL)
    {
        if((ver+n->n)->color==0)
        {
            (ver+n->n)->par=s;
            (ver+n->n)->color=1;
            tedge=addedge(tedge,s,n->n);
            dfs(n->n);
        }
        else if((ver+n->n)->color==1)
        {
            bedge=addedge(bedge,s,n->n);
        }
        else
        {
            if(isparent(s,n->n))
            {
                fedge=addedge(fedge,s,n->n);
            }
            else
            {
                cedge=addedge(cedge,s,n->n);
            }

        }
        n=n->next;
    }
    (ver+s)->color=2;
}
void initialize()
{
    int i;
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
void display(edgelist* n)
{
    while(n!=NULL)
    {
        printf("(%c,%c)\n",(n->s+97),(n->d+97));
        n=n->next;
    }
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
    g->ed=ed;
    g->e=e;
    g->v=v;
    fgetc(fp);
    initialize();
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
    /*for(i=0;i<v;i++)
    {
        node *n=(ad+i)->next;
        printf("%c :",(i+97));
        while(n!=NULL)
        {
            printf("%c, ",((n->n)+97));
            n=n->next;
        }
        printf("\n");
    }*/
    for(i=0;i<v;i++)
    {
        if((ver+i)->color==0)
        {
            dfs(i);
        }
    }
    printf("TREE EDGE:\n");
    display(tedge);
    printf("FORWARD EDGE:\n");
    display(fedge);
    printf("BACK EDGE:\n");
    display(bedge);
    printf("CROSS EDGE:\n");
    display(cedge);

    fclose(fp);
    return 0;
}
