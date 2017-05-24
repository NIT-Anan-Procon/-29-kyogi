#include <stdio.h>

#include "vector.h"

#define VER_MAX 16

typedef struct{
	Coord vertex[VER_MAX];
	int vertex_number;
	Vector side[VER_MAX];
	double angle[VER_MAX];
}Polygon;

void Read_vertex(FILE *file, Polygon *p); //ファイルから図形の頂点座標を読みこむ

void printPolygon (Polygon *p); //Polygon構造体の情報を出力する
