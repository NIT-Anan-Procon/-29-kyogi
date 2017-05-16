#include <stdio.h>

enum{
	TRUE,
	FALSE
};

typedef struct{
	int index;
	int total;
}Node;

Node stack[100];

int top, data[] = {1,2,3,4,5}, k;

Node pop(){
	return stack[top--];
}

void push(Node x){
	stack[++top] = x;
	return;
}

Node node_make(Node parent, int buy){
	Node child;
	child.index = parent.index + 1;
	child.total = (buy==TRUE)? (parent.total + data[child.index]) : (parent.total);
	return child;
}

void stack_init(){
	top = -1;
	Node null;
	null.index = -1;
	null.total = 0;
	push( node_make(null,TRUE) );
	push( node_make(null,FALSE) );
	return;
}

int solve(){
	stack_init();

	Node x;
	int last_index = (int)(sizeof(data)/sizeof(data[0]))-1;
	while(top >= 0){
		x = pop();

		if(x.total == k) return TRUE;
		if( x.index < last_index){
			push( node_make(x,TRUE) );
			push( node_make(x,FALSE) );
		}
	}
	return FALSE;
}

int main(){
	printf("k=");
	scanf("%d",&k);

	printf( (solve()==TRUE)?  "possible\n":"impossible\n" );

	return 0;
}

