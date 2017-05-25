#include <stdio.h>

#include "coord.h"

typedef struct{
  int x;
  int y;
}Vector;

Vector findVector(Coord a, Coord b); //2つの頂点座標から辺ベクトルを求める

double findAngle(Vector a, Vector b); //2つの辺ベクトルの角度を求める
