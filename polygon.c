#include <stdio.h>

#define VER_MAX 16

typedef struct{
	int x;
	int y;
}Coord;

typedef struct{ //ベクトルのx、y成分を格納する構造体
	int x;
	int y;
}Vector;

typedef struct{ //多角形の情報を格納する構造体
	Coord vertex[VER_MAX];
	int vertex_number;
	Vector side[VER_MAX];
	double angle[VER_MAX];
}Polygon;

void Read_vertex(FILE *file, Polygon *p){
	int number=0,i=0;
	Coord c;

	fscanf(file,"%d", &number);
	p->vertex_number = number;

	for(i=0;i<number;i++){
		fscanf(file,"%d%d", &c.x, &c.y);
		p->vertex[i] = c;
	}
}

void printPolygon (Polygon *p) {
	int i;
	for (i = 0; i < p->vertex_number; i++) {
		printf("vertex(x, y) = (%d, %d)", p->vertex[i].x, p->vertex[i].y);
		printf("side(x, y) = (%d, %d)" p->side[i].x, p->side[i].y);
		printf("angle = %f", p->angle);
	}
}
