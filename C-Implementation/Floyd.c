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

int **dist;
int **par;
int v;

void initialize()
{
    int i,j;
    for(i=0;i<v;i++)
    {
        for(j=0;j<v;j++)
        {
            dist[i][j]=max_int;
            par[i][j]=-1;
        }
    }
}
void floyd_find()
{
    int i,j,k;
    for(k=0;k<v;k++)
    {
        for(i=0;i<v;i++)
        {
            for(j=0;j<v;j++)
            {
                if(dist[i][j]>dist[i][k]+dist[k][j])
                {
                    dist[i][j]=dist[i][k]+dist[k][j];
                    par[i][j]=par[k][j];
                }
            }
        }
    }
}
int main(void)
{
    FILE *fp=fopen("floyd.txt","r+");
    int e,i,j;
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
    dist=(int**)malloc(sizeof(int*)*v);
    par=(int**)malloc(sizeof(int*)*v);
    for(i=0;i<v;i++)
    {
        dist[i]=(int*)malloc(sizeof(int)*v);
        par[i]=(int*)malloc(sizeof(int)*v);
    }
    initialize();
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
        dist[src][dest]=wt;
        par[src][dest]=src;
    }
    for(i=0;i<v;i++)
    {
        dist[i][i]=0;
    }
    floyd_find();
    printf("DISTANCE\n");
    for(j=0;j<v;j++)
    {
        for(i=0;i<v;i++)
        {
            printf("%d\t",dist[j][i]);
        }
        printf("\n");
    }
    printf("PARENT\n");
    for(j=0;j<v;j++)
    {
        for(i=0;i<v;i++)
        {
            printf("%d\t",par[j][i]);
        }
        printf("\n");
    }
    fclose(fp);
    return 0;
}
