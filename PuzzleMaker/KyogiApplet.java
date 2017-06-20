/*------------------------------------------------------------------------------
This class is using and excuting Applet.
You can excute this application by the command "appletviewer KyogiApplet.java"
------------------------------------------------------------------------------*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/*
    <applet code="KyogiApplet.class" width=928 height=640>
    </applet>
*/

public class KyogiApplet extends Applet implements ActionListener {
    public int x_range, y_range;    // number of max pixel
    public double n_grid;   // devide number
    public int cell;        // number of pixels between one grid.
    public MyMouseListener mouse_listen;
    public Piece list;      // for Normal mode
    public Puzzle puzzle;   // for Puzzle mode
    public MyPoint zero;    // Original Point
    public boolean pzl_flag;// Is puzzle mode? or normal mode?
    public boolean show_flag;
    public boolean hide_flag;
    public Color[] colors;  // To change colors
    public int clr_idx;     //
    public Button b;

    public void init(){
        cell = 8;      // 10 pixels equals 2.5mm.
                        // if you want to change this value, you must change applet's width and height in 7 line.
        n_grid = 4.0;   // 10mm / 4 = 2.5mm.
        x_range = cell*116;
        y_range = cell*80;

        mouse_listen = new MyMouseListener();
        addMouseListener(mouse_listen);
        list = new Piece();
        puzzle = new Puzzle();
        zero = new MyPoint(cell*8,cell*8);

        pzl_flag = false;
        show_flag = false;
        hide_flag = false;

        colors = new Color[12];
        colors[0] = new Color(255,0,0);
        colors[1] = new Color(255,127,0);
        colors[2] = new Color(255,255,0);
        colors[3] = new Color(127,255,0);
        colors[4] = new Color(0,255,0);
        colors[5] = new Color(0,255,127);
        colors[6] = new Color(0,255,255);
        colors[7] = new Color(0,127,255);
        colors[8] = new Color(0,0,255);
        colors[9] = new Color(127,0,255);
        colors[10] = new Color(255,0,255);
        colors[11] = new Color(255,0,127);

        clr_idx = 0;

        System.out.println(">>Let's start!");

        System.out.println("\n>>First, you can select mode, Normal or Puzzle.");
        System.out.println(">>  Normal Mode : you can make single Piece and get data.");
        System.out.println(">>  Puzzle Mode : you can make Puzzle and get data.");
        System.out.println("\n>>Second, you can use option button such as \"ClearA\" button.");
        System.out.println(">>  ClearA : you can clear all data.");
        System.out.println(">>  Clear  : you can clear Piece data in only Puzzle Mode.");
        System.out.println(">>  Back   : you can retry to point a coord.");
        System.out.println(">>  Print  : you can print the data.");
        System.out.println("\n>>In Puzzle Mode, you can set the Frame and Pieces by Set Button");
        System.out.println(">>  setFrame : In Puzzle Mode, you can fix the Frame's figure.");
        System.out.println(">>  setPiece : In Puzzle Mode, you can fix the Piece's figure.");

        System.out.print("\n>>Now, ");
        if(pzl_flag == true) {
            System.out.println("Puzzle Mode!\n");
            System.out.println(">>Please input Frame's vertexes and push \"setFrame\" button.");
        }
        else {
            System.out.println("Normal Mode!");
            System.out.println(">>Please input Piece's vertexes and push \"Print\" button.");
        }
    }

    public void start() {
        if(pzl_flag == false)
//            setNormalButton();
            setPuzzleButton();
//        else
//            setPuzzleButton();
//        System.out.println(pzl_flag);
    }

    void setNormalButton() {
        b = new Button("ClearA");
        b.addActionListener(this);
        add(b);
        b = new Button("Back");
        b.addActionListener(this);
        add(b);
        b = new Button("Print");
        b.addActionListener(this);
        add(b);
        b = new Button("Puzzle");
        b.addActionListener(this);
        add(b);
    }

    void setPuzzleButton() {
        b = new Button("Print");
        b.addActionListener(this);
        add(b);
        b = new Button("Normal");
        b.addActionListener(this);
        add(b);
        b = new Button("Puzzle");
        b.addActionListener(this);
        add(b);
        b = new Button("setFrame");
        b.addActionListener(this);
        add(b);
        b = new Button("setPiece");
        b.addActionListener(this);
        add(b);
        b = new Button("showGrid");
        b.addActionListener(this);
        add(b);
        b = new Button("hide");
        b.addActionListener(this);
        add(b);
        b = new Button("Back");
        b.addActionListener(this);
        add(b);
        b = new Button("Clear");
        b.addActionListener(this);
        add(b);
        b = new Button("ClearA");
        b.addActionListener(this);
        add(b);
//        b = new Button("Write");
//        b.addActionListener(this);
//        add(b);
//        b = new Button("Read");
//        b.addActionListener(this);
//        add(b);
//        b = new Button("ReadP");
//        b.addActionListener(this);
//        add(b);
    }

    public void paint(Graphics g) {
        paintGrid(g);
        paintZero(g);

        if(pzl_flag) {
            paintPiece(g,puzzle.frame,new Color(128,128,128));
            paintGrid(g);
            if(puzzle.size() > 0) {
                clr_idx = 0;
                for(int i=0;i<puzzle.size();i++) {
                    Piece pi = puzzle.get(i);
                    paintPiece(g,pi,getColor());
//                    if(i == puzzle.size()-1 && drawing_flag == true) {
//                        paintVertex(g,pi.get(pi.size()-1),Color.black);
//                    }
                }
            }
        }

//            paintPiece(g,list,Color.black);
        if(hide_flag == false) {
            paintPiece(g,list,Color.black);
        } else {
            hide_flag = false;
        }
        if(list.size() > 0) {
            paintVertex(g,list.get(list.size()-1),Color.black);
        }

        if(show_flag == true) {
            paintGrid(g);
            paintZero(g);
            show_flag = false;
        }
    }

    void paintGrid(Graphics g) {
        for(int i=0;i<x_range/cell;i++) {
            if((i+13)%20==0) g.setColor(new Color(0x22,0,0xdd));    // 13 is number to adjust origin point and axises.
            else if((i+13)%4==0) g.setColor(new Color(0,0x66,255));
            else g.setColor(new Color(0,0xcc,255));
            g.drawLine((i+1)*cell,0,(i+1)*cell,y_range);
        }
        for(int i=0;i<y_range/cell;i++) {
            if((i+13)%20==0) g.setColor(new Color(0x22,0,0xdd));
            else if((i+13)%4==0) g.setColor(new Color(0,0x66,255));
            else g.setColor(new Color(0,0xcc,255));
            g.drawLine(0,(i+1)*cell,x_range,(i+1)*cell);
        }
        g.setColor(Color.red);
        g.drawLine(0,8*cell,x_range,8*cell);
        g.drawLine(8*cell,0,8*cell,y_range);
    }

    void paintZero(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(zero.getX_i()-3,zero.getY_i()-3,6,6);
        g.drawString("(0,0)",zero.getX_i()-30,zero.getY_i()+20);
    }

    void paintVertex(Graphics g, Coord c, Color color) {
        g.setColor(color);
        MyPoint p = new MyPoint(c);
        g.fillOval(p.getX_i()-4,p.getY_i()-4,8,8);
    }

    void paintSide(Graphics g, Coord start, Coord goal, Color color) {
        g.setColor(color);
        MyPoint st = new MyPoint(start);
        MyPoint go = new MyPoint(goal);
        g.drawLine(st.getX_i(),st.getY_i(),go.getX_i(),go.getY_i());
    }

    void paintPiece(Graphics g, Piece list, Color color) {
        g.setColor(color);
        if(list.size() > 0) {
            int[] x_v = new int[list.size()];
            int[] y_v = new int[list.size()];
            for(int i=0;i<list.size();i++) {
                MyPoint p = new MyPoint(list.get(i));
                x_v[i] = p.getX_i();
                y_v[i] = p.getY_i();
            }
            g.fillPolygon(x_v, y_v, list.size());
        }

        /*
            for(int i=0;i<list.size();i++) {
                paintVertex(g,list.get(i),color);
            }
        g.setColor(color);
        if(list.size() > 0) {
            for(int i=0;i<list.size();i++) {
                Coord now = list.get(i);
                Coord next = list.get((i+1)%list.size());
                paintVertex(g,now,color);
                if(list.size() > 1) {
                    paintSide(g,now,next,color);
                }
            }
        }
        */
    }

    void delete() {
        if(list.size() == 0) return;
        list.removeLast();
        repaint();
        System.out.println("\n>>Deleted.");
    }

    void clearAll() {
        list = new Piece();
        puzzle = new Puzzle();
        System.out.println(">>Clear All.");
        System.out.print("\n\n\n\n");
        repaint();

        System.out.print(">>Now, ");
        if(pzl_flag == true) {
            System.out.println("Puzzle Mode!\n");
            System.out.println(">>Please input Frame's vertexes and push \"setFrame\" button.");
        }
        else {
            System.out.println("Normal Mode!");
            System.out.println(">>Please input Piece's vertexes and push \"Print\" button.");
        }
    }

    void clear() {
        if(puzzle.size() == 0) return;
        puzzle.removeLast();
        repaint();
    }

    void setFrame() {
        puzzle.frame = list.clone();
//        System.out.println(frame);
//        data += toString();
//        initList();
//        pzl_flag = true;
        list.init();
        repaint();

        System.out.println("\n>>Frame is fixed!\n>>Next, input Pieces and push \'setPiece\' button.");
    }

    void setPiece() {
        puzzle.add(list.clone());
        list.init();
        repaint();
        System.out.println("\n>>Piece(" + puzzle.size() + ") is fixed!\n>>Next, input Pieces and push \'setPiece\' button.");
    }

    void changePuzzle() {
        pzl_flag = true;
        clearAll();
        start();
        repaint();
    }

    void changeNormal() {
        pzl_flag = false;
        clearAll();
        start();
        repaint();
    }

    Color getColor() {
        Color c = colors[(clr_idx)%colors.length];
        clr_idx++;
        return c;
    }

/*
    void write() {
        File manual = new File("Manual.txt");
        String path = manual.getAbsolutePath();

        System.out.println("File : " + path);
    }

    void read() {
        System.out.println("\n>>Read Mode\nPlease input text data to terminal.");
        Scanner stdIn = new Scanner(System.in);
        int n = stdIn.nextInt();
        for(int i=0;i<n;i++) {
//            list.add(new Coord(stdIn.nextDouble(), stdIn.nextDouble()));
//            MyPoint p = new MyPoint(stdIn.nextDouble()+100, stdIn.nextDouble()+100);
//            Coord c = p.toCoord();
            Coord c = new Coord(stdIn.nextDouble(), stdIn.nextDouble());
            list.add(c);
            stdIn.nextLine();
        }
        System.out.println("\n>>Finished reading! Now, we will show it.");
        repaint();
    }

    void readPuzzle() {
        clearAll();
        Scanner stdIn = new Scanner(System.in);
        int n = stdIn.nextInt();
        for(int i=0;i<n;i++) {
//            MyPoint p = new MyPoint(stdIn.nextDouble()+100, stdIn.nextDouble()+100);
//            Coord c = p.toCoord();
            Coord c = new Coord(stdIn.nextDouble(), stdIn.nextDouble());
            puzzle.frame.add(c);
            stdIn.nextLine();
        }
        n = stdIn.nextInt();
        for(int i=0;i<n;i++) {
            int m = stdIn.nextInt();
            puzzle.pieces.add(new Piece());
            puzzle.pieces.get(i).ref = new Coord(stdIn.nextDouble(), stdIn.nextDouble());
//            System.out.println("\n\t" + puzzle.pieces.get(i).ref);
            puzzle.pieces.get(i).angle = stdIn.nextDouble();
            for(int j=0;j<m;j++) {
//                MyPoint p = new MyPoint(stdIn.nextDouble()+100, stdIn.nextDouble()+100);
//                Coord c = p.toCoord();
                Coord c = new Coord(stdIn.nextDouble(), stdIn.nextDouble());
                puzzle.get(i).add(c);
            }
        }
        pzl_flag = true;
        repaint();
    }
*/
/*
    public ArrayList<Coord> myclone(ArrayList<Coord> a) {
        ArrayList<Coord> temp = new ArrayList<Coord>();
        for(int i=0;i<a.size();i++) {
            temp.add(a.get(i).clone());
        }
        return temp;
    }


    public String toString() {
        String temp = "";
        temp += list.size() + "\n";
        for(int i=0;i<list.size()-1;i++) {
            temp += list.get(i).toString() + "\n";
        }
        temp += list.get(list.size()-1).toString();
        return temp;
    }
*/

    public void actionPerformed(ActionEvent ev) {
        String cmd = ev.getActionCommand();
        if(cmd.equals("ClearA"))
            clearAll();
            start();
        if(cmd.equals("Back"))
            delete();
        if(cmd.equals("Print")) {
            System.out.println("\n>>Printing......");
            if(pzl_flag == false)
                System.out.println(list.toStringLine());
            else
                System.out.println(puzzle.toStringLine());
            System.out.println("\n>>Continue?, or Change Mode?");
        }
        if(cmd.equals("Puzzle"))
            changePuzzle();
        if(cmd.equals("Clear"))
            clear();
        if(cmd.equals("setFrame"))
            setFrame();
        if(cmd.equals("setPiece"))
            setPiece();
        if(cmd.equals("Normal"))
            changeNormal();
        if(cmd.equals("showGrid")) {
            show_flag = true;
            repaint();
        }
        if(cmd.equals("hide")) {
            hide_flag = true;
            repaint();
        }
//        if(cmd.equals("Write"))
//            write();
//        if(cmd.equals("Read"))
//            read();
//        if(cmd.equals("ReadP"))
//            readPuzzle();
    }

    public class MyMouseListener implements MouseListener {
        public int x;
        public int y;
        public int x_t;
        public int y_t;
        public double r_rate;

        public MyMouseListener() {
            x = 0;
            y = 0;
            x_t = 0;
            y_t = 0;
            r_rate = 0.4;
        }

        public void mouseEntered(MouseEvent e){}

        public void mouseExited(MouseEvent e){}

        public void mousePressed(MouseEvent e){}

        public void mouseReleased(MouseEvent e){}

        public void mouseClicked(MouseEvent e){
            Point click = e.getPoint();
            x = click.x;
            y = click.y;
            x_t = x/cell * cell + ((x%cell > cell-x%cell)?cell:0);
            y_t = y/cell * cell + ((y%cell > cell-y%cell)?cell:0);

            if(inArea()) {
                //            x_t = min(x%cell, x%cell-cell) + x/cell * cell;
                //            y_t = min(y%cell, y%cell-cell) + y/cell * cell;
                MyPoint hoge = new MyPoint(x_t, y_t);
//                System.out.println(hoge);
                list.add(hoge.toCoord());
                repaint();
            }
        }

        boolean inArea() {
//            return (x%cell <= cell*r_rate || cell-x%cell <= cell*r_rate) &&
//            (y%cell <= cell*r_rate || cell-y%cell <= cell*r_rate);
            return (x-x_t)*(x-x_t) + (y-y_t)*(y-y_t) < (cell*r_rate)*(cell*r_rate);
        }
    }

    public class MyPoint extends Pair {
        public MyPoint(int x, int y) {
            super(x,y);
        }

        public MyPoint(double x, double y) {
            super(x,y);
        }

        public MyPoint(Coord c) {
            this.x = c.x*cell + zero.x;
            this.y = c.y*cell + zero.y;
        }

        public MyPoint(MyPoint copy) {
            this.x = copy.x;
            this.y = copy.y;
        }

        public MyPoint clone() {
            return new MyPoint(this);
        }

        public Coord toCoord() {
            Coord temp = new Coord((this.x-zero.x)/(cell),(this.y-zero.y)/(cell));
            return temp;
        }

        public String toString() {
            String temp = "";
            temp = String.format("%d %d", x, y);
            return temp;
        }
    }
}
