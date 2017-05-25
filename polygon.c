#include <stdio.h>
#include "head/vector.h"

#define VER_MAX 16

typedef struct{ //多角形の情報を格納する構造体
	Coord vertex[VER_MAX];
	int vertex_number;
	Vector side[VER_MAX];
	double angle[VER_MAX];
}Polygon;

void read_vertex(FILE *file, Polygon *p){
	int number=0,i=0;
	Coord c;

	fscanf(file,"%*c%d", &number);
	p->vertex_number = number;
	for(i=0;i<number;i++){
		fscanf(file,"%d%d", &c.x, &c.y);
		p->vertex[i] = c;
	}
}

void readAllVertex(FILE *file, Polygon *p, int n){
	int i;
	for(i=0;i<n;i++){
		read_vertex(file, &p[i]);
	}
}

void calc_polygon(Polygon *p){
	int i;
	for(i=0;i < p->vertex_number;i++){
		p->side[i] = find_vector(p->vertex[i], p->vertex[ (i==p->vertex_number-1? 0:(i+1)) ]);
	}
	for(i=0;i < p->vertex_number;i++){
		p->angle[i] = find_angle(p->side[i], p->side[ (i==p->vertex_number-1? 0:(i+1)) ]);
	}
	return;
}

void printPolygon (Polygon *p) {
	int i;
	for (i = 0; i < p->vertex_number; i++) {
		printf("vertex(x, y) = (%d, %d) ", p->vertex[i].x, p->vertex[i].y);
		printf("side(x, y) = (%d, %d) ", p->side[i].x, p->side[i].y);
		printf("angle = %f\n", p->angle[i]);
	}
}

void printAllPolygon(Polygon *p, int n){
	int i=0;
	for(i=0;i<n;i++){
		printf("piece%02d\n",i);
		printPolygon(&p[i]);
		printf("\n");
	}
}
