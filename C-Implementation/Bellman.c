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

void initialize(int *d,int *p,int v)
{
    int i;
    for(i=0;i<v;i++)
    {
        d[i]=max_int;
        p[i]=-1;
    }
}
int bellman_find(graph *g,int *dist,int *par)
{
    edge* ed=g->ed;
    int v=g->v;
    int e=g->e;
    int i,j;
    for(i=0;i<v-1;i++)
    {
        for(j=0;j<e;j++)
        {
            int s=(ed+j)->s;
            int d=(ed+j)->d;
            int wt=(ed+j)->wt;
            if(dist[s]!=max_int && dist[d]>dist[s]+wt)
            {
                dist[d]=dist[s]+wt;
                par[d]=s;
            }
        }
    }
    for(j=0;j<e;j++)
    {
        int s=(ed+j)->s;
        int d=(ed+j)->d;
        int wt=(ed+j)->wt;
        if(dist[s]!=max_int && dist[d]>dist[s]+wt)
        {
            return 0;
        }
    }
    return 1;
}
void find_path(int d,int s,int *par)
{
    while(par[d]!=-1)
    {
        printf("%c-",(d+97));
        d=par[d];
    }
    printf("%c\n",(d+97));
}

int main(void)
{
    FILE *fp=fopen("bellman.txt","r+");
    int v,e,i,j;
    int size=0;
    int src,dest,wt;
    graph *g=(graph*)malloc(sizeof(graph));
    edge *ed;
    fscanf(fp,"%d",&v);
    fscanf(fp,"%d",&e);
    ed=(edge*)malloc(sizeof(edge)*e);
    g->ed=ed;
    g->e=e;
    g->v=v;
    fgetc(fp);
    int* dist=(int*)malloc(sizeof(int)*v);
    int* par=(int*)malloc(sizeof(int)*v);
    initialize(dist,par,v);
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
    src=0;
    dist[src]=0;
    if(bellman_find(g,dist,par))
    {
        for(i=0;i<v;i++)
        {
            printf("Path from %c to %c (%d): ",(src+97),(i+97),dist[i]);
            find_path(i,src,par);
        }
    }
    else
    {
        printf("Path Not Possible");
    }
    fclose(fp);
    return 0;
}
