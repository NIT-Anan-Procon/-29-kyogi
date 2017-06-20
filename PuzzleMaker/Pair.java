public class Pair {
    double x;
    double y;

    public Pair() {
        x = 0.0;
        y = 0.0;
    }

    public Pair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Pair(int x, int y) {
        this((double)x,(double)y);
    }

    public Pair(Pair c, double dx, double dy) {
        this.x = c.x + dx;
        this.y = c.y + dy;
    }

    public Pair(Pair c, int dx, int dy) {
        this(c,(double)dx,(double)dy);
    }

    public int getX_i() {
        return (int)x;
    }

    public int getY_i() {
        return (int)y;
    }

    public String toString() {
        String temp = "";
        temp = String.format("%f %f", x, y);
        return temp;
    }
}
