#include <stdio.h>
#include <time.h>

clock_t start, end;

void clockStart(void){
	start = clock();
}

void clockEnd(void){
	end = clock();
	printf("Execution Time:%lu[msec]\n",end-start);
}
