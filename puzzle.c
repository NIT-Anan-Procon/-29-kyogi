#include <stdio.h>
#include "head/polygon.h"

#define NEW_VECTOR(a,b) ((Vector){a,b})
#define NEW_COORD(a,b) ((Coord){a,b})

int solve(void){
/*	int i;
	Polygon poly;
	poly.vertex_number = 3;
	poly.vertex[0] = NEW_COORD(3,4);
	poly.vertex[1] = NEW_COORD(2,6);
	poly.vertex[2] = NEW_COORD(1,0);
	for(i=0;i<poly.vertex_number;i++){
		poly.side[i] = find_vector(poly.vertex[i], poly.vertex[ (i==poly.vertex_number-1? 0:(i+1)) ]);
	}
	for(i=0;i<poly.vertex_number;i++){
		poly.angle[i] = find_angle(poly.side[i], poly.side[ (i==poly.vertex_number-1? 0:(i+1)) ]);
	}

	printPolygon(&poly);
*/

	Polygon frame;
	int piece_number, i;

	FILE *file;
	file = fopen("Data.txt", "r");
	fscanf(file, "%d", &piece_number);
	Polygon piece[piece_number];

	readAllVertex(file, piece, piece_number);
	fclose(file);
	for(i=0;i<piece_number;i++){
		calc_polygon(&piece[i]);
	}
	printAllPolygon(piece, piece_number);
	return 0;
}
