#include <stdio.h>
#include <math.h>

#include "head/coord.h"

typedef struct {
  int x;
  int y;
}Vector;

Vector findVector(Coord a, Coord b){
  Vector v;
  v.x = b.x - a.x;
  v.y = b.y - a.y;
  return v;
}

double findAngle(Vector a, Vector b){
	double inner_product, rad;
	inner_product = ((a.x * b.x) + (a.y * b.y)) /
				(sqrt((a.x*a.x)+(a.y*a.y)) * sqrt((b.x*b.x)+(b.y*b.y)));
	rad = M_PI - acos(inner_product);
  return rad;
}
