#include <stdio.h>
#include <stdlib.h>

int n, b,m;
int main()
{
scanf("%d",&n);
int  c;
int e,s;
int i,j,k;
//printf("n=%d\n",n);
for (i=0;i<n;i++)
   {
    scanf("%d",&b);
    scanf("%d",&m);
    scanf("%d\n",&k);
    fflush(stdin);
//    printf("\nb=%d, k=%d, m=%d. \n",b,k,m);
    for (j=0;j<m;j++)
       {
        s=0;
        e=0;
        c='\0';
        while ( !(c==-1) )
             {
              scanf("%d",&c);
              if ( (c!=-1)  )
                {             
                 if ((c>= 0) && (c<b) )
                        s=( s * b + c ) % k;
                   else e=1; 
                }              
             }
        if ( (e==0) && (s==0) ) printf("1");
           else printf("0"); 
       }
    printf("\n");   
   }

printf("\n");
return 0;
}
