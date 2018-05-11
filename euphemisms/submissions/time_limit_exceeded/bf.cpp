/*
 */
#include <cassert>
#include <cmath>
#include <algorithm>
#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <vector>
#define tol (1e-13)
using namespace std;
#define MAXLEN (1<<11)
#define N 32

char text[MAXLEN],s[N][MAXLEN],len[N];
int n;

bool matches( char *x, char *y ) {
	for ( ;*x && *x == *y; ++x, ++y ) ;
	return !*x;
}

bool good( char *x ) {
	int t = strlen(x);
	for ( int i = 0; i < n; ++i )
		for ( int k = 0; k+len[i] <= t; ++k )
			if ( matches(s[i],x+k) )
				return false;
	return true;
}

int main() {
	int i,j,k,cs = 0,ts,ans,m,u;
	for ( scanf("%d",&ts); ts-- && 1 == scanf("%d",&n); ) {
		for ( i = 0; i < n; ++i ) scanf("%s",s[i]), len[i] = strlen(s[i]);
		scanf("%s",text), ans = strlen(text)<<1;
		for ( m = strlen(text), u = 0; u < (1<<m); ++u ) {
			char e[MAXLEN], *ptr = e;
			for ( ptr = e, k = 0, i = 0; i < m; ++i )
				if ( (u>>i)&1 )
					*ptr++ = text[i];
				else ++k;
			*ptr = '\0';
			if ( good(e) ) 
				ans = min(ans,k);
		}
		printf("Case #%d: %d\n",++cs,ans);
	}
	return 0;
}

