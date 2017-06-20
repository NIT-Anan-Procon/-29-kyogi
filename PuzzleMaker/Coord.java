public class Coord extends Pair {
    public Coord() {
        super();
    }

    public Coord(double x, double y) {
        super(x,y);
    }

    public Coord(int x, int y) {
        super(x,y);
    }

    public Coord(Coord c, double x, double y) {
        super(c,x,y);
    }

    public Coord(Coord c, int x, int y) {
        super(c,x,y);
    }

    public Coord(Coord c, double a) {
        this.x = c.x * Math.cos(a) - c.y * Math.sin(a);
        this.y = c.x * Math.sin(a) + c.y * Math.cos(a);
    }

    public Coord(Coord copy) {
        this.x = copy.x;
        this.y = copy.y;
    }

    public Coord clone() {
        return new Coord(this);
    }

    public String toString() {
        String temp = "";
//        temp = String.format("%f %f", (x-zero.x)/(double)Data.cell, (y-zero.y)/(double)Data.cell);
        temp = String.format("%f %f", (double)x, (double)y);
        return temp;
    }

    public String toStringInt() {
        String temp = "";
//        temp = String.format("%f %f", (x-zero.x)/(double)Data.cell, (y-zero.y)/(double)Data.cell);
        temp = String.format("%d %d", (int)x, (int)y);
        return temp;
    }
}
