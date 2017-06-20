import java.util.*;
import java.io.*;
import java.applet.*;
import java.awt.*;

/*
<applet code="DrawingPolygon.class" width="720" height="500">
</applet>
*/

public class DrawingPolygon extends Applet{
    public final int CELL_SIZE = 10;
    public final int WINDOW_WIDTH = 720;
    public final int WINDOW_HEIGHT = 500;

    public int pieceNumber;
    public int[][] vertex_x;
    public int[][] vertex_y;
    public int[] vertexNumber;

    public String s;

//    public int prev_max_x = 0, prev_min_x = 0, prev_max_y = 0, prev_min_y = 0;

    int findNumber(){
        int digit, t;

        if(":".equals(s.substring(1, 2)) || " ".equals(s.substring(1, 2))){
            digit = 1;
        }else if(":".equals(s.substring(2, 3)) || " ".equals(s.substring(2, 3))){
            digit = 2;
        }else{
            digit = 3;
        }

        t = Integer.parseInt(s.substring(0, digit));
        s = s.substring((digit + 1), s.length());

        return t;
    }

/*  public int findMax(int[] dt){
        int max = dt[0];

        for(int i = 1; i < dt.length; i++){
            if(dt[i] > max) max = dt[i];
        }

        return max;
    }

    public int findMin(int[] dt){
        int min = dt[0];

        for(int i = 1; i < dt.length; i++){
            if(dt[i] < min) min = dt[i];
        }

        return min;
    }

    public void changePosition(int[] dt_x, int[] dt_y){
        int max_x = findMax(dt_x), min_x = findMin(dt_x);
        int max_y = findMax(dt_y) > prev_max_y ? findMax(dt_y) : prev_max_y;
        int min_y = findMin(dt_y);
        int i;
        boolean reset = false;
        System.out.println(max_x + " " + min_x);
        System.out.println(max_y + " " + min_y + "\n");

        // Move vertexes
        for(i = 0; i < dt_x.length; i++){
            dt_x[i] += (prev_max_x - min_x + 10);
            if(dt_x[i] > (WINDOW_WIDTH - 10)){
                reset = true;
            }
        }

        System.out.println(Arrays.toString(dt_x) + "\n");

        // vertex > WINDOW_WIDTH
        if(reset == true){
            int moveLength_x = (min_x - 10);

            for(i = 0; i < dt_x.length; i++){
                dt_x[i] -= moveLength_x;
            }
        }

        if(min_y < 10 || reset == true) {
            for(i = 0; i < dt_y.length; i++){
                dt_y[i] += (max_y + 10);
            }
        }

        prev_max_x = max_x; prev_min_x = min_x;
        prev_max_y = max_y; prev_min_y = min_y;
    } */

    public void start(){
        try{
            Scanner scan = new Scanner(System.in);
            s = scan.nextLine();
        }catch(Exception e){
            System.out.println(e);
        }

        pieceNumber = findNumber();

        vertex_x = new int[pieceNumber][16];
        vertex_y = new int[pieceNumber][16];
        vertexNumber = new int[pieceNumber];

        for(int i = 0; i < pieceNumber; i++){
            vertexNumber[i] = findNumber();
            for(int j = 0; j < vertexNumber[i]; j++){
                vertex_x[i][j] = findNumber() * CELL_SIZE;
                vertex_y[i][j] = findNumber() * CELL_SIZE;
            }
        }
    }

    public void paint(Graphics g){
        Graphics2D grid = (Graphics2D)g, polygon = (Graphics2D)g;
        super.paint(grid);
        BasicStroke Thin = new BasicStroke(0.5f);
        grid.setStroke(Thin);

        for(int i = 0; i <= 720; i += CELL_SIZE){
            grid.drawLine(i, 0, i, 720);
            grid.drawLine(0, i, 720, i);
        }

        super.paint(polygon);
        BasicStroke Bold = new BasicStroke(2.0f);
        polygon.setStroke(Bold);

        for(int i = 0; i < pieceNumber; i++){
//            changePosition(vertex_x[i], vertex_y[i]);
            polygon.drawPolygon(vertex_x[i], vertex_y[i], vertexNumber[i]);
        }
    }
}
