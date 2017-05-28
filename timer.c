#include <stdio.h>
#include <time.h>

clock_t start, end;

void clockStart(void){
	start = clock();
}

void clockEnd(void){
	end = clock();
	printf("計測時間:%lu[msec]\n",end-start);
}
