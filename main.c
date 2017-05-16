#include <stdio.h>

typedef struct{
	int x;
	int y;
}Coord;

typedef struct{
	int x;
	int y;
}Vector;

typedef struct{
	Coord vertex[16];
	int vertex_number;
	int side[16];
	double angle[16]; 
}Polygon;


