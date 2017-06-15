#include <stdio.h>

typedef struct{
	int x;
	int y;
}Coord;

void reverseVertex(Coord *vertex);	//頂点座標を原点を中心に反転する

void rotateVertex(Coord *vertex);	//頂点座標を原点を中心に反時計回りに90°回転する
