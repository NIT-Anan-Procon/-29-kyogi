#include <stdio.h>

#include "coord.h"

typedef struct{
  int x;
  int y;
}Vector;

double find_vector(Coord a, Coord b); //2つの頂点座標から辺ベクトルを求める

double find_angle(Vector a, Vector b); //2つの辺ベクトルの角度を求める
