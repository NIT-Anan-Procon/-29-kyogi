#include <stdio.h>

typedef struct{
	int x;
	int y;
}Coord;

void reverseVertex(Coord *vertex){
	vertex->x += vertex->y;
	vertex->y -= vertex->x;
	vertex->x = -vertex->x - vertex->y;
}

void rotateVertex(Coord *vertex){
	vertex->x += vertex->y;
	vertex->y = vertex->x - vertex->y;
	vertex->x = vertex->y - vertex->x;	
}
