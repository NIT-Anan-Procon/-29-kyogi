#include <stdio.h>
#include "head/vector.h"

#define VER_MAX 16

typedef struct{ //多角形の情報を格納する構造体
	Coord vertex[VER_MAX];
	int vertex_number;
	Vector side[VER_MAX];
	double angle[VER_MAX];
}Polygon;

void readVertex(FILE *file, Polygon *p){
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
		readVertex(file, &p[i]);
	}
}

void calcPolygon(Polygon *p){
	int i;
	for(i=0;i < p->vertex_number;i++){
		p->side[i] = findVector(p->vertex[i], p->vertex[ (i==p->vertex_number-1? 0:(i+1)) ]);
	}
	for(i=0;i < p->vertex_number;i++){
		p->angle[i] = findAngle(p->side[i], p->side[ (i==p->vertex_number-1? 0:(i+1)) ]);
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
	int i;
	for(i=0;i<n;i++){
		printf("piece%02d\n",i);
		printPolygon(&p[i]);
		printf("\n");
	}
}
