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
    public int[] frameVertex_x;
    public int[] frameVretex_y;

    public String s;

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

    public int findMax(int[] dt){
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

    public int[] changePosition(int[] vertex){
        int min = findMin(vertex);

        for (int i = 0; i < 16; i++){
            vertex[i] += 10 - min;
        }

        return vertex;
    }

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
        vertexNumber = new int[pieceNumber + 1];

        for(int i = 0; i < pieceNumber; i++){
            vertexNumber[i] = findNumber();
            for(int j = 0; j < vertexNumber[i]; j++){
                vertex_x[i][j] = findNumber() * CELL_SIZE;
                vertex_y[i][j] = findNumber() * CELL_SIZE;
            }
        }

        vertexNumber[pieceNumber] = findNumber();
        frameVertex_x = new int[32];
        frameVretex_y = new int[32];

        for(int i = 0; i < vertexNumber[pieceNumber]; i++){
            frameVertex_x[i] = findNumber() * CELL_SIZE;
            frameVretex_y[i] = findNumber() * CELL_SIZE;
        }
    }

    public void paint(Graphics g){
        Graphics2D line = (Graphics2D)g;
        super.paint(line);
        BasicStroke Thin = new BasicStroke(0.5f);
        line.setStroke(Thin);

        for(int i = 0; i <= 720; i += CELL_SIZE){
            line.drawLine(i, 0, i, 720);
            line.drawLine(0, i, 720, i);
        }

        super.paint(line);
        BasicStroke Bold = new BasicStroke(2.0f);
        line.setStroke(Bold);
        line.setColor(Color.blue);

        for(int i = 0; i < pieceNumber; i++){
            vertex_x[i] = changePosition(vertex_x[i]);
            vertex_y[i] = changePosition(vertex_y[i]);
            line.drawPolygon(vertex_x[i], vertex_y[i], vertexNumber[i]);
        }

        line.setColor(Color.black);
        frameVertex_x = changePosition(frameVertex_x);
        frameVretex_y = changePosition(frameVretex_y);
        line.drawPolygon(frameVertex_x, frameVretex_y, vertexNumber[pieceNumber]);

    }
}
