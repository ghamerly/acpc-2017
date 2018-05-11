/*
 * Euphemisms-Shmeuphemisms
 * Author of the solution: skazi@dal.ca
 * TOPIC: Dynamic programming on the states of Aho-Corasick automaton, BFS
 */
#include <cassert>
#include <algorithm>
#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#define A 2
// extracts the LSB of an unsigned int,
// useful when alphabet size <= 64 -- we can 
// efficiently iterate through the set bits = existing transitions
#define L(k) ((k)&((~(k))+1ULL))
#define MAXNODES (1<<12)
#define BIT(k) (1ULL<<(k))
#define MAXLEN (1<<11)
#define oo (0xfffffffful)
#include <queue>
#define who(u) (which[u])
const char *alphabet = "01";
using namespace std;

char which[1<<10],s[MAXLEN],text[MAXLEN];

class ACautomaton {
private:
	struct cell {
		cell *son[A], *slink;
		unsigned char u;
		bool is_accepting;
		unsigned int z[MAXLEN];
		cell() { memset(z,0xff,sizeof z), is_accepting = false, u = 0, slink = NULL; }
		cell *find( char ch ) const { return u&BIT(ch-'0') ? son[ch-'0']:NULL; }
	} pool[MAXNODES], *ptr, *q0, *root;
	cell *init_node() { return ptr++; }
	void add_son( cell *x, char ch ) { x->u |= BIT(ch-'0'), x->son[ch-'0'] = init_node(); }
	void add_son( cell *x, char ch, cell *y ) { x->u |= BIT(ch-'0'), x->son[ch-'0'] = y; }
public:
	void push( char *s ) {
		cell *x = root;
		for ( ;*s; x = (!x->find(*s))?(add_son(x,*s),x->find(*s++)):x->find(*s++) ) ;
		x->is_accepting = true ;
	}
	ACautomaton() {
		ptr = pool, root = init_node(), q0 = init_node(), root->slink = q0;
		for ( const char *a = alphabet; *a; add_son(q0,*a++,root) ) ;
	}
	//compute suffix links as in the standard Aho-Corasick algorithm
	void build() {
		unsigned int u,v,ch;
		queue<cell *> q;
		cell *x,*y,*z;
		for ( ;!q.empty(); q.pop() ) ;
		for ( q.push(root); !q.empty(); )
			for ( u=(x=q.front())->u, q.pop(); u; y->slink = z->find(ch), y->is_accepting|=y->slink->is_accepting, q.push(y), u &= ~L(u) ) 
				for ( y=x->find(ch=who(L(u))+'0'), z=x->slink; !z->find(ch); z = z->slink ) ;
	}
	// lazy computation of explicit failure links:
	// if the node x does not have a ch-transition,
	// compute its suffix link's x->slink's ch-transition and assign
	// x->son[ch] = x->slink->son[ch]
	cell *failure_transition( cell *x, char ch ) {
		if ( x->find(ch) ) return x->find(ch);
		add_son(x,ch,failure_transition(x->slink,ch));
		return x->find(ch);
	}
	// basically a BFS, but a Dijkstra with a binary heap with decrease-key will work too
	/// the implicit graph's node is a pair (v,k) -- the state of the AC-automaton + the prefix-length of T
	// the weights are all 0-1, but this is extendable to more general cases when e.g.
	// when the costs are not uniform
	unsigned int DP( char *s ) {
		unsigned int ans = +oo;
		cell *x = root,*y;
		int len;
		queue<pair<cell*,int> > q; 
		for ( ;!q.empty(); q.pop() ) ;
		for ( x->z[0] = 0, q.push(make_pair(x,0)); !q.empty(); ) {
			pair<cell*,int> r = q.front(); q.pop();
			x = r.first, len = r.second;
			if ( !s[len] ) {
				ans = min(ans,x->z[len]);
				continue ;
			}
			if ( x->z[len+1] > x->z[len]+1 )
				x->z[len+1] = x->z[len]+1, q.push(make_pair(x,len+1));
			if ( !(y=failure_transition(x,s[len]))->is_accepting && y->z[len+1] > x->z[len] )
				y->z[len+1] = x->z[len], q.push(make_pair(y,len+1));
		}
		return ans;
	}
} *ac;

int main() {
	int i,j,k,forbidden_patterns,ts,cs = 0;
	for ( i = 0; i < 10; which[BIT(i)] = i, ++i ) ;
	for ( scanf("%d",&ts); ts--; ) {
		for ( ac = new ACautomaton(), scanf("%d",&forbidden_patterns); forbidden_patterns-- && 1 == scanf("%s",s); ac->push(s) ) ;
		ac->build(), scanf("%s",text), printf("Case #%d: %u\n",++cs,ac->DP(text));
		delete ac;
	}
	return 0;
}

