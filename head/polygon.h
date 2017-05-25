#include <stdio.h>

#include "vector.h"

#define VER_MAX 16

typedef struct{
	Coord vertex[VER_MAX];
	int vertex_number;
	Vector side[VER_MAX];
	double angle[VER_MAX];
}Polygon;

void readVertex(FILE *file, Polygon *p); //ファイルから図形の頂点座標を読みこむ

void readAllVertex(FILE *file, Polygon *p, int n); //全ピースの頂点座標をファイルから読み込む

void calcPolygon(Polygon *p); //引数のPolygonの頂点情報から、残りの情報を計算し格納する。

void printPolygon (Polygon *p); //Polygon構造体の情報を出力する

void printAllPolygon(Polygon *p, int n); //Polygon構造体の配列の各要素を表示する
