#include <stdio.h>
#include <math.h>

#define RADIAN(A) ((A)*3.14159 / 180)

typedef struct{
	int x;
	int y;
}Vector;

double find_angle(Vector a, Vector b){
	double inner_product;
	inner_product = ((a.x * b.x) + (a.y * b.y)) /
				(sqrt((a.x*a.x)+(a.y*a.y)) * sqrt((b.x*b.x)+(b.y*b.y)));
	return acos(inner_product);
}

int main(void){
	Vector a = {1,0}, b = {1,1};
	
	printf("%f\n", find_angle(a,b));
}
