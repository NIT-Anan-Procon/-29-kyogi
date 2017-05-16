#include <stdio.h>
#include <string.h>

int stack[100], top, num;
char command[8];

void init();
void push(int a);
int pop();

int main(){
	while(1){
		scanf("%s", command);
		if(strcmp(command,"pop") == 0){
			if(top > 0) printf("%d\n", pop());
			else printf("error\n");
		}else if(strcmp(command,"push") == 0){
			scanf("%d", &num);
			push(num);
		}else{
			break;
		}
	}
	return 0;
}

void init(){
	top = 0;
}

void push(int a){
	stack[top++] = a;
}

int pop(){
	return stack[--top];
}

